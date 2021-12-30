package com.briefly.backenddesign.service;
import com.briefly.backenddesign.db.entity.LongToShortUrl;
import com.briefly.backenddesign.tinyurl.TinyUrlGenerator;
import com.briefly.backenddesign.utils.UrlUtil;
import com.briefly.backenddesign.vo.UrlVO;
import com.briefly.backenddesign.db.repository.LongToShortUrlRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class LongToShortService implements ILongToShortService {

  private static final Logger logger = LoggerFactory.getLogger(LongToShortService.class);
  private static final int DEFAULT_CACHE_TTL = 60;
  private final TinyUrlGenerator tinyUrlGenerator;
  private final LongToShortUrlRepository longToShortUrlRepository;
  private final RedisService redisService;
  private long counter;
  @Autowired
  public LongToShortService(
          LongToShortUrlRepository longToShortUrlRepository,
          RedisService redisService,
          TinyUrlGenerator tinyUrlGenerator) {
            this.longToShortUrlRepository = longToShortUrlRepository;
            this.redisService = redisService;
            this.tinyUrlGenerator = tinyUrlGenerator;
            this.counter = 0L;
          }

  @Transactional
  public UrlVO longToShort(String longUrl, HttpServletRequest request) {
    if (!UrlUtil.isValidLongUrl(longUrl)) {
      logger.error("Invalid long URL");
      return null;
    }

    UrlVO urlVo = fetchTinyUrlFromCache(longUrl);

    if (urlVo != null) {
      return urlVo;
    }

    Optional<LongToShortUrl> longToShortOpt = longToShortUrlRepository.findByLongUrl(longUrl);
    if (longToShortOpt.isPresent()) {
      return postProcessDataFromDB(longToShortOpt.get());
    }

    String shortUrl = fetchNextAvailableShortUrl();

    redisService.setLongAndShort(longUrl, shortUrl, DEFAULT_CACHE_TTL);

    SaveUrl(longUrl, shortUrl);
    urlVo = createUrlVO(shortUrl);

    return urlVo;
  }


  @Override
  public UrlVO longToShort(String longUrl) {
    if (!UrlUtil.isValidLongUrl(longUrl)) {
      logger.error("Invalid long URL");
      return null;
    }

    UrlVO urlVo = fetchUrlFromDb(longUrl);
    if (urlVo != null) {
      return urlVo;
    }

    Optional<LongToShortUrl> longToShortOpt = longToShortUrlRepository.findByLongUrl(longUrl);
    if (longToShortOpt.isPresent()) {
      return postProcessDataFromDB(longToShortOpt.get());
    }

    String shortUrl = fetchNextAvailableShortUrl();

    redisService.setLongAndShort(longUrl, shortUrl, DEFAULT_CACHE_TTL);
    urlVo = createUrlVO(shortUrl);
    SaveUrl(longUrl, shortUrl);

    return urlVo;
  }

  @Override
  public String shortToLong(String shortUrl) {
    String longUrl = fetchLongUrl(shortUrl);
    return longUrl;
  }


  private String fetchLongUrl(String shortUrl) {
    String longUrl = (String) redisService.get(shortUrl);
    redisService.expire(shortUrl, 60);
    if (!StringUtils.isEmpty(longUrl)) {
      return longUrl;
    }
    
    Optional<LongToShortUrl> longUrlOptional = longToShortUrlRepository.findByShortUrl(shortUrl);

    if (longUrlOptional.isPresent()) {
      longUrl = longUrlOptional.get().getLongUrl();
      redisService.set(shortUrl, longUrl, 60);
    } else {
      longUrl = null;
    }

    return longUrl;
  }


  private String fetchNextAvailableShortUrl() {
    String shortUrl = null;

    while (true) {
      shortUrl = tinyUrlGenerator.generate();
      Optional<LongToShortUrl> shortUrlOptional = longToShortUrlRepository.findByShortUrl(shortUrl);
      if (shortUrlOptional.isPresent()) {
        continue;
      } else {
        break;
      }
    }

    return shortUrl;
  }


  private UrlVO fetchUrlFromDb(String longUrl) {
      UrlVO urlVo = null;
      Optional<LongToShortUrl> longUrlOptional = longToShortUrlRepository.findByLongUrl(longUrl);
      if(longUrlOptional.isPresent()){
        LongToShortUrl shortExistUrl = longUrlOptional.get();
        String shortExist = shortExistUrl.getShortUrl();
        urlVo = createUrlVO(shortExist);
      }
      return urlVo;
  }

  private UrlVO postProcessDataFromDB(LongToShortUrl longToShortData) {
    String longUrlMeta = longToShortData.getLongUrl();
    String shortUrlMeta = longToShortData.getShortUrl();

    redisService.setLongAndShort(longUrlMeta, shortUrlMeta, DEFAULT_CACHE_TTL);
    UrlVO urlVo = createUrlVO(shortUrlMeta);
    return urlVo;
  }


  private void SaveUrl(String longUrl, String shortUrl){
    LongToShortUrl url = new LongToShortUrl();
    url.setLongUrl(longUrl);
    url.setShortUrl(shortUrl);
    longToShortUrlRepository.save(url);
  }


  private UrlVO createUrlVO(String shortUrlMeta) {
    UrlVO urlVo = new UrlVO();
    String fullUrl = constructTinyUrl(shortUrlMeta);
    urlVo.setUrl(fullUrl);
    return urlVo;
  }

  private String constructTinyUrl(String shortExist) {
    return "http://brieflyUrl/" + shortExist;
  }


  private UrlVO fetchTinyUrlFromCache(String url) {
    UrlVO urlVo = null;

    String shortExist = fetchValueByKey(url);

    if (!StringUtils.isEmpty(shortExist)) {
      urlVo = createUrlVO(shortExist);
    }

    return urlVo;
  }


  private String fetchValueByKey(String key) {
    String value = (String) redisService.get(key);
    redisService.expire(key, DEFAULT_CACHE_TTL);
    return value;
  }

}
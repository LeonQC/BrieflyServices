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
import java.util.Optional;


@Service
public class LongToShortService implements ILongToShortService {

  private static final Logger logger = LoggerFactory.getLogger(LongToShortService.class);
  private static final int DEFAULT_CACHE_TTL = 60;
  private final TinyUrlGenerator tinyUrlGenerator;
  private final LongToShortUrlRepository longToShortUrlRepository;
  private long counter;
  @Autowired
  public LongToShortService(
          LongToShortUrlRepository longToShortUrlRepository,
          TinyUrlGenerator tinyUrlGenerator) {
            this.longToShortUrlRepository = longToShortUrlRepository;
            this.tinyUrlGenerator = tinyUrlGenerator;
            this.counter = 0L;
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

    String shortUrl = fetchNextAvailableShortUrl();
    urlVo = createUrlVO(shortUrl);
    SaveUrl(longUrl, shortUrl);
    return urlVo;
  }

  @Override
  public String shortToLong(String shortUrl) {
    String longUrl = null;
    Optional<LongToShortUrl> shortUrlOptional = longToShortUrlRepository.findByShortUrl(shortUrl);
    if(shortUrlOptional.isPresent()){
      LongToShortUrl url = shortUrlOptional.get();
      longUrl = url.getLongUrl();
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


}
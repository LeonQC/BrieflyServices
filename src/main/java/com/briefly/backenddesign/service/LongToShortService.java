package com.briefly.backenddesign.service;

import com.briefly.backenddesign.tinyurl.TinyUrlGenerator;
import com.briefly.backenddesign.utils.UrlUtil;
import com.briefly.backenddesign.vo.UrlVO;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LongToShortService implements ILongToShortService {

  private static final Logger logger = LoggerFactory.getLogger(LongToShortService.class);
  private static final int DEFAULT_CACHE_TTL = 60;
  private final TinyUrlGenerator tinyUrlGenerator;
  private long counter;

  public LongToShortService(
          TinyUrlGenerator tinyUrlGenerator) {
            this.tinyUrlGenerator = tinyUrlGenerator;
            this.counter = 0L;
          }

  @Value("${shorturl.prefix}")
  private String shortUrlPrefix;
  @Override
  public UrlVO longToShort(String longUrl, HttpServletRequest request) {
    if (!UrlUtil.isValidLongUrl(longUrl)) {
      logger.error("Invalid long URL");
      return null;
    }

    UrlVO urlVo = createUrlVO(longUrl);
    return urlVo;
  }



  @Override
  public UrlVO longToShort(String longUrl) {
    if (!UrlUtil.isValidLongUrl(longUrl)) {
      logger.error("Invalid long URL");
      return null;
    }

    counter++;
    UrlVO shortUrlVO = convertSequenceIdToShortKey(counter);
    return shortUrlVO;
  }

  private UrlVO convertSequenceIdToShortKey(Long sequenceId) {
    String shortKey = tinyUrlGenerator.generate(sequenceId);
    UrlVO urlVo = createUrlVO(shortKey);
    return urlVo;
  }

  private String fetchNextAvailableShortUrl() {
    String shortUrl = null;

    //while (true) {
      shortUrl = tinyUrlGenerator.generate();
    /*  Optional<LongToShort> shortUrlOptional = longToShortRepository.findByShortUrl(shortUrl);
      if (shortUrlOptional.isPresent()) {
        continue;
      } else {
        break;
      }
    }*/

    return shortUrl;
  }

  private UrlVO createUrlVO(String shortUrlMeta) {
    UrlVO urlVo = new UrlVO();
    String fullUrl = constructTinyUrl(shortUrlMeta);
    urlVo.setUrl(fullUrl);
    return urlVo;
  }

  private String constructTinyUrl(String shortExist) {
    return "http://tinyurl/" + shortExist;
  }
}
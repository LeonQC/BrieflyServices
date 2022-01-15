package com.briefly.backenddesign.service;

import com.briefly.backenddesign.vo.UrlVO;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;
public interface ILongToShortService {
    UrlVO longToShort(String longUrl, HttpServletRequest request);

    UrlVO longToShort(String longUrl);

    String shortToLong(String shortUrl);
}

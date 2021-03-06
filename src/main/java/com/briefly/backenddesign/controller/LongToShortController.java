package com.briefly.backenddesign.controller;

import com.briefly.backenddesign.annotation.RateLimit;
import com.briefly.backenddesign.db.entity.LongToShortUrl;
import com.briefly.backenddesign.db.repository.LongToShortUrlRepository;
import com.briefly.backenddesign.enums.LimitType;
import com.briefly.backenddesign.service.ILongToShortService;
import com.briefly.backenddesign.service.LongToShortService;

import com.briefly.backenddesign.tinyurl.TinyUrlGenerator;
import com.briefly.backenddesign.vo.UrlVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @auther: WZ
 * @Date: 2020/9/7 14:59
 * @Description: 长网址/短网址 相互转换
 */
@RestController
@RequestMapping("/url")
@Api
@CrossOrigin
public class LongToShortController {
    @Autowired
    private ILongToShortService longToShortService;



    /**
     * transform接口
     *
     * @param urlVo
     * @param request
     * @return json：{"url" : "http://www.baidu.com"}
     */
    @RateLimit(permitsPerSecond = 0.1, period = 1, permits = 1, limitType = LimitType.IP)
    @PostMapping("/transform")
    public UrlVO longTransfer(@RequestBody UrlVO urlVo, HttpServletRequest request) {
        String Url = urlVo.getUrl();
        UrlVO longToShort = longToShortService.longToShort(Url, request);
        //return Result.ofSuccess(longToShort).setCode(200);
        return longToShort;
    }

    @PostMapping("/transformId")
    public UrlVO longTransfer(@RequestBody UrlVO urlVo) {
        String Url = urlVo.getUrl();
        UrlVO longToShort = longToShortService.longToShort(Url); //longToShortService.longToShort(Url, request);
        //return Result.ofSuccess(longToShort).setCode(200);
        return longToShort;
    }
}

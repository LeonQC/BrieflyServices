package com.briefly.backenddesign.vo;
/**
 * @auther: WZ
 * @Date: 2020/9/9 11:09
 * @Description: UrlVo
 */
public class UrlVO {
    private String url;

    public UrlVO() {
    }

    public UrlVO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "UrlVO{" +
                "url='" + url + '\'' +
                '}';
    }
}



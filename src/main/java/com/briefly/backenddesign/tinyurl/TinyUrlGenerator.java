package com.briefly.backenddesign.tinyurl;

import org.springframework.stereotype.Component;

@Component
public class TinyUrlGenerator {

    private static final int DEFAULT_URL_LENGTH = 6;

    public String generate(){
        return RandomTinyUrl.generate(DEFAULT_URL_LENGTH);
    }


    public String generate(long id){
        return Base62TinyUrl.generate(id, DEFAULT_URL_LENGTH);
    }

    public long convertTinyUrlToId(String url){
        return Base62TinyUrl.tinyUrlToId(url);
    }
}

package com.briefly.backenddesign.tinyurl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomTinyUrlTest {
    @Test
    public void shouldgetUrl(){
        RandomTinyUrl getUrl = new RandomTinyUrl();
        Assertions.assertThat(getUrl.generate(5).length()).isEqualTo(5);
    }
}
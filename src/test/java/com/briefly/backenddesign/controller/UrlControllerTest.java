package com.briefly.backenddesign.controller;

import com.briefly.backenddesign.db.entity.LongToShortUrl;
import com.briefly.backenddesign.db.repository.LongToShortUrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class UrlControllerTest {
    @Autowired
    private LongToShortUrlRepository longToShortUrlRepository;
    @Test
    public void ShouldgetAllUrl() {
        UrlController urlController = new UrlController();
        LongToShortUrl test = new LongToShortUrl(1, "http://youtube.com", "HeuMmp");
        urlController.createUrl(test);
        Assertions.assertFalse(urlController.getAllUrl().isEmpty());
        Assertions.assertEquals(1, urlController.getAllUrl().size());
    }
}
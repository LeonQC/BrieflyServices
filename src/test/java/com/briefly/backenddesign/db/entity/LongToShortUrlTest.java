package com.briefly.backenddesign.db.entity;

import com.briefly.backenddesign.db.repository.LongToShortUrlRepository;
import org.flywaydb.test.junit5.annotation.FlywayTestExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@FlywayTestExtension
@DataJpaTest
class LongToShortUrlTest {
    @Autowired
    private LongToShortUrlRepository longToShortUrlRepository;

    @Test
    public void saveUrlTest(){
        LongToShortUrl url = LongToShortUrl.builder()
                .longUrl("https://youtube.com")
                .shortUrl("HeuMmp")
                .build();

        longToShortUrlRepository.save(url);
        Assertions.assertEquals(1, url.getId());
    }


}
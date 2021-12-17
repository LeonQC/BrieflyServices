package com.briefly.backenddesign.db.repository;

import com.briefly.backenddesign.db.entity.LongToShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LongToShortUrlRepository extends JpaRepository<LongToShortUrl, Long> {


    Optional<LongToShortUrl> findByShortUrl(String shortUrl);

    Optional<LongToShortUrl> findByLongUrl(String longUrl);

}
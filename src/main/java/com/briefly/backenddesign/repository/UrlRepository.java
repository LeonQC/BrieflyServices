package com.briefly.backenddesign.repository;

import com.briefly.backenddesign.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {
    //all crud databases methods
}

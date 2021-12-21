package com.briefly.backenddesign.controller;

import com.briefly.backenddesign.db.entity.LongToShortUrl;
import com.briefly.backenddesign.db.repository.LongToShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/url")
@CrossOrigin
public class UrlController {
    @Autowired
    private LongToShortUrlRepository longToShortUrlRepository;
    @GetMapping("/getAll")
    public List<LongToShortUrl> getAllUrl(){
        return longToShortUrlRepository.findAll();
    }

    @PostMapping
    public LongToShortUrl createUrl(@RequestBody LongToShortUrl url){
        return longToShortUrlRepository.save(url);
    }
 /*
    // build get Url by id REST API
    @GetMapping("{id}")
    public ResponseEntity<LongToShortUrl> getUrlById(@PathVariable  long id){
        LongToShortUrl url = urlRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(url);
    }

    // build update Url REST API
    @PutMapping("{id}")
    public ResponseEntity<LongToShortUrl> updateUrl(@PathVariable long id, @RequestBody LongToShortUrl urlDetails) {
        LongToShortUrl updateUrl = urlRepository.findById(id).orElseThrow();

        updateUrl.setLongUrl(urlDetails.getLongUrl());
        urlRepository.save(updateUrl);

        return ResponseEntity.ok(updateUrl);
    }

   */

    // build delete Url REST API
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){

        LongToShortUrl employee = longToShortUrlRepository.findById(id).orElseThrow(null);

        longToShortUrlRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }



}

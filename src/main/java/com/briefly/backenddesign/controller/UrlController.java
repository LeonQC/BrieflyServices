package com.briefly.backenddesign.controller;

import com.briefly.backenddesign.model.Url;
import com.briefly.backenddesign.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/url")
public class UrlController {
    @Autowired
    private UrlRepository urlRepository;
    @GetMapping
    public List<Url> getAllUrl(){
        return urlRepository.findAll();
    }

    @PostMapping
    public Url createUrl(@RequestBody Url url){
        return urlRepository.save(url);
    }

    // build get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Url> getUrlById(@PathVariable  long id){
        Url url = urlRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(url);
    }

    // build update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<Url> updateUrl(@PathVariable long id,@RequestBody Url urlDetails) {
        Url updateUrl = urlRepository.findById(id).orElseThrow();

        updateUrl.setLongUrl(urlDetails.getLongUrl());
        urlRepository.save(updateUrl);

        return ResponseEntity.ok(updateUrl);
    }



    // build delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){

        Url employee = urlRepository.findById(id).orElseThrow();

        urlRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}

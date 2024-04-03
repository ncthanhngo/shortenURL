package com.mycompany.web_looker.controller;

import com.mycompany.web_looker.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/url")
public class UrlController {
    @Autowired
    private UrlService urlService;
    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestParam("url") String originalUrl){
        return ResponseEntity.ok(urlService.shortenUrl(originalUrl));
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortUrl){
        return ResponseEntity.ok(urlService.getOriginalUrl(shortUrl));
    }
}

package com.thanhngo.shortenurl.controller;

import com.thanhngo.shortenurl.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua_parser.UserAgentParser;
import java.lang.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1")
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
    @GetMapping("/info/{shortUrl}")
    public ResponseEntity<String> getInfoAccessUrl(@PathVariable String shortUrl, HttpServletRequest request){
        //Get user Ipaddress
        String ipAddress = request.getRemoteAddr();
        //Get user browser
        String browserInfo = request.getHeader("User-Agent");
        //Get user device information
        String deviceInfo = request.getHeader("User-Agent");
        // Get user accessTime information
        LocalDateTime accessTime = LocalDateTime.now();
        //save user access
        int clickCount = urlService.saveUrlAccess(shortUrl,ipAddress,browserInfo,deviceInfo, accessTime);
        return ResponseEntity.ok(urlService.getOriginalUrl("Total click for "+ shortUrl+ ":"+clickCount));  //
    }
}

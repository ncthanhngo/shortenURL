package com.thanhngo.shortenurl.controller;
import com.thanhngo.shortenurl.model.Url;
import com.thanhngo.shortenurl.ropository.UrlRepository;
import com.thanhngo.shortenurl.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.lang.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import eu.bitwalker.useragentutils.UserAgent;
@RestController
@RequestMapping("api/v1")
public class UrlController {
    @Autowired
    private UrlRepository urlRepository;
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
        Url url = urlRepository.findByShortUrl(shortUrl);
        if(url == null) {
            return ResponseEntity.badRequest().body("Not found");
        } else {
            // Get user Ipaddress
            String ipAddress = request.getRemoteAddr();

            // Get user browser
            String browserInfo = request.getHeader("User-Agent");

            // Get user device information
            String deviceInfo = DeviceDetector.getDeviceInfo(request);

            // Get user accessTime information
            LocalDateTime accessTime = LocalDateTime.now();
            // Save user access
            int clickCount = urlService.saveUrlAccess(shortUrl, ipAddress, browserInfo, deviceInfo, accessTime);

            // Return the information about the URL and the total click count
            return ResponseEntity.ok("Original URL: " + url.getOriginalUrl() + "\nTotal click count for " + shortUrl + ": " + clickCount);
        }
    }
    public class DeviceDetector {
        public static String getDeviceInfo(HttpServletRequest request) {
            String userAgentString = request.getHeader("User-Agent");
            UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
            return userAgent.getOperatingSystem().getDeviceType().getName();
        }
    }

}

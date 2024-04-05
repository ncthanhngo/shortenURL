package com.thanhngo.shortenurl.service;

import com.thanhngo.shortenurl.model.Url;
import com.thanhngo.shortenurl.model.UrlAccess;
import com.thanhngo.shortenurl.ropository.UrlAccessRepository;
import com.thanhngo.shortenurl.ropository.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private UrlAccessRepository urlAccessRepository;
    public String shortenUrl(String originalUrl){
        String shortUrl = RandomStringUtils.randomAlphabetic(6);
        Url url = Url.builder()
                .shortUrl(shortUrl)
                .originalUrl(originalUrl)
                .build();
        urlRepository.save(url);
        return shortUrl;

    }
    public String getOriginalUrl(String shortUrl){
        Url url = urlRepository.findByShortUrl(shortUrl);
        return url!=null? url.getOriginalUrl():null;
    }
    //Save infomation from user access the url
    public int saveUrlAccess(String shortUrl, String ipAddress,String browserInfo, String deviceInfo, LocalDateTime accessTime){
        //Save access info to database
        Url url = urlRepository.findByShortUrl(shortUrl);

        UrlAccess urlAccess = UrlAccess.builder()
                .shortUrl(shortUrl)
                .ipAddress(ipAddress)
                .browserInfo(browserInfo)
                .deviceInfo(deviceInfo)
                .accessTime(accessTime)
                .build();
        urlAccessRepository.save(urlAccess);
       // Count the access url

        int clickCount = urlAccessRepository.countByShortUrl(shortUrl);
        return clickCount;
    }
}

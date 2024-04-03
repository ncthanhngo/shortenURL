package com.mycompany.web_looker.service;
import com.mycompany.web_looker.model.Url;
import org.apache.commons.lang3.RandomStringUtils;
import com.mycompany.web_looker.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(String originalUrl) {
        String shortUrl = RandomStringUtils.randomAlphanumeric(6); // Generate a random string
        Url url = Url.builder()
                .shortUrl(shortUrl)
                .originalUrl(originalUrl)
                .build(); // Create the Url object using builder pattern
        urlRepository.save(url); // Save the Url object to the database
        return shortUrl;
    }

    public String getOriginalUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl);
        return url != null ? url.getOriginalUrl() : null;
    }
}


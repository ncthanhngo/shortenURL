package com.thanhngo.shortenurl.ropository;

import com.thanhngo.shortenurl.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface UrlRepository extends JpaRepository<Url,Long> {
    Url  findByShortUrl(String shortUrl);
}

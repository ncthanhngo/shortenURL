package com.thanhngo.shortenurl.ropository;

import com.thanhngo.shortenurl.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url,Long> {
    Url  findByShortUrl(String shortUrl);
}

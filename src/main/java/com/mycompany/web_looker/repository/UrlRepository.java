package com.mycompany.web_looker.repository;

import com.mycompany.web_looker.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url,Long> {
    Url findByShortUrl(String shortUrl);
}

package com.thanhngo.shortenurl.ropository;

import com.thanhngo.shortenurl.model.UrlAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository


public interface UrlAccessRepository extends JpaRepository<UrlAccess,Long> {
    @Query("SELECT COUNT(a) FROM UrlAccess a WHERE a.shortUrl = :shortUrl")
    int countByShortUrl(@Param("shortUrl") String shortUrl);

}
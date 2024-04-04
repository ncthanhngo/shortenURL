package com.thanhngo.shortenurl.ropository;

import com.thanhngo.shortenurl.model.UrlAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UrlAccessRepository extends JpaRepository<UrlAccess,Long> {
    @Query("select count(a) from UrlAccess a where a.short_Url= :shortUrl")
    int countByUrl(@Param("shortUrl") String shortUrl);
}
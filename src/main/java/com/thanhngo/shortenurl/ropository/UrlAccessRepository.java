package com.thanhngo.shortenurl.ropository;

import com.thanhngo.shortenurl.model.UrlAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository


public interface UrlAccessRepository extends JpaRepository<UrlAccess,Long> {
    @Query("select count(a) from UrlAccess a where a.url.id = :urlId")
    int countByUrlId(@Param("urlId") Long urlId);

}
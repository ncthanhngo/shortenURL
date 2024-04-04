package com.thanhngo.shortenurl.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)// Auto increment
    private Long id;
    @ManyToOne
    @JoinColumn(name = "short_url", referencedColumnName = "shortUrl")
    private Url url;
    private String ipAddress;
    private String browserInfo;
    private String deviceInfo;
    private LocalDateTime accessTime;
}

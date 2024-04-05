package com.thanhngo.shortenurl.model;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "url")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)// Auto increament
    private Long id;
    private String originalUrl;
    @Column(unique = true)
    private String shortUrl;
}

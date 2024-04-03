package com.mycompany.web_looker.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "url")
@Builder
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//Auto increament
    private Long id;
    private String originalUrl;
    private String shortUrl;

}

package com.briefly.backenddesign.db.entity;


import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="urls")
public class LongToShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="long_Url")
    public String longUrl;
    @Column(name="short_Url")
    public String shortUrl;
}

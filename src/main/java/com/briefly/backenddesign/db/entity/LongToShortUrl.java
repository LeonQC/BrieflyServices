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
@Builder
@Entity
@Table(name="LONG_TO_SHORT")
public class LongToShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public String longUrl;

    public String shortUrl;
}

package com.github.vivyteam.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "url")
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Url {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name="create_date")
    private LocalDateTime createDate;

    @Column(name = "original_url")
    private String originalUrl;
}

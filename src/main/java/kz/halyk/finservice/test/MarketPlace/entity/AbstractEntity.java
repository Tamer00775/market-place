package kz.halyk.finservice.test.MarketPlace.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class AbstractEntity {
    @Column(name = "created_date", updatable = false)
    @Getter
    private LocalDateTime createdDate = LocalDateTime.now();
}

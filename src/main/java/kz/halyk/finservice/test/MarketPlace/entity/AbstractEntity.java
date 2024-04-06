package kz.halyk.finservice.test.MarketPlace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class AbstractEntity {
    @Column(name = "created_date", updatable = false)
    @Getter
    @JsonIgnore
    private LocalDateTime createdDate = LocalDateTime.now();
}

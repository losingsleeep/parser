package com.bobby.parser.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/10
 */
@Entity
public class Batch implements Serializable {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime creationDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer threshold;


    public Long getId() {
        return id;
    }

    public Batch setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Batch setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Batch setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Batch setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public Batch setThreshold(Integer threshold) {
        this.threshold = threshold;
        return this;
    }
}

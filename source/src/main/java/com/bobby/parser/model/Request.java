package com.bobby.parser.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/10
 */
@Entity
public class Request implements Serializable {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    private LocalDateTime date;
    private String address;
    private String data;

    public Long getId() {
        return id;
    }

    public Request setId(Long id) {
        this.id = id;
        return this;
    }

    public Batch getBatch() {
        return batch;
    }

    public Request setBatch(Batch batch) {
        this.batch = batch;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Request setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Request setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getData() {
        return data;
    }

    public Request setData(String data) {
        this.data = data;
        return this;
    }
}

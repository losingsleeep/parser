package com.bobby.parser.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/10
 */
@Entity
public class Blocked implements Serializable {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    private String address;
    private Integer count;
    private String comment;

    public Long getId() {
        return id;
    }

    public Blocked setId(Long id) {
        this.id = id;
        return this;
    }

    public Batch getBatch() {
        return batch;
    }

    public Blocked setBatch(Batch batch) {
        this.batch = batch;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Blocked setAddress(String address) {
        this.address = address;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public Blocked setCount(Integer count) {
        this.count = count;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Blocked setComment(String comment) {
        this.comment = comment;
        return this;
    }
}

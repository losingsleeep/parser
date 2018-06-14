package com.bobby.parser.model;

import java.io.Serializable;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/10
 */
public class BlockedId implements Serializable {

    private String address;
    private Long batchId;

    public BlockedId() {
    }

    public BlockedId(String address, Long batchId) {
        this.address = address;
        this.batchId = batchId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public BlockedId setBatchId(Long batchId) {
        this.batchId = batchId;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public BlockedId setAddress(String address) {
        this.address = address;
        return this;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        BlockedId blockedId = (BlockedId) o;

        if (!address.equals(blockedId.address))
            return false;
        return batchId.equals(blockedId.batchId);
    }

    @Override public int hashCode() {
        int result = address.hashCode();
        result = 31 * result + batchId.hashCode();
        return result;
    }
}

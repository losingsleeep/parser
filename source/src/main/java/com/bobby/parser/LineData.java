package com.bobby.parser;

import java.time.LocalDateTime;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/08
 */
public class LineData {

    private LocalDateTime date;
    private String address;
    private String data;

    public LocalDateTime getDate() {
        return date;
    }

    public LineData setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public LineData setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getData() {
        return data;
    }

    public LineData setData(String data) {
        this.data = data;
        return this;
    }

    @Override public String toString() {
        return "LineData{" + "date=" + date + ", address='" + address + '\''
            + ", data='" + data + '\'' + '}';
    }
}

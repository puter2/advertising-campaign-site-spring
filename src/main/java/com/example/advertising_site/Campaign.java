package com.example.advertising_site;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Campaign implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String keywords;
    @Column(nullable = false)
    private int bid;
    @Column(nullable = false)
    private int fund;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String town;
    @Column(nullable = false)
    private int radius;

    protected Campaign(){}

    public Campaign(String name, String keywords, int bid, int fund, String status, String town, int radius) {
        this.name = name;
        this.keywords = keywords;
        this.bid = bid;
        this.fund = fund;
        this.status = status;
        this.town = town;
        this.radius = radius;
    }

    public String getId() {
        return String.valueOf(this.id);
    }

    public String getName() {
        return this.name;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public int getBid() {
        return this.bid;
    }

    public int getFund() {
        return this.fund;
    }

    public String getStatus() {
        return this.status;
    }

    public String getTown() {
        return this.town;
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFund(int fund) {
        this.fund = fund;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.akhahaha.giftr.service.data.models;

import java.util.Date;

/**
 * Match model
 * Created by Alan on 4/29/2016.
 */
public class Match {
    private Integer id;
    private MatchStatus status;
    private Date created;
    private Date lastModified;
    private User user1;
    private User user2;
    private String user1Transaction;
    private String user2Transaction;

    public Match(User user1, User user2) {
        this.status = MatchStatus.PENDING;
        Date currentDate = new Date();
        this.created = currentDate;
        this.lastModified = currentDate;
        this.user1 = user1;
        this.user2 = user2;
    }

    public Match(Integer id, MatchStatus status, Date created, Date lastModified, User user1, User user2,
                 String user1Transaction, String user2Transaction) {
        this.id = id;
        this.status = status;
        this.created = created;
        this.lastModified = lastModified;
        this.user1 = user1;
        this.user2 = user2;
        this.user1Transaction = user1Transaction;
        this.user2Transaction = user2Transaction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public String getUser1Transaction() {
        return user1Transaction;
    }

    public void setUser1Transaction(String user1Transaction) {
        this.user1Transaction = user1Transaction;
    }

    public String getUser2Transaction() {
        return user2Transaction;
    }

    public void setUser2Transaction(String user2Transaction) {
        this.user2Transaction = user2Transaction;
    }
}

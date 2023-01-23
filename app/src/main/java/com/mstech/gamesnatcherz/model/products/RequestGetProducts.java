package com.mstech.gamesnatcherz.model.products;

/** HARISH GADDAM */

public class RequestGetProducts {

    private String str;
    private int pgsize;
    private int pgindex;

    public RequestGetProducts(String str, int pgsize, int pgindex) {
        this.str = str;
        this.pgsize = pgsize;
        this.pgindex = pgindex;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getPgsize() {
        return pgsize;
    }

    public void setPgsize(int pgsize) {
        this.pgsize = pgsize;
    }

    public int getPgindex() {
        return pgindex;
    }

    public void setPgindex(int pgindex) {
        this.pgindex = pgindex;
    }
}

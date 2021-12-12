//package com.example.activities;

package com.example.hw2.activities;

public class Img {
    private int res = 0;
    private boolean isPrize = false;
    private boolean isLive = false;

    public Img() { }

    public int getRes() {
        return res;
    }

    public Img setRes(int res) {
        this.res = res;
        return this;
    }

    public boolean isLive() {
        return isLive;
    }

    public Img setLive(boolean live) {
        isLive = live;
        return this;
    }

    public boolean isPrize() {
        return isPrize;
    }

    public Img setPrize(boolean prize) {
        isPrize = prize;
        return this;
    }

    @Override
    public String toString() {
        return "Img{" +
                "res=" + res +
                ", isPrize=" + isPrize +
                '}';
    }
}


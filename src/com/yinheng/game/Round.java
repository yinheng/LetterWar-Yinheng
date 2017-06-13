package com.yinheng.game;

/**
 * Created by 尹恒 on 2017/6/13.
 */
public enum Round {
    round1("round1", 2000) , round2("round2", 1500), round3("round3", 1000), round4("round4", 500);

    public final String name;
    public final int waitTime;

    private Round(String name, int waitTime) {
        this.name = name;
        this.waitTime = waitTime;
    }
}

package com.yinheng.game;

import java.util.Random;

/**
 * Created by 尹恒 on 2017/6/13.
 */
public class LetterCreate {
    Letter[] letters = Letter.values();
    public Letter createLetter() {
        Random random = new Random();
        return letters[random.nextInt(26)];
    }
}

package com.yinheng.game;

import org.junit.Test;

/**
 * Created by 尹恒 on 2017/6/13.
 */
public class TestLetterCreate {
    @Test
    public void TestCreateLetter() {
        LetterCreate letterCreate = new LetterCreate();
        System.out.println(letterCreate.createLetter());
        System.out.println(new LetterCreate().createLetter());
        System.out.println(new LetterCreate().createLetter());
        System.out.println(new LetterCreate().createLetter());
    }

}

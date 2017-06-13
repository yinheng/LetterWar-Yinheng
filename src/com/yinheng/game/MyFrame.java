package com.yinheng.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by 尹恒 on 2017/6/13.
 */
public class MyFrame {
    private JButton startBtn;
    private JTextPane jTextPane;
    private JTextArea jTextArea;
    private JTextArea scoreArea;
    private  LetterCreate letterCreate = new LetterCreate();
    private boolean inputOk;
    private int score = 0;
    private int waitTime = Round.round1.waitTime;
    private Round round;

    ActionListener actionListener;


    public void show() {

        JFrame jFrame = new JFrame("LetterWar");
        jFrame.setSize(800, 600);
        jFrame.setResizable(true);

        startBtn = new JButton("start");

        jTextArea = new JTextArea();
        jTextArea.setSize(500, 500);
        jTextArea.setEditable(false);
        jTextArea.setFont(new Font("sefi", 0, 180));

        jTextPane = new JTextPane();
        jTextPane.setSize(500, 500);
        jTextPane.setEditable(false);
        jTextPane.setFont(new Font("sefi", 0, 90));
        jTextPane.setText("---");

        scoreArea = new JTextArea();
        scoreArea.setSize(500, 500);
        scoreArea.setEditable(false);
        scoreArea.setFont(new Font("sefi", 0, 45));
        scoreArea.setText("sore : 0" + Round.round1.name);

        jFrame.add(startBtn, BorderLayout.SOUTH);
        jFrame.add(jTextArea, BorderLayout.WEST);
        jFrame.add(jTextPane, BorderLayout.EAST);
        jFrame.add(scoreArea, BorderLayout.NORTH);
        jFrame.setVisible(true);

        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("jTextArea.getText(): " + jTextArea.getText());
                        startCheck();
                    }
                }).start();

            }
        };

        startBtn.addActionListener(actionListener);


    }

    private void startCheck() {
        jTextArea.setText(letterCreate.createLetter().name().toUpperCase());
        String key = jTextArea.getText();
        if(waitForInput(key)) {
            score ++;
            scoreArea.setText(String.format("score : %s", score));
            jTextArea.setBackground(Color.BLUE);
        }
        else {
            score --;
            scoreArea.setText(String.format("score : %s", score));
            jTextArea.setBackground(Color.red);
        }
        scoreCheck();
        startCheck();
    }

    private boolean waitForInput(String key) {
        inputOk = false;
        Object lock = new Object();

        startBtn.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                System.out.println(e.getKeyChar());

                jTextPane.setText(String.valueOf(e.getKeyChar()));
                String str = String.valueOf(e.getKeyChar());

                if(str.equalsIgnoreCase(key)) {

                    System.out.println("jTextPane.getText(): " + jTextPane.getText());
                    System.out.println("key: " + key);

                    inputOk = true;
                }

                synchronized (lock) {
                    lock.notify();
                }

            }
        });

        try {
            synchronized (lock) {

                lock.wait(waitTime);
            }
            return inputOk;

        } catch (InterruptedException e) {
            return false;
        }

    }

    private void scoreCheck() {
        if(waitTime == 2000 && score == 10) {
            round = Round.round2;
            scoreArea.append(round.name);
            score = 0;
            waitTime = round.waitTime;
        }

        if(waitTime == 1500 && score == 10) {
            round = Round.round3;
            scoreArea.append(round.name);
            score = 0;
            waitTime = round.waitTime;
        }


        if(waitTime == 1000 && score == 10) {
            round = Round.round4;
            scoreArea.append(round.name);
            score = 0;
            waitTime = round.waitTime;
        }

        if(waitTime == 500 && score == 10) {
            scoreArea.append("通关啦");
            score = 0;
        }
    }
}

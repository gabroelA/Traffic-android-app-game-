package com.gavriel.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.sql.SQLData;

public class MyTextInputListener implements Input.TextInputListener {
    private String name;
    private int score;

    @Override
    public void input(String text) {
        this.name = text;
        addScore();
    }

    @Override
    public void canceled() {

    }

    public void scoreInput(int score){
        this.score = score;
    }

    public void addScore(){
        Save.gd.addHighScore(score, name);
        Save.save();
    }
}

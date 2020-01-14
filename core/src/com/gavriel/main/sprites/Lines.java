package com.gavriel.main.sprites;

import com.badlogic.gdx.graphics.Texture;


public class Lines {


    private Texture lines;

    private float x, y;

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Lines(int x, int y){
        lines = new Texture("Lines.png");
        this.x = x;
        this.y = y;

    }

    public Texture getTexture() {
        return lines;
    }


}

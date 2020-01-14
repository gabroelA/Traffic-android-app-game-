package com.gavriel.main.states;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.gavriel.main.Game;
import com.gavriel.main.MyTextInputListener;

public class GameOverState extends State {

    MyTextInputListener textListener;

    Texture crashedTitle;
    Texture playAgainButton;
    Texture menuButton;

    int deviceWidth;
    int deviceHeight;



    protected GameOverState(GameStateManager gsm, int score) {
        super(gsm);
        camera.setToOrtho(false,Game.WIDTH/2, Game.HEIGHT/2);


        deviceWidth = Gdx.graphics.getWidth();
        deviceHeight = Gdx.graphics.getHeight();

        textListener = new MyTextInputListener();
        Gdx.input.getTextInput(textListener, "Game Over", "", "Enter your name:");
        textListener.scoreInput(score);



        crashedTitle = new Texture("crashedTitle.png");
        playAgainButton = new Texture("againButton.png");
        menuButton = new Texture("menuButtton.png");

    }

    @Override
    protected void handleInput() {

        if(Gdx.input.justTouched() && (Gdx.input.getX() > deviceWidth/2/2 - 25 && Gdx.input.getX() < deviceWidth/2/2 + 25
        && Gdx.input.getY() > deviceHeight/2/2 && Gdx.input.getY() < deviceHeight/2/2+25)) {
            gsm.set(new PlayState(gsm));
            dispose();
        }

        if(Gdx.input.getX() > deviceWidth/3 && Gdx.input.getX() < deviceWidth*2/3){
            if(Gdx.input.getY() > deviceHeight/3 && Gdx.input.getY() < deviceHeight*2/3 && Gdx.input.justTouched()){
                gsm.set(new PlayState(gsm));
                dispose();
            }
            else if(Gdx.input.getY() > deviceHeight*2/3 && Gdx.input.getY() < deviceHeight && Gdx.input.justTouched()){
                gsm.set(new MenuState(gsm));
                dispose();
            }
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(crashedTitle, Game.WIDTH/2/2 - 50, Game.HEIGHT/2-70, 100, 50);

        sb.draw(crashedTitle,  0, Game.HEIGHT/3, 400, 1);

        sb.draw(playAgainButton, Game.WIDTH/2/2 - 25, Game.HEIGHT/2/2, 50, 25);

        sb.draw(crashedTitle,  0, Game.HEIGHT/2/3, 400, 1);

        sb.draw(menuButton, Game.WIDTH/2/2 - 25, Game.HEIGHT/2/4-40, 50, 25);

        sb.end();
    }

    @Override
    public void dispose() {
        crashedTitle.dispose();
        playAgainButton.dispose();
        menuButton.dispose();
    }

}

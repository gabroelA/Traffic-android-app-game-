package com.gavriel.main.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gavriel.main.Game;
import com.gavriel.main.Save;

public class MenuState extends State {
    private Texture title;
    private Texture startButton;
    private Texture backGround;
    private Texture highScores;

    private int deviceWidth;
    private int deviceHeight;
    private Music music;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false,Game.WIDTH/2, Game.HEIGHT/2);
        startButton = new Texture("startBtn.png");
        title = new Texture("title.png");
        backGround = new Texture("RoadAgain.png");
        highScores = new Texture("highScores.png");

        deviceWidth = Gdx.graphics.getWidth();
        deviceHeight = Gdx.graphics.getHeight();

        music = Gdx.audio.newMusic(Gdx.files.internal("UI.mp3"));
        music.setLooping(true);
        music.setVolume(0.6f);
        music.play();

        Save.load();

    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched() && Gdx.input.getY() < deviceHeight/2) {
            gsm.set(new PlayState(gsm));
            music.stop();
            dispose();
        }
        else if(Gdx.input.justTouched() && Gdx.input.getY() > deviceHeight*2/3){
            gsm.set(new TableState(gsm));
            music.stop();
            dispose();
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
        sb.draw(backGround, 0, 0, Game.WIDTH/2, Game.HEIGHT/2);
        sb.draw(title, Game.WIDTH/2/2 - 50, Game.HEIGHT/2-70, 100, 50);
        sb.draw(startButton, Game.WIDTH/2/2 - 25, Game.HEIGHT/2/2, 50, 25);
        sb.draw(highScores, Game.WIDTH/2/2 - 25, Game.HEIGHT/2/4-40, 50, 25);
        sb.end();
    }

    @Override
    public void dispose() {
        backGround.dispose();
        title.dispose();
        startButton.dispose();
        highScores.dispose();
        music.dispose();
    }
}

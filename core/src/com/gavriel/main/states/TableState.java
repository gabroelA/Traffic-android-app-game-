package com.gavriel.main.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gavriel.main.Game;
import com.gavriel.main.Save;

public class TableState extends State {
    private BitmapFont font;

    private int[] highScores;
    private String[] names;

    protected TableState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false,Game.WIDTH/2, Game.HEIGHT/2);
        font = new BitmapFont();
        font.setColor(Color.WHITE);

        Save.load();
        highScores = Save.gd.getHighScores();
        names = Save.gd.getNames();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            gsm.set(new MenuState(gsm));
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
        String s;
        float w;

        s = "High Scores";

        font.draw(sb, s, (Game.WIDTH ) / 8, 300);

        for(int i = 0; i < highScores.length; i++) {
            s = String.format(
                    "%2d. %7s %s",
                    i + 1,
                    highScores[i],
                    names[i]
            );

            font.draw(sb, s, (Game.WIDTH ) / 8, 270 - 20 * i);
        }

        sb.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }

}

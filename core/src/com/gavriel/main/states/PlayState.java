package com.gavriel.main.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.gavriel.main.Game;

import com.gavriel.main.sprites.Car;
import com.gavriel.main.sprites.Lines;


import java.util.ArrayList;


public class PlayState extends State {


    public static final float OFFSET_CAR = 44;
    public static final float START_OFFSET_CAR = 38;


    private float lineSpeed = 1;
    private float boostLineSpeed = 5;
    private float speed = 1.2f;
    private float boostSpeed = 5.2f;
    private Texture road;
    private BitmapFont font;
    private Lines lines1;
    private Lines lines2;
    private Car car;
    private int y =450;
    private int carX = 38;

    private ArrayList<Car> cars;
    private ArrayList<Car> cars2;

    private int score;

    private int deviceWidth;
    private int deviceHeight;



    private Music music;
    private Sound crash;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false,Game.WIDTH/2, Game.HEIGHT/2);
        road = new Texture("RoadAgain.png");
        car = new Car(START_OFFSET_CAR+OFFSET_CAR, 10, true);
        lines1 = new Lines(76, 0);
        lines2 = new Lines(76, Game.HEIGHT/2+30);

        cars = new ArrayList<Car>();

        cars.add(new Car(START_OFFSET_CAR, y-50, false));

        cars.add(new Car(START_OFFSET_CAR+OFFSET_CAR*2, y, false));


        cars2 = new ArrayList<Car>();
        cars2.add(new Car(carX, y+350, false));
        cars2.add(new Car(carX+OFFSET_CAR+OFFSET_CAR, y+300, false));

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        score = 0;

        deviceWidth = Gdx.graphics.getWidth();
        deviceHeight = Gdx.graphics.getHeight();

        music = Gdx.audio.newMusic(Gdx.files.internal("music1.mp3"));
        music.setLooping(true);
        music.setVolume(0.6f);
        music.play();

        crash = Gdx.audio.newSound(Gdx.files.internal("crash.mp3"));

    }

    @Override
    protected void handleInput()  {

        if(car.getPosition().x > START_OFFSET_CAR && (Gdx.input.getX() < deviceWidth/3-50 && Gdx.input.justTouched())){
            car.setPosition(car.getPosition().x-OFFSET_CAR, car.getPosition().y);
        }
        else if(car.getPosition().x < START_OFFSET_CAR +OFFSET_CAR*2 && (Gdx.input.getX()> deviceWidth*2/3+50 && Gdx.input.justTouched())){
            car.setPosition(car.getPosition().x+OFFSET_CAR, car.getPosition().y);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.SPACE) || (Gdx.input.getX()> deviceWidth/3 && Gdx.input.getX() < deviceWidth*2/3 && Gdx.input.isTouched())){
            lines1.setY(lines1.getY()-boostLineSpeed);
            lines2.setY(lines2.getY()-boostLineSpeed);
            for(Car car: cars){
                car.setPosition(car.getPosition().x, car.getPosition().y-boostSpeed);
            }
            for(Car car: cars2) {
                car.setPosition(car.getPosition().x, car.getPosition().y - boostSpeed);
            }
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();

        lines1.setY(lines1.getY()-lineSpeed);
        lines2.setY(lines2.getY()-lineSpeed);
        if (lines1.getY() < - Game.HEIGHT/2){
            lines1.setY(Game.HEIGHT/2);
        }
        if (lines2.getY() < - Game.HEIGHT/2){
            lines2.setY(Game.HEIGHT/2);
        }



        for(Car car: cars){
            car.setPosition(car.getPosition().x, car.getPosition().y-speed);
            if(car.getPosition().y < -160){
                car.reposition();
                car.setRandomPlaces();
                score++;


            }

            if(this.car.getCarBounds().overlaps(car.getCarBounds())){
                music.stop();
                crash.play();
                dispose();
                gsm.set(new GameOverState(gsm, score));
            }
        }


        for(Car car: cars2){
            car.setPosition(car.getPosition().x, car.getPosition().y-speed);
            if(car.getPosition().y < -160){
                car.reposition();
                car.setRandomPlaces();
                score++;

                speed += 0.01;
                lineSpeed += 0.01;


            }

            if(this.car.getCarBounds().overlaps(car.getCarBounds())){
                music.stop();
                crash.play();
                dispose();
                gsm.set(new GameOverState(gsm, score));
            }
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(road, 0, 0, Game.WIDTH/2, Game.HEIGHT/2);


        sb.draw(lines1.getTexture(), lines1.getX(), lines1.getY(), 3, Game.HEIGHT/2);
        sb.draw(lines1.getTexture(), lines1.getX()+44, lines1.getY(), 3, Game.HEIGHT/2);

        sb.draw(lines2.getTexture(), lines2.getX(), lines2.getY(), 3, Game.HEIGHT/2);
        sb.draw(lines2.getTexture(), lines2.getX()+44, lines2.getY(), 3, Game.HEIGHT/2);

        sb.draw(car.getTexture(), car.getPosition().x, car.getPosition().y, 35, 75);


        for(Car car: cars){
            if(cars.get(0).getPosition().x == cars.get(1).getPosition().x)
                sb.draw(cars.get(0).getTexture(), cars.get(0).getPosition().x, cars.get(0).getPosition().y, 35, 75);
            else
                sb.draw(car.getTexture(), car.getPosition().x, car.getPosition().y, 35, 75);
        }


        for(Car car: cars2){
            if(cars2.get(0).getPosition().x == cars2.get(1).getPosition().x)
                sb.draw(cars2.get(0).getTexture(), cars2.get(0).getPosition().x, cars2.get(0).getPosition().y, 35, 75);
            else
                sb.draw(car.getTexture(), car.getPosition().x, car.getPosition().y, 35, 75);
        }

        font.draw(sb,"SCORE: " + score,0, Game.HEIGHT/2);

        sb.end();
    }

    @Override
    public void dispose() {
        road.dispose();
        lines1.getTexture().dispose();
        lines2.getTexture().dispose();
        car.getTexture().dispose();
        for(Car car: cars){
            car.getTexture().dispose();
        }
        for(Car car: cars2){
            car.getTexture().dispose();
        }
        font.dispose();

        music.dispose();
        crash.dispose();
    }



}

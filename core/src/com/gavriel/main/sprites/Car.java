package com.gavriel.main.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.gavriel.main.states.PlayState;

import java.util.ArrayList;
import java.util.Random;

public class Car {

    private Vector3 position;
    private Texture car;
    private ArrayList<Texture> carsTexture;


    public Car(float x, int y, boolean isMain){
        position = new Vector3(x, y, 0);

        carsTexture = new ArrayList<Texture>();
        carsTexture.add(new Texture("car1.png"));
        carsTexture.add(new Texture("car2.png"));
        carsTexture.add(new Texture("car3.png"));
        carsTexture.add(new Texture("car4.png"));
        carsTexture.add(new Texture("car5.png"));
        carsTexture.add(new Texture("car6.png"));
        carsTexture.add(new Texture("car7.png"));
        carsTexture.add(new Texture("car8.png"));
        carsTexture.add(new Texture("car9.png"));
        carsTexture.add(new Texture("car10.png"));
        carsTexture.add(new Texture("car12.png"));
        carsTexture.add(new Texture("car13.png"));
        carsTexture.add(new Texture("car14.png"));
        carsTexture.add(new Texture("car15.png"));
        carsTexture.add(new Texture("car16.png"));

        if(isMain) {
            car = new Texture("car.png");
        }
        else{
            selectCar();

        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return car;
    }

    public Rectangle getCarBounds(){
        return new Rectangle(getPosition().x, getPosition().y, 25, 65);
    }

    public void selectCar(){
        Random r = new Random();
        int num = r.nextInt(15);
        car = carsTexture.get(num);

    }

    public void setPosition(float x, float y) {
        position.set(x,  y, 0);
    }

    public void setRandomPlaces(){
        Random r = new Random();
        int num = r.nextInt(3)+1;

        if(num == 1) {
            setPosition(PlayState.START_OFFSET_CAR, getPosition().y+800);
        }
        else if(num == 2){
            setPosition(PlayState.START_OFFSET_CAR+PlayState.OFFSET_CAR*2, getPosition().y+800);
        }
        else{
            setPosition(PlayState.START_OFFSET_CAR+PlayState.OFFSET_CAR, getPosition().y+800);
        }
    }

    public void reposition(){
        selectCar();


    }



}

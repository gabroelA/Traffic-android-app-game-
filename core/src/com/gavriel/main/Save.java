package com.gavriel.main;

import com.badlogic.gdx.Gdx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Save {
    public static GameData gd;

    public static void save() {
        String filePath = Gdx.files.getExternalStoragePath() + "/highscores.sav";
        File f = new File(filePath);

        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(f)
            );
            out.writeObject(gd);
            out.close();
        }
        catch(Exception e) {
            e.printStackTrace();
            Gdx.app.exit();
        }

    }

    public static void load() {

        String filePath = Gdx.files.getExternalStoragePath() + "/highscores.sav";
        File f = new File(filePath);

        try {
            if(!saveFileExists()) {
                init();
                return;
            }
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(f)
            );
            gd = (GameData) in.readObject();
            in.close();
        }
        catch(Exception e) {
            e.printStackTrace();
            Gdx.app.exit();
        }

    }

    public static boolean saveFileExists() {
        String filePath = Gdx.files.getExternalStoragePath() + "/highscores.sav";
        File f = new File(filePath);
        return f.exists();
    }

    public static void init() {
        gd = new GameData();
        gd.init();
        save();
    }
}

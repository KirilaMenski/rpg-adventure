package com.ansgar.adventure.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * Created by kirill on 1.11.17.
 */

public class ResourceManager {

    private static HashMap<String, Texture> textures;
    private static HashMap<String, Music> musics;
    private static HashMap<String, Sound> sounds;

    private static ResourceManager resourceManagerInstance;

    public static void init() {
        if (resourceManagerInstance == null) {
            synchronized (ResourceManager.class) {
                if (resourceManagerInstance == null)
                    resourceManagerInstance = new ResourceManager();
            }
        }
    }

    private ResourceManager() {
        textures = new HashMap<String, Texture>();
        musics = new HashMap<String, Music>();
        sounds = new HashMap<String, Sound>();
    }

    public static void putTexture(String key, String path) {
        Texture texture = new Texture(Gdx.files.internal(path));
        textures.put(key, texture);
    }

    public static Texture getTexture(String key) {
        return textures.get(key);
    }

    public static void removeTexture(String key) {
        Texture texture = textures.get(key);
        if (texture != null) {
            texture.dispose();
            textures.remove(key);
        }
    }

    public static void putSound(String key, String path) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
        sounds.put(key, sound);
    }

    public Sound getSound(String key) {
        return sounds.get(key);
    }

    public void removeSound(String key) {
        Sound sound = sounds.get(key);
        if (sound != null) {
            sound.dispose();
            sounds.remove(key);
        }
    }

    public void putMusic(String key, String path) {
        Music music = Gdx.audio.newMusic(Gdx.files.internal(path));
        musics.put(key, music);
    }

    public Music getMusic(String key) {
        return musics.get(key);
    }

    public void removeMusic(String key) {
        Music music = musics.get(key);
        if (music != null) {
            music.dispose();
            musics.remove(key);
        }
    }

}

package com.ansgar.adventure.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by kirill on 10.11.17.
 */

public abstract class BaseManager {

    protected World world;

    public BaseManager(World world) {
        this.world = world;
    }

    public abstract void create(int direction, float x, float y, String key, String path);
    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();

    public abstract Object getObject();

}

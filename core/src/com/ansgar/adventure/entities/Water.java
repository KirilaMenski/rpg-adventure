package com.ansgar.adventure.entities;

import com.ansgar.adventure.managers.ResourceManager;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by kirill on 15.11.17.
 */

public class Water extends GameObject {

    public Water(Body body, float x, float y, String key, String resource, float delay, int countFrames, int spriteRow) {
        super(body, x, y);
        setResourceName(key);

        ResourceManager.putTexture(key, resource);

        setTexture(ResourceManager.getTexture(getResourceName()));
        setTextureRegions(TextureRegion.split(getTexture(), 64, 64));
        setAnimation(getTextureRegions(), delay);
        getAnimation().setSpritePosition(countFrames, spriteRow);
    }

}

package com.ansgar.adventure.entities;

import com.ansgar.adventure.managers.ResourceManager;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by kirill on 9.11.17.
 */

public class Magic extends GameObject {

    public Magic(Body body, float x, float y, String key, String resource) {
        super(body, x, y);
        setResourceName(key);

        ResourceManager.putTexture(key, resource);

        setTexture(ResourceManager.getTexture(getResourceName()));
        setTextureRegions(TextureRegion.split(getTexture(),
                64,
                64));
        setAnimation(getTextureRegions(), 1 / 12f);
    }

}

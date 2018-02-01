package com.ansgar.adventure.entities;

import com.ansgar.adventure.managers.AnimationManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import static com.ansgar.adventure.utils.VariableUtil.numbers.PPM;

/**
 * Created by kirill on 1.11.17.
 */

public class GameObject {

    private Body body;
    private Texture texture;
    private TextureRegion[][] textureRegions;
    private AnimationManager animation;

    private float x;
    private float y;

    private float width;
    private float height;

    private int spriteWidth;
    private int spriteHeight;

    private String resourceName;

    public GameObject(Body body, float x, float y) {
        this.body = body;
        this.x = x;
        this.y = y;
    }

    public void setAnimation(TextureRegion[][] textureRegions, float delay) {
        animation = new AnimationManager(body, textureRegions, delay);
        width = textureRegions[0][0].getRegionWidth();
        height = textureRegions[0][0].getRegionHeight();
    }

    public void update(float dt) {
        animation.update(dt);
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(animation.getFrame(),
                body.getPosition().x * PPM,
                body.getPosition().y * PPM);
        spriteBatch.end();
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public TextureRegion[][] getTextureRegions() {
        return textureRegions;
    }

    public void setTextureRegions(TextureRegion[][] textureRegions) {
        this.textureRegions = textureRegions;
    }

    public AnimationManager getAnimation() {
        return animation;
    }

    public void setAnimation(AnimationManager animation) {
        this.animation = animation;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public void setSpriteHeight(int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}

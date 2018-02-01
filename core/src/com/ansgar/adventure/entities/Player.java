package com.ansgar.adventure.entities;

import com.ansgar.adventure.managers.ResourceManager;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by kirill on 1.11.17.
 */

public class Player extends GameObject {

    private float maxHealth = 100f;
    private float health = 100f;
    private float maxMana = 100f;
    private float mana = 100f;
    private float maxStamina = 100f;
    private float stamina = 100f;

    public Player(Body body, float x, float y, String key, String resource) {
        super(body, x, y);
        setResourceName(key);

        ResourceManager.putTexture(key, resource);

        setTexture(ResourceManager.getTexture(getResourceName()));
        setTextureRegions(TextureRegion.split(getTexture(),
                64,
                64));
        setAnimation(getTextureRegions(), 1 / 12f);
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        if (health <= maxHealth) health += 0.01f;
        if (mana <= maxMana) mana += 0.01f;
        if (stamina <= maxStamina) stamina += 0.01f;

    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(float maxMana) {
        this.maxMana = maxMana;
    }

    public float getMana() {
        return mana;
    }

    public void setMana(float mana) {
        this.mana = mana;
    }

    public float getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(float maxStamina) {
        this.maxStamina = maxStamina;
    }

    public float getStamina() {
        return stamina;
    }

    public void setStamina(float stamina) {
        this.stamina = stamina;
    }
}

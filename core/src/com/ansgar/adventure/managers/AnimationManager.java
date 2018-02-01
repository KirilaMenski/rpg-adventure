package com.ansgar.adventure.managers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by kirill on 1.11.17.
 */

public class AnimationManager {

    public static final short UP_SPELL = 0;
    public static final short LEFT_SPELL = 1;
    public static final short DOWN_SPELL = 2;
    public static final short RIGHT_SPELL = 3;
    public static final short UP_THRUST = 4;
    public static final short LEFT_THRUST = 5;
    public static final short DOWN_THRUST = 6;
    public static final short RIGHT_THRUST = 7;
    public static final short UP_WALK = 8;
    public static final short LEFT_WALK = 9;
    public static final short DOWN_WALK = 10;
    public static final short RIGHT_WALK = 11;
    public static final short UP_SLASH = 12;
    public static final short LEFT_SLASH = 13;
    public static final short DOWN_SLASH = 14;
    public static final short RIGHT_SLASH = 15;
    public static final short UP_SHOOT = 16;
    public static final short LEFT_SHOOT = 17;
    public static final short DOWN_SHOOT = 18;
    public static final short RIGHT_SHOOT = 19;
    public static final short UP_HURT = 20;

    public static final short STAY_FRAMES = 1;
    public static final short SPELL_FRAMES = 7;
    public static final short THRUST_FRAMES = 8;
    public static final short WALK_FRAMES = 9;
    public static final short SLASH_FRAMES = 6;
    public static final short SHOOT_FRAMES = 13;
    public static final short HURT_FRAMES = 6;

    public static final short MAGIC_FRAMES = 16;

    private AnimationListener listener;

    private TextureRegion[][] frames;
    private Body body;
    private float delay;
    private float time;
    private int spriteCol;
    private int spriteRow;
    private int countFrames;

    public AnimationManager(Body body, TextureRegion[][] frames, float delay) {
        this.body = body;
        this.frames = frames;
        this.delay = delay;
        this.countFrames = 1;
    }

    public void update(float dt) {
        if (delay <= 0) return;
        time += dt;
        while (time >= delay) {
            step();
        }
    }

    private void step() {
        time -= delay;
        spriteCol++;
        if (spriteCol >= countFrames) {
            if (listener != null) listener.endAnimation(body);
            else spriteCol = 0;
        }
    }

    public TextureRegion getFrame() {
        return frames[spriteRow][spriteCol];
    }

    public void setSpritePosition(int countFrames, int spriteRow) {
        this.countFrames = countFrames;
        this.spriteRow = spriteRow;
    }

    public void setSpritePosition(int countFrames, int spriteRow, int spriteCol) {
        this.countFrames = countFrames;
        this.spriteRow = spriteRow;
        this.spriteCol = spriteCol;
    }

    public void setListener(AnimationListener listener) {
        this.listener = listener;
    }

    public interface AnimationListener {
        void endAnimation(Body body);
    }

}

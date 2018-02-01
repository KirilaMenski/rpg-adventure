package com.ansgar.adventure.managers;

import static com.badlogic.gdx.Input.Keys.*;

/**
 * Created by kirill on 4.11.17.
 */

public class InputHandler {

    private static final int NUM_KEYS = 255;

    private static boolean[] keys = new boolean[NUM_KEYS];
    private static boolean[] pKeys = new boolean[NUM_KEYS];

    public static void update() {
        for (int i = 0; i < NUM_KEYS; i++) pKeys[i] = keys[i];
    }

    public static void setKey(int i, boolean b) {
        keys[i] = b;
    }

    public static boolean isDown(int i) {
        return keys[i];
    }

    public static boolean isPressed(int i) {
        return keys[i] && !pKeys[i];
    }

    public static boolean isMoving() {
        return isDown(W) || isDown(A) || isDown(S) || isDown(D);
    }

    public static boolean isSpelling() {
        return isPressed(NUM_1) || isPressed(NUM_2) || isPressed(NUM_3) || isPressed(NUM_4) || isPressed(NUM_5);
    }

}

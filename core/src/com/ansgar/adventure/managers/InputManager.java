package com.ansgar.adventure.managers;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.ansgar.adventure.managers.InputHandler.*;

/**
 * Created by kirill on 10.11.17.
 */

public class InputManager implements InputProcessor {

    private static InputMultiplexer instance;

    public static InputMultiplexer instance() {
        if (instance == null) {
            synchronized (InputManager.class) {
                if (instance == null) {
                    instance = new InputMultiplexer(new InputManager(), new Stage());
                }
            }
        }
        return instance;
    }

    private InputManager() {

    }

    @Override
    public boolean keyDown(int keycode) {
        setKey(keycode, true);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        setKey(keycode, false);
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

package com.ansgar.adventure;

import com.ansgar.adventure.managers.InputHandler;
import com.ansgar.adventure.managers.InputManager;
import com.ansgar.adventure.managers.ResourceManager;
import com.ansgar.adventure.managers.GameStateManager;
import com.ansgar.adventure.utils.VariableUtil;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {

    private SpriteBatch spriteBatch;

    private OrthographicCamera mainCamera, hudCamera;
    private GameStateManager gameStateManager;

    private float accumulat;

    @Override
    public void create() {

//        InputManager inputProcessor = InputManager.instance();
        InputMultiplexer inputProcessor = InputManager.instance();
        Gdx.input.setInputProcessor(inputProcessor);

        spriteBatch = new SpriteBatch();

        ResourceManager.init();

        mainCamera = new OrthographicCamera();
        mainCamera.setToOrtho(false, VariableUtil.numbers.GAME_WIDTH, VariableUtil.numbers.GAME_HEIGHT);
        hudCamera = new OrthographicCamera();
        hudCamera.setToOrtho(false, VariableUtil.numbers.GAME_WIDTH, VariableUtil.numbers.GAME_HEIGHT);

        gameStateManager = new GameStateManager(this);
    }

    @Override
    public void render() {
        accumulat += Gdx.graphics.getDeltaTime();

        float step = VariableUtil.numbers.STEP;
        while (accumulat >= step) {
            accumulat -= step;
            gameStateManager.update(step);
            gameStateManager.render();

            InputHandler.update();
        }
    }

    @Override
    public void dispose() {

    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public OrthographicCamera getMainCamera() {
        return mainCamera;
    }

    public OrthographicCamera getHudCamera() {
        return hudCamera;
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }
}

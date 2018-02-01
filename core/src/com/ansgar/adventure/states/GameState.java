package com.ansgar.adventure.states;

import com.ansgar.adventure.Game;
import com.ansgar.adventure.managers.GameStateManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by kirill on 1.11.17.
 */

public abstract class GameState {

    protected GameStateManager gameStateManager;
    protected Game game;

    protected SpriteBatch spriteBatch;
    protected OrthographicCamera mainOrthoCamera, hudOrthoCamera;

    public GameState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        game = gameStateManager.getGame();
        spriteBatch = game.getSpriteBatch();
        mainOrthoCamera = game.getMainCamera();
        hudOrthoCamera = game.getHudCamera();
    }

    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render();
    public abstract void dispose();

}

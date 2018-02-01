package com.ansgar.adventure.managers;

import com.ansgar.adventure.Game;
import com.ansgar.adventure.states.GameState;
import com.ansgar.adventure.states.Menu;
import com.ansgar.adventure.states.Play;

import java.util.Stack;

/**
 * Created by kirill on 1.11.17.
 */

public class GameStateManager {

    private Game game;
    private Stack<GameState> gameStates;

    public enum GameStateEnum {
        MENU, PLAY
    }

    public GameStateManager(Game game) {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushSate(GameStateEnum.PLAY);
    }

    public Game getGame() {
        return game;
    }

    public GameState getGameState(GameStateEnum state) {

        switch (state) {
            default:
            case MENU:
                return new Menu(this);
            case PLAY:
                return new Play(this);
        }
    }

    public void setGameState(GameStateEnum state) {
        popState();
        pushSate(state);
    }

    public void popState() {
        GameState gameState = gameStates.pop();
        gameState.dispose();
    }

    public void pushSate(GameStateEnum state) {
        gameStates.push(getGameState(state));
    }

    public void update(float dt) {
        gameStates.peek().update(dt);
    }

    public void render() {
        gameStates.peek().render();
    }

}

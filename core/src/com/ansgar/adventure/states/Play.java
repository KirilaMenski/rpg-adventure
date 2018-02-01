package com.ansgar.adventure.states;

import com.ansgar.adventure.entities.Player;
import com.ansgar.adventure.listeners.ContactListener;
import com.ansgar.adventure.managers.InterfaceManager;
import com.ansgar.adventure.managers.PlayerManager;
import com.ansgar.adventure.managers.TextManager;
import com.ansgar.adventure.managers.WorldManager;
import com.ansgar.adventure.managers.GameStateManager;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import static com.ansgar.adventure.utils.VariableUtil.numbers.*;

/**
 * Created by kirill on 1.11.17.
 */

public class Play extends GameState {

    private Box2DDebugRenderer b2DR;

    private World world;
    private OrthographicCamera box2dCam;
    private WorldManager worldManager;
    private PlayerManager playerManager;
    private TextManager fpsText;
    private InterfaceManager interfaceManager;

    public Play(GameStateManager gameStateManager) {
        super(gameStateManager);

        world = new World(new Vector2(0, 0f), true);
        world.setContactListener(new ContactListener());

        b2DR = new Box2DDebugRenderer();

        // createWorld();
        worldManager = new WorldManager(world, "images/world_map/aeterna/aeterna.tmx");
        worldManager.createWorld();

        //create player
        playerManager = new PlayerManager(world);
        playerManager.create(0, 100 / PPM, 200 / PPM, "player", "images/sprites/player/player_female.png");

        fpsText = new TextManager();
        interfaceManager = new InterfaceManager((Player) playerManager.getObject());

        box2dCam = new OrthographicCamera();
        box2dCam.setToOrtho(false, GAME_WIDTH / PPM, GAME_HEIGHT / PPM);

    }

    @Override
    public void handleInput() {
        worldManager.handleInput(mainOrthoCamera);
        playerManager.handleInput();
    }

    @Override
    public void update(float dt) {

        handleInput();

        mainOrthoCamera.update();
        world.step(dt, 6, 2);

        //update world
        worldManager.update(dt);

        //update player
        playerManager.update(dt);

        // interface manager
        interfaceManager.update();

    }

    @Override
    public void render() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainOrthoCamera.position.set(playerManager.getPosition());
        mainOrthoCamera.update();

        spriteBatch.setProjectionMatrix(mainOrthoCamera.combined);

        //draw map
        worldManager.getMapRenderer().setView(mainOrthoCamera);
        worldManager.getMapRenderer().render();
        worldManager.render(spriteBatch);

        //draw player
        playerManager.render(spriteBatch);

//        b2DR.render(world, box2dCam.combined);

        spriteBatch.setProjectionMatrix(hudOrthoCamera.combined);
        fpsText.render(spriteBatch, GAME_WIDTH, GAME_HEIGHT,
                "FPS: " + Gdx.graphics.getFramesPerSecond(), 10f, 0.5f, true);

        // interface
        interfaceManager.render(spriteBatch);
    }

    @Override
    public void dispose() {
        playerManager.dispose();
        if (fpsText != null) fpsText.dispose();
        if (interfaceManager != null) interfaceManager.dispose();
    }
}

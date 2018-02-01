package com.ansgar.adventure.managers;

import com.ansgar.adventure.entities.Water;
import com.ansgar.adventure.utils.VariableUtil;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

import static com.ansgar.adventure.utils.VariableUtil.numbers.PPM;
import static com.badlogic.gdx.Input.Keys.MINUS;
import static com.badlogic.gdx.Input.Keys.PLUS;

/**
 * Created by kirill on 8.11.17.
 */

public class WorldManager {

    private TiledMap tiledMap;
    private World world;
    private OrthogonalTiledMapRenderer mapRenderer;
    private float tileSize;
    private List<Water> waters = new ArrayList<>();

    public WorldManager(World world, String path) {
        this.world = world;
        tiledMap = new TmxMapLoader().load(path);
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        tileSize = (int) tiledMap.getProperties().get("tilewidth");
    }

    public void handleInput(OrthographicCamera camera) {
        if (InputHandler.isDown(PLUS) && camera.zoom <= 5) camera.zoom += 0.2;
        if (InputHandler.isDown(MINUS) && camera.zoom >= 0.5) camera.zoom -= 0.2;
    }

    public void createWorld() {

        for (int i = 0; i < tiledMap.getLayers().size(); i++) {
            TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(i);
            short bit = VariableUtil.BitEnum.getBit(layer.getName()).getBit();
            if (bit != 0) createLayer(layer, bit);
        }
    }

    private void createLayer(TiledMapTileLayer layer, short bits) {
        BodyDef bDef = new BodyDef();
        FixtureDef fDef = new FixtureDef();
        PolygonShape pShape = new PolygonShape();

        for (int row = 0; row < layer.getHeight(); row++) {
            for (int col = 0; col < layer.getWidth(); col++) {

                TiledMapTileLayer.Cell cell = layer.getCell(col, row);

                if (cell != null && cell.getTile() != null) {

                    float x = (col) * tileSize / PPM;
                    float y = (row) * tileSize / PPM;

                    bDef.type = BodyDef.BodyType.StaticBody;
                    bDef.position.set(x, y);

                    pShape.setAsBox(tileSize / 2 / PPM, tileSize / 2 / PPM);

                    fDef.friction = 0;
                    fDef.shape = pShape;
                    fDef.filter.categoryBits = bits;
                    fDef.filter.maskBits = VariableUtil.bits.PLAYER_BIT | VariableUtil.bits.PLAYER_FOOT_BIT;
                    fDef.isSensor = false;

                    Body body = world.createBody(bDef);

                    body.createFixture(fDef);

                    if (layer.getName().equals(VariableUtil.BitEnum.WATER_BIT.getName())) {
                        createWater(body, x, y, "water" + col + "," + row,
                                "images/world_map/aeterna/water_an.png",
                                0.5f, 2, 0);
                    } else if (layer.getName().equals(VariableUtil.BitEnum.WATER_FALL_1_BIT.getName())) {
                        createWater(body, x, y, "water" + col + "," + row,
                                "images/world_map/aeterna/water_fall.png",
                                1 / 12f, 4, 0);
                    } else if (layer.getName().equals(VariableUtil.BitEnum.WATER_FALL_2_BIT.getName())) {
                        createWater(body, x, y, "water" + col + "," + row,
                                "images/world_map/aeterna/water_fall.png",
                                1 / 12f, 4, 1);
                    } else {
                        body.setUserData(layer.getName());
                    }
                }
            }
        }

        pShape.dispose();
    }

    private void createWater(Body body, float x, float y, String key, String resource,
                             float delay, int countFrames, int spriteRow) {
        Water water = new Water(body, x, y, key, resource, delay, countFrames, spriteRow);
        waters.add(water);
        body.setUserData(water);
    }

    public void render(SpriteBatch spriteBatch) {
        for (Water water : waters)
            water.render(spriteBatch);
    }

    public void update(float dt) {
        for (Water water : waters)
            water.update(dt);
    }

    public OrthogonalTiledMapRenderer getMapRenderer() {
        return mapRenderer;
    }

}

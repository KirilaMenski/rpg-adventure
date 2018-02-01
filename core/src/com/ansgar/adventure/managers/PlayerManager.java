package com.ansgar.adventure.managers;

import com.ansgar.adventure.entities.Magic;
import com.ansgar.adventure.entities.Player;
import com.ansgar.adventure.utils.VariableUtil;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static com.ansgar.adventure.utils.VariableUtil.numbers.*;
import static com.badlogic.gdx.Input.Keys.*;

/**
 * Created by kirill on 10.11.17.
 */

public class PlayerManager extends BaseManager implements AnimationManager.AnimationListener {

    private Player player;
    private MagicManager magicManager;

    private int direction = AnimationManager.DOWN_SPELL;
    private boolean spelling = false;

    public PlayerManager(World world) {
        super(world);
    }

    @Override
    public void create(int direction, float x, float y, String key, String path) {
        BodyDef bDef = new BodyDef();
        PolygonShape pShape = new PolygonShape();
        FixtureDef fDef = new FixtureDef();

        bDef.position.set(x, y);
        bDef.type = BodyDef.BodyType.DynamicBody;

        Body body = world.createBody(bDef);

        pShape.setAsBox(20 / PPM, 5 / PPM, new Vector2(0, -20 / PPM), 0);
        fDef.shape = pShape;
        fDef.filter.categoryBits = VariableUtil.bits.PLAYER_BIT;
//        fDef.filter.categoryBits = VariableUtil.bits.PLAYER_FOOT_BIT;
        fDef.filter.maskBits = VariableUtil.bits.BORDERS_BIT | VariableUtil.bits.WATER_BIT;
        body.createFixture(fDef);

        pShape.setAsBox(30 / PPM, 30 / PPM, new Vector2(0, 0 / PPM), 0);
        fDef.isSensor = true;
        body.createFixture(fDef).setUserData(VariableUtil.data.PLAYER_FOOT);

        player = new Player(body, x, y, key, path);

        body.setUserData(player);

        pShape.dispose();
    }

    @Override
    public void handleInput() {
        float hast = 50 * Gdx.graphics.getDeltaTime();
        float pHealth = player.getHealth();
        float pMana = player.getMana();
        float pStamina = player.getStamina();

        if (InputHandler.isDown(SHIFT_LEFT) && InputHandler.isMoving() && pStamina > 0f) {
            player.setStamina(player.getStamina() - 1f);
            hast *= 2;
        } else {
            hast /= 2;
        }

        if (InputHandler.isDown(W)) {
            player.getAnimation().setSpritePosition(AnimationManager.WALK_FRAMES, AnimationManager.UP_WALK);
            player.getBody().setLinearVelocity(0, hast);
            direction = AnimationManager.UP_SPELL;
        }

        if (InputHandler.isDown(A)) {
            player.getAnimation().setSpritePosition(AnimationManager.WALK_FRAMES, AnimationManager.LEFT_WALK);
            player.getBody().setLinearVelocity(-hast, 0);
            direction = AnimationManager.LEFT_SPELL;
        }

        if (InputHandler.isDown(S)) {
            player.getAnimation().setSpritePosition(AnimationManager.WALK_FRAMES, AnimationManager.DOWN_WALK);
            player.getBody().setLinearVelocity(0, -hast);
            direction = AnimationManager.DOWN_SPELL;
        }

        if (InputHandler.isDown(D)) {
            player.getAnimation().setSpritePosition(AnimationManager.WALK_FRAMES, AnimationManager.RIGHT_WALK);
            player.getBody().setLinearVelocity(hast, 0);
            direction = AnimationManager.RIGHT_SPELL;
        }

        if (InputHandler.isPressed(NUM_1) && !InputHandler.isMoving() && !spelling && player.getMana() > 0) {
            spelling = true;

            float x = player.getBody().getPosition().x;
            float y = player.getBody().getPosition().y;
            float distance = 40 / PPM;
            if (direction == AnimationManager.UP_SPELL) y += distance;
            if (direction == AnimationManager.LEFT_SPELL) x -= distance;
            if (direction == AnimationManager.DOWN_SPELL) y -= distance;
            if (direction == AnimationManager.RIGHT_SPELL) x += distance;

            magicManager = new MagicManager(world);
            magicManager.create(direction, x, y, "fire_pantera", "images/magic/fire/fire_pantera.png");
            ((Magic) magicManager.getObject()).getAnimation().setListener(this);

            player.setMana(player.getMana() - 10);
        }

        if (!InputHandler.isMoving() && !spelling) {
            player.getAnimation().setSpritePosition(1, direction, 0);
            player.getBody().setLinearVelocity(0, 0);
        }

        if (InputHandler.isSpelling() && spelling) {
            player.getAnimation().setSpritePosition(AnimationManager.SPELL_FRAMES, direction);
            player.getBody().setLinearVelocity(0, 0);
        }

    }

    @Override
    public void update(float dt) {
        player.update(dt);

        //update magic
        if (magicManager != null && magicManager.getObject() != null)
            ((Magic) magicManager.getObject()).update(dt);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        player.render(spriteBatch);

        if (magicManager != null && magicManager.getObject() != null) {
            ((Magic) magicManager.getObject()).render(spriteBatch);
            TextManager textManager = new TextManager();
            textManager.render(spriteBatch,
                    player.getBody().getPosition().x * PPM + 40,
                    player.getBody().getPosition().y * PPM + 64,
                    "magic",
                    0,
                    0.5f,
                    1.5f,
                    true,
                    () -> textManager.dispose());
        }
    }

    @Override
    public void dispose() {
        player.getTexture().dispose();
        if (magicManager != null) magicManager.dispose();
    }

    @Override
    public Object getObject() {
        return player;
    }

    public int getDirection() {
        return direction;
    }

    public Vector3 getPosition() {
        return new Vector3(player.getBody().getPosition().x * PPM + GAME_WIDTH / 4 - 55,
                player.getBody().getPosition().y * PPM + 10, 0);
    }

    @Override
    public void endAnimation(Body body) {
        magicManager = null;
        world.destroyBody(body);

        spelling = false;
    }

}

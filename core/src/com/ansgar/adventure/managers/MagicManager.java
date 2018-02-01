package com.ansgar.adventure.managers;

import com.ansgar.adventure.entities.Magic;
import com.ansgar.adventure.utils.VariableUtil;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by kirill on 9.11.17.
 */

public class MagicManager extends BaseManager {

    private Magic magic;

    public MagicManager(World world) {
        super(world);
    }

    @Override
    public void create(int direction, float x, float y, String key, String path) {

        BodyDef bDef = new BodyDef();
        PolygonShape pShape = new PolygonShape();
        FixtureDef fDef = new FixtureDef();

        bDef.position.set(x, y);
        bDef.type = BodyDef.BodyType.StaticBody;

        Body body = world.createBody(bDef);

        pShape.setAsBox(25 / VariableUtil.numbers.PPM, 25 / VariableUtil.numbers.PPM);
        fDef.shape = pShape;
        fDef.filter.categoryBits = VariableUtil.bits.ATTACK_BIT;
// TODO        fDef.filter.maskBits =
        body.createFixture(fDef);

        magic = new Magic(body, x, y, key, path);
        magic.getAnimation().setSpritePosition(AnimationManager.MAGIC_FRAMES, direction);

        body.setUserData(magic);

        pShape.dispose();

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {
        magic.getTexture().dispose();
    }

    @Override
    public Object getObject() {
        return magic;
    }

}

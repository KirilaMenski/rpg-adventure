package com.ansgar.adventure.managers;

import com.ansgar.adventure.entities.Player;
import com.ansgar.adventure.utils.VariableUtil;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;

/**
 * Created by kirill on 20.11.17.
 */

public class InterfaceManager {

    private TextManager text;
    private ShapeRenderer rect;
    private Player player;
    private int x;
    private int y;
    private Stage stage;

    public InterfaceManager(Player player) {
        this(player, 10, VariableUtil.numbers.GAME_HEIGHT - 15);
    }

    public InterfaceManager(Player player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
        rect = new ShapeRenderer();
        text = new TextManager();
//        stage = new Stage();

//        Gdx.input.setInputProcessor(stage);

        Array<InputProcessor> inputProcessorList = InputManager.instance().getProcessors();
        for (int i = 0; i < inputProcessorList.size; i++) {
            if (inputProcessorList.get(i) instanceof Stage) {
                stage = (Stage) inputProcessorList.get(i);
            }
        }

        createButtons();
    }

    public void update() {

    }

    public void render(SpriteBatch spriteBatch) {
        rect.setProjectionMatrix(spriteBatch.getProjectionMatrix());
        rect.setAutoShapeType(true);
        rect.begin(ShapeRenderer.ShapeType.Filled);

        // health
        rect.setColor(Color.RED);
        rect.rect(x, y, player.getHealth(), 10);

        // mana
        rect.setColor(Color.BLUE);
        rect.rect(x, y - 15, player.getMana(), 10);

        // stamina
        rect.setColor(Color.valueOf("#D4CA90"));
        rect.rect(x, y - 30, player.getStamina(), 10);

        rect.set(ShapeRenderer.ShapeType.Line);

        // health
        rect.setColor(Color.RED);
        rect.rect(x, y, player.getMaxHealth(), 10);

        // mana
        rect.setColor(Color.BLUE);
        rect.rect(x, y - 15, player.getMaxMana(), 10);

        // stamina
        rect.setColor(Color.valueOf("#D4CA90"));
        rect.rect(x, y - 30, player.getMaxStamina(), 10);

        rect.end();

        text.render(spriteBatch, x + 5, y + 8,
                String.format("Health: %d/%d", (int) player.getHealth(), (int) player.getMaxStamina()),
                0, 0.4f, false);
        text.render(spriteBatch, x + 5, y - 8,
                String.format("Mana: %d/%d", (int) player.getMana(), (int) player.getMaxMana()),
                0, 0.4f, false);
        text.render(spriteBatch, x + 5, y - 22,
                String.format("Stamina: %d/%d", (int) player.getStamina(), (int) player.getMaxStamina()),
                0, 0.4f, false);

        stage.draw();
    }

    public void dispose() {
        if (rect != null) rect.dispose();
    }

    private void createButtons() {
        BitmapFont font = new BitmapFont();
        TextButton.TextButtonStyle testStyle = new TextButton.TextButtonStyle();
        testStyle.font = font;
        TextButton test = new TextButton("Test", testStyle);
        test.addListener(event -> {
            if (event.isHandled()) {
                System.out.println("Hello, World!");
            }
            return true;
        });
        stage.addActor(test);
    }

}

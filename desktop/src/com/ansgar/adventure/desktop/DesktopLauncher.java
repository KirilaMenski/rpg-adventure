package com.ansgar.adventure.desktop;

import com.ansgar.adventure.utils.VariableUtil;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ansgar.adventure.Game;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = VariableUtil.numbers.GAME_WIDTH * VariableUtil.numbers.SCALE;
        config.height = VariableUtil.numbers.GAME_HEIGHT * VariableUtil.numbers.SCALE;
        config.backgroundFPS = VariableUtil.numbers.FPS;
        config.foregroundFPS = VariableUtil.numbers.FPS;
        config.title = VariableUtil.strings.TITLE;
        new LwjglApplication(new Game(), config);
    }
}

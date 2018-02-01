package com.ansgar.adventure.managers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by kirill on 10.11.17.
 */

public class TextManager {

    private BitmapFont bitmapFont;
    private GlyphLayout glyphLayout;

    public TextManager() {
        bitmapFont = new BitmapFont();
        glyphLayout = new GlyphLayout();
    }

    public void render(SpriteBatch spriteBatch, float x, float y, String text) {
        render(spriteBatch, x, y, text, 0, 1f, 0, false, null);
    }

    public void render(SpriteBatch spriteBatch, float x, float y, String text, float padding, float scale, boolean isPadding) {
        render(spriteBatch, x, y, text, padding, scale, 0, isPadding, null);
    }

    public void render(SpriteBatch spriteBatch, float x, float y, String text, float padding, float scale,
                       float delay, boolean isPadding, TextListener listener) {
        bitmapFont.getData().setScale(scale);
        glyphLayout.setText(bitmapFont, text);

        float x_coord = isPadding ? x - (glyphLayout.width + padding) : x;
        float y_coord = isPadding ? y - padding : y;

        spriteBatch.begin();
        bitmapFont.draw(spriteBatch, text,
                x_coord,
                y_coord);
        spriteBatch.end();

        if (listener != null && TimeUtils.timeSinceNanos(TimeUtils.nanoTime()) > delay * 1000)
            listener.endTextDrawing();

    }

    public void dispose() {
        if (bitmapFont != null) bitmapFont.dispose();
    }

    public interface TextListener {
        void endTextDrawing();
    }

}

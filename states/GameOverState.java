package com.lii2.spritegame.states;

/**
 * Created by Lii2 on 2016/4/25.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lii2.spritegame.SpriteGame;

public class GameOverState extends State {
    Texture backgroundTexture;

    public GameOverState(GameStateManager gsm) {
        super(gsm);

        backgroundTexture = new Texture("gameover art.png");

        cam.setToOrtho(false, SpriteGame.WIDTH, SpriteGame.HEIGHT);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new MenuState(gsm));//no need to dispose because gsm handles it when it's being popped off
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(cam.combined);

        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0,0);
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        System.out.println("GameOverState disposed");
    }
}

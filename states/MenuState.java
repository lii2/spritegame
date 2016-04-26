package com.lii2.spritegame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lii2.spritegame.SpriteGame;

/**
 * Created by Lii2 on 2016/4/22.
 */
public class MenuState extends State {
    Texture backgroundTexture;
    Texture playButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        backgroundTexture = new Texture("menustate bg.png");
        playButton = new Texture("playbtn.png");

        cam.setToOrtho(false, SpriteGame.WIDTH, SpriteGame.HEIGHT);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new CutsceneState(gsm));//no need to dispose because gsm handles it when it's being popped off
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
        spriteBatch.draw(playButton, (cam.position.x) - (playButton.getWidth() / 2),
                cam.position.y);
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        playButton.dispose();
        System.out.println("MenuState disposed");
    }
}

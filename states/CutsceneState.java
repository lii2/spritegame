package com.lii2.spritegame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lii2.spritegame.SpriteGame;

/**
 * Created by Lii2 on 2016/4/24.
 */
public class CutsceneState extends State {
    Texture cutsceneTexture;
    BitmapFont bitmapFont;
    String cutsceneWords = "Oh no! The Earth has exploded!\nEscape the planet!";

    public CutsceneState(GameStateManager gsm) {
        super(gsm);

        cutsceneTexture = new Texture("cutscene art.png");

        cam.setToOrtho(false, SpriteGame.WIDTH, SpriteGame.HEIGHT);

        bitmapFont = new BitmapFont();
        bitmapFont.getData().setScale(2f);
        bitmapFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
        gsm.set(new PlayState(gsm));//no need to dispose because gsm handles it when it's being popped off
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
        spriteBatch.draw(cutsceneTexture, 0, 0);


        bitmapFont.draw(spriteBatch, cutsceneWords,
                0, SpriteGame.HEIGHT/2);

        spriteBatch.end();


    }

    @Override
    public void dispose() {
        cutsceneTexture.dispose();
        System.out.println("CutsceneState disposed");
    }
}

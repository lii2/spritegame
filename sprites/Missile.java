package com.lii2.spritegame.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.lii2.spritegame.SpriteGame;

/**
 * Created by Lii2 on 2016/4/24.
 */
public class Missile extends Sprite {
    public static final int MISSILE_SPEED = 1000;

    private Texture missileTexture;
    private Rectangle bounds;

    private boolean exists; //determines whether or not it is existing

    public Missile(int x, int y) {
        super(0,0);
        missileTexture = new Texture("missile.png");
        exists = false;

        bounds = new Rectangle(x, y, missileTexture.getWidth(), missileTexture.getHeight());
    }

    @Override
    public Vector2 getPosition() {
        return super.getPosition();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        bounds.setPosition(position.x, position.y);

        if (position.y >= SpriteGame.HEIGHT + missileTexture.getHeight() / 2) {
            missileHit();
        }
    }

    @Override
    public void dispose() {
        missileTexture.dispose();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void missileHit() {
        exists = false;
        position.set(0, 0);
        velocity.set(0, 0);
    }

    public boolean exists() {
        return exists;
    }

    public void shoot(float x, float y) {
        position.set(x, y);
        exists = true;
        velocity.set(0, MISSILE_SPEED);
    }


    public Texture getMissileTexture() {
        return missileTexture;
    }

}
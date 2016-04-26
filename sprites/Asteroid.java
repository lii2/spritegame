package com.lii2.spritegame.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.lii2.spritegame.SpriteGame;

import java.util.Random;

/**
 * Created by Lii2 on 2016/4/24.
 */
public class Asteroid extends Sprite {
    public static final int ASTEROID_WIDTH = 112;
    public static final int ASTEROID_SPEED = 100;

    public boolean destroyed;

    public Texture asteroidTexture;

    private Rectangle bounds;

    public Asteroid(int x, int y) {
        super(x, y);
        asteroidTexture = new Texture("asteroid placeholder.png");

        destroyed = false;

        velocity.set(0, ASTEROID_SPEED);

        bounds = new Rectangle(position.x, position.y,
                asteroidTexture.getWidth(), asteroidTexture.getHeight());
    }

    public void reposition(float y, Random rand) {
        position.set(rand.nextInt(SpriteGame.WIDTH), y);

        bounds.setPosition(position.x, position.y);
    }

    @Override
    public Vector2 getPosition() {
        return super.getPosition();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);//this handles adding the velocity to the position
        bounds.setPosition(position.x, position.y);
    }

    @Override
    public void dispose() {
        asteroidTexture.dispose();
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(bounds);
    }

    public Texture getAsteroidTexture() {
        return asteroidTexture;
    }
}

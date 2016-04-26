package com.lii2.spritegame.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Lii2 on 2016/4/24.
 */
public abstract class Sprite {
    protected Vector2 position;
    protected Vector2 velocity;

    public Sprite(int x, int y) {

        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
    }

    public Vector2 getPosition(){return position;};

    public void update(float deltaTime){
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
    };

    public abstract void dispose();
}

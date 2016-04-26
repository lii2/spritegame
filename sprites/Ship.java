package com.lii2.spritegame.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.lii2.spritegame.SpriteGame;



/**
 * Created by Lii2 on 2016/4/24.
 */
public class Ship extends Sprite{
    private static final int GRAVITY = -10;
    private static final int JUMP_SPEED = 500;
    private static final int DECELERATION_FACTOR = 20;

    private Texture shipTexture;
    private Vector2 initialPosition;

    private Rectangle bounds;

    public Rectangle getBounds() {
        return bounds;
    }

    public Ship(int x, int y) {
        super(x, y);
        initialPosition = new Vector2(x, y);
        shipTexture = new Texture("ship.png");

        bounds = new Rectangle(x, y, shipTexture.getWidth(), shipTexture.getHeight());
    }

    @Override
    public Vector2 getPosition() {
        return super.getPosition();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);//this handles adding the velocity to the position
        velocity.add(GRAVITY * (int) Math.signum(position.x - initialPosition.x) - velocity.x/DECELERATION_FACTOR,
                0);

        if (position.x > SpriteGame.WIDTH - shipTexture.getWidth() / 2) {
                position.x = SpriteGame.WIDTH  - shipTexture.getWidth()/2;
        }

        if (position.x < shipTexture.getWidth() / 2) {
            position.x = shipTexture.getWidth() / 2;
        }

        bounds.setPosition(position.x, position.y);

    }

    public Texture getShipTexture() {
        return shipTexture;
    }

    public void flingRight() {
        velocity.add(JUMP_SPEED, 0);
    }

    public void flingLeft() {
        velocity.add(-1 * JUMP_SPEED, 0);
    }

    @Override
    public void dispose() {
        shipTexture.dispose();
    }
}

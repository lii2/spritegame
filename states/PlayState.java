package com.lii2.spritegame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.lii2.spritegame.SpriteGame;
import com.lii2.spritegame.sprites.Asteroid;
import com.lii2.spritegame.sprites.Missile;
import com.lii2.spritegame.sprites.Ship;
import com.lii2.spritegame.sprites.Sprite;

import java.util.Random;

/**
 * Created by Lii2 on 2016/4/23.
 */
public class PlayState extends State {
    private static final int SHIP_WIDTH = 34;
    private static final int ASTEROID_COUNT = 5;
    private static final int ASTEROID_SPACING = 150;

    Texture backgroundTexture, instructions;
    private Ship ship;
    private Missile missile;
    public Random rand;

    private Array<Asteroid> asteroids;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        rand = new Random();
        backgroundTexture = new Texture("playstate bg.png");

        cam.setToOrtho(false, SpriteGame.WIDTH, SpriteGame.HEIGHT);

        ship = new Ship(SpriteGame.WIDTH / 2 - SHIP_WIDTH / 2,
                SpriteGame.HEIGHT/2);
        missile = new Missile(0, 0);

        asteroids = new Array<Asteroid>();

        //needs to be 1 because i can't equal to zero and therefore the boolean must be < or =
        for(int i = 1; i <= ASTEROID_COUNT; i++) {
            asteroids.add(new Asteroid(rand.nextInt(SpriteGame.WIDTH),
                    (0 - i * ASTEROID_SPACING - Asteroid.ASTEROID_WIDTH)));
        }

        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

            @Override
            public void onUp() {
                missile.shoot(ship.getPosition().x, ship.getPosition().y);
            }

            @Override
            public void onRight() {
                ship.flingRight();
            }

            @Override
            public void onLeft() {
                ship.flingLeft();

            }

            @Override
            public void onDown() {

            }
        }));
    }

    @Override
    protected void handleInput() {
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        ship.update(deltaTime);
        missile.update(deltaTime);

        for(int i = 0 ; i < ASTEROID_COUNT; i++) {
            Asteroid asteroid = asteroids.get(i);

            asteroid.update(deltaTime);

            if ((asteroid.getPosition().y + asteroid.getAsteroidTexture().getHeight() / 2)
                    > SpriteGame.HEIGHT) {
                asteroid.reposition(0 -
                        asteroid.getAsteroidTexture().getHeight() / 2,
                        rand);
                asteroid.destroyed = false;
            }
            if (asteroid.collides(missile.getBounds()) && missile.exists()) {
                asteroid.destroyed = true;
                missile.missileHit();
            }

            if (asteroid.collides(ship.getBounds())
                    && !asteroid.destroyed) {
                //rewrite to make it only check if it's near the ship later.
                gsm.set(new GameOverState(gsm));
            }
        }


    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(cam.combined);

        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0);
        spriteBatch.draw(ship.getShipTexture(),
                ship.getPosition().x, ship.getPosition().y);

        for (Asteroid asteroid : asteroids) {
            if (!asteroid.destroyed) {
                spriteBatch.draw(asteroid.getAsteroidTexture(),
                        asteroid.getPosition().x, asteroid.getPosition().y);
            }
        }

        if (missile.exists()) {
            spriteBatch.draw(missile.getMissileTexture(),
                    missile.getPosition().x, missile.getPosition().y);
        }

        spriteBatch.end();


    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();

        ship.dispose();
        for (Asteroid asteroid : asteroids) {
            asteroid.dispose();
        }

        missile.dispose();
        System.out.println("PlayState disposed");


    }


}

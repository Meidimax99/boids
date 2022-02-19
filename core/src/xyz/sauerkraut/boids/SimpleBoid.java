package xyz.sauerkraut.boids;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Generic class of Boids,
 * TODO Use the Decorator pattern to implement different combinations of behaviours for boids, which can be changed at runtime
 */
public class SimpleBoid implements Boid {

    //TODO Extend Sprite?
    private Shape2D hitbox;
    private Sprite sprite;
    private Vector2 velocity;
    private Vector2 acceleration;


    public SimpleBoid(Sprite sprite, Vector2 position, Vector2 velocity) {
        this.sprite = sprite;
        this.sprite.setX(position.x);
        this.sprite.setY(position.y);
        this.velocity = velocity;
        this.acceleration = new Vector2();

        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    }

    public Vector2 getPosition() {
        return new Vector2(sprite.getX(), sprite.getY());
    }

    public void setPosition(Vector2 position) {
        this.sprite.setX(position.x);
        this.sprite.setY(position.y);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public void update(float deltaTime) {
        this.sprite.setRotation(this.velocity.angleDeg() - 90);
        this.sprite.setX(sprite.getX() + velocity.x * deltaTime);
        this.sprite.setY(sprite.getY() + velocity.y * deltaTime);
    }

    private static void drawSprite(SpriteBatch spriteBatch, Sprite sprite) {
        spriteBatch.draw(sprite, sprite.getX() - sprite.getOriginX(), sprite.getY() - sprite.getOriginY(), sprite.getOriginX(), sprite.getOriginY(), sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
    }

    public void render(SpriteBatch batch) {
        drawSprite(batch, sprite);
    }

}

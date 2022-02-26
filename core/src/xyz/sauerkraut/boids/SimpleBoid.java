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

    //Shape2D hitbox;
    Sprite sprite;
    Vector2 velocity;
    Vector2 acceleration;

    public SimpleBoid(Sprite sprite) {
        this.sprite = new Sprite(sprite);
        //this.hitbox = new Circle(new Vector2(sprite.getX(), sprite.getY()), sprite.getWidth()/2);
        velocity = new Vector2();
        acceleration = new Vector2();
        this.sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    }

    public SimpleBoid(Sprite sprite, Vector2 position) {
        this(sprite);
        this.sprite.setX(position.x);
        this.sprite.setY(position.y);
    }


    @Override
    public void updateAcceleration(float deltaTime) {

    }

    @Override
    public void updateVelocity(float deltaTime) {

    }

    @Override
    public void updatePosition(float deltaTime) {
        this.sprite.setX(sprite.getX() + velocity.x * deltaTime);
        this.sprite.setY(sprite.getY() + velocity.y * deltaTime);
    }

    @Override
    public void updateRotation(float deltaTime) {
        this.sprite.setRotation(this.velocity.angleDeg() - 90);
    }


    public void render(ActualSpriteBatch batch) {
        batch.draw(sprite);
    }

    @Override
    public Boid duplicate() {
        return new SimpleBoid(sprite);
    }

    @Override
    public Vector2 getAcceleration() {
        return acceleration.cpy();
    }

    @Override
    public void setAcceleration(Vector2 acceleration) {
        this.acceleration.set(acceleration);
    }

    @Override
    public Vector2 getVelocity() {
        return velocity.cpy();
    }

    @Override
    public void setVelocity(Vector2 velocity) {
        this.velocity.set(velocity);
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(sprite.getX(),sprite.getY());
    }

    @Override
    public void setPosition(Vector2 position) {
        this.sprite.setX(position.x);
        this.sprite.setY(position.y);
    }


}

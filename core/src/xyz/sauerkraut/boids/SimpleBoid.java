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

    Sprite sprite;
    Vector2 velocity;
    Vector2 acceleration;
    Swarm swarm;

    private float radius;

    public SimpleBoid(Sprite sprite) {
        this.sprite = new Sprite(sprite);
        velocity = new Vector2();
        acceleration = new Vector2();
        this.sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        radius = Float.max(sprite.getWidth(), sprite.getHeight())/2;
    }

    public SimpleBoid(Sprite sprite, Vector2 position) {
        this(sprite);
        this.sprite.setX(position.x);
        this.sprite.setY(position.y);
    }


    public void updateAcceleration(float deltaTime) {

    }

    public void updateVelocity(float deltaTime) {
        velocity.add(acceleration.scl(deltaTime));
    }

    public void updatePosition(float deltaTime) {
        this.sprite.setX(sprite.getX() + velocity.x * deltaTime);
        this.sprite.setY(sprite.getY() + velocity.y * deltaTime);
    }

    public void updateRotation(float deltaTime) {
        this.sprite.setRotation(this.velocity.angleDeg() - 90);
    }

    public void render(ActualSpriteBatch batch) {
        batch.draw(sprite);
    }

    public Boid duplicate() {
        return new SimpleBoid(sprite);
    }

    public Vector2 getAcceleration() {
        return acceleration.cpy();
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration.set(acceleration);
    }

    public Vector2 getVelocity() {
        return velocity.cpy();
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity.set(velocity);
    }

    public Vector2 getPosition() {
        return new Vector2(sprite.getX(),sprite.getY());
    }

    public void setPosition(Vector2 position) {
        this.sprite.setX(position.x);
        this.sprite.setY(position.y);
    }

    @Override
    public Vector2 getDimensions() {
        return new Vector2(sprite.getWidth(),sprite.getHeight());
    }

    @Override
    public void setSwarm(Swarm swarm) {
        this.swarm = swarm;
    }

    @Override
    public Swarm getSwarm() {
        return swarm;
    }

    @Override
    public float getRadius() {
        return radius;
    }

    @Override
    public void setRadius(float radius) {
        this.radius = radius;
    }
}

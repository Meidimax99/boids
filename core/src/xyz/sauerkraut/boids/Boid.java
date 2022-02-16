package xyz.sauerkraut.boids;


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
public class Boid {

    private Shape2D hitbox;
    private Sprite sprite;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    public Boid(Sprite sprite) {

        this.sprite = sprite;
        Random random = new Random();
        this.position = new Vector2(random.nextFloat(), random.nextFloat());
        this.velocity = new Vector2(random.nextFloat(), random.nextFloat());
        this.acceleration = new Vector2();
        hitbox = new Circle(position, 5);
    }

    public Boid(Sprite sprite, Vector2 postion, Vector2 velocity) {
        this.sprite = sprite;
        this.position = postion;
        this.velocity = velocity;
        this.acceleration = new Vector2();
        hitbox = new Circle(position, 5);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
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
        this.sprite.setRotation(sprite.getRotation()+10f);
        this.position.add(velocity.x * deltaTime, velocity.y * deltaTime);
    }

    public void render(SpriteBatch batch) {
        batch.draw(sprite,position.x,position.y, sprite.getOriginX(), sprite.getOriginY(), 100,100,1,1,sprite.getRotation());
    }

}

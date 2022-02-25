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
        this.sprite = new Sprite(sprite);
        this.sprite.setX(position.x);
        this.sprite.setY(position.y);
        this.velocity = velocity;
        this.acceleration = new Vector2();

        this.hitbox = new Circle(position, sprite.getWidth()/2);

        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    }

    public SimpleBoid(Sprite sprite, Vector2 position) {
        new SimpleBoid(sprite, position, new Vector2(0,0));
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

    @Override
    public void updateAcceleration(float deltaTime) {

    }

    @Override
    public void updateVelocity(float deltaTime) {

    }


    //TODO Default impl problem???
    @Override
    public void updatePosition(float deltaTime) {
        System.out.println(this.sprite == null);
        this.sprite.setX(sprite.getX() + velocity.x * deltaTime);
        this.sprite.setY(sprite.getY() + velocity.y * deltaTime);
    }

    @Override
    public void updateRotation(float deltaTime) {
        this.sprite.setRotation(this.velocity.angleDeg() - 90);
    }

    private static void drawSprite(SpriteBatch spriteBatch, Sprite sprite) {
        spriteBatch.draw(sprite, sprite.getX() - sprite.getOriginX(), sprite.getY() - sprite.getOriginY(), sprite.getOriginX(), sprite.getOriginY(), sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
    }

    public void render(SpriteBatch batch) {
        drawSprite(batch, sprite);
    }

    @Override
    public Boid duplicate() {
        Random random = new Random();
        Vector2 randomPosition = new Vector2(random.nextFloat()* BoidSimulation.WIDTH, random.nextFloat() * BoidSimulation.HEIGHT);
        Boid boid = new SimpleBoid(new Sprite(sprite), randomPosition);
        System.out.println(boid.getPosition() == null);
        return boid;
    }

    //Add to abstract boid class - > like thread for runnable
//    @Override
//    public void update(float deltaTime) {
//        updateAcceleration(deltaTime);
//        updateVelocity(deltaTime);
//        updatePosition(deltaTime);
//        updateRotation(deltaTime);
//    }
}

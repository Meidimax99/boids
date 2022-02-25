package xyz.sauerkraut.boids;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface Boid {

    public default void update(float deltaTime) {
        updateAcceleration(deltaTime);
        updateVelocity(deltaTime);
        updatePosition(deltaTime);
        updateRotation(deltaTime);
    }

    public void updateAcceleration(float deltaTime);

    public void updateVelocity(float deltaTime);

    public void updatePosition(float deltaTime);

    public void updateRotation(float deltaTime);

    public void render(SpriteBatch batch);

    public Vector2 getPosition();

    public void setPosition(Vector2 position);

    public Vector2 getVelocity();

    public void setVelocity(Vector2 velocity);

    public Vector2 getAcceleration();

    public void setAcceleration(Vector2 acceleration);

    public Boid duplicate();
}

package xyz.sauerkraut.boids.decorators;

import com.badlogic.gdx.math.Vector2;
import xyz.sauerkraut.boids.ActualSpriteBatch;
import xyz.sauerkraut.boids.Boid;

public abstract class BoidDecorator implements Boid {

    private Boid component;

    public BoidDecorator(Boid boid) {
        component = boid;
    }

    public void render(ActualSpriteBatch batch) {
        component.render(batch);
    }

    public abstract Boid duplicate();

    public void updateAcceleration(float deltaTime) {
        this.component.updateAcceleration(deltaTime);
    }

    public void updateVelocity(float deltaTime) {
        this.component.updateVelocity(deltaTime);
    }

    public void updatePosition(float deltaTime) {
        this.component.updatePosition(deltaTime);
    }

    public void updateRotation(float deltaTime) {
        this.component.updateRotation(deltaTime);
    }

    public Vector2 getAcceleration() {
        return this.component.getAcceleration();
    }

    public void setAcceleration(Vector2 acceleration) {
        this.component.setAcceleration(acceleration);
    }

    public Vector2 getVelocity() {
        return this.component.getVelocity();
    }

    public void setVelocity(Vector2 velocity) {
        this.component.setVelocity(velocity);
    }

    public Vector2 getPosition() {
        return this.component.getPosition();
    }

    public void setPosition(Vector2 position) {
        this.component.setPosition(position);
    }

    protected Boid getComponent() {
        return component;
    }

    protected void setComponent(Boid component) {
        this.component = component;
    }

    public Vector2 getDimensions() {
        return this.component.getDimensions();
    }
}

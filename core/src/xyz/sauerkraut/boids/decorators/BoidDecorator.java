package xyz.sauerkraut.boids.decorators;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import xyz.sauerkraut.boids.ActualSpriteBatch;
import xyz.sauerkraut.boids.Boid;

public class BoidDecorator implements Boid {

    private Boid component;

    public BoidDecorator(Boid boid) {
        component = boid;
    }

    @Override
    public void render(ActualSpriteBatch batch) {
        component.render(batch);
    }

    @Override
    public Boid duplicate() {
        return new BoidDecorator(component.duplicate());
    }

    @Override
    public void updateAcceleration(float deltaTime) {
        this.component.updateAcceleration(deltaTime);
    }

    @Override
    public void updateVelocity(float deltaTime) {
        this.component.updateVelocity(deltaTime);
    }

    @Override
    public void updatePosition(float deltaTime) {
        this.component.updatePosition(deltaTime);
    }

    @Override
    public void updateRotation(float deltaTime) {
        this.component.updateRotation(deltaTime);
    }

    @Override
    public Vector2 getAcceleration() {
        return this.component.getAcceleration();
    }

    @Override
    public void setAcceleration(Vector2 acceleration) {
        this.component.setAcceleration(acceleration);
    }

    @Override
    public Vector2 getVelocity() {
        return this.component.getVelocity();
    }

    @Override
    public void setVelocity(Vector2 velocity) {
        this.component.setVelocity(velocity);
    }

    @Override
    public Vector2 getPosition() {
        return this.component.getPosition();
    }

    @Override
    public void setPosition(Vector2 position) {
        this.component.setPosition(position);
    }

    protected Boid getComponent() {
        return component;
    }

    protected void setComponent(Boid component) {
        this.component = component;
    }
}

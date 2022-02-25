package xyz.sauerkraut.boids.decorators;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import xyz.sauerkraut.boids.Boid;

public class BoidDecorator implements Boid {


    private Boid component;

    public Boid getComponent() {
        return component;
    }

    public void setComponent(Boid component) {
        this.component = component;
    }

    public BoidDecorator(Boid component) {
        this.component = component;
    }

    @Override
    public void update(float deltaTime) {
        component.update(deltaTime);
    }

    @Override
    public void updateAcceleration(float deltaTime) {
        component.updateAcceleration(deltaTime);
    }

    @Override
    public void updateVelocity(float deltaTime) {
        component.updateVelocity(deltaTime);
    }

    @Override
    public void updatePosition(float deltaTime) {
        component.updatePosition(deltaTime);
    }

    @Override
    public void updateRotation(float deltaTime) {
        component.updateRotation(deltaTime);
    }

    @Override
    public void render(SpriteBatch batch) {
        component.render(batch);
    }

    @Override
    public Vector2 getPosition() {
        return component.getPosition();
    }

    @Override
    public void setPosition(Vector2 position) {
        component.setPosition(position);
    }

    @Override
    public Vector2 getVelocity() {
        return component.getVelocity();
    }

    @Override
    public void setVelocity(Vector2 velocity) {
        component.setVelocity(velocity);
    }

    @Override
    public Vector2 getAcceleration() {
        return component.getAcceleration();
    }

    @Override
    public void setAcceleration(Vector2 acceleration) {
        component.setAcceleration(acceleration);
    }

    //TODO wrap in new instance of the decorator
    @Override
    public Boid duplicate() {
        return new BoidDecorator(component.duplicate());
    }
}

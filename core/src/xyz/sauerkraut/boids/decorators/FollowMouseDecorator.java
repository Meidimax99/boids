package xyz.sauerkraut.boids.decorators;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import xyz.sauerkraut.boids.Boid;
import xyz.sauerkraut.boids.decorators.BoidDecorator;

public class FollowMouseDecorator extends BoidDecorator {

    //TODO possibly split this decorator up in 2 -> getting mouse direction and getting mouse velocity from distance to mouse
    public FollowMouseDecorator(Boid boid) {
        super(boid);
    }

    @Override
    public void updateVelocity(float deltaTime) {
        Vector2 componentPosition = this.getComponent().getPosition();
        this.getComponent().setVelocity(new Vector2(Gdx.input.getX() - componentPosition.x, Gdx.input.getY() - componentPosition.y));
        this.getComponent().updateVelocity(deltaTime);
    }

    @Override
    public Boid duplicate() {
        return new FollowMouseDecorator(getComponent().duplicate());
    }
}

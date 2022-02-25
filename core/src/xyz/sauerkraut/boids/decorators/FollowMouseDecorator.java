package xyz.sauerkraut.boids.decorators;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import xyz.sauerkraut.boids.Boid;
import xyz.sauerkraut.boids.decorators.BoidDecorator;

public class FollowMouseDecorator extends BoidDecorator {

    public FollowMouseDecorator(Boid component) {
        super(component);
    }


    private void setDirectionToMouse(){
        Vector2 componentPosition = this.getComponent().getPosition();
        this.getComponent().getVelocity().x = Gdx.input.getX() - componentPosition.x;
        this.getComponent().getVelocity().y = Gdx.input.getY() - componentPosition.y;
    }

    @Override
    public void update(float deltaTime) {
        setDirectionToMouse();
        getComponent().update(deltaTime);
    }

    @Override
    public Boid duplicate() {
        return new FollowMouseDecorator(this.getComponent());
    }
}

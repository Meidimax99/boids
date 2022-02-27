package xyz.sauerkraut.boids.decorators;

import com.badlogic.gdx.math.Vector2;
import xyz.sauerkraut.boids.Boid;

import java.util.Random;

public class RandomDirection extends BoidDecorator{

    private float speedFac;

    public RandomDirection(Boid boid) {
        super(boid);
        Random rand = new Random();
        this.setVelocity(new Vector2(rand.nextFloat()*2-1,rand.nextFloat()*2-1));
        this.speedFac = 1;
    }

    public RandomDirection(Boid boid, float speedFac) {
        this(boid);
        this.speedFac = speedFac;
        this.setVelocity(this.getVelocity().scl(speedFac));
    }

    @Override
    public Boid duplicate() {
        return new RandomDirection(this.getComponent().duplicate(), speedFac);
    }
}

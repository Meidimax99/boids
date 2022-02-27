package xyz.sauerkraut.boids.decorators;

import xyz.sauerkraut.boids.Boid;

public class Debug extends BoidDecorator{

    public Debug(Boid boid) {
        super(boid);
    }

    @Override
    public Boid duplicate() {
        return null;
    }
}

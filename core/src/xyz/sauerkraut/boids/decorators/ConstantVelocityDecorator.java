package xyz.sauerkraut.boids.decorators;

import xyz.sauerkraut.boids.Boid;

public class ConstantVelocityDecorator extends BoidDecorator{


    private float fac = 1;

    public ConstantVelocityDecorator(Boid boid) {
        super(boid);
    }

    public ConstantVelocityDecorator(Boid boid, float fac) {
        super(boid);
        this.fac = fac;
    }

    @Override
    public void updateVelocity(float deltaTime) {
        this.getComponent().updateVelocity(deltaTime);
        this.setVelocity(this.getVelocity().nor().scl(fac));
    }

    @Override
    public Boid duplicate() {
        return new ConstantVelocityDecorator(getComponent().duplicate(), fac);
    }
}

package xyz.sauerkraut.boids.decorators;

import xyz.sauerkraut.boids.Boid;

public class ConstantVelocityDecorator extends BoidDecorator{

    private float fac = 1;
    public ConstantVelocityDecorator(Boid component) {
        super(component);
    }

    public ConstantVelocityDecorator(Boid component, float velocity) {
        super(component);
        this.fac = velocity;
    }

    public float getFac() {
        return fac;
    }

    public void setFac(float velocity) {
        this.fac = velocity;
    }

    //TODO make decorator ordering not affect end result - template method? further splitting down update method?

    @Override
    public void update(float deltaTime) {
        this.getComponent().setVelocity(getComponent().getVelocity().nor().scl(fac));
        getComponent().update(deltaTime);
        //normalize velocity and multiply with factor
    }

    @Override
    public Boid duplicate() {
        return new ConstantVelocityDecorator(this.getComponent(), fac);
    }
}

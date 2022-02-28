package xyz.sauerkraut.boids.decorators;

import com.badlogic.gdx.math.Vector2;
import xyz.sauerkraut.boids.Boid;
import xyz.sauerkraut.boids.Swarm;

public class Collision extends BoidDecorator{

    private float strength;

    //TODO make compatible with followmouse and constantvelocity decorators???
    public Collision(Boid boid) {
        super(boid);
        strength = 1f;
    }

    public Collision(Boid boid, float strength) {
        super(boid);
        this.strength = strength;
    }


    @Override
    public void updateAcceleration(float deltaTime) {
        Swarm swarm = getSwarm();

        Vector2 acc = new Vector2();
        for(Boid boid : swarm.getBoids()) {
            if(!boid.equals(this) && isCollidingWith(boid)) {
                acc.add(getCollisionVector(boid));
            }
        }
        setAcceleration(acc);
    }

    //TODO adjust for other Boids hitbox, currently only center to edge, should be edge to edge -> Problem is only visible when there are boids with different hitbox sizes
    public boolean isCollidingWith(Boid boid) {
        return boid.getPosition().dst(this.getPosition()) < getRadius()*2;
    }

    public Vector2 getCollisionVector(Boid boid) {
        Vector2 direction = boid.getPosition().sub(this.getPosition()).rotateDeg(180).nor();
        float distance = boid.getPosition().dst(this.getPosition());
        return direction.scl(getRadius()*2/(distance)).scl(strength);
    }

    @Override
    public Boid duplicate() {
        return new Collision(this.getComponent().duplicate(), strength);
    }
}

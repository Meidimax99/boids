package xyz.sauerkraut.boids;

import java.util.LinkedList;
import java.util.List;

public class SimpleSwarm implements Swarm{

    private final List<Boid> boids = new LinkedList<Boid>();

    public void addBoid(Boid boid) {
        this.boids.add(boid);
        boid.setSwarm(this);
    }

    public List<Boid> getBoids() {
        return boids;
    }
    public void updateAll(float deltaTime){
        for(Boid boid : boids) {
            boid.update(deltaTime);
        }
    }

    public void renderAll(ActualSpriteBatch batch){
        for(Boid boid : boids) {
            boid.render(batch);
        }
    }


}

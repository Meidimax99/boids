package xyz.sauerkraut.boids;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;
import java.util.List;

public class Swarm {

    private List<Boid> boids = new LinkedList<>();

    public Swarm() {
    }

    public void addBoid(Boid boid) {
        this.boids.add(boid);
    }

    public void updateAll(float deltaTime){
        for(Boid boid : boids) {
            boid.update(deltaTime);
        }
    }

    public void renderAll(SpriteBatch batch){
        for(Boid boid : boids) {
            boid.render(batch);
        }
    }
}

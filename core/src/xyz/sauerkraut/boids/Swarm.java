package xyz.sauerkraut.boids;

import java.util.List;

public interface Swarm {


    public void addBoid(Boid boid);

    public void updateAll(float deltaTime);

    public void renderAll(ActualSpriteBatch batch);

    public List<Boid> getBoids();
}

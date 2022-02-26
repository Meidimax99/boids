package xyz.sauerkraut.boids;

import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BoidFactory {

    public static Swarm swarmFromBoid(Boid boid, int number) {
        if (number < 0) {
            number = 0;
        }
        Swarm swarm = new Swarm();
        Random rand = new Random();
        for (int i = 0; i < number; i++) {
            Boid newBoid = boid.duplicate();
            newBoid.setPosition(new Vector2(rand.nextFloat() * BoidSimulation.WIDTH, rand.nextFloat()* BoidSimulation.HEIGHT));
            swarm.addBoid(newBoid);
        }
        return swarm;
    }
}

package xyz.sauerkraut.boids;

import java.util.LinkedList;
import java.util.List;

public class BoidFactory {

    //TODO Actual meaningful implementation
    public static SimpleBoid newGenericBoid() {
        return null;
    }

    public static Swarm swarmFromBoid(Boid boid, int number) {
        if (number < 0) {
            number = 0;
        }
        Swarm swarm = new Swarm();
        for (int i = 0; i < number; i++) {
            swarm.addBoid(boid.duplicate());
        }
        return swarm;
    }
}

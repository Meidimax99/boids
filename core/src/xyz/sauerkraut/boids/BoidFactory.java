package xyz.sauerkraut.boids;

import java.util.LinkedList;
import java.util.List;

public class BoidFactory {

    //TODO Actual meaningful implementation
    public static Boid newGenericBoid() {
        return null;
    }

    public static Swarm swarmFromBoid(Boid boid, int number) {
        if(number < 0) {
            number = 0;
        }

        List<Boid> boidList = new LinkedList<Boid>();
        for(int i = 0; i < number; i++) {
            break;
        }
        return new Swarm();
    }
}

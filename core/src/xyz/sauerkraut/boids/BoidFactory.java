package xyz.sauerkraut.boids;

import java.util.LinkedList;
import java.util.List;

public class BoidFactory {

    //TODO Actual meaningful implementation
    public static SimpleBoid newGenericBoid() {
        return null;
    }

    public static Swarm swarmFromBoid(SimpleBoid boid, int number) {
        if(number < 0) {
            number = 0;
        }

        List<SimpleBoid> boidList = new LinkedList<SimpleBoid>();
        for(int i = 0; i < number; i++) {
            break;
        }
        return new Swarm();
    }
}

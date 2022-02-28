package xyz.sauerkraut.boids;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class BoidFactory {

    public static SimpleSwarm swarmFromBoid(Boid boid, int number) {
        if (number < 0) {
            number = 0;
        }
        SimpleSwarm simpleSwarm = new SimpleSwarm();
        Random rand = new Random();
        for (int i = 0; i < number; i++) {
            Boid newBoid = boid.duplicate();
            newBoid.setPosition(new Vector2(rand.nextFloat() * BoidSimulation.WIDTH, rand.nextFloat()* BoidSimulation.HEIGHT));
            simpleSwarm.addBoid(newBoid);
        }
        return simpleSwarm;
    }
}

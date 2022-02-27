package xyz.sauerkraut.boids.decorators;

import com.badlogic.gdx.math.Vector2;
import sun.jvm.hotspot.ui.tree.FloatTreeNodeAdapter;
import xyz.sauerkraut.boids.Boid;
import xyz.sauerkraut.boids.BoidSimulation;

//TODO Better Name -> Boids with this decorator are entering the screen from the other side upon leaving the screen
public class Infinite extends BoidDecorator{

    private float threshold;

    public Infinite(Boid boid) {
        super(boid);
        Vector2 dim = boid.getDimensions();
        threshold = Float.max(dim.x,dim.y)/2;
    }

    public Infinite(Boid boid, float threshold) {
        super(boid);
        this.threshold = threshold;
    }

    private float modulus(float num, float upperBound) {
        upperBound += threshold*2;
        return ((((num+threshold) % upperBound) + upperBound) % upperBound)-threshold;
    }

    @Override
    public void updatePosition(float deltaTime) {
        super.updatePosition(deltaTime);
        Vector2 position = getPosition();
        setPosition(new Vector2(modulus(position.x, BoidSimulation.WIDTH), modulus(position.y, BoidSimulation.HEIGHT)));
    }

    @Override
    public Boid duplicate() {
        return new Infinite(this.getComponent().duplicate());
    }

}

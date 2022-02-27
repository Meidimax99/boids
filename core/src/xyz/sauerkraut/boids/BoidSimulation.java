package xyz.sauerkraut.boids;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import xyz.sauerkraut.boids.decorators.Infinite;
import xyz.sauerkraut.boids.decorators.RandomDirection;

public class BoidSimulation extends ApplicationAdapter {

    private OrthographicCamera camera;
    private Texture boidTexture;

    private Swarm swarm;

    private ActualSpriteBatch batch;

    public static final int WIDTH = 1600;
    public static final int HEIGHT = 1200;

    Sprite sprite;

    @Override
    public void create() {

        //Camera Setup
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1600 , 1200);

        //Textures
        boidTexture = new Texture(Gdx.files.internal("SimpleDirectionalBoid.png"));
        sprite = new Sprite(boidTexture);

        batch = new ActualSpriteBatch();

        Boid boid = new Infinite(new RandomDirection(new SimpleBoid(sprite), 100));

        swarm = BoidFactory.swarmFromBoid(boid, 100000);

    }

    @Override
    public void render() {
        ScreenUtils.clear(0f,0f,0f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        swarm.updateAll(Gdx.graphics.getDeltaTime());
        batch.begin();
        swarm.renderAll(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        boidTexture.dispose();
        batch.dispose();
    }
}

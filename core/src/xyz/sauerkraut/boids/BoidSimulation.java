package xyz.sauerkraut.boids;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BoidSimulation extends ApplicationAdapter {

    private OrthographicCamera camera;
    private Texture boid;

    private Swarm swarm = new Swarm();

    private SpriteBatch batch;

    private int width = 1600;
    private int height = 1200;

    Sprite sprite;

    @Override
    public void create() {

        //Camera Setup
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600 , 1200);

        //Textures
        boid = new Texture(Gdx.files.internal("SimpleDirectionalBoid.png"));
        sprite = new Sprite(boid);

        batch = new SpriteBatch();

        swarm.addBoid(new Boid(sprite, new Vector2(((float) width/2), (float) height / 2 ),new Vector2(20, 20)));
    }

    @Override
    public void render() {
        Random random = new Random();
        ScreenUtils.clear(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        swarm.updateAll(Gdx.graphics.getDeltaTime());

        batch.begin();
        swarm.renderAll(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        boid.dispose();
        batch.dispose();
    }
}

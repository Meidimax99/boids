package xyz.sauerkraut.boids;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import xyz.sauerkraut.boids.decorators.ConstantVelocityDecorator;
import xyz.sauerkraut.boids.decorators.FollowMouseDecorator;

import java.util.Random;

public class BoidSimulation extends ApplicationAdapter {

    private OrthographicCamera camera;
    private Texture boidTexture;

    private Swarm swarm;
    private Boid boid;

    private SpriteBatch batch;

    public static final int WIDTH = 1600;
    public static final int HEIGHT = 1200;

    Sprite sprite;

    private static ShapeRenderer debugRenderer;

    public static void drawDebugLine(Vector2 start, Vector2 end, int lineWidth, Color color, Matrix4 projectionMatrix) {
        Gdx.gl.glLineWidth(lineWidth);
        debugRenderer.setProjectionMatrix(projectionMatrix);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        debugRenderer.setColor(color);
        debugRenderer.line(start, end);
        debugRenderer.end();
        Gdx.gl.glLineWidth(1);
    }

    public static void drawDebugLine(Vector2 start, Vector2 end, Matrix4 projectionMatrix) {
        drawDebugLine(start,end,2,Color.RED,projectionMatrix);
    }

    @Override
    public void create() {

        //Camera Setup
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1600 , 1200);

        //Textures
        boidTexture = new Texture(Gdx.files.internal("SimpleDirectionalBoid.png"));
        sprite = new Sprite(boidTexture);

        batch = new SpriteBatch();
        boid = new SimpleBoid(sprite, new Vector2(((float) WIDTH/2), (float) HEIGHT / 2 ));

        swarm = BoidFactory.swarmFromBoid(boid, 1);
        //DEBUG
        debugRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        Random random = new Random();
        ScreenUtils.clear(0f,0f,0f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        //swarm.updateAll(Gdx.graphics.getDeltaTime());
        //boid.update(Gdx.graphics.getDeltaTime());
        swarm.updateAll(Gdx.graphics.getDeltaTime());
        batch.begin();
        //boid.render(batch);
        swarm.renderAll(batch);
        batch.end();

       Vector2 position = new Vector2(sprite.getX(), sprite.getY());
       Vector2 absOrigin = new Vector2(sprite.getX() + sprite.getOriginX(), sprite.getY() + sprite.getOriginY());
       Vector2 direction = new Vector2(sprite.getX()+boid.getVelocity().x, sprite.getY()+boid.getVelocity().y);
       drawDebugLine(new Vector2(0,0), position, camera.combined);
       drawDebugLine(position, absOrigin, 2, Color.GREEN, camera.combined);
       drawDebugLine(position, direction, 3, Color.BLUE, camera.combined);

    }

    @Override
    public void dispose() {
        boidTexture.dispose();
        batch.dispose();
    }
}

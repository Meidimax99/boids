package com.tictactoe.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.awt.*;
import java.util.Iterator;

public class MyGame extends ApplicationAdapter {

	private Texture dropImage;
	private Texture bucketImage;
	private Texture bucketFrontImage;
	private Array<Sound> dropSounds;
	private Music rainMusic;
	private BitmapFont font;

	private OrthographicCamera camera;
	private SpriteBatch batch;

	private Rectangle bucket;

	private Array<Rectangle> raindrops;

	private long lastDropTime;
	//Drops per second
	private final float dropRate = 10f;

	private final int dropSpeed = 100;

	private int score = 0;


	private void spawnRaindrop() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800-64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}

	void loadSounds() {
		char[] notes = {'C','D','E','F','G','A','B'};
		dropSounds = new Array<Sound>();
		for (char note : notes) {
			dropSounds.add(Gdx.audio.newSound(Gdx.files.internal("platsch"+note+".mp3")));
		}
	}
	@Override
	public void create () {

		font = new BitmapFont();
		font.setColor(1,0,0,1);
		// load the images for the droplet and the bucket, 64x64 pixels each
		dropImage = new Texture(Gdx.files.internal("drop.png"));
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));
		bucketFrontImage = new Texture(Gdx.files.internal("bucketFront.png"));

		// load the drop sound effect and the rain background "music"
		loadSounds();
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

		// start the playback of the background music immediately
		rainMusic.setLooping(true);
		rainMusic.play();

		//Camera Setup
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		batch = new SpriteBatch();

		//Geometry
		bucket = new Rectangle();

		bucket.width = 64;
		bucket.height = 64;

		bucket.x = camera.viewportWidth / 2 - (float) (bucket.width / 2);
		bucket.y = 20;

		//Drops
		raindrops = new Array<Rectangle>();



	}

	boolean dropIsInBucket(Rectangle drop) {
		float bucketCenter = bucket.x + bucket.width / 2;
		float dropCenter = drop.x + drop.width / 2;

		//How precisely should the drop be in the bucket: 0 -> Pixelperfect in center, 1 -> Center of Drop can be on edge of bucket
		float threshold = 1;
		float tolerance = bucket.width /2 * threshold;

		//X Axis
		boolean isInX = Math.abs(bucketCenter - dropCenter) <= tolerance;
		//y Axis -> is between ground offset and bucket height
		boolean isInY = drop.y > 20 && drop.y <= bucket.width / 2;
		return isInX && isInY;
	}

	boolean dropHitGround(Rectangle drop) {
		return drop.y <= 20;
	}

	void playRandomScoreSound() {
		int index = (int) (Math.random() * (dropSounds.size - 1));
		dropSounds.get(index).play();
	}

	void scored() {
		playRandomScoreSound();
		score++;
	}
	void updateRainDrops() {
		Iterator<Rectangle> iterator = raindrops.iterator();
		while(iterator.hasNext()) {
			Rectangle drop = iterator.next();
			drop.y = drop.y - (dropSpeed * Gdx.graphics.getDeltaTime());
			if(dropIsInBucket(drop)) {
				scored();
				iterator.remove();
			} else if (dropHitGround(drop)) {
				iterator.remove();
			}
		}
	}

	boolean isTimeToSpawnNewDrop() {
		long now = TimeUtils.nanoTime();
		float timeBetweenDrops = 1 / dropRate;
		return (now - lastDropTime) > (timeBetweenDrops * Math.pow(10, 9));
	}
	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();

		updateRainDrops();
		//Spawn new drops
		if(isTimeToSpawnNewDrop()) {
			spawnRaindrop();
		}

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//Draw back of the bucket
		batch.draw(bucketImage, bucket.x, bucket.y);
		//Draw Raindrops
		for (Rectangle drop : raindrops) {
			batch.draw(dropImage, drop.x, drop.y);
		}
		//Draw Front of the Bucket
		batch.draw(bucketFrontImage, bucket.x, bucket.y);

		font.draw(batch, "" + score, 10,20);
		batch.end();

		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = touchPos.x - (float) (64 / 2);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();
		if(bucket.x < 0 - bucket.width) bucket.x = camera.viewportWidth;
		if(bucket.x > camera.viewportWidth) bucket.x = 0 - bucket.width;
	}
	
	@Override
	public void dispose () {
		dropImage.dispose();
		bucketImage.dispose();
		bucketFrontImage.dispose();
		font.dispose();

		for(Sound sound: dropSounds) {
			sound.dispose();
		}
		batch.dispose();
		rainMusic.dispose();
	}
}

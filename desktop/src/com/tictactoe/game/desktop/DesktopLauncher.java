package com.tictactoe.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tictactoe.game.MyGame;
import xyz.sauerkraut.boids.BoidSimulation;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Boids";
		config.width = 1600;
		config.height = 1200;
		config.pauseWhenBackground = true;
		new LwjglApplication(new BoidSimulation(), config);
	}
}

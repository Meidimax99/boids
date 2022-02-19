package xyz.sauerkraut.boids;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ActualSpriteBatch extends SpriteBatch {

    //With Origin Adjustment
    public void draw(Sprite sprite) {
        draw(sprite, sprite.getX() - sprite.getOriginX(), sprite.getY() - sprite.getOriginY(), sprite.getOriginX(), sprite.getOriginY(), sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
    }
}

package puppy.code.Managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import puppy.code.Drops.ProyectilAbs;

public class HitBoxManager {
    public Rectangle hitBox;
    private float x;
    private float y;
    private float hitBoxPlusX;
    private float hitBoxPlusY;

    public HitBoxManager() {
        this.hitBox = new Rectangle();
    }

    public HitBoxManager(int x, int y) {
        this.hitBox = new Rectangle(x,y,64,64);
        this.x = x;
        this.y = y;
    }

    public void dibujarHitbox(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(this.hitBox.x, this.hitBox.y, this.hitBox.width, this.hitBox.height);
    }

    public void actualizarX(){
        this.hitBox.x += hitBoxPlusX;
    }
    public void actualizarY(){
        this.hitBox.y += hitBoxPlusY;
    }

    public void setX(float x){
        this.x = x;
        this.hitBox.x = (x+hitBoxPlusX);
    }

    public void setY(float y) {
        this.y = y;
        this.hitBox.y = (y+hitBoxPlusY);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getHitBoxPlusX() {
        return hitBoxPlusX;
    }

    public void setHitBoxPlusX(float hitBoxPlusX) {
        this.hitBoxPlusX = hitBoxPlusX;
    }
    public float getHitBoxPlusY() {
        return hitBoxPlusY;
    }

    public void setHitBoxPlusY(float hitBoxPlus) {
        this.hitBoxPlusY = hitBoxPlus;
    }




}

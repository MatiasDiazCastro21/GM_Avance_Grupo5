package puppy.code.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import puppy.code.Managers.AudioVisualManager;
import puppy.code.Managers.HitBoxManager;

public class Canasta {
    private HitBoxManager bucket;
    private MovimientoStrategy movimientoStrategy;
    private int vidas = 3;
    private int puntos = 0;
    private final int velx = 400;
    private boolean herido = false;
    private final int tiempoHeridoMax = 50;
    private int tiempoHerido;
    private boolean efectoCalavera = false;
    private long tiempoCalavera;
    private int cargarDash = 3;
    private int direccion;

    public Canasta() {
        //default
        movimientoStrategy = new MovimientoNormal();
        crear();
    }

    public int getVidas() {
        return vidas;
    }

    public int getPuntos() {
        return puntos;
    }

    public Rectangle getArea() {
        return bucket.hitBox;
    }

    public void sumarPuntos(int pp) {
        puntos += pp;
    }

    public void crear() {
        bucket = new HitBoxManager();
        float posX = (float)((800 / 2) - (AudioVisualManager.getCanastaTexture().getWidth() / 2));
        float posY = 20;

        bucket.setX(posX);
        bucket.setY(posY);

        bucket.hitBox.x = posX+5;
        bucket.hitBox.y = posY+15;
        bucket.setHitBoxPlusX(5);
        bucket.hitBox.width = AudioVisualManager.getCanastaTexture().getWidth()-10;
        bucket.hitBox.height = AudioVisualManager.getCanastaTexture().getHeight()-30;
    }

    public void dañar() {
        vidas--;
        herido = true;
        tiempoHerido = tiempoHeridoMax;
        AudioVisualManager.getHurtSound().play(0.05f);
    }

    public void sumarVida() {
        vidas++;
        tiempoHerido = tiempoHeridoMax;
    }

    public void dibujarHitbox(ShapeRenderer shapeRenderer) {
        bucket.dibujarHitbox(shapeRenderer);
    }

    public void dibujar(SpriteBatch batch) {
        if (!herido) {
            batch.draw(AudioVisualManager.getCanastaTexture(), bucket.getX(), bucket.getY());
        } else {
            direccion = 0;
            batch.draw(AudioVisualManager.getCanastaTexture(), bucket.getX(), bucket.getY() + MathUtils.random(-5, 5));
            tiempoHerido--;
            if (tiempoHerido <= 0) {
                herido = false;
            }
        }
    }

    public void setMovimientoStrategy(MovimientoStrategy movimientoStrategy){
        this.movimientoStrategy = movimientoStrategy;
    }

    public void actualizar(){
        movimientoStrategy.mover(this);

        if (bucket.hitBox.x < 0) {
            bucket.setX(0);
        }
        if (bucket.hitBox.x > 800 - bucket.hitBox.width) {
            bucket.setX(800 - bucket.hitBox.width);
        }

    }

    public void destruir() {
        AudioVisualManager.getCanastaTexture().dispose();
        AudioVisualManager.getHurtSound().dispose();
    }

    public boolean estaHerido() {
        return herido;
    }

    public void activarEfectoCalavera(long tiempoInicio) {
        this.efectoCalavera = true;
        this.tiempoCalavera = tiempoInicio;
    }

    public boolean efectoCalavera() {
        return efectoCalavera && TimeUtils.nanoTime() - tiempoCalavera < 10_000_000_000L;
    }

    public void desactivarEfectoCalavera() {
        this.efectoCalavera = false;
    }

    public long getTiempoCalavera() {
        return tiempoCalavera;
    }

    public void obtenerDash() {
        if (cargarDash < 3) {
            cargarDash++;
        }
    }

    public int getCargasDash() {
        return cargarDash;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setHerido(boolean herido) {
        this.herido = herido;
    }

    public void setTiempoHerido(int tiempoHerido) {
        this.tiempoHerido = tiempoHerido;
    }

    public void setEfectoCalavera(boolean efectoCalavera) {
        this.efectoCalavera = efectoCalavera;
    }

    public void setTiempoCalavera(long tiempoCalavera) {
        this.tiempoCalavera = tiempoCalavera;
    }

    public void setCargarDash(int cargarDash) {
        this.cargarDash = cargarDash;
    }


    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public int getDireccion(){
        return direccion;
    }

    public void setBucket(HitBoxManager bucket) {
        this.bucket = bucket;
    }

    public Texture getBucketImage() {
        return AudioVisualManager.getCanastaTexture();
    }

    public int getTiempoHeridoMax() {
        return tiempoHeridoMax;
    }

    public int getTiempoHerido() {
        return tiempoHerido;
    }

    public boolean isEfectoCalavera() {
        return efectoCalavera;
    }

    public int getVelx() {
        return velx;
    }

    public HitBoxManager getBucket() {
        return bucket;
    }
}

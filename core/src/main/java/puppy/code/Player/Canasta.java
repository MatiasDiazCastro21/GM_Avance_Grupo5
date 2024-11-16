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
import puppy.code.Managers.HitBoxManager;

public class Canasta {
    private HitBoxManager bucket;
    private Texture bucketImage;
    private Sound sonidoHerido;
    private int vidas = 1000;
    private int puntos = 0;
    private int velx = 400;
    private boolean herido = false;
    private int tiempoHeridoMax = 50;
    private int tiempoHerido;
    private boolean efectoCalavera = false;
    private long tiempoCalavera;
    private int cargarDash = 3;
    private static final int MAX_CARGAS_DASH = 3;
    private boolean tieneDash = false;
    private static final float DASH_DISTANCE = 190;
    private int direccion;
    private Sound sonidoDash;

    public Canasta(Texture tex, Sound ss) {
        bucketImage = tex;
        sonidoHerido = ss;
        sonidoDash = Gdx.audio.newSound(Gdx.files.internal("dashSound.mp3"));
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
        float posX = (float)((800 / 2) - (bucketImage.getWidth() / 2));
        float posY = 20;

        bucket.setX(posX);
        bucket.setY(posY);

        bucket.hitBox.x = posX+5;
        bucket.hitBox.y = posY+15;
        bucket.setHitBoxPlusX(5);
        bucket.hitBox.width = bucketImage.getWidth()-10;
        bucket.hitBox.height = bucketImage.getHeight()-30;
    }

    public void dañar() {
        vidas--;
        herido = true;
        tiempoHerido = tiempoHeridoMax;
        sonidoHerido.play(0.05f);
    }

    public void sumarVida() {
        vidas++;
        tiempoHerido = tiempoHeridoMax;
    }

    public void dibujarHitbox(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bucket.hitBox.x, bucket.hitBox.y, bucket.hitBox.width, bucket.hitBox.height);
    }

    public void dibujar(SpriteBatch batch) {
        if (!herido) {
            batch.draw(bucketImage, bucket.getX(), bucket.getY());
        } else {
            direccion = 0;
            batch.draw(bucketImage, bucket.getX(), bucket.getY() + MathUtils.random(-5, 5));
            tiempoHerido--;
            if (tiempoHerido <= 0) {
                herido = false;
            }
        }
    }

    public void actualizar() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            direccion = -1;
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                bucket.setX(bucket.getX() - velx * Gdx.graphics.getDeltaTime() * 2);
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)) {
                dash(); // Usar dash cuando Shift es presionado
            } else {
                bucket.setX(bucket.getX() - velx * Gdx.graphics.getDeltaTime());
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            direccion = 1;
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                bucket.setX(bucket.getX() + velx * Gdx.graphics.getDeltaTime() * 2);
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)) {
                dash(); // Usar dash cuando Shift es presionado
            } else {
                bucket.setX(bucket.getX() + velx * Gdx.graphics.getDeltaTime());
            }
        }

        // que no se salga de los bordes izq y der
        if (bucket.hitBox.x < 0) {
            bucket.setX(0);
        }
        if (bucket.hitBox.x > 800 - bucket.hitBox.width) {
            bucket.setX(800 - bucket.hitBox.width);
        }
    }

    public void destruir() {
        bucketImage.dispose();
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

    public void dash() {
        if (cargarDash > 0) { // Usar solo si hay cargas disponibles
            if (direccion != 0) {
                if (bucket.hitBox.x <= 0 && direccion == -1) {
                    bucket.setX(0);
                } else if (bucket.hitBox.x >= 800 - bucket.hitBox.width && direccion == 1) {
                    bucket.setX(800 - bucket.hitBox.width);
                } else {
                    if (!herido) {
                        bucket.setX(bucket.getX() + (DASH_DISTANCE * direccion));
                        bucket.setX(MathUtils.clamp(bucket.getX(), 0, 800 - bucket.hitBox.width));
                        sonidoDash.play(0.05f);
                        cargarDash--;
                    }
                }
                // Consumir una carga de dash
                if (cargarDash == 0) {
                    tieneDash = false; // Desactivar si se agotaron las cargas
                }
            }
        }
    }

    public void obtenerDash() {
        if (cargarDash < MAX_CARGAS_DASH) {
            cargarDash++;
            tieneDash = true;
        }
    }

    public int getCargasDash() {
        return cargarDash;
    }

    public int getMaxCargasDash() {
        return MAX_CARGAS_DASH;
    }
}

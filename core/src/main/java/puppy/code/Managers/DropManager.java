package puppy.code.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import puppy.code.Drops.*;
import puppy.code.Player.Canasta;

public class DropManager {
    private Array<ProyectilAbs> drops;
    private long lastDropTime;
    private AudioVisualManager aVM;
    /*
    private Texture manzana;
    private Texture bomba;
    private Texture vidaExtra;
    private Texture manzanaOro;
    private Texture calavera;
    private Texture dashTexture;
    private Sound vidaSound;
    private Sound puntosSound;
    private Sound scoreExtraSound;
    private Sound explosion;
    private Sound dashSound;
    */
    private int multiTiempo = 300;
    private double exponente = 1.0;

    public DropManager() {
        aVM = new AudioVisualManager();
    }

    public void crearConCanasta(Canasta c) {
        drops = new Array<>();
        crearDrop(c);
    }

    private void crearDrop(Canasta canasta) {
        int random = MathUtils.random(1, 100);

        if(canasta.efectoCalavera())
        {
            if (random < 2) {
                objetoEspecial();
            }
            else if (random < 65){
                drops.add(new Bomba(aVM.getBombaT(), aVM.getBombaS()));
            }
        }
        else {
            if (random < 70) {
                drops.add(new Manzana(aVM.getManzanaT(), aVM.getManzanaS()));

            } else if (random < 98) {
                drops.add(new Bomba(aVM.getBombaT(), aVM.getBombaS()));
            }
            else{
                objetoEspecial();
            }

        }
        lastDropTime = TimeUtils.nanoTime();


    }

    private void objetoEspecial(){
        int random = MathUtils.random(1, 100);
        if (random < 25) {
            drops.add(new VidaExtra(aVM.getVidaExtraT(), aVM.getVidaExtraS()));

        } else if (random < 50) {
            drops.add(new ScoreExtra(aVM.getManzanaOroT(), aVM.getManzanaOroS()));
        }
        else if (random < 75)
        {
            drops.add(new Dash(aVM.getDashT(), aVM.getDashS()));
        }
        else {
            drops.add(new Calavera(aVM.getCalaveraT(), aVM.getManzanaOroS()));
        }
    }

    public void dibujarHitbox(ShapeRenderer shapeRenderer) {
        for (ProyectilAbs drop : drops) {
            shapeRenderer.rect(drop.sprite.hitBox.x, drop.sprite.hitBox.y, drop.sprite.hitBox.width, drop.sprite.hitBox.height);
        }
    }

    public void cambioVelocidad(Canasta canasta){
        if (canasta.getPuntos() >= 100*(Math.pow(10,exponente))){
            exponente++;
            multiTiempo = 300 * (int)exponente;
        }
    }

    public boolean actualizarMovimiento(Canasta canasta) {
        if (TimeUtils.nanoTime() - lastDropTime > 100000000) crearDrop(canasta);

        if (!canasta.efectoCalavera()) {
            canasta.desactivarEfectoCalavera();
        }

        for (int i = 0; i < drops.size; i++) {
            ProyectilAbs drop = drops.get(i);
            //Prueba de cambio de velocidad exponencialmente
            cambioVelocidad(canasta);
            drop.sprite.setY(drop.sprite.getY()-(multiTiempo * Gdx.graphics.getDeltaTime()));

            //drop.sprite.setY(drop.sprite.getY()-(300 * Gdx.graphics.getDeltaTime()));

            if (drop.sprite.hitBox.y + 64 < 0) {
                drops.removeIndex(i);
            } else if (drop.sprite.hitBox.overlaps(canasta.getArea())) {
                drop.interactuarConCanasta(canasta);
                if (canasta.getVidas() <= 0) {
                    return false;
                }
                drops.removeIndex(i);
            }
        }
        return true;
    }

    public void dibujar(SpriteBatch batch) {
        for (ProyectilAbs drop : drops) {
            batch.draw(drop.textura, drop.sprite.getX(), drop.sprite.getY());
        }
    }

    public void destruir() {
        drops.clear();
        aVM.dispose();
    }

    public long getLastDropTime() {
        return lastDropTime;
    }

    public void setLastDropTime(long lastDropTime) {
        this.lastDropTime = lastDropTime;
    }



}

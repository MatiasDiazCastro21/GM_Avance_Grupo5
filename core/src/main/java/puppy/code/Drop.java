package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Drop{
    private Array<ProyectilAbs> drops;
    private long lastDropTime;
    private Texture manzana;
    private Texture bomba;
    private Texture vidaExtra;
    private Texture manzanaOro;
    private Texture calavera;
    private Sound vidaSound;
    private Sound puntosSound;
    private Sound scoreExtraSound;
    private Sound explosion;

    public Drop(Texture manzana,Texture manzanaOro, Texture bomba, Texture vidaExtra,Texture calavera, Sound puntosSound, Sound vidaSound,Sound scoreExtraSound,Sound explosion) {
        this.puntosSound = puntosSound;
        this.vidaSound = vidaSound;
        this.manzana = manzana;
        this.manzanaOro = manzanaOro;
        this.calavera = calavera;
        this.bomba = bomba;
        this.vidaExtra = vidaExtra;
        this.scoreExtraSound = scoreExtraSound;
        this.explosion = explosion;
    }

    public void crearConCanasta(Canasta c) {
        drops = new Array<>();
        crearDrop(c);
    }

    private void crearDrop(Canasta canasta) {
        int random = MathUtils.random(1, 100);

        if(canasta.efectoCalavera())
        {
            if (random < 40) {
                drops.add(new Manzana(manzana, puntosSound));

            } else if (random < 99) {
                drops.add(new Bomba(bomba, explosion));

            }
            else{
                objetoEspecial();
            }
        }
        else {
            if (random < 65) {
                drops.add(new Manzana(manzana, puntosSound));

            } else if (random < 98) {
                drops.add(new Bomba(bomba, explosion));
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
            drops.add(new VidaExtra(vidaExtra, vidaSound));

        } else if (random < 50) {
            drops.add(new ScoreExtra(manzanaOro, scoreExtraSound));
        }
        else if (random < 75)
        {
            drops.add(new Dash(new Texture(Gdx.files.internal("Dash.png")),explosion));
        }
        else {
            drops.add(new Calavera(calavera, scoreExtraSound));

        }
    }

    public boolean actualizarMovimiento(Canasta canasta) {
        if (TimeUtils.nanoTime() - lastDropTime > 100000000) crearDrop(canasta);

        if (!canasta.efectoCalavera()) {
            canasta.desactivarEfectoCalavera();
        }

        for (int i = 0; i < drops.size; i++) {
            ProyectilAbs drop = drops.get(i);
            drop.hitBox.y -= 300 * Gdx.graphics.getDeltaTime();

            if (drop.hitBox.y + 64 < 0) {
                drops.removeIndex(i);
            } else if (drop.hitBox.overlaps(canasta.getArea())) {
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
            batch.draw(drop.textura, drop.hitBox.x, drop.hitBox.y);
        }
    }

    public void destruir() {
        puntosSound.dispose();
    }



}

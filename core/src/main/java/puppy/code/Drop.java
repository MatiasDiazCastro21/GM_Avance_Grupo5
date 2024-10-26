package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Drop extends Create {
    private Array<ProyectilAbs> drops;
    private long lastDropTime;
    private Texture manzana;
    private Texture bomba;
    private Texture vidaExtra;
    private Texture manzanaOro;
    private Sound vidaSound;
    private Sound dropSound;

    public Drop(Texture manzana,Texture manzanaOro, Texture bomba, Texture vidaExtra, Sound ss, Sound vidaSound) {
        dropSound = ss;
        this.vidaSound = vidaSound;
        this.manzana = manzana;
        this.manzanaOro = manzanaOro;
        this.bomba = bomba;
        this.vidaExtra = vidaExtra;
    }

    @Override
    public void crear() {
        drops = new Array<>();
        crearDrop();
    }

    private void crearDrop() {
        int random = MathUtils.random(1, 100);
        if (random < 70) {
            drops.add(new Manzana(manzana, dropSound));
        } else if (random < 99) {
            drops.add(new Bomba(bomba, dropSound));
        } else {
            random = MathUtils.random(1,10);
            if(random < 5)
            {
                drops.add(new VidaExtra( vidaExtra, vidaSound));
            }
            else{
                drops.add(new ScoreExtra(manzanaOro,vidaSound));
            }
        }

        lastDropTime = TimeUtils.nanoTime();
    }

    public boolean actualizarMovimiento(Canasta canasta) {
        if (TimeUtils.nanoTime() - lastDropTime > 100000000) crearDrop();

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

    @Override
    public void dibujar(SpriteBatch batch) {
        for (ProyectilAbs drop : drops) {
            batch.draw(drop.textura, drop.hitBox.x, drop.hitBox.y);
        }
    }

    public void destruir() {
        dropSound.dispose();
    }

    public void pausar() {}

    public void continuar() {}

    @Override
    public void actualizar() {}
}

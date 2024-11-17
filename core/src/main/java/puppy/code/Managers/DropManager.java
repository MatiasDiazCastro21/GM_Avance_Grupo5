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
    //Posible cambio de lugar
    private ProbabilityManager pM;

    public DropManager() {
        pM = new ProbabilityManager();
    }

    public void crearConCanasta(Canasta c) {
        drops = new Array<>();
        crearDrop(c);
    }

    private void crearDrop(Canasta canasta) {
        ProyectilAbs drop = pM.getDrop(canasta.efectoCalavera());
        drops.add(drop);
        lastDropTime = TimeUtils.nanoTime();
    }

    public void dibujarHitbox(ShapeRenderer shapeRenderer) {
        for (ProyectilAbs drop : drops) {
            drop.sprite.dibujarHitbox(shapeRenderer);
        }
    }

    public boolean actualizarMovimiento(Canasta canasta) {
        float movimiento;
        if (TimeUtils.nanoTime() - lastDropTime > 100000000) crearDrop(canasta);

        if (!canasta.efectoCalavera()) {
            canasta.desactivarEfectoCalavera();
        }

        for (int i = 0; i < drops.size; i++) {
            ProyectilAbs drop = drops.get(i);
            movimiento = drop.sprite.getY()-(300 * Gdx.graphics.getDeltaTime());
            if (drop.interactuar(canasta, movimiento)) {
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
        pM.dispose();
    }

    public long getLastDropTime() {
        return lastDropTime;
    }

    public void setLastDropTime(long lastDropTime) {
        this.lastDropTime = lastDropTime;
    }



}

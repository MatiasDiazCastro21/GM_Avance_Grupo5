package puppy.code.Drops;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import puppy.code.Player.Canasta;

public class Calavera extends ProyectilAbs {

    private long tiempoInicio;

    public Calavera(Texture textura, Sound sonido)
    {
        super(textura,sonido);
        sprite.hitBox.width -= 20;
        sprite.setHitBoxPlusX(8);
        sprite.actualizarX();
        sprite.hitBox.height -= 12;
        sprite.setHitBoxPlusY(6);
        sprite.actualizarY();
    }

    @Override
    public void interactuarConCanasta(Canasta canasta) {
        tiempoInicio = TimeUtils.nanoTime();
        canasta.activarEfectoCalavera(tiempoInicio);
    }
    public void sonido()
    {
        sonido.play(0.05f);
    }



}

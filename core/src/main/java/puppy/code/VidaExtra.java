package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class VidaExtra extends ProyectilAbs {
    public VidaExtra(Texture textura, Sound sonido) {
        super(textura, sonido);
    }

    @Override
    public void interactuarConCanasta(Canasta canasta) {
        canasta.sumarVida();
        sonido.play(0.025f);
    }
}

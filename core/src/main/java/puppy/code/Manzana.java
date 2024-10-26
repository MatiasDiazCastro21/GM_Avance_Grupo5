package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Manzana extends ProyectilAbs {
    public Manzana(Texture textura, Sound sonido) {
        super(textura, sonido);
    }

    @Override
    public void interactuarConCanasta(Canasta canasta) {
        canasta.sumarPuntos(10);
        sonido.play(0.05f);
    }

}

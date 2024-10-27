package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Dash extends ProyectilAbs{
    public Dash(Texture textura, Sound sonido) {
        super(textura, sonido);
    }

    @Override
    public void interactuarConCanasta(Canasta canasta) {
        canasta.obtenerDash();
        sonido.play(0.05f);
    }


}


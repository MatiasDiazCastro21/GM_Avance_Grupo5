package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class ScoreExtra extends ProyectilAbs{

    public ScoreExtra(Texture textura, Sound sonido) {
        super(textura, sonido);
    }

    @Override
    public void interactuarConCanasta(Canasta canasta) {
        canasta.sumarPuntos(1000);
        sonido.play(0.05f);
    }

}

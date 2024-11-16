package puppy.code.Drops;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import puppy.code.Player.Canasta;

public class ScoreExtra extends ProyectilAbs {

    public ScoreExtra(Texture textura, Sound sonido) {
        super(textura, sonido);
        sprite.hitBox.width -= 12;
        sprite.setHitBoxPlusX(8);
        sprite.actualizarX();
        sprite.hitBox.height -= 12;
        sprite.setHitBoxPlusY(0);
        sprite.actualizarY();
    }

    @Override
    public void interactuarConCanasta(Canasta canasta) {
        canasta.sumarPuntos(1000);
    }
    public void sonido ()
    {
        sonido.play(0.05f);
    }

}

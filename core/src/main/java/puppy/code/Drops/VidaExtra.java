package puppy.code.Drops;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import puppy.code.Player.Canasta;

public class VidaExtra extends ProyectilAbs {
    public VidaExtra(Texture textura, Sound sonido) {
        super(textura, sonido);
        sprite.hitBox.width += 7;
        sprite.setHitBoxPlusX(0);
        sprite.actualizarX();
    }

    @Override
    public void interactuarConCanasta(Canasta canasta) {
        canasta.sumarVida();
    }

    public void sonido()
    {
        sonido.play(0.025f);
    }
}

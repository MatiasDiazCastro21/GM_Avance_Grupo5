package puppy.code.Drops;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import puppy.code.Player.Canasta;

public class Manzana extends ProyectilAbs {
    public Manzana(Texture textura, Sound sonido) {
        super(textura, sonido);
        sprite.hitBox.width -= 12;
        sprite.setHitBoxPlusX(12);
        sprite.hitBox.height -= 20;
        sprite.setHitBoxPlusY(2);
        sprite.actualizarY();
    }

    @Override
    public void interactuarConCanasta(Canasta canasta) {
        canasta.sumarPuntos(10);
    }

    public void sonido ()
    {
        sonido.play(0.05f);
    }

}

package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Bomba extends ProyectilAbs {
    public Bomba(Texture textura, Sound sonido) {
        super(textura, sonido);
        sprite.hitBox.width -= 20;
        sprite.setHitBoxPlusX(0);
        sprite.hitBox.height -= 20;
        sprite.setHitBoxPlusY(5);
        sprite.actualizarY();
    }
    @Override
    public void interactuarConCanasta(Canasta canasta) {
        canasta.dañar();
        sonido.play(0.05f);

    }


}

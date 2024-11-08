package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Dash extends ProyectilAbs{
    public Dash(Texture textura, Sound sonido) {
        super(textura, sonido);
        sprite.hitBox.width -= 0;
        sprite.setHitBoxPlusX(0);
        sprite.hitBox.height -= 2;
        sprite.setHitBoxPlusY(5);
        sprite.actualizarY();
    }

    @Override
    public void interactuarConCanasta(Canasta canasta) {
        canasta.obtenerDash();
        sonido.play(0.25f);
    }


}


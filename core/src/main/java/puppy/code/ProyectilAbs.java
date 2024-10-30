package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public abstract class ProyectilAbs {
    public Rectangle hitBox;
    public Texture textura;
    public Sound sonido;

    //Este constructor no es para instanciar la clase, es para llamarla con super()
    public ProyectilAbs(Texture textura, Sound sonido) {
        this.hitBox = new Rectangle(MathUtils.random(0, 800-64),480,64,64);
        this.textura = textura;
        this.sonido = sonido;
    }
    public abstract void interactuarConCanasta(Canasta canasta);
}

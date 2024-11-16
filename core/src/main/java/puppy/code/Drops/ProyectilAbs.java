package puppy.code.Drops;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import puppy.code.Player.Canasta;
import puppy.code.Managers.HitBoxManager;

public abstract class ProyectilAbs {
    public Texture textura;
    public Sound sonido;
    public HitBoxManager sprite;

    //Este constructor no es para instanciar la clase, es para llamarla con super()
    public ProyectilAbs(Texture textura, Sound sonido) {
        int PosXOg = MathUtils.random(0, 800-64);
        int PosYOg = 480;
        this.sprite = new HitBoxManager(PosXOg,PosYOg);
        this.textura = textura;
        this.sonido = sonido;
    }

    public abstract void interactuarConCanasta(Canasta canasta);



}

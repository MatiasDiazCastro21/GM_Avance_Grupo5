package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

public class Calavera extends ProyectilAbs{

    private long tiempoInicio;

    public Calavera(Texture textura, Sound sonido)
    {
        super(textura,sonido);
    }

    @Override
    public void interactuarConCanasta(Canasta canasta) {
        tiempoInicio = TimeUtils.nanoTime();
        canasta.activarEfectoCalavera(tiempoInicio);
    }



}

package puppy.code;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Create {
    public abstract void crear();
    public abstract void actualizar();
    public abstract void dibujar(SpriteBatch batch);
    public abstract void destruir();
}

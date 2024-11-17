package puppy.code.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MovimientoNormal implements MovimientoStrategy{
    @Override
    public void mover(Canasta canasta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            canasta.setDireccion(-1);
            canasta.getBucket().setX(canasta.getBucket().getX() - canasta.getVelx() * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            canasta.setDireccion(1);
            canasta.getBucket().setX(canasta.getBucket().getX() + canasta.getVelx() * Gdx.graphics.getDeltaTime());
        }
    }
}

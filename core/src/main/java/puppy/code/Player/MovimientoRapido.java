package puppy.code.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MovimientoRapido implements MovimientoStrategy{
    @Override
    public void mover(Canasta canasta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            canasta.getBucket().setX(canasta.getBucket().getX() - canasta.getVelx() * Gdx.graphics.getDeltaTime() * 2);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            canasta.getBucket().setX(canasta.getBucket().getX() + canasta.getVelx() * Gdx.graphics.getDeltaTime() * 2);
        }
    }
}

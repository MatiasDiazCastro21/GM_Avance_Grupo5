package puppy.code.Player;

import com.badlogic.gdx.math.MathUtils;

public class MovimientoConDash implements MovimientoStrategy{
    @Override
    public void mover(Canasta canasta) {
        if (canasta.getCargasDash() > 0) { // Usar solo si hay cargas disponibles
            if (canasta.getDireccion() != 0) {
                if (canasta.getBucket().hitBox.x <= 0 && canasta.getDireccion() == -1) {
                    canasta.getBucket().setX(0);
                } else if (canasta.getBucket().hitBox.x >= (800 - (canasta.getBucket().hitBox.width)) && canasta.getDireccion() == 1) {
                    canasta.getBucket().setX(800 - canasta.getBucket().hitBox.width);
                } else {
                    if (!canasta.estaHerido()) {
                        canasta.getBucket().setX(canasta.getBucket().getX() + (canasta.getDASH_DISTANCE() * canasta.getDireccion()));
                        canasta.getBucket().setX(MathUtils.clamp(canasta.getBucket().getX(), 0, 800 - canasta.getBucket().hitBox.width));
                        canasta.getSonidoHerido().play(0.05f);
                        canasta.setCargarDash(canasta.getCargasDash() - 1);
                    }
                }
                // Consumir una carga de dash
                if (canasta.getCargasDash() == 0) {
                    canasta.setTieneDash(false); // Desactivar si se agotaron las cargas
                }
            }
        }
    }
}

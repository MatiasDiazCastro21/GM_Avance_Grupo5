package puppy.code.Managers;

import com.badlogic.gdx.math.MathUtils;
import puppy.code.Drops.*;

public class ProbabilityManager {
    private ProyectilAbs drop;
    private final AudioVisualManager aVM;

    public ProbabilityManager() {
        this.drop = null;
        this.aVM = new AudioVisualManager();
    }

    public ProyectilAbs getDrop(boolean efectoCalavera) {
        int random = MathUtils.random(1, 100);
        if (efectoCalavera) {
            if (random < 15) {
                getDropEspecial();
            }
            else if (random <= 100) {
                drop = new Bomba(aVM.getBombaT(), aVM.getBombaS());
            }
        }
        else {
            if (random < 65) {
                drop = new Manzana(aVM.getManzanaT(), aVM.getManzanaS());
            }
            else if (random < 98) {
                drop = new Bomba(aVM.getBombaT(), aVM.getBombaS());
            }
            else {
                getDropEspecial();
            }
        }
        return drop;

    }

    private void getDropEspecial(){
        int random = MathUtils.random(1, 100);
        if (random < 25) {
            drop = new VidaExtra(aVM.getVidaExtraT(), aVM.getVidaExtraS());
        }
        else if (random < 50) {
            drop = new ScoreExtra(aVM.getManzanaOroT(), aVM.getManzanaOroS());
        }
        else if (random < 75){
            drop = new Dash(aVM.getDashT(), aVM.getDashS());
        }
        else {
            drop = new Calavera(aVM.getCalaveraT(), aVM.getCalaveraS());
        }
    }

    public void dispose() {
        aVM.dispose();
    }
}

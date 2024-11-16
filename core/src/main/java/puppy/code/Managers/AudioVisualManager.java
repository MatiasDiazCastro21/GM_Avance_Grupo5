package puppy.code.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class AudioVisualManager {
    //Texturas
    private Texture manzanaT;
    private Texture bombaT;
    private Texture vidaExtraT;
    private Texture manzanaOroT;
    private Texture calaveraT;
    private Texture dashT;

    //Sonidos
    private Sound vidaExtraS;
    private Sound manzanaS;
    private Sound manzanaOroS;
    private Sound bombaS;
    private Sound dashS;

    private void cargarAssets() {
        manzanaT = new Texture(Gdx.files.internal("Manzana.png"));
        bombaT = new Texture(Gdx.files.internal("Bomba.png"));
        vidaExtraT = new Texture(Gdx.files.internal("corazon.png"));
        manzanaOroT = new Texture(Gdx.files.internal("ManzanaOro.png"));
        calaveraT = new Texture(Gdx.files.internal("calavera.png"));
        dashT = new Texture(Gdx.files.internal("dash.png"));

        manzanaS = Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"));
        vidaExtraS = Gdx.audio.newSound(Gdx.files.internal("vidaExtra.mp3"));
        manzanaOroS = Gdx.audio.newSound(Gdx.files.internal("scoreExtra.mp3"));
        bombaS = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
        dashS = Gdx.audio.newSound(Gdx.files.internal("SonidoDash.mp3"));
    }

    public AudioVisualManager() {
        cargarAssets();
    }

    //Getters de texturas
    public Texture getManzanaT() {
        return manzanaT;
    }

    public Texture getBombaT() {
        return bombaT;
    }

    public Texture getVidaExtraT() {
        return vidaExtraT;
    }

    public Texture getManzanaOroT() {
        return manzanaOroT;
    }

    public Texture getCalaveraT() {
        return calaveraT;
    }

    public Texture getDashT() {
        return dashT;
    }

    //Getters de sonidos
    public Sound getVidaExtraS() {
        return vidaExtraS;
    }

    public Sound getManzanaS() {
        return manzanaS;
    }

    public Sound getManzanaOroS() {
        return manzanaOroS;
    }

    public Sound getBombaS() {
        return bombaS;
    }

    public Sound getDashS() {
        return dashS;
    }

    public void dispose() {
        manzanaT.dispose();
        bombaT.dispose();
        vidaExtraT.dispose();
        manzanaOroT.dispose();
        calaveraT.dispose();
        dashT.dispose();

        manzanaS.dispose();
        vidaExtraS.dispose();
        manzanaOroS.dispose();
        bombaS.dispose();
        dashS.dispose();
    }

}

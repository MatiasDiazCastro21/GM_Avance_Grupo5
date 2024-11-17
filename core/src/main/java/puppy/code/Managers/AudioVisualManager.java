package puppy.code.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class AudioVisualManager {
    //Texturas drops
    private Texture manzanaT;
    private Texture bombaT;
    private Texture vidaExtraT;
    private Texture manzanaOroT;
    private Texture calaveraT;
    private Texture dashT;

    //Sonidos drops
    private Sound vidaExtraS;
    private Sound manzanaS;
    private Sound manzanaOroS;
    private Sound bombaS;
    private Sound dashS;
    private Sound calaveraS;

    //Sonidos canasta
    private static Sound hurtSound;
    private static Sound dashSound;

    //Textura canasta
    private static Texture canastaTexture;

    private void cargarAssets() {
        //Cargar texturas drops
        manzanaT = new Texture(Gdx.files.internal("Manzana.png"));
        bombaT = new Texture(Gdx.files.internal("Bomba.png"));
        vidaExtraT = new Texture(Gdx.files.internal("corazon.png"));
        manzanaOroT = new Texture(Gdx.files.internal("ManzanaOro.png"));
        calaveraT = new Texture(Gdx.files.internal("calavera.png"));
        dashT = new Texture(Gdx.files.internal("dash.png"));

        //Cargar sonidos drops
        manzanaS = Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"));
        vidaExtraS = Gdx.audio.newSound(Gdx.files.internal("vidaExtra.mp3"));
        manzanaOroS = Gdx.audio.newSound(Gdx.files.internal("scoreExtra.mp3"));
        bombaS = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
        dashS = Gdx.audio.newSound(Gdx.files.internal("SonidoDash.mp3"));
        calaveraS = Gdx.audio.newSound(Gdx.files.internal("sonidoCalavera.mp3"));
    }

    public AudioVisualManager() {
        cargarAssets();
    }
    //Getters de sonidos canasta
    public static Sound getHurtSound() {
        if (hurtSound == null) {
            hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurtSound.mp3"));
        }
        return hurtSound;
    }

    public static Sound getDashSound() {
        if (dashSound == null) {
            dashSound = Gdx.audio.newSound(Gdx.files.internal("dashSound.mp3"));
        }
        return dashSound;
    }

    //Getter de textura canasta

    public static Texture getCanastaTexture() {
        if (canastaTexture == null) {
            canastaTexture = new Texture(Gdx.files.internal("canasta.png"));
        }
        return canastaTexture;
    }

    //Getters de texturas drops
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

    //Getters de sonidos drops
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

    public Sound getCalaveraS() {
        return calaveraS;
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
        calaveraS.dispose();
    }

}

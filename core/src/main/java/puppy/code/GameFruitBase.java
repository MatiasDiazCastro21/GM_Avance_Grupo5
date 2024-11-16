package puppy.code;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import puppy.code.Screens.MainMenuScreen;

public class GameFruitBase extends Game {
    private static GameFruitBase instance;
    private SpriteBatch batch;
    private BitmapFont font;
    private int higherScore;
    private Music music;
    private Texture fondo;

    private GameFruitBase() {}

    //Patron singleton
    public static GameFruitBase getIns() {
        if (instance == null) {
            instance = new GameFruitBase();
        }
        return instance;
    }

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(); // use libGDX's default Arial font
        music = Gdx.audio.newMusic(Gdx.files.internal("MenuMusic.mp3"));
        fondo = new Texture(Gdx.files.internal("fondo.png"));
        music.setLooping(true);
        music.setVolume(0.025f);
        this.setScreen(new MainMenuScreen());
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
        music.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }
    public int getHigherScore() {
        return higherScore;
    }
    public void setHigherScore(int higherScore) {
        this.higherScore = higherScore;
    }
    public Music getMusic() {
        return music;
    }
    public Texture getFondo(){
        return fondo;
    }
}

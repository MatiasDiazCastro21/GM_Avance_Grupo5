package puppy.code.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import puppy.code.Player.Canasta;
import puppy.code.Managers.DropManager;
import puppy.code.GameFruitBase;
import puppy.code.Player.MovimientoConDash;
import puppy.code.Player.MovimientoNormal;
import puppy.code.Player.MovimientoRapido;

public class GameScreen implements Screen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Canasta canasta;
    private DropManager dropManager;
    private Texture fondo;
    private Texture fondoCalavera;
    private Music music;
    private Music musicCalaca;
    private ShapeRenderer shapeRenderer;
    private boolean hitBoxOn = false;

    //boolean activo = true;
    public GameScreen() {
        this.batch = GameFruitBase.getIns().getBatch();
        this.font = GameFruitBase.getIns().getFont();
        shapeRenderer = new ShapeRenderer();
        cargarAssets();
        dropManager = new  DropManager();
        music.setLooping(true);
        music.setVolume(0.05f);
        musicCalaca.setLooping(true);
        musicCalaca.setVolume(0.05f);
        //camara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        //creacion de "jugador"
        canasta.crear();

        //creacion de dropManager
        dropManager.crearConCanasta(canasta);
        music.play();
    }

    private void cargarAssets(){
        Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurtSound.mp3"));
        Texture canastaTexture = new Texture(Gdx.files.internal("canasta.png"));

        fondo = new Texture(Gdx.files.internal("Fondo_juego.png"));
        fondoCalavera = new Texture(Gdx.files.internal("FondoCalavera.png"));
        music = Gdx.audio.newMusic(Gdx.files.internal("Music.mp3"));
        musicCalaca = Gdx.audio.newMusic(Gdx.files.internal("MusicaCalavera.mp3"));

        canasta = new Canasta(canastaTexture,hurtSound);
    }

    private void dibujarComponentes(){
        dropManager.dibujar(batch);
        font.draw(batch, "Puntos totales: " + canasta.getPuntos(), 5, 475);
        font.draw(batch, "Vidas : " + canasta.getVidas(), 650, 475);
        font.draw(batch, "HighScore : " + GameFruitBase.getIns().getHigherScore(), camera.viewportWidth/2-50, 475);
        font.draw(batch, "Dash: " + canasta.getCargasDash() + "/" + canasta.getMaxCargasDash(), 5, 450);
        canasta.dibujar(batch);
        batch.end();
        if(hitBoxOn){
            dibujarHitbox();
        }
        batch.begin();
    }

    private void movimientoCanasta(){
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            canasta.setMovimientoStrategy(new MovimientoRapido());
        }else{
            canasta.setMovimientoStrategy(new MovimientoNormal());
            if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT) && canasta.getCargasDash() > 0){
                canasta.setMovimientoStrategy(new MovimientoConDash());
            }
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        //actualizar matrices de la cámara
        camera.update();
        //actualizar
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        //Strategy
        movimientoCanasta();

        if (canasta.efectoCalavera()) {
            music.pause();
            if (!musicCalaca.isPlaying()) {
                musicCalaca.play();
            }
            batch.draw(fondoCalavera, 0, 0, camera.viewportWidth, camera.viewportHeight);
            long tiempoRestante = 10 - (TimeUtils.nanoTime() - canasta.getTiempoCalavera()) / 1_000_000_000L;
            font.draw(batch, "Efecto Calavera: " + tiempoRestante + "s", camera.viewportWidth/2-105, 400);
        }


        else{
            batch.draw(fondo, 0, 0, camera.viewportWidth, camera.viewportHeight);
            musicCalaca.stop();
        }
        dibujarComponentes();
        if (!canasta.estaHerido()) {
            if (!music.isPlaying() && !canasta.efectoCalavera()) {
                music.play();
            }
            // movimiento del tarro desde teclado
            canasta.actualizar();
            // caida de la dropManager
            if (!dropManager.actualizarMovimiento(canasta)) {
            //actualizar HigherScore
                if (GameFruitBase.getIns().getHigherScore()< canasta.getPuntos()){
                    GameFruitBase.getIns().setHigherScore(canasta.getPuntos());
                }
                music.stop();
                musicCalaca.stop();
                canasta.destruir();
                GameFruitBase.getIns().setScreen(new GameOverScreen());
                dispose();
            }
        }
        else{
            music.pause();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            pause();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F3)) {
            hitBoxOn = !hitBoxOn;
        }
        batch.end();
    }

    private void dibujarHitbox() {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 0, 1, 1);
        canasta.dibujarHitbox(shapeRenderer);
        dropManager.dibujarHitbox(shapeRenderer);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {

    }
    @Override
    public void show() {
        // continuar con sonido de dropManager
        music.play();
    }
    @Override
    public void hide() {
    }

    @Override
    public void pause() {
        music.pause();
        musicCalaca.pause();
        GameFruitBase.getIns().setScreen(new PausaScreen( this));
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        canasta.destruir();
        dropManager.destruir();
    }

    public void setHitBoxON(boolean hitBoxOn) {
        this.hitBoxOn = hitBoxOn;
    }

    public boolean getHitBoxON() {
        return hitBoxOn;
    }

}

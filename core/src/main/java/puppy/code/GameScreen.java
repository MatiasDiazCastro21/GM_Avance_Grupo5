package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
	final GameLluviaMenu game;
    private OrthographicCamera camera;
	private SpriteBatch batch;
	private BitmapFont font;
	private Canasta canasta;
	private Drop drop;
    private Texture fondo;
    private Music music;

	//boolean activo = true;
	public GameScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        cargarAssets();
        music.setLooping(true);
        music.setVolume(0.05f);
        //camara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        //creacion de "jugador"
        canasta.crear();

        //creacion de drop y inicio de musica
        drop.crear();
        music.play();


	}

    private void cargarAssets(){
        Sound explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
        canasta = new Canasta(new Texture(Gdx.files.internal("canasta.png")),explosion);
        Texture score = new Texture(Gdx.files.internal("Manzana.png"));
        Texture bomba = new Texture(Gdx.files.internal("Bomba.png"));
        Texture vidaExtra = new Texture(Gdx.files.internal("corazon.png"));
        Texture scoreExtra = new Texture(Gdx.files.internal("ManzanaOro.png"));
        Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"));
        Sound sonidoVida = Gdx.audio.newSound(Gdx.files.internal("vidaExtra.mp3"));
        fondo = new Texture(Gdx.files.internal("Fondo_juego.png"));
        music = Gdx.audio.newMusic(Gdx.files.internal("Music.mp3"));
        drop = new Drop(score,scoreExtra, bomba, vidaExtra,dropSound,sonidoVida);
    }

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		//actualizar matrices de la cámara
		camera.update();
		//actualizar
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
        batch.draw(fondo, 0, 0, camera.viewportWidth, camera.viewportHeight);
		//dibujar textos
		font.draw(batch, "Puntos totales: " + canasta.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + canasta.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);


		if (!canasta.estaHerido()) {
            if (!music.isPlaying()) {
                music.play();
            }
			// movimiento del tarro desde teclado
	        canasta.actualizar();
			// caida de la drop
	       if (!drop.actualizarMovimiento(canasta)) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()< canasta.getPuntos())
	    		  game.setHigherScore(canasta.getPuntos());
	    	  //ir a la ventana de finde juego y destruir la actual
              music.stop();
	    	  game.setScreen(new GameOverScreen(game));
	    	  dispose();
	       }
		}
        else{
            music.pause();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            pause();
        }
		canasta.dibujar(batch);
		drop.dibujar(batch);

		batch.end();

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	  // continuar con sonido de drop
	  drop.continuar();
      music.play();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		drop.pausar();
        music.pause();
		game.setScreen(new PausaScreen(game, this));
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
      canasta.destruir();
      drop.destruir();

	}

}

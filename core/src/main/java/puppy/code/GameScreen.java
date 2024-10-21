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
	private Proyectil proyectil;

    private Texture fondo;

	//boolean activo = true;
	public GameScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		  // load the images for the droplet and the bucket, 64x64 pixels each
		  Sound explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		  canasta = new Canasta(new Texture(Gdx.files.internal("canasta.png")),explosion);

	      // load the drop sound effect and the rain background "music"
         Texture fruta = new Texture(Gdx.files.internal("Fruta.png"));
         Texture bomba = new Texture(Gdx.files.internal("Bomba.png"));

         Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"));

	     Music musicaDeFondo = Gdx.audio.newMusic(Gdx.files.internal("pou.mp3"));
         proyectil = new Proyectil(fruta, bomba, dropSound, musicaDeFondo);

         fondo = new Texture(Gdx.files.internal("Fondo_juego.png"));

	      // camera
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 480);
	      batch = new SpriteBatch();
	      // creacion del tarro
	      canasta.crear();

	      // creacion de la proyectil
	      proyectil.crear();
	}
	@Override
	public void render(float delta) {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);

		//actualizar matrices de la cámara
		camera.update();
		//actualizar
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
        batch.draw(fondo, 0, 0, camera.viewportWidth, camera.viewportHeight);
		//dibujar textos
		font.draw(batch, "Gotas totales: " + canasta.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + canasta.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);


		if (!canasta.estaHerido()) {
			// movimiento del tarro desde teclado
	        canasta.actualizar();
			// caida de la proyectil
	       if (!proyectil.actualizarMovimiento(canasta)) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()< canasta.getPuntos())
	    		  game.setHigherScore(canasta.getPuntos());
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  game.setScreen(new GameOverScreen(game));
	    	  dispose();
	       }
		}
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            pause();
        }
		canasta.dibujar(batch);
		proyectil.dibujar(batch);

		batch.end();

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	  // continuar con sonido de proyectil
	  proyectil.continuar();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		proyectil.pausar();
		game.setScreen(new PausaScreen(game, this));
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
      canasta.destruir();
      proyectil.destruir();

	}

}

package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class PausaScreen implements Screen {

	private final GameLluviaMenu game;
	private GameScreen juego;
	private SpriteBatch batch;
    private BitmapFont font;
	private OrthographicCamera camera;
    private Stage stage;
    private UIBoton botonReanudar;
    private UIBoton botonSalir;
    private Texture fondo;

	public PausaScreen (final GameLluviaMenu game, GameScreen juego) {
		this.game = game;
        this.juego = juego;
        this.batch = game.getBatch();
        this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        fondo = new Texture(Gdx.files.internal("fondo.png"));


        botonReanudar = new UIBoton(stage,100,camera.viewportHeight/2+50,font,"Reanudar juego",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(juego);
                dispose();
            }
        });
        botonReanudar.crearComponente();

        botonSalir = new UIBoton(stage,100,camera.viewportHeight/2-10,font,"Salir menu",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        botonSalir.crearComponente();
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 1.0f, 0.5f);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
        batch.draw(fondo, 0, 0, camera.viewportWidth, camera.viewportHeight);
		font.draw(batch, "Juego en Pausa ", 100, camera.viewportHeight/2+150);
		batch.end();

        stage.act();
        stage.draw();
        botonReanudar.dibujarComponente();
        botonSalir.dibujarComponente();

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			game.setScreen(juego);
			dispose();
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}


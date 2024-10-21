package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameOverScreen implements Screen {
	private final GameLluviaMenu game;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
    private UIBoton botonReiniciar;
    private UIBoton botonSalir;
    private Stage stage;
    private Texture fondo;

	public GameOverScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        fondo = new Texture(Gdx.files.internal("fondo.png"));

        botonReiniciar = new UIBoton(stage,100,camera.viewportHeight/2-50,font,"Reiniciar juego",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });
        botonReiniciar.crearComponente();

        botonSalir = new UIBoton(stage,100,camera.viewportHeight/2-110,font,"Volver a menú",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        botonSalir.crearComponente();


	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
        batch.draw(fondo, 0, 0, camera.viewportWidth, camera.viewportHeight);
		font.draw(batch, "GAME OVER ", 100, camera.viewportHeight/2+50);
		batch.end();
        botonReiniciar.dibujarComponente();
        botonSalir.dibujarComponente();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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

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


public class MainMenuScreen implements Screen {

	final GameLluviaMenu game;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
    private UIBoton botonIniciar;
    private UIBoton botonSalir;
    private UIBoton botonTutorial;
    private Stage stage;
    private Texture fondo;


	public MainMenuScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        fondo = new Texture(Gdx.files.internal("fondo.png"));

        //Iniciar
        botonIniciar = new UIBoton(stage,100,camera.viewportHeight/2+50,font,"Empezar juego",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });
        botonIniciar.crearComponente();

        //Tutorial
        botonTutorial = new UIBoton(stage,100,camera.viewportHeight/2-10,font,"Tutorial",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new TutorialScreen(game));
                dispose();
            }
        });
        botonTutorial.crearComponente();


        //Salir
        botonSalir = new UIBoton(stage,100,camera.viewportHeight/2-70,font,"Cerrar juego",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        botonSalir.crearComponente();
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(1, 0, 0, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
        font.getData().setScale(2, 2);
        batch.draw(fondo, 0, 0, camera.viewportWidth, camera.viewportHeight);
		font.draw(batch, "Bienvenido a Recolecta Fruta!!! ", 100, camera.viewportHeight/2+150);

		batch.end();

        botonSalir.dibujarComponente();
        botonIniciar.dibujarComponente();
        botonTutorial.dibujarComponente();

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

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


public class TutorialScreen implements Screen{
    final GameLluviaMenu game;
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private UIBoton botonRegresar;
    private Stage stage;
    //private Texture fondo;

    public TutorialScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        botonRegresar = new UIBoton(stage,100,camera.viewportHeight/2-200,font,"Regresar",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });
        botonRegresar.crearComponente();
        //fondo = new Texture("fondo.png");

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.draw(batch, "Controles de juego ", 100, camera.viewportHeight/2+150);
        font.draw(batch, "Mover canasta: Flechas izquierda y derecha", 100, camera.viewportHeight/2+100);
        font.draw(batch, "Pausar juego: Tecla ESC", 100, camera.viewportHeight/2+50);
        font.draw(batch, "Reanudar juego: Tecla ESC", 100, camera.viewportHeight/2);
        font.draw(batch, "Las bombas te quitan vidas", 100, camera.viewportHeight/2-50);
        font.draw(batch, "Las frutas te dan puntos", 100, camera.viewportHeight/2-100);
        batch.end();
        botonRegresar.dibujarComponente();
    }

    @Override
    public void show() {
        //TODO
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        //TODO
    }

    @Override
    public void resume() {
        //TODO
    }

    @Override
    public void hide() {
        //TODO
    }

    @Override
    public void dispose() {
        //TODO
    }


}

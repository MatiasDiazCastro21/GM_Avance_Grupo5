package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
    private Texture flechas, escape,puntos,danio,vida;
    private ImgText tutorialFlecha, tutorialEscape, tutorialPuntos, tutorialDanio, tutorialVida;
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
        iniciarTutorial();


    }


    private void iniciarTutorial(){
        escape = new Texture(Gdx.files.internal("escape.png"));
        tutorialEscape = new ImgText(font, batch, escape, "Para pausar el juego con Escape", 100, 170, 50, 35,25);
        flechas = new Texture(Gdx.files.internal("flechas.png"));
        tutorialFlecha = new ImgText(font, batch, flechas, "Desplazarse con las flechas", 100, 130, 130, 50,10);
        puntos = new Texture(Gdx.files.internal("Fruta.png"));
        tutorialPuntos = new ImgText(font, batch, puntos, "Este objeto da puntos:", 100, 340, 30, 30,10);
        danio = new Texture(Gdx.files.internal("Bomba.png"));
        tutorialDanio = new ImgText(font, batch, danio, "Este objeto quita vida:", 100, 300, 30, 30,17);
        vida = new Texture(Gdx.files.internal("corazon.png"));
        tutorialVida = new ImgText(font, batch, vida, "Este objeto da vida:", 100, 260, 30, 30,42);
    }

    private void dibujarTutorial(){
        tutorialEscape.dibujarComponente();
        tutorialFlecha.dibujarComponente();
        tutorialPuntos.dibujarComponente();
        tutorialDanio.dibujarComponente();
        tutorialVida.dibujarComponente();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        BitmapFont.BitmapFontData fontData = font.getData();
        float ogScaleX = fontData.scaleX;
        float ogScaleY = fontData.scaleY;

        font.getData().setScale(3.0f);
        font.draw(batch, "Como jugar", 100, camera.viewportHeight/2+200);
        font.getData().setScale(ogScaleX, ogScaleY);
        font.draw(batch, "Obten la mayor cantidad de puntos posibles!", 100, 380);
        font.getData().setScale(3.0f);
        font.draw(batch, "Controles", 100, 220);
        font.getData().setScale(ogScaleX, ogScaleY);
        dibujarTutorial();
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

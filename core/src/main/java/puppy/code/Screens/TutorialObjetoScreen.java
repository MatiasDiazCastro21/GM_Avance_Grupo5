package puppy.code.Screens;

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
import puppy.code.GameFruitBase;
import puppy.code.UIs.ConcreteUIFactory;
import puppy.code.UIs.UI;
import puppy.code.UIs.UIFactory;

public class TutorialObjetoScreen implements Screen {
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private UI botonRegresar, botonControl;
    private UI tutorialPuntos, tutorialDanio, tutorialVida, tutorialScoreExtra, tutorialCalavera, tutorialDash;
    private Stage stage;
    private Texture puntos,danio,vida,scoreExtra,calavera,dash;
    private UIFactory factory;

    public TutorialObjetoScreen() {
        this.batch = GameFruitBase.getIns().getBatch();
        this.font = GameFruitBase.getIns().getFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        inicializarCompUI();


    }

    public void inicializarCompUI()
    {
        factory = new ConcreteUIFactory();

        botonRegresar = factory.crearBoton(stage,350,camera.viewportHeight/2-200,font,"Regresar",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameFruitBase.getIns().setScreen(new MainMenuScreen());
                dispose();
            }
        });

        botonRegresar.crearComponente();
        iniciarTutorial();

        botonControl = factory.crearBoton(stage,100,camera.viewportHeight/2-200,font,"Controles",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameFruitBase.getIns().setScreen(new TutorialControlScreen());
                dispose();
            }
        });
        botonControl.crearComponente();
    }

    private void iniciarTutorial(){

        puntos = new Texture(Gdx.files.internal("manzana.png"));
        tutorialPuntos = factory.crearImgText(font, batch, puntos, "Este objeto da puntos:", 100, 340, 30, 30,10);

        danio = new Texture(Gdx.files.internal("Bomba.png"));
        tutorialDanio = factory.crearImgText(font, batch, danio, "Este objeto quita vida:", 100, 300, 30, 30,17);

        vida = new Texture(Gdx.files.internal("corazon.png"));
        tutorialVida = factory.crearImgText(font, batch, vida, "Este objeto da vida:", 100, 260, 30, 30, 10);

        scoreExtra = new Texture(Gdx.files.internal("ManzanaOro.png"));
        tutorialScoreExtra = factory.crearImgText(font, batch, scoreExtra, "Este objeto da mas puntos:", 100, 220, 30, 30, 10);

        calavera = new Texture(Gdx.files.internal("calavera.png"));
        tutorialCalavera = factory.crearImgText(font, batch, calavera, "Este objeto hace el juego mas difícil:", 100, 180, 30, 30, 17);

        dash = new Texture(Gdx.files.internal("dash.png"));
        tutorialDash = factory.crearImgText(font, batch, dash, "Este objeto te da cargas de dash:", 100, 140, 30, 30, 10);
    }


    private void dibujarTutorial(){
        tutorialPuntos.dibujarComponente();
        tutorialDanio.dibujarComponente();
        tutorialVida.dibujarComponente();
        tutorialScoreExtra.dibujarComponente();
        tutorialCalavera.dibujarComponente();
        tutorialDash.dibujarComponente();
    }

    @Override
    public void render(float delta){
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        BitmapFont.BitmapFontData fontData = font.getData();
        float ogScaleX = fontData.scaleX;
        float ogScaleY = fontData.scaleY;
        font.getData().setScale(3.0f);
        batch.draw(GameFruitBase.getIns().getFondo(), 0, 0, camera.viewportWidth, camera.viewportHeight);
        font.draw(batch, "Como jugar", 100, camera.viewportHeight/2+200);
        font.getData().setScale(2.0f, 2.0f);
        font.draw(batch, "Obten la mayor cantidad de puntos posibles!", 100, 380);
        font.getData().setScale(ogScaleX, ogScaleY);
        dibujarTutorial();
        batch.end();
        botonRegresar.dibujarComponente();
    }


    @Override
    public void show() {
        GameFruitBase.getIns().getMusic().play();
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

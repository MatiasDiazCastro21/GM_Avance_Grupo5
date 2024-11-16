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
import puppy.code.UIs.*;


public class TutorialControlScreen implements Screen{
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private UI botonRegresar, botonObjeto;
    private UI tutorialFlecha, tutorialEscape, tutorialAAndD, tutorialShift, tutorialEspacio;
    private UIFactory factory;
    private Stage stage;
    private Texture flechas, escape,aAndD,shift,espacio;

    public TutorialControlScreen() {
        this.batch = GameFruitBase.getIns().getBatch();
        this.font = GameFruitBase.getIns().getFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        instanciarCompUI();


    }

    private void instanciarCompUI(){
        //Botones
        factory = new ConcreteUIFactory();

        botonRegresar = factory.crearBoton(stage,100,camera.viewportHeight/2-200,font,"Regresar",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameFruitBase.getIns().setScreen(new MainMenuScreen());
                dispose();
            }
        });
        botonRegresar.crearComponente();

        botonObjeto = factory.crearBoton(stage,350,camera.viewportHeight/2-200,font,"Objetos",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameFruitBase.getIns().setScreen(new TutorialObjetoScreen());
                dispose();
            }
        });
        botonObjeto.crearComponente();
        iniciarTutorial();
    }

    private void iniciarTutorial(){
        escape = new Texture(Gdx.files.internal("escape.png"));
        tutorialEscape = factory.crearImgText(font, batch, escape, "Para pausar el juego con Escape", 100, 340, 50, 35,25);

        flechas = new Texture(Gdx.files.internal("flechas.png"));
        tutorialFlecha = factory.crearImgText(font, batch, flechas, "Desplazarse con las flechas", 100, 290, 130, 50,10);

        aAndD = new Texture(Gdx.files.internal("AyD.png"));
        tutorialAAndD = factory.crearImgText(font, batch, aAndD, "Desplazarse con las teclas A y D", 100, 240, 130, 50,10);

        shift = new Texture(Gdx.files.internal("shift.png"));
        tutorialShift = factory.crearImgText(font, batch, shift, "Dash con la tecla Shift mientras te mueves", 100, 190, 80, 50,25);

        espacio = new Texture(Gdx.files.internal("espacio.png"));
        tutorialEspacio = factory.crearImgText(font, batch, espacio, "Ir más rápido con la tecla Espacio", 100, 140, 80, 50,25);

    }

    private void dibujarTutorial(){
        tutorialEscape.dibujarComponente();
        tutorialFlecha.dibujarComponente();
        tutorialAAndD.dibujarComponente();
        tutorialShift.dibujarComponente();
        tutorialEspacio.dibujarComponente();

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
        batch.draw(GameFruitBase.getIns().getFondo(), 0, 0, camera.viewportWidth, camera.viewportHeight);
        font.draw(batch, "Controles", 100, camera.viewportHeight/2+200);
        font.getData().setScale(ogScaleX, ogScaleY);
        dibujarTutorial();
        batch.end();
        botonRegresar.dibujarComponente();
        botonObjeto.dibujarComponente();
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

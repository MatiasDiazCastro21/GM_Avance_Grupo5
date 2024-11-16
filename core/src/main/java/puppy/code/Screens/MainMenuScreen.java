package puppy.code.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import puppy.code.UIs.UIBoton;
import puppy.code.UIs.UIFactory;


public class MainMenuScreen implements Screen {
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private UI botonIniciar;
    private UI botonSalir;
    private UI botonTutorial;
    private UIFactory factory;
    private Stage stage;

    public MainMenuScreen() {
        //final GameFruitBase game
        //this.game = game;
        this.batch = GameFruitBase.getIns().getBatch();
        this.font = GameFruitBase.getIns().getFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        instanciarCompUI();
    }


    private void instanciarCompUI(){
        //Iniciar
        factory = new ConcreteUIFactory();

        botonIniciar = factory.crearBoton(stage,100,camera.viewportHeight/2+50,font,"Empezar juego",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameFruitBase.getIns().setScreen(new GameScreen());
                GameFruitBase.getIns().getMusic().stop();
                dispose();
            }
        });
        botonIniciar.crearComponente();

        //Tutorial
        botonTutorial = factory.crearBoton(stage,100,camera.viewportHeight/2-10,font,"Tutorial",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameFruitBase.getIns().setScreen(new TutorialControlScreen());
                dispose();
            }
        });
        botonTutorial.crearComponente();


        //Salir
        botonSalir = factory.crearBoton(stage,100,camera.viewportHeight/2-70,font,"Cerrar juego",new ClickListener(){
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
        batch.draw(GameFruitBase.getIns().getFondo(), 0, 0, camera.viewportWidth, camera.viewportHeight);
        font.getData().setScale(3, 3);
        font.draw(batch, "Apple Drop ", 100, camera.viewportHeight/2+150);
        font.getData().setScale(2, 2);
        batch.end();

        botonSalir.dibujarComponente();
        botonIniciar.dibujarComponente();
        botonTutorial.dibujarComponente();

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
        stage.dispose();
    }
}

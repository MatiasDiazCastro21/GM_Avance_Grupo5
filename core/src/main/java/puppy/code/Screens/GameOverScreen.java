package puppy.code.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
import puppy.code.UIs.UIBoton;
import puppy.code.UIs.UIFactory;

public class GameOverScreen implements Screen {
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;

    //Atributos tipo UI
    private UI botonReiniciar;
    private UI botonSalir;
    private UIFactory factory;

    private Stage stage;
    private Texture fondo;
    private Music musicGameOver;

    public GameOverScreen() {
        this.batch = GameFruitBase.getIns().getBatch();
        this.font = GameFruitBase.getIns().getFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        fondo = new Texture(Gdx.files.internal("fondo.png"));
        musicGameOver = Gdx.audio.newMusic(Gdx.files.internal("GameOver.mp3"));
        musicGameOver.setVolume(0.05f);
        instanciarCompUI();

    }


    private void instanciarCompUI(){
        //Botones
        factory = new ConcreteUIFactory();

        botonReiniciar = factory.crearBoton(stage,100,camera.viewportHeight/2-50,font,"Reiniciar juego",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameFruitBase.getIns().setScreen(new GameScreen());
                musicGameOver.stop();
                dispose();
            }
        });
        botonReiniciar.crearComponente();

        botonSalir = factory.crearBoton(stage,100,camera.viewportHeight/2-110,font,"Volver a menú",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameFruitBase.getIns().setScreen(new MainMenuScreen());
                musicGameOver.stop();
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
        musicGameOver.play();
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
        musicGameOver.dispose();
        fondo.dispose();
        stage.dispose();
    }
}

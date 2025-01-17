package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class UIBoton implements UI {

    private final Stage stage;
    private final float posX;
    private final float posY;
    private final BitmapFont fontJuego;
    private final ClickListener clickListener;
    private final String textoBoton;

    public UIBoton(Stage stage, float posX, float posY, BitmapFont fontJuego, String textoBoton, ClickListener clickListener) {
        this.posX = posX;
        this.posY = posY;
        this.fontJuego = fontJuego;
        this.textoBoton = textoBoton;
        this.clickListener = clickListener;
        this.stage = stage;
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void crearComponente() {
        Drawable upDrawable = new TextureRegionDrawable(new Texture(Gdx.files.internal("button-up.jpg")));
        Drawable downDrawable = new TextureRegionDrawable(new Texture(Gdx.files.internal("button-down.jpg")));

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = upDrawable;
        textButtonStyle.down = downDrawable;
        textButtonStyle.font = this.fontJuego;
        TextButton boton = new TextButton(textoBoton, textButtonStyle);
        boton.setSize(200, 50);
        boton.setPosition(posX, posY);
        boton.addListener(clickListener);
        stage.addActor(boton);

    }


    public void dibujarComponente() {
        stage.draw();
        stage.act();
    }

    public Stage getStage() {
        return stage;
    }
}

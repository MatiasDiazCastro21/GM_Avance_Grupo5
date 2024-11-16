package puppy.code.UIs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public interface UIFactory{
    UI crearBoton(Stage stage, float posX, float posY, BitmapFont fontJuego, String textoBoton, ClickListener clickListener);
    UI crearImgText(BitmapFont font, Batch batch, Texture img, String texto, int posXText, int posYText, int ancho, int alto, int distancia);
}

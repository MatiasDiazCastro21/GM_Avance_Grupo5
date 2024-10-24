package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;


public class ImgText implements UI{
    private BitmapFont font;
    private Batch batch;
    private Texture img;
    private String texto;
    private int posXImg, posYImg;
    private int posXText, posYText;
    private int ancho, alto;
    private int distancia;

    public ImgText(BitmapFont font, Batch batch, Texture img, String texto, int posXText, int posYText, int ancho, int alto,int distancia) {
        this.font = font;
        this.batch = batch;
        this.img = img;
        this.texto = texto;
        this.posXText = posXText;
        this.posYText = posYText;
        this.ancho = ancho;
        this.alto = alto;
        this.distancia = distancia;
        crearComponente();
    }


    @Override
    public void crearComponente() {
        GlyphLayout layout = new GlyphLayout(font, texto);
        posXImg = posXText + (int) layout.width + distancia;
        posYImg = posYText - alto / 2;
    }

    @Override
    public void dibujarComponente() {
        batch.draw(img, posXImg, posYImg, ancho, alto);
        font.draw(batch, texto, posXText, posYText);
    }

}

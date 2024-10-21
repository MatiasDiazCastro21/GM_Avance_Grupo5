package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Proyectil extends Create{
	private Array<Rectangle> rainDropsPos;
	private Array<Integer> rainDropsType;
    private long lastDropTime;
    private Texture manzana;
    private Texture bomba;
    private Texture vidaExtra;
    private Sound vida;
    private Sound dropSound;
    private Music rainMusic;

	public Proyectil(Texture manzana, Texture bomba, Texture vidaExtra, Sound ss, Music mm,Sound vida) {
		rainMusic = mm;
		dropSound = ss;
        this.vida = vida;
		this.manzana = manzana;
		this.bomba = bomba;
        this.vidaExtra =vidaExtra;
	}
    @Override
	public void crear() {
		rainDropsPos = new Array<Rectangle>();
		rainDropsType = new Array<Integer>();
		crearGotaDeLluvia();
	      // start the playback of the background music immediately
        /*
        Music pou = Gdx.audio.newMusic(Gdx.files.internal("pou.mp3"));
        pou.setLooping(true);  // Hace que la música se reproduzca en bucle
        pou.setVolume(0.6f);   // Ajusta el volumen de la música
        pou.play();
        */
        rainMusic.setLooping(true);
        rainMusic.setVolume(0.05f);
        rainMusic.play();
	}

	private void crearGotaDeLluvia() {
	      Rectangle raindrop = new Rectangle();
	      raindrop.x = MathUtils.random(0, 800-64);
	      raindrop.y = 480;
	      raindrop.width = 64;
	      raindrop.height = 64;
	      rainDropsPos.add(raindrop);
	      /* ver el tipo de gota
	      if (MathUtils.random(1,10)<5)
	         rainDropsType.add(1);
	      else
	    	 rainDropsType.add(2);
	      lastDropTime = TimeUtils.nanoTime();

	       */
        int random = MathUtils.random(1, 100);

        if (random < 70)
            rainDropsType.add(2);
        else if (random < 99)
            rainDropsType.add(1);
        else
            rainDropsType.add(3);

        lastDropTime = TimeUtils.nanoTime();
	   }

   public boolean actualizarMovimiento(Canasta canasta) {
	   // generar gotas de lluvia
	   if(TimeUtils.nanoTime() - lastDropTime > 100000000) crearGotaDeLluvia();
	   // revisar si las gotas cayeron al suelo o chocaron con el tarro
	   for (int i=0; i < rainDropsPos.size; i++ ) {
		  Rectangle raindrop = rainDropsPos.get(i);
	      raindrop.y -= 300 * Gdx.graphics.getDeltaTime();
	      //cae al suelo y se elimina
	      if(raindrop.y + 64 < 0) {
	    	  rainDropsPos.removeIndex(i);
	    	  rainDropsType.removeIndex(i);
	      }
	      if(raindrop.overlaps(canasta.getArea())) { //la gota choca con el tarro
	    	if(rainDropsType.get(i)==1) { // gota dañina
	    	  canasta.dañar();
	    	  if (canasta.getVidas()<=0)
	    		 return false; // si se queda sin vidas retorna falso /game over
	    	  rainDropsPos.removeIndex(i);
	          rainDropsType.removeIndex(i);
	      	}/*else { // gota a recolectar
	    	  canasta.sumarPuntos(10);
	          dropSound.play(0.1f);
	          rainDropsPos.removeIndex(i);
	          rainDropsType.removeIndex(i);
	      	}*/
            else if (rainDropsType.get(i)==2)
            {
                canasta.sumarPuntos(10);
                dropSound.play(0.1f);
                rainDropsPos.removeIndex(i);
                rainDropsType.removeIndex(i);
            }
            else if (rainDropsType.get(i) == 3)
            {
                canasta.sumarVida();
                vida.play(0.1f);
                rainDropsPos.removeIndex(i);
                rainDropsType.removeIndex(i);

            }
	      }
	   }
	  return true;
   }
   @Override
   /*
   public void dibujar(SpriteBatch batch) {
	  for (int i=0; i < rainDropsPos.size; i++ ) {
		  Rectangle raindrop = rainDropsPos.get(i);
		  if(rainDropsType.get(i)==1) // gota dañina
	         batch.draw(bomba, raindrop.x, raindrop.y);
		  else
			 batch.draw(manzana, raindrop.x, raindrop.y);
	   }
   }
   */
    public void dibujar(SpriteBatch batch) {
        for (int i = 0; i < rainDropsPos.size; i++) {
            Rectangle raindrop = rainDropsPos.get(i);
            if (rainDropsType.get(i) == 1) {
                batch.draw(bomba, raindrop.x, raindrop.y);  // Gota dañina
            } else if (rainDropsType.get(i) == 2) {
                batch.draw(manzana, raindrop.x, raindrop.y);  // Gota buena
            } else if (rainDropsType.get(i) == 3) {
                batch.draw(vidaExtra, raindrop.x, raindrop.y);
            }
        }
    }
   public void destruir() {
      dropSound.dispose();
      rainMusic.dispose();
   }
   public void pausar() {
	  rainMusic.stop();
   }
   public void continuar() {
	  rainMusic.play();
   }
   @Override
   public void actualizar() {}
}

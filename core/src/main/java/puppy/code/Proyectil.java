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
	private Array<Rectangle> dropsPos;
	private Array<Integer> dropsType;
    private long lastDropTime;
    private Texture manzana;
    private Texture bomba;
    private Texture vidaExtra;
    private Sound vida;
    private Sound dropSound;
    private Music music;

	public Proyectil(Texture manzana, Texture bomba, Texture vidaExtra, Sound ss, Music mm,Sound vida) {
		music = mm;
		dropSound = ss;
        this.vida = vida;
		this.manzana = manzana;
		this.bomba = bomba;
        this.vidaExtra =vidaExtra;
	}
    @Override
	public void crear() {
		dropsPos = new Array<Rectangle>();
		dropsType = new Array<Integer>();
		crearGotaDeLluvia();
        // start the playback of the background music immediately
        music.setLooping(true);
        music.setVolume(0.05f);
        music.play();
	}

	private void crearGotaDeLluvia() {
	      Rectangle raindrop = new Rectangle();
	      raindrop.x = MathUtils.random(0, 800-64);
	      raindrop.y = 480;
	      raindrop.width = 64;
	      raindrop.height = 64;
	      dropsPos.add(raindrop);
	      /* ver el tipo de gota
	      if (MathUtils.random(1,10)<5)
	         rainDropsType.add(1);
	      else
	    	 rainDropsType.add(2);
	      lastDropTime = TimeUtils.nanoTime();

	       */
        int random = MathUtils.random(1, 100);

        if (random < 70)
            dropsType.add(2);
        else if (random < 99)
            dropsType.add(1);
        else
            dropsType.add(3);

        lastDropTime = TimeUtils.nanoTime();
	   }

   public boolean actualizarMovimiento(Canasta canasta) {
	   // generar gotas de lluvia
	   if(TimeUtils.nanoTime() - lastDropTime > 100000000) crearGotaDeLluvia();
	   // revisar si las gotas cayeron al suelo o chocaron con el tarro
	   for (int i = 0; i < dropsPos.size; i++ ) {
		  Rectangle raindrop = dropsPos.get(i);
	      raindrop.y -= 300 * Gdx.graphics.getDeltaTime();
	      //cae al suelo y se elimina
	      if(raindrop.y + 64 < 0) {
	    	  dropsPos.removeIndex(i);
	    	  dropsType.removeIndex(i);
	      }
	      if(raindrop.overlaps(canasta.getArea())) { //la gota choca con el tarro
	    	if(dropsType.get(i)==1) { // gota dañina
	    	  canasta.dañar();
	    	  if (canasta.getVidas()<=0)
	    		 return false; // si se queda sin vidas retorna falso /game over
	    	  dropsPos.removeIndex(i);
	          dropsType.removeIndex(i);
	      	}/*else { // gota a recolectar
	    	  canasta.sumarPuntos(10);
	          dropSound.play(0.1f);
	          rainDropsPos.removeIndex(i);
	          rainDropsType.removeIndex(i);
	      	}*/
            else if (dropsType.get(i)==2)
            {
                canasta.sumarPuntos(10);
                dropSound.play(0.05f);
                dropsPos.removeIndex(i);
                dropsType.removeIndex(i);
            }
            else if (dropsType.get(i) == 3)
            {
                canasta.sumarVida();
                vida.play(0.025f);
                dropsPos.removeIndex(i);
                dropsType.removeIndex(i);

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
        for (int i = 0; i < dropsPos.size; i++) {
            Rectangle raindrop = dropsPos.get(i);
            if (dropsType.get(i) == 1) {
                batch.draw(bomba, raindrop.x, raindrop.y);  // Gota dañina
            } else if (dropsType.get(i) == 2) {
                batch.draw(manzana, raindrop.x, raindrop.y);  // Gota buena
            } else if (dropsType.get(i) == 3) {
                batch.draw(vidaExtra, raindrop.x, raindrop.y);
            }
        }
    }
   public void destruir() {
      dropSound.dispose();
      music.dispose();
   }
   public void pausar() {
	  music.pause();
   }
   public void continuar() {
	  music.play();
   }
   @Override
   public void actualizar() {}
}

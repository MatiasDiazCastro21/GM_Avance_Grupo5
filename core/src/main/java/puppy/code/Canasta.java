package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Canasta {
	   private Rectangle bucket;
	   private Texture bucketImage;
	   private Sound sonidoHerido;
	   private int vidas = 3;
	   private int puntos = 0;
	   private int velx = 400;
	   private boolean herido = false;
	   private int tiempoHeridoMax=50;
	   private int tiempoHerido;
       private boolean efectoCalavera = false;
       private long tiempoCalavera;
       private int cargarDash = 3;
       private static final int MAX_CARGAS_DASH = 3;
       private boolean tieneDash = false;
       private static final float DASH_DISTANCE = 250;
       private int direccion;
       private Sound sonidoDash;


	   public Canasta(Texture tex, Sound ss) {
		   bucketImage = tex;
		   sonidoHerido = ss;
           sonidoDash = Gdx.audio.newSound(Gdx.files.internal("dashSound.mp3"));
	   }

		public int getVidas() {
			return vidas;
		}

		public int getPuntos() {
			return puntos;
		}
		public Rectangle getArea() {
			return bucket;
		}
		public void sumarPuntos(int pp) {
			puntos+=pp;
		}


	   public void crear() {
           bucket = new Rectangle();
           bucket.x = 800 / 2 - bucketImage.getWidth() / 2;
           bucket.y = 20;
           bucket.width = bucketImage.getWidth();
           bucket.height = bucketImage.getHeight();
	   }

	   public void dañar() {
		  vidas--;
		  herido = true;
		  tiempoHerido=tiempoHeridoMax;
		  sonidoHerido.play(0.05f);
	   }

       public void sumarVida()
       {
           vidas++;
           tiempoHerido=tiempoHeridoMax;

       }


	   public void dibujar(SpriteBatch batch) {
		 if (!herido) {
             batch.draw(bucketImage, bucket.x, bucket.y);
         }
		 else {
             direccion = 0;
             batch.draw(bucketImage, bucket.x, bucket.y+ MathUtils.random(-5,5));

		   tiempoHerido--;
		   if (tiempoHerido<=0) herido = false;
		 }
	   }

       public void actualizar() {
           if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
               direccion = -1;
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                     bucket.x -= velx * Gdx.graphics.getDeltaTime()*2;
                }
                else{
                    bucket.x -= velx * Gdx.graphics.getDeltaTime();
                }
           }
           if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
               direccion = 1;
               if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                   bucket.x += velx * Gdx.graphics.getDeltaTime()*2;
               }
               else{
                   bucket.x += velx * Gdx.graphics.getDeltaTime();
               }

           }

           if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)) {
               dash(); // Usar dash cuando Shift es presionado
           }

		   // que no se salga de los bordes izq y der
		   if(bucket.x < 0) bucket.x = 0;
		   if(bucket.x > 800 - 64) bucket.x = 800 - bucket.width;
	   }


       public void destruir() {
		    bucketImage.dispose();
	   }

       public boolean estaHerido() {
	        return herido;
       }

    public void activarEfectoCalavera(long tiempoInicio) {
        this.efectoCalavera = true;
        this.tiempoCalavera = tiempoInicio;
    }

    public boolean efectoCalavera() {
        return efectoCalavera && TimeUtils.nanoTime() - tiempoCalavera < 10_000_000_000L;
    }

    public void desactivarEfectoCalavera() {
        this.efectoCalavera = false;
    }

    public long getTiempoCalavera() {
        return tiempoCalavera;
    }

    public void dash() {
        if (cargarDash > 0) { //Usar solo si hay cargas disponibles

            if (direccion != 0) { // Solo ejecutar el dash si hay dirección
                sonidoDash.play(0.05f);
                bucket.x += DASH_DISTANCE * direccion;

                //Limitar dentro de los bordes
                if(bucket.x < 0) bucket.x = 0;
                if(bucket.x > 800 - bucket.width) bucket.x = 800 - bucket.width;

                //Consumir una carga de dash
                cargarDash--;
                if (cargarDash == 0) {
                    tieneDash = false; // Desactivar si se agotaron las cargas
                }
            }
        }
    }

    public void obtenerDash() {
        if (cargarDash < MAX_CARGAS_DASH) {
            cargarDash++;
            tieneDash = true;
        }
    }

    public int getCargasDash() {
        return cargarDash;
    }

    public int getMaxCargasDash() {
        return MAX_CARGAS_DASH;
    }

}

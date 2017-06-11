import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;

/**
 * Write a description of class Manzana here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Manzana extends Circle
{
    // instance variables - replace the example below with your own
    private static int RADIO = 5;
    private int anchoEscena;
    private int altoEscena;
    
    /**
     * Constructor for objects of class Manzana
     */
    public Manzana(int anchoEscena, int altoEscena)
    {
        super();
        setFill(Color.RED);
        setRadius(RADIO);
        this.altoEscena = altoEscena;
        this.anchoEscena = anchoEscena;
        Random aleat = new Random();
        int posX = aleat.nextInt((int) (anchoEscena -  RADIO));
        int posY = aleat.nextInt((int) (altoEscena - RADIO));
        setCenterX(posX);
        setCenterY(posY);
    }
    
     public boolean comprobarChoque(Rectangle objeto){
        boolean comprobacion = false;
        Shape c = Shape.intersect(this, objeto);
        double ancho = c.getBoundsInParent().getWidth();
        if(ancho != -1){
            comprobacion = true;
        }
        return comprobacion;
    }
}

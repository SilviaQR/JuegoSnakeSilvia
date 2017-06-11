import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Write a description of class Serpiente here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Serpiente extends Rectangle
{   
    private static int LONGITUD_SERPIENTE = 3;
    private static int POS_X = 350;
    private static int POS_Y = 250;
    private int posicionMaximaX;
    private int posicionMinimaX;
    private int posicionMinimaY;
    private int posicionMaximaY;
    private int movimientoSerpiente;
    private int movimientoSerpienteY;
    private boolean arriba, abajo, derecha, izquierda = false;
    /**
     * Constructor for objects of class Serpiente
     */
    public Serpiente(int posicionMaxima, int posicionMinima, int posicionMaximaY, int posicionMinimaY)
    {
        super();
        setHeight(15);
        setWidth(15);
        setFill(Color.BLACK);
        setX(POS_X);
        setY(POS_Y);
        this.posicionMaximaX = posicionMaximaX;
        this.posicionMinimaX = posicionMinimaX;
        this.posicionMaximaY = posicionMaximaY;
        this.posicionMinimaY = posicionMinimaY;
    }

    public void moverALaIzquierda()
    {
        if(getBoundsInParent().getMinX() > posicionMinimaX){
            movimientoSerpiente = -1;
        }
    }

    public void moverALaDerecha()
    {
        if(getBoundsInParent().getMaxX() < posicionMaximaX){
            movimientoSerpiente = 1;
        }
    }

    public void moverArriba()
    {
        if(getBoundsInParent().getMinY() > posicionMinimaY){
            movimientoSerpiente = -1;
        }
    }

    public void moverAbajo()
    {
        if(getBoundsInParent().getMaxY() < posicionMaximaY){
            movimientoSerpiente = 1;
        }
    }

    public void mover()
    {
        
        setTranslateX(getTranslateX() + movimientoSerpiente);
        if(getBoundsInParent().getMinX() == posicionMinimaX || getBoundsInParent().getMaxX() == posicionMaximaX
        || getBoundsInParent().getMinY() == posicionMinimaY || getBoundsInParent().getMaxY() == posicionMaximaY){
            movimientoSerpiente = 0;
            movimientoSerpienteY = 0;
        }
    }
}

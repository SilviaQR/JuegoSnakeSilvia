import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import java.util.ArrayList;
import javafx.scene.Group;

/**
 * Write a description of class Serpiente here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Serpiente
{   
    private static int LONGITUD_SERPIENTE = 3;
    private static int POS_X = 350;
    private static int POS_Y = 250;
    private int posicionMaximaX;
    private int posicionMinimaX;
    private int posicionMinimaY;
    private int posicionMaximaY;
    private int movimientoSerpienteX;
    private int movimientoSerpienteY;
    private Rectangle cabeza;
    private Rectangle cola;
    private ArrayList<Rectangle> colas;
    private int elementosDeLaCola;
    private boolean ejeX, ejeY = false;
    /**
     * Constructor for objects of class Serpiente
     */
    public Serpiente(int posicionMaximaX, int posicionMinimaX, int posicionMaximaY, int posicionMinimaY)
    {
        cabeza = new Rectangle();
        cabeza.setHeight(15);
        cabeza.setWidth(15);
        cabeza.setFill(Color.BLACK);
        cabeza.setX(POS_X);
        cabeza.setY(POS_Y);
        elementosDeLaCola = 3;
        colas = new ArrayList<>();
        cola = new Rectangle();
        cola.setHeight(15);
        cola.setWidth(15);
        cola.setFill(Color.BLACK);
        cola.setX(POS_X + 17);
        cola.setY(POS_Y);
        colas.add(cola);
        for(int i = 1; i < elementosDeLaCola; i++){
            Rectangle cola1 = new Rectangle();
            cola1.setHeight(15);
            cola1.setWidth(15);
            cola1.setFill(Color.BLACK);
            cola1.setX(colas.get(i - 1).getX() + 17);
            cola1.setY(POS_Y);
            colas.add(cola1);
        }
        this.posicionMaximaX = posicionMaximaX;
        this.posicionMinimaX = posicionMinimaX;
        this.posicionMaximaY = posicionMaximaY;
        this.posicionMinimaY = posicionMinimaY;
    }

    public boolean moverALaIzquierda()
    {

        if(cabeza.getBoundsInParent().getMinX() > posicionMinimaX){
            movimientoSerpienteX = -1;
            movimientoSerpienteY = 0;
            ejeX = true;
        }
        return ejeX;
    }

    public boolean moverALaDerecha()
    {

        if(cabeza.getBoundsInParent().getMaxX() < posicionMaximaX){
            movimientoSerpienteX = 1;
            movimientoSerpienteY = 0;
            ejeX = true;
        }
        return ejeX;
    }

    public boolean moverArriba()
    {

        if(cabeza.getBoundsInParent().getMinY() > posicionMinimaY){
            movimientoSerpienteY = -1;
            movimientoSerpienteX = 0;
            ejeY = true;
        }
        return ejeY;
    }

    public boolean moverAbajo()
    {

        if(cabeza.getBoundsInParent().getMaxY() < posicionMaximaY){
            movimientoSerpienteY = 1;
            movimientoSerpienteX = 0;
            ejeY = true;
        }
        return ejeY;
    }

    public boolean comerManzana(Circle object){
        boolean comprobacion = false;
        Shape c = Shape.intersect(cabeza, object);
        double ancho = c.getBoundsInParent().getWidth();
        if(ancho != -1){
            comprobacion = true;
        }
        return comprobacion;
    }

    public void anadirA(Group scene){
        scene.getChildren().add(cabeza);
        for(Rectangle nuevaCola : colas){
            scene.getChildren().add(nuevaCola);
        }
    }

    public Rectangle getCabeza(){
        return cabeza;
    }
    
    public void mover()
    {
        double posXCabeza = cabeza.getTranslateX();
        cabeza.setTranslateY(cabeza.getTranslateY() + movimientoSerpienteY);
        cabeza.setTranslateX(cabeza.getTranslateX() + movimientoSerpienteX);
        for(int i = 0; i < colas.size(); i++){

            if(ejeY){
                colas.get(i).setX(colas.get(i).getX() + posXCabeza);
                colas.get(i).setTranslateX(colas.get(i).getTranslateX() + movimientoSerpienteX);
                colas.get(i).setTranslateY(colas.get(i).getTranslateY() + movimientoSerpienteY);

            }
            colas.get(i).setTranslateX(colas.get(i).getTranslateX() + movimientoSerpienteX);
            colas.get(i).setTranslateY(colas.get(i).getTranslateY() + movimientoSerpienteY);

        }
        if(cabeza.getBoundsInParent().getMinX() == posicionMinimaX || cabeza.getBoundsInParent().getMaxX() == posicionMaximaX 
        || cabeza.getBoundsInParent().getMinY() == posicionMinimaY || cabeza.getBoundsInParent().getMaxY() == posicionMaximaY){
            movimientoSerpienteX = 0;
            movimientoSerpienteY = 0;
        }
    }
}

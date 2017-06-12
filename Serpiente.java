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
        elementosDeLaCola = 2;
        colas = new ArrayList<>();
        cola = new Rectangle();
        cola.setHeight(20);
        cola.setWidth(20);
        cola.setFill(Color.WHITE);
        cola.setX(POS_X + 17);
        cola.setY(POS_Y);
        colas.add(cola);
        for(int i = 0; i < elementosDeLaCola; i++){
            Rectangle cola1 = new Rectangle();
            cola1.setHeight(20);
            cola1.setWidth(20);
            cola1.setFill(Color.WHITE);
            cola1.setX(colas.get(i).getX() + 17);
            cola1.setY(POS_Y);
            colas.add(cola1);
        }
        this.posicionMaximaX = posicionMaximaX;
        this.posicionMinimaX = posicionMinimaX;
        this.posicionMaximaY = posicionMaximaY;
        this.posicionMinimaY = posicionMinimaY;
    }

    public void moverALaIzquierda()
    {
        if(cabeza.getBoundsInParent().getMinX() > posicionMinimaX){
            movimientoSerpienteX = -1;
            movimientoSerpienteY = 0;
        }
    }

    public void moverALaDerecha()
    {
        if(cabeza.getBoundsInParent().getMaxX() < posicionMaximaX){
            movimientoSerpienteX = 1;
            movimientoSerpienteY = 0;
        }
    }

    public void moverArriba()
    {
        if(cabeza.getBoundsInParent().getMinY() > posicionMinimaY){
            movimientoSerpienteY = -1;
            movimientoSerpienteX = 0;
        }
    }

    public void moverAbajo()
    {
        if(cabeza.getBoundsInParent().getMaxY() < posicionMaximaY){
            movimientoSerpienteY = 1;
            movimientoSerpienteX = 0;
        }
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
        scene.getChildren().add(cola);
        //for(Rectangle cola : colas){
          //  scene.getChildren().add(cola);
        //}
    }

    public void mover()
    {
        cabeza.setTranslateX(cabeza.getTranslateX() + movimientoSerpienteX);
        cabeza.setTranslateY(cabeza.getTranslateY() + movimientoSerpienteY);
        int posicionY = (int) cabeza.getY();
        int posicionX = (int) cabeza.getX();
        for(int i = 0; i < colas.size(); i++){
            colas.get(i).setTranslateX(posicionY + movimientoSerpienteX);
            colas.get(i).setTranslateY(posicionX + movimientoSerpienteY);
            posicionY =(int) colas.get(i).getY();
            posicionX =(int) colas.get(i).getX();
        }
        if(cabeza.getBoundsInParent().getMinX() == posicionMinimaX || cabeza.getBoundsInParent().getMaxX() == posicionMaximaX
        || cabeza.getBoundsInParent().getMinY() == posicionMinimaY || cabeza.getBoundsInParent().getMaxY() == posicionMaximaY){
            movimientoSerpienteX = 0;
            movimientoSerpienteY = 0;
        }
    }
}

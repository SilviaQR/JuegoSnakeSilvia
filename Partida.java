import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation;

/**
 * Write a description of class Partida here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Partida extends Application
{
    private static int ANCHO_VENTANA = 700;
    private static int ALTO_VENTANA = 500;
    private static int ALTO_BARRA_INFERIOR = 450;
    private int puntuacion;
    private static int LONGITUD_SERPIENTE = 3;
    private static int POS_X = 350;
    private static int POS_Y = 250;
    //private ArrayList<Serpiente> serpientes;
    private ArrayList<Manzana> manzanas;

    public static void main(String args[]){
        launch(args);
    }

    public void start(Stage escenario){
        // Creamos el contenedor y la escena con sus características
        manzanas = new ArrayList<>();
        Group contenedor = new Group();
        Scene escena = new Scene(contenedor, ANCHO_VENTANA, ALTO_VENTANA);
        escena.setFill(Color.GREEN);
        escenario.setScene(escena);
        // Creamos la serpiente

        Serpiente serp = new Serpiente((int) escena.getWidth(), 0, (int) escena.getHeight(), 0);
        serp.anadirA(contenedor);
        //serpientes.add(serp);
        puntuacion = 0;
        Label puntos = new Label("Puntos: 0");
        puntos.setTranslateX(600);
        puntos.setTranslateY(460);
        puntos.setTextFill(Color.WHITE);
        contenedor.getChildren().add(puntos);
        // Se crean las etiquetas para el tiempo y la puntuacion
        Manzana manz = new Manzana(ANCHO_VENTANA - 20, ALTO_BARRA_INFERIOR);
        contenedor.getChildren().add(manz);
        manzanas.add(manz);
        Timeline timeline = new Timeline();
        KeyFrame kf = new KeyFrame((Duration.seconds(0.017)), event ->{
                    serp.mover();

                    if(manzanas.get(0).comprobarChoque(serp.getCabeza()) && serp.comerManzana(manzanas.get(0))){
                        //Serpiente nuevaSerp = new Serpiente((int) escena.getWidth(), 0, (int) escena.getHeight(), 0);
                        //serpientes.add(nuevaSerp);

                        //contenedor.getChildren().remove(0);
                        //contenedor.getChildren().add(nuevaSerp);
                        puntuacion = puntuacion + 10;

                        //Manzana nuevaManz = new Manzana(ANCHO_VENTANA - 20, ALTO_BARRA_INFERIOR);
                        //contenedor.getChildren().add(nuevaManz);
                        //manzanas.add(nuevaManz);
                    }

                });
        puntos.setText("Puntuación: " + puntuacion);
        // Añadimos todos los elementos creados al contenedor y la escena y mostramos su contenido
        escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.LEFT) {
                        serp.moverALaIzquierda();
                    }
                    else if(event.getCode() == KeyCode.RIGHT){
                        serp.moverALaDerecha();
                    }
                    else if(event.getCode() == KeyCode.UP){
                        serp.moverArriba();
                    }
                    else if(event.getCode() == KeyCode.DOWN){
                        serp.moverAbajo();
                    }
                }
            });
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(kf);

        timeline.play();
        escenario.show();
    }
}

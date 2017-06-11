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
    private ArrayList<Serpiente> serpientes;
    private Manzana[] manzanas;

    public static void main(String args[]){
        launch(args);
    }

    public void start(Stage escenario){
        // Creamos el contenedor y la escena con sus características
        manzanas = new Manzana[1];
        Group contenedor = new Group();
        Scene escena = new Scene(contenedor, ANCHO_VENTANA, ALTO_VENTANA);
        escena.setFill(Color.GREEN);

        // Creamos la serpiente
        serpientes = new ArrayList<>();
        Serpiente serp = new Serpiente((int) escena.getWidth(), 0, (int) escena.getHeight(), 0);
        contenedor.getChildren().add(serp);
        serpientes.add(serp);

        for(int i = 0; i < LONGITUD_SERPIENTE; i++){
            Serpiente serpi = new Serpiente((int) escena.getWidth(), 0, (int) escena.getHeight(), 0);
            serpi.setX(serpientes.get(i).getX() + 17);
            serpientes.add(serpi);
            contenedor.getChildren().add(serpi);
        }

        puntuacion = 0;
        Label puntos = new Label("Puntuación: " + puntuacion);
        puntos.setTranslateX(600);
        puntos.setTranslateY(460);
        puntos.setTextFill(Color.WHITE);

        // Se crean las etiquetas para el tiempo y la puntuacion
        Manzana manz = new Manzana(ANCHO_VENTANA - 20, ALTO_BARRA_INFERIOR);
        contenedor.getChildren().add(manz);
        manzanas[0] = manz;
        Timeline timeline = new Timeline();
        KeyFrame kf = new KeyFrame((Duration.seconds(0.017)), event ->{

                    for(Serpiente sierpe : serpientes){

                        sierpe.mover();
                        if(manzanas[0].comprobarChoque(serp) == true){
                            manz.setVisible(false);
                            contenedor.getChildren().remove(manzanas[0]);

                            manzanas[0] = null;
                            puntuacion = puntuacion + 10;
                        }
                    }

                });
        // Añadimos todos los elementos creados al contenedor y la escena y mostramos su contenido
        escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    for(Serpiente sierpe : serpientes){
                        sierpe.mover();
                        if (event.getCode() == KeyCode.LEFT) {
                            sierpe.moverALaIzquierda();
                        }
                        else if(event.getCode() == KeyCode.RIGHT){
                            sierpe.moverALaDerecha();
                        }
                        else if(event.getCode() == KeyCode.UP){
                            sierpe.moverArriba();
                        }
                        else if(event.getCode() == KeyCode.DOWN){
                            sierpe.moverAbajo();
                        }
                    }

                }
            });
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(kf);
        contenedor.getChildren().add(puntos);
        timeline.play();
        escenario.setScene(escena);
        escenario.show();
    }
}

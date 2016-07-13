/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectopa_final;

import Jogo.GestorJogo;
import MVC.Controller.FXMLMenuInicialController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author David
 */
public class ProjectoPA_Final extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MVC/View/FXMLMenuInicial.fxml"));
        Parent root = (Parent) loader.load();
        FXMLMenuInicialController myController = loader.getController();

//        Canvas area = new Canvas(590, 590);
//        GraphicsContext gc = area.getGraphicsContext2D();
//        paint(gc);
//        StackPane sp = new StackPane();
//        
//        sp.getChildren().add(root);
//        sp.getChildren().add(area);
//        
        Scene scene = new Scene(root);
        stage.setTitle("Solitário");
        stage.setMaxHeight(400);
        stage.setMaxWidth(700);
        stage.setMinHeight(400);
        stage.setMinWidth(700);
        stage.setScene(scene);
        
        stage.show();
        final GestorJogo gj = new GestorJogo();
        
        //gj.lerFile();
        myController.inicializarInfo(gj);
        
//        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            public void handle(WindowEvent we) {
//                    gj.escreverFile();
//            }
//        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

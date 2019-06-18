/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Samuel
 */
public class TelaInicial extends Application {
    
    private static Stage stage;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }    

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLTelaInicial.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("StockManagement_Biblioteca");
        stage.setScene(scene);
        stage.getIcons().add(new Image("img/bibliotecaIcone.png"));
        stage.show();
        setStage(stage);
    }
    
    public static void setStage( Stage stage ){
        TelaInicial.stage = stage;
    }
    
    public static Stage getStage(){
        return TelaInicial.stage;
    }
}

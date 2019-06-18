/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class FXMLTelaSecundariaController implements Initializable {
    
    @FXML
    private Label label_tipoDeUsuario;

    @FXML
    private Label label_nomeUsuario;

    @FXML
    private Label label_hora;

    @FXML
    private Label hora;

    @FXML
    private Label label_data;

    @FXML
    private Label data;

    @FXML
    private Label label_descricaoBusca;

    @FXML
    private Label label_busca;

    @FXML
    private TextField textField_busca;

    @FXML
    private MenuButton menuButton_atributo;

    @FXML
    private Button button_pesquisar;

    @FXML
    private Button button_limpar;

    @FXML
    private TableView<?> tableView_busca;

    @FXML
    private TableColumn<?, ?> column_atributo1;

    @FXML
    private TableColumn<?, ?> column_atributo2;

    @FXML
    private TableColumn<?, ?> column_atributo3;

    @FXML
    private TableColumn<?, ?> column_atributo4;

    @FXML
    private TableColumn<?, ?> column_atributo5;

    @FXML
    private TableColumn<?, ?> column_atributo6;

    @FXML
    private TableColumn<?, ?> column_atributo7;

    @FXML
    private Separator separator_busca;

    @FXML
    private Button button_cadastrar;

    @FXML
    private Button button_alterar;

    @FXML
    private Button button_remover;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializaHoraData();
    }    

    private void inicializaHoraData(){
    
        LocalDateTime dt = LocalDateTime.now();
        String dataAtual = dt.getDayOfMonth() + "/" + dt.getMonth() + "/" + dt.getYear();
        String horaAtual = dt.getHour() + ":" + dt.getMinute();
        data.setText(dataAtual);
        hora.setText(horaAtual);
    }
    
    @FXML
    void altera(ActionEvent event) {

    }

    @FXML
    void cadastra(ActionEvent event) {

    }

    @FXML
    void remove(ActionEvent event) {

    }
    
     
}

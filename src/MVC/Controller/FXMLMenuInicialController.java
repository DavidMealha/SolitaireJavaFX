/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Controller;

import Jogo.GestorJogo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author David
 */
public class FXMLMenuInicialController implements Initializable {

    @FXML
    private TextField textFieldUsername;

    @FXML
    private Button newGameButton;

    @FXML
    private Button verHistoricoButton;

    @FXML
    private Button verRankingButton;

    @FXML
    private Button registoButton;

    @FXML
    private Button loginButton;

    @FXML
    private ComboBox pontuacaoOption;

    @FXML
    private ComboBox varianteOption;

    @FXML
    private ComboBox loggerOption;

    @FXML
    private Label warningLabel;

    private GestorJogo gestorJogo;
    //private GestorJogo gestorJogo = new GestorJogo();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pontuacaoOption.getItems().addAll("Normal mode", "Hard mode");
        varianteOption.getItems().addAll("Base", "Mão de três", "Infantil");
        loggerOption.getItems().addAll("Tipo jogo", "Tempo de jogodas", "Jogadas");
    }

    public void inicializarInfo(GestorJogo gj) {
        this.gestorJogo = gj;
    }

    @FXML
    public void adicionaPlayer(ActionEvent event) {

        String nome = textFieldUsername.getText();

        if (nome.equals("")) {
            throw new RuntimeException("Caixa de texto vazia");
        }

        boolean val = gestorJogo.registarJogador(nome);
        if (val) {
            warningLabel.setText("Registo feito com sucesso!");
        }else{
            warningLabel.setText("Insira um username diferente!");
        }
    }

    @FXML
    public void loginPlayer(ActionEvent event) {

        String nome = textFieldUsername.getText();

        if (nome.equals("")) {
            throw new RuntimeException("Caixa de texto vazia");
        }

        gestorJogo.loginJogo(nome);
        warningLabel.setText("Login efetuado!");
    }

    @FXML
    public void novoJogo(ActionEvent event) throws IOException {
        
        if (gestorJogo.getJogadorAtual() == null) {
        
            warningLabel.setText("Nenhum jogador está logado!");
        
        } else if (pontuacaoOption.getSelectionModel().isEmpty() || varianteOption.getSelectionModel().isEmpty() || loggerOption.getSelectionModel().isEmpty()) {
            
            warningLabel.setText("Opção de jogo em falta!");
        
        } else {

            int pont, var, log;
            pont = pontuacaoOption.getSelectionModel().getSelectedIndex()+1;
            var = varianteOption.getSelectionModel().getSelectedIndex()+1;
            log = loggerOption.getSelectionModel().getSelectedIndex()+1;
            
            gestorJogo.novoJogo(pont, var, log);

            criarJanelaJogo(new Stage());
            warningLabel.setText("");
        }
    }
    
     @FXML
    void seeHistorico(ActionEvent event) throws IOException {
         criarJanelaHistorico(new Stage());
    }

    @FXML
    void seeRanking(ActionEvent event) throws IOException {
        criarJanelaRanking(new Stage());
    }

    private FXMLLoader criarJanelaJogo(Window windowParent) throws IOException {
        Stage stage = new Stage();

        URL location = getClass().getResource("/MVC/View/FXMLDocument.fxml");

        FXMLLoader fl = new FXMLLoader();
        fl.setLocation(location);
        fl.load();
        Parent root = fl.getRoot();

        FXMLDocumentController myController = fl.getController();
        myController.receberInfo(gestorJogo, varianteOption.getSelectionModel().getSelectedIndex()+1);
        
        Stage modal_dialog = new Stage(StageStyle.DECORATED);
        modal_dialog.initModality(Modality.WINDOW_MODAL);
        modal_dialog.setTitle("Jogo Solitário");
        Scene scene = new Scene(root);
        modal_dialog.setScene(scene);
        modal_dialog.initOwner(windowParent);
        modal_dialog.show();
        return fl;
    }
    
    private FXMLLoader criarJanelaHistorico(Window windowParent) throws IOException {
        Stage stage = new Stage();

        URL location = getClass().getResource("/MVC/View/FXMLInterfaceHistorico.fxml");

        FXMLLoader fl = new FXMLLoader();
        fl.setLocation(location);
        fl.load();
        Parent root = fl.getRoot();

        FXMLInterfaceHistoricoController myController = fl.getController();
        myController.receberInfo(gestorJogo);
        
        Stage modal_dialog = new Stage(StageStyle.DECORATED);
        modal_dialog.initModality(Modality.WINDOW_MODAL);
        modal_dialog.setTitle("Jogo Solitário");
        Scene scene = new Scene(root);
        modal_dialog.setScene(scene);
        modal_dialog.initOwner(windowParent);
        modal_dialog.show();
        return fl;
    }
    
    private FXMLLoader criarJanelaRanking(Window windowParent) throws IOException {
        Stage stage = new Stage();

        URL location = getClass().getResource("/MVC/View/FXMLInterfaceRanking.fxml");

        FXMLLoader fl = new FXMLLoader();
        fl.setLocation(location);
        fl.load();
        Parent root = fl.getRoot();

        FXMLInterfaceRankingController myController = fl.getController();
        myController.receberInfo(gestorJogo);
        
        Stage modal_dialog = new Stage(StageStyle.DECORATED);
        modal_dialog.initModality(Modality.WINDOW_MODAL);
        modal_dialog.setTitle("Jogo Solitário");
        Scene scene = new Scene(root);
        modal_dialog.setScene(scene);
        modal_dialog.initOwner(windowParent);
        modal_dialog.show();
        return fl;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Controller;

import Jogo.GestorJogo;
import Jogo.Historico;
import PadroesSoftware.Iterator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author David
 */
public class FXMLInterfaceHistoricoController implements Initializable {

    
    @FXML
    private TextField textFieldName;

    @FXML
    private Button procurarButton;

    @FXML
    private ListView<String> listaHistorico;

    @FXML
    private Label warning;
    
    private GestorJogo gj;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    void procurarHistorico(ActionEvent event) {

        if(textFieldName.getText().isEmpty()){
            
            warning.setText("Insira o nome do jogador!");
        }else{
        
            String nome = textFieldName.getText();
            gj.getJogadorAtual().getHistorico().adicionarAoHistorico("lolz");
            Historico ht = gj.getHistoricoJogador(nome);
            if(ht == null){
                warning.setText("O jogador em questão não jogou nenhum partida.");
            }else{
                ObservableList<String> items =FXCollections.observableArrayList();
                Iterator it = ht.getHistorico().getIterator();
                while(it.hasNext()){
                    items.add(it.next().toString());
                }
                listaHistorico.setItems(items);
            }
        }
        
    }
    
    public void receberInfo(GestorJogo gj){
        this.gj = gj;
    }
    
    
}

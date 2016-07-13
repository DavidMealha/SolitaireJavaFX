/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Controller;

import Jogo.Enumerados;
import Jogo.GestorJogo;
import PadroesSoftware.Iterator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author David
 */
public class FXMLInterfaceRankingController implements Initializable {

    @FXML
    private Button searchPontuacao;

    @FXML
    private Button searchVariante;
    
    @FXML
    private ListView<String> listaRanking;

    private GestorJogo gj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void receberInfo(GestorJogo gj) {
        this.gj = gj;
        
        listaRanking.getItems().removeAll();
        ObservableList<String> items =FXCollections.observableArrayList();
//        gj.getRanking().adicionarAoRanking("Joãozito - 1800 pontos", Enumerados.OpcaoEstrategiaUtilizada.PONTUACAO_1, Enumerados.OpcaoVarianteUtilizada.BASE);
//        gj.getRanking().adicionarAoRanking("Joãozito - 1900 pontos", Enumerados.OpcaoEstrategiaUtilizada.PONTUACAO_2, Enumerados.OpcaoVarianteUtilizada.INFANTIL);
//        gj.getRanking().adicionarAoRanking("Joãozito - 2000 pontos", Enumerados.OpcaoEstrategiaUtilizada.PONTUACAO_2, Enumerados.OpcaoVarianteUtilizada.MAO_DE_TRES);
//        gj.getRanking().adicionarAoRanking("Joãozito - 2100 pontos", Enumerados.OpcaoEstrategiaUtilizada.PONTUACAO_1, Enumerados.OpcaoVarianteUtilizada.MAO_DE_TRES);
//        gj.getRanking().adicionarAoRanking("Joãozito - 2200 pontos", Enumerados.OpcaoEstrategiaUtilizada.PONTUACAO_1, Enumerados.OpcaoVarianteUtilizada.BASE);
//        gj.getRanking().adicionarAoRanking("Joãozito - 2300 pontos", Enumerados.OpcaoEstrategiaUtilizada.PONTUACAO_2, Enumerados.OpcaoVarianteUtilizada.INFANTIL);
//        gj.getRanking().adicionarAoRanking("Joãozito - 2400 pontos", Enumerados.OpcaoEstrategiaUtilizada.PONTUACAO_2, Enumerados.OpcaoVarianteUtilizada.MAO_DE_TRES);
//        gj.getRanking().adicionarAoRanking("Joãozito - 2500 pontos", Enumerados.OpcaoEstrategiaUtilizada.PONTUACAO_1, Enumerados.OpcaoVarianteUtilizada.BASE);
//        
        for (int i = 0; i < gj.getRanking().getRankingJogadores().size(); i++) {
            items.add(gj.getRanking().getRankingJogadores().get(i).toString());
        }
        listaRanking.setItems(items);
        
    }
    
    @FXML
    void ordernarVariante(ActionEvent event) {

        gj.getRanking().ordenarPorVariante();

        listaRanking.getItems().removeAll();
        ObservableList<String> items =FXCollections.observableArrayList();
        
        for (int i = 0; i < gj.getRanking().getRankingJogadores().size(); i++) {
            items.add(gj.getRanking().getRankingJogadores().get(i).toString());
        }
        listaRanking.setItems(items);
    }


    @FXML
    void ordenarPontuacao(ActionEvent event) {

        gj.getRanking().ordenarPorEstrategia();

        listaRanking.getItems().removeAll();
        ObservableList<String> items =FXCollections.observableArrayList();
        
        for (int i = 0; i < gj.getRanking().getRankingJogadores().size(); i++) {
            items.add(gj.getRanking().getRankingJogadores().get(i).toString());
        }
        listaRanking.setItems(items);
        
    }


}

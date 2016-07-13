/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Controller;

import Jogo.Carta;
import Jogo.GestorJogo;
import Jogo.JogoSolitario.Jogo;
import Jogo.JogoSolitario.JogoSolitarioBase;
import Jogo.JogoSolitario.Memento;
import Jogo.MonteDeCartas;
import Jogo.MonteDeCartasSequencial;
import PadroesSoftware.EstrategiaPontuacao1;
import PadroesSoftware.Iterator;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author David
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Canvas areaJogo;

    @FXML
    private Label pontuacao;

    private GestorJogo gj;

    private Carta cartaAux;
    private MonteDeCartasSequencial monteAux;
    private int variante;
    private Memento backup;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        Jogo jg = new JogoSolitarioBase(new EstrategiaPontuacao1());
//        jg.distribuirCartas();
//        jg.desenharCartas(areaJogo.getGraphicsContext2D());
    }

    @FXML
    void escolherCarta(MouseEvent event) {

        backup = gj.getJogoAtual().save();

        double x = event.getX();
        double y = event.getY();

        if (x > 170 && x < 242 && y > 35 && y < 131) {
            cartaAux = gj.retirarCartaMonteLixo();
            //cartaAux = gj.getJogoAtual().retirarCartaMonteLixo();
        } else if (x > 70 && x < 142 & y > 35 && y < 131) {
            gj.virarCarta();
            redesenharBackground();
            gj.drawGame();

        } else {

            for (int i = 0; i < 7; i++) {
                if (gj.getJogoAtual().getColunas()[i].tamanhoMonteVisto() > 0) {
                    if (x > gj.getJogoAtual().getColunas()[i].getXUltimaCarta()
                            && x < gj.getJogoAtual().getColunas()[i].getXUltimaCarta() + 72
                            && y > gj.getJogoAtual().getColunas()[i].getYUltimaCarta()
                            && y < gj.getJogoAtual().getColunas()[i].getYUltimaCarta() + 96) {

                        cartaAux = gj.retirarCartaColuna(i + 1);
                        //cartaAux = gj.getJogoAtual().retirarCartaColuna(i + 1);

                    } else if (y < gj.getJogoAtual().getColunas()[i].getYUltimaCarta()
                            && x > gj.getJogoAtual().getColunas()[i].getXUltimaCarta()
                            && x < gj.getJogoAtual().getColunas()[i].getXUltimaCarta() + 72) {

                        double aux = (gj.getJogoAtual().getColunas()[i].getYUltimaCarta() - y);
                        int nrCartas = (int) Math.ceil(aux / 20);

                        monteAux = gj.retirarMonteColuna(i + 1, nrCartas + 1);
                        //monteAux = gj.getJogoAtual().retirarMonteColuna(i + 1, nrCartas + 1);

                    }
                }
//                } else if (gj.getJogoAtual().getColunas()[i].tamanhoMonteVisto() == 0) {
//                    if (x > 70 && x < 142 + (100 * i) && y > 190 && y < 190 + 96) {
//                        cartaAux = gj.getJogoAtual().retirarCartaColuna(i + 1);
//                    }
//                }
            }
        }

//        else {
//            int colNr = gj.getColunaEscolhida(x, y);
//            cartaAux = gj.getJogoAtual().retirarCartaColuna(colNr);
//        }
    }

    @FXML
    void moverCarta(MouseEvent event) {

        redesenharBackground();
        gj.drawGame();

        double cordX = event.getX() - 36;
        double cordY = event.getY();

        if (cartaAux != null) {
            cartaAux.desenhar(areaJogo.getGraphicsContext2D(), cordX, cordY - 48);
        } else {

            int count = 0;
            ArrayList<Carta> lct = new ArrayList<>();
            Iterator it = monteAux.getIterator();
            while (it.hasNext()) {
                lct.add((Carta) it.next());
            }

            for (int i = monteAux.tamanhoMonte() - 1; i >= 0; i--) {
                lct.get(i).desenhar(areaJogo.getGraphicsContext2D(), cordX, cordY + count);
                count += 20;
            }
        }

    }

    @FXML
    void libertarCarta(MouseEvent event) {

        double x = event.getX();
        double y = event.getY();

        boolean success = false;

        for (int i = 0; i < 4; i++) {
            if (x > 360 + (i * 100) && x < 442 + (100 * i) && y > 49 && y < 159) {

                success = gj.adicionarCartaCasaNaipe(cartaAux, i + 1);
                //gj.getJogoAtual().adicionarCartaCasaNaipe(cartaAux, i + 1);
            }
        }

        int colNr = gj.getColunaEscolhida(x, y);

        for (int i = 0; i < 7; i++) {
            //Caso o numero da coluna seja igual a i, irá adicionar a carta a essa coluna
            if (colNr == i + 1) {
                if (cartaAux != null) {
                    success = gj.adicionarCartaColuna(cartaAux, colNr);
                    //gj.getJogoAtual().adicionarCartaColuna(cartaAux, colNr);
                } else {
                    success = gj.adicionarMonteColuna(monteAux, colNr);
                    //gj.getJogoAtual().adicionarMonteColuna(monteAux, colNr);
                }
            }
        }
        
        if (!success) {
            gj.getJogoAtual().restore(backup);
            redesenharBackground();
            gj.drawGame();
            cartaAux = null;
            monteAux = null;
            //faz undo e mete o jogo como estava antes da jogada
        } else {
            redesenharBackground();
            gj.drawGame();
            cartaAux = null;
            monteAux = null;
            //completa jogada e desenha o novo game
        }

        pontuacao.setText("Pontuação : " + gj.getJogoAtual().getPontuacao());
    }

    @FXML
    void virarCarta(MouseEvent event) {

        if (event.getButton() == MouseButton.PRIMARY) {
            int x = (int) event.getX();
            int y = (int) event.getY();

//            if (x > 70 && x < 142 & y > 35 && y < 131) {
//                gj.virarCarta();
//                redesenharBackground();
//                gj.drawGame();
//            }
        } else if (event.getButton() == MouseButton.SECONDARY) {
            //botao direita para meter carta automaticamente na casa de baralho
        }

    }

    public void receberInfo(GestorJogo gj, int op) {
        this.gj = gj;
        this.gj.setAreaDeJogo(areaJogo);
        this.gj.drawGame();
        this.variante = op;
    }

    private void redesenharBackground() {

        GraphicsContext aux = areaJogo.getGraphicsContext2D();
        aux.clearRect(0, 0, areaJogo.getWidth(), areaJogo.getHeight());
        aux.drawImage(new Image("/images/mesa1.jpg"), 0, 0);
        aux.setStroke(Color.BLACK);
        aux.setFill(Color.rgb(52, 77, 58));

        for (int i = 0; i < 4; i++) {
            aux.fillRect(360 + (i * 100), 25, 82, 110);
            aux.strokeRect(360 + (i * 100), 25, 82, 110);
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.JogoSolitario;

import Jogo.Carta;
import Jogo.ColunaDeCartas;
import Jogo.MonteCartasSaiTres;
import Jogo.MonteDeCartas;
import Jogo.MonteDeCartasSequencial;
import PadroesSoftware.EstrategiaOrdenacaoMonteSequencialValor;
import PadroesSoftware.EstrategiaPontuacao;
import PadroesSoftware.Iterator;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author David
 */
public class JogoSolitarioSaiTres extends JogoSolitarioBase{

    public JogoSolitarioSaiTres(EstrategiaPontuacao estrategiaPontos) {
        super(estrategiaPontos);
        casaDoBaralho = new MonteCartasSaiTres();
        monteLixo = new MonteCartasSaiTres();
    }
 
    @Override
    public void inicializarColunas(){
        
        for (int i = 0; i < colunas.length; i++) {
            colunas[i] = new ColunaDeCartas(new EstrategiaOrdenacaoMonteSequencialValor());
        }
    }
    
    @Override
    public void virarCarta() {

        if (casaDoBaralho.monteVazio()) {
            for (int i = 0; i < monteLixo.tamanhoMonte(); i++) {
                casaDoBaralho.adicionarCarta(monteLixo.retiraCarta());
            }
        } else {
            Carta[] ct =casaDoBaralho.retirarCartas();
            for (int i = 0; i < ct.length; i++) {
                monteLixo.adicionarCarta(ct[i]);
            }
        }
    }
    
    @Override
    public void desenharCartas(GraphicsContext gc) {
        
        if (casaDoBaralho.monteVazio()) {
            gc.setStroke(Color.GREENYELLOW);
            gc.setLineWidth(5);
            gc.setFill(Color.RED);
            gc.strokeOval(90, 60, 45, 45);
            gc.fillOval(90, 60, 45, 45);
            gc.setLineWidth(2);
            
        } else {
            Iterator itx2 = casaDoBaralho.getIterator();
            while (itx2.hasNext()) {
                ((Carta) itx2.next()).desenharOculta(gc, 70, 35);
            }
        }

        if (!monteLixo.monteVazio()) {
            Carta[] ct = new Carta[3];
            ct = monteLixo.cartaTopo();
            for (int i = 0; i < 3; i++) {
                ct[i].desenhar(gc, 170+(20*i), 35);
            }
            //ct[0].desenhar(gc, 170, 35);
            
        }

        for (int i = 0; i < 7; i++) {

            double x = 100 * i;
            double count = 10;

            Iterator it = colunas[i].getCartasOcultas().getIterator();
            ArrayList<Carta> ocultas = new ArrayList<>();
            while (it.hasNext()) {
                ocultas.add((Carta) it.next());
            }
            for (int j = ocultas.size() - 1; j >= 0; j--) {
                ocultas.get(j).desenharOculta(gc, 70 + x, 190 + (count * 2));
                count += 10;
            }

            Iterator itx = colunas[i].getCartasVistas().getIterator();
            ArrayList<Carta> vistas = new ArrayList<>();
            while (itx.hasNext()) {
                vistas.add((Carta) itx.next());
            }
            for (int j = vistas.size() - 1; j >= 0; j--) {
                vistas.get(j).desenhar(gc, 70 + x, 190 + (count * 2));
                count += 10;
            }
        }
        
        for (int i = 0; i < 4; i++) {
            if(!casaDeNaipe[i].monteVazio()){
                casaDeNaipe[i].cartaTopo()[0].desenhar(gc, 365+(100*i), 32);
            }else{
                //gc.drawImage(new Image("/images/11.png"),365+(100*i), 32);
            }
        }
    }
    
}

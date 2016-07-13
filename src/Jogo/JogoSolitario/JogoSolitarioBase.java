/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo.JogoSolitario;

import Jogo.Baralho;
import Jogo.BaralhoSolitario;
import Jogo.Carta;
import Jogo.ColunaDeCartas;
import Jogo.ColunaInvalidaException;
import Jogo.MonteDeCartas;
import Jogo.MonteDeCartasSequencial;
import Jogo.OperacaoInvalidaException;
import PadroesSoftware.EstrategiaOrdenacaoMonteSequencialNaipe;
import PadroesSoftware.EstrategiaOrdenacaoMonteSequencialValor;
import PadroesSoftware.EstrategiaPontuacao;
import PadroesSoftware.Iterator;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author David
 */
public class JogoSolitarioBase extends Jogo {

    protected MonteDeCartas casaDoBaralho;
    protected MonteDeCartas monteLixo;
    protected MonteDeCartas[] casaDeNaipe;
    protected ColunaDeCartas[] colunas;
    protected EstrategiaPontuacao estrategiaPontos;

    public JogoSolitarioBase(EstrategiaPontuacao estrategiaPontos) {

        super(new BaralhoSolitario());
        super.getBaralhoDeJogo().baralhar();

        this.estrategiaPontos = estrategiaPontos;

        monteLixo = new MonteDeCartas();
        colunas = new ColunaDeCartas[7];
        casaDoBaralho = new MonteDeCartas();

        casaDeNaipe = new MonteDeCartasSequencial[4];
        for (int i = 0; i < casaDeNaipe.length; i++) {
            casaDeNaipe[i] = new MonteDeCartasSequencial(new EstrategiaOrdenacaoMonteSequencialNaipe());
        }

    }

    @Override
    public ColunaDeCartas[] getColunas() {
        return colunas;
    }
    
    public void inicializarColunas() {

        for (int i = 0; i < colunas.length; i++) {
            colunas[i] = new ColunaDeCartas(new EstrategiaOrdenacaoMonteSequencialValor());
        }

    }

    /**
     * Neste método é feita a distribuição das cartas que se apresentam no
     * baralho de jogo. São distribuidas cartas por cada coluna, de acordo com
     * as regras do jogo do solitário, e as restante ficam na casa de baralho.
     */
    @Override
    public void distribuirCartas() {

        inicializarColunas();

        //Distribuição das cartas pelas colunas
        Carta[] ct;
        for (int i = 0; i < 7; i++) {

            ct = super.getBaralhoDeJogo().retirarCartas(i + 1);

            for (int j = 0; j < ct.length; j++) {
                if (j < ct.length - 1) {
                    colunas[i].adicionarCartaOculta(ct[j]); //Adiciona length-1 cartas ocultas em cada coluna.
                } else {
                    colunas[i].adicionarCartaVista(ct[j]); //Adiciona a última carta retirada, e esta será a carta que poderá ser vista
                }
                //System.out.println("Coluna" + (i + 1) + " : " + ct[j].toString());
            }
        }

        //Restantes cartas ficam na casa de baralho
        int nrCartasRestantes = super.getBaralhoDeJogo().getNrCartas();
        Carta[] ctRestantes = super.getBaralhoDeJogo().retirarCartas(nrCartasRestantes);

        //Restantes carta  
        for (int i = 0; i < nrCartasRestantes; i++) {
            casaDoBaralho.adicionarCarta(ctRestantes[i]);
        }

    }

    /**
     * Quando o jogo termina é feito o calcula da pontuacao final.
     */
    @Override
    public void calcularPontuacaoFinal() {

        int time = super.calcTempoJogo();
        int ptAux = estrategiaPontos.jogadaFinal(time);
        super.setPontuacao(super.getPontuacao() + ptAux);

    }

    /**
     * Função onde é feita a adição de uma carta a
     *
     * @param carta
     * @param nrColuna
     */
    @Override
    public boolean adicionarCartaColuna(Carta carta, int nrColuna) {

        if (nrColuna < 1 && nrColuna > 7) {
            throw new ColunaInvalidaException();
        }

        ColunaDeCartas col = colunas[nrColuna - 1];
        boolean val;

        if (col.tamanhoMonteOculto() == 0 && col.tamanhoMonteVisto() == 0 && carta.getValorFace() == 13) {
            val = col.adicionarCartaVista(carta);
        } else {
            val = col.adicionarCartaVista(carta);
        }

        //Caso a carta tenha sido adicionada com sucesso à coluna
        if (val) {
            int ptAux = estrategiaPontos.calcularPontuacao(1);
            super.setPontuacao(super.getPontuacao() + ptAux);
            super.contarJogada();
            col.setXUltimaCarta(col.getXUltimaCarta()+20);
            col.setYUltimaCarta(col.getYUltimaCarta()+20);
            
            return true;
        } else {
            throw new OperacaoInvalidaException();
        }

    }

    /**
     * Método com a função de tirar a carta no topo de uma certa coluna
     *
     * @param nrColuna
     * @return carta retirada
     */
    @Override
    public Carta retirarCartaColuna(int nrColuna) {

        if (nrColuna < 1 && nrColuna > 7) {
            throw new ColunaInvalidaException();
        }

        ColunaDeCartas col = colunas[nrColuna - 1];
        Carta ct = null;

        if (col.tamanhoMonteVisto() == 1) {
            ct = col.retirarCartaTopo();
            verificaColuna(nrColuna);
        } else if(col.tamanhoMonteVisto() > 1){
            ct = col.retirarCartaTopo();
        }

        return ct;
    }

    /**
     * Método onde é feita a adição de um monte a uma coluna
     *
     * @param monte
     * @param nrColuna
     */
    @Override
    public boolean adicionarMonteColuna(MonteDeCartasSequencial monte, int nrColuna) {

        if (nrColuna < 1 && nrColuna > 7) {
            throw new ColunaInvalidaException();
        }

        ColunaDeCartas col = colunas[nrColuna - 1];
        boolean val = col.adicionarMonte(monte);

        //Caso a carta tenha sido adicionada com sucesso à coluna
        if (val) {
            int nrCartas = monte.tamanhoMonte();
            int ptAux = 0;

            for (int i = 0; i < nrCartas; i++) {
                ptAux += estrategiaPontos.calcularPontuacao(1);
            }
            super.setPontuacao(super.getPontuacao() + ptAux);
            super.contarJogada();

            return true;
        } else {
            throw new OperacaoInvalidaException();
        }

    }

    /**
     * Função para remover um monte de cartas de uma certa coluna
     *
     * @param nrColuna
     * @param sizeMonte
     * @return o monte retirado
     */
    @Override
    public MonteDeCartasSequencial retirarMonteColuna(int nrColuna, int sizeMonte) {

        if (nrColuna < 1 && nrColuna > 7) {
            throw new ColunaInvalidaException();
        }

        ColunaDeCartas col = colunas[nrColuna - 1];
        MonteDeCartasSequencial mt = null;
        
        if (sizeMonte > col.tamanhoMonteVisto()) {
            throw new OperacaoInvalidaException();
        }else if (sizeMonte == col.tamanhoMonteVisto()) {
             mt = col.retirarMonte(sizeMonte);
            verificaColuna(nrColuna);
        }

        return mt;
    }

    /**
     * Método responsável pela adição de uma carta a uma casa de naipe, a carta
     * pode pertencer ao baralho ou a uma das colunas
     *
     * @param carta
     * @param nrCasa
     */
    @Override
    public boolean adicionarCartaCasaNaipe(Carta carta, int nrCasa) {

        if (nrCasa < 1 && nrCasa > 4) {
            throw new ColunaInvalidaException();
        }
        MonteDeCartas mt = casaDeNaipe[nrCasa - 1];
        int x = mt.tamanhoMonte();
        mt.adicionarCarta(carta);
        int y = mt.tamanhoMonte();

        if (x + 1 == y) { //Caso a carta tenha sido adicionada com sucesso
            int ptAux;

            if (casaDeNaipe[nrCasa - 1].tamanhoMonte() == 13) {

                System.out.println("Sequência completa!");
                ptAux = estrategiaPontos.calcularPontuacao(3);
                super.setPontuacao(super.getPontuacao() + ptAux);
                super.contarJogada();

                boolean val = verificaCasasNaipe();
                if (val) {
                    System.out.println("Jogo terminado");
                }

            } else {

                ptAux = estrategiaPontos.calcularPontuacao(2);
                super.setPontuacao(super.getPontuacao() + ptAux);
                super.contarJogada();

            }
            
            return true;
        } else {
            throw new OperacaoInvalidaException();
        }
    }

    /**
     * Neste método corresponde à função de quando o utilizador pretende virar
     * as cartas do baralho, de acordo com a variante escolhida o utilizador
     * vira 1 ou 3 cartas. Caso o baralho esteja vazio, as carta do monte de
     * lixo são repostas no baralho, baralhadas, para serem retiradas novamente.
     */
    @Override
    public void virarCarta() {

        if (casaDoBaralho.monteVazio()) {
            while(!monteLixo.monteVazio()){
                casaDoBaralho.adicionarCarta(monteLixo.retiraCarta());
            }
        } else {
            monteLixo.adicionarCarta(casaDoBaralho.retiraCarta());
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Carta retirarCartaMonteLixo() {

        if (!monteLixo.monteVazio()) {
            return monteLixo.retiraCarta();
        } else {
            throw new OperacaoInvalidaException();
        }
    }

    @Override
    public Memento save() {
        return new Memento(super.getBaralhoDeJogo(), pontuacao, nrJogadas, tempoInicial, casaDoBaralho, monteLixo, casaDeNaipe, colunas);
    }

    @Override
    public void restore(Object objMemento) {
        Memento memento = (Memento) objMemento;
        super.setBaralhoDeJogo(memento.baralhoDeJogo);
        super.pontuacao = memento.pontuacao;
        super.nrJogadas = memento.nrJogadas;
        super.tempoInicial = memento.tempoInicial;
        this.casaDoBaralho = memento.casaDoBaralho;
        this.monteLixo = memento.monteLixo;
        this.casaDeNaipe = memento.casaDeNaipe;
        this.colunas = memento.colunas;
    }

    /**
     * Numa certa coluna irá verificar se é necessario virar uma carta do monte
     * oculto
     *
     * @param nrCol
     */
    private void verificaColuna(int nrCol) {
        colunas[nrCol - 1].verificaMonteSequencial();
    }

    /**
     * Método para verificar se todas as casas de naipe estão completas, se
     * todas estiverem o jogo foi terminado.
     *
     * @return true caso todas as casas de naipe estejam todas preenchidas, caso
     * contrario retorna false
     */
    private boolean verificaCasasNaipe() {

        int contador = 0;
        for (int i = 0; i < casaDeNaipe.length; i++) {
            if (casaDeNaipe[i].tamanhoMonte() == 13) {
                contador++;
            }
        }
        if (contador == 4) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {

        String mostrar = "Jogo do solitário \n" + "\n";

        mostrar += "Casas de naipe :";

        for (int i = 0; i < 4; i++) {
            if (!casaDeNaipe[i].monteVazio()) {
                mostrar += casaDeNaipe[i].cartaTopo()[0].toString();
            } else {
                mostrar += "( Casa vazia )";
            }
        }

        if (!monteLixo.monteVazio()) {
            mostrar += "\nCarta do baralho :" + monteLixo.cartaTopo()[monteLixo.cartaTopo().length - 1].toString() + "\n\n";
        } else {
            mostrar += "\nCartas por virar...\n";
        }

        for (int i = 0; i < colunas.length; i++) {
            mostrar += "Coluna" + (i + 1) + ":" + colunas[i].toString() + "\n";
        }

        //mostrar += "Carta disponivel: " + monteLixo.toString();
        return mostrar;

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
            Carta[] ct = monteLixo.cartaTopo();
            ct[0].desenhar(gc, 170, 35);
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
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        JogoSolitarioBase jg = (JogoSolitarioBase) super.clone();
        Object ob = super.clone();
        return null;
    }

}

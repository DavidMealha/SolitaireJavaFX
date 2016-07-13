/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import Jogo.JogoSolitario.Jogo;
import Jogo.JogoSolitario.JogoSolitarioBase;
import Jogo.JogoSolitario.JogoSolitarioInfantil;
import Jogo.JogoSolitario.JogoSolitarioSaiTres;
import PadroesSoftware.EstrategiaPontuacao;
import PadroesSoftware.EstrategiaPontuacao1;
import PadroesSoftware.EstrategiaPontuacao2;
import PadroesSoftware.ReporterJogadas;
import PadroesSoftware.ReporterTempoJogadas;
import PadroesSoftware.ReporterTipoJogo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;

/**
 *
 * @author David
 */
public class GestorJogo {

    private Ranking ranking;
    private ArrayList<Jogador> jogadores;
    private Jogador jogadorAtual;
    private Jogo jogoAtual;
    private ReporterTipoJogo logTipoJogo;
    private ReporterTempoJogadas logTempoJogadas;
    private ReporterJogadas logJogadas;
    private int opLogger;
    private Canvas areaDeJogo;
    private long timer;

    private static final String FILE = "data.dat";
    File myFile = new File(FILE);
    ObjectOutputStream outputstream;
    ObjectInputStream inputstream;

    public GestorJogo() {
        ranking = new Ranking();
        jogadores = new ArrayList();
    }

    public Jogador getJogadorAtual() {
        return jogadorAtual;
    }

    public Jogo getJogoAtual() {
        return jogoAtual;
    }
    
    public Ranking getRanking(){
        return ranking;
    }

    public void setAreaDeJogo(Canvas areaDeJogo) {
        this.areaDeJogo = areaDeJogo;
    }

    /**
     * Método que consiste no registo de um novo jogador no jogo.
     *
     * @param nome
     * @return
     */
    public boolean registarJogador(String nome) {

        if (nome != null && getJogador(nome) == null) {
            Jogador novoJogador = new Jogador(nome);
            jogadores.add(novoJogador);
            System.out.println("Registo feito com sucesso!");
            return true;
        }
        return false;
    }

    /**
     * Método para efetuar o login de um jogador
     *
     * @param nomeJogador
     * @return o jogador que efetuou o login
     */
    public Jogador loginJogo(String nomeJogador) {

        for (int i = 0; i < jogadores.size(); i++) {
            if (jogadores.get(i).getLoginName().equals(nomeJogador)) {
                System.out.println("Login feito com sucesso!");
                jogadorAtual = jogadores.get(i);
                return jogadores.get(i);
            } else {
                throw new JogadorNaoRegistadoException();
            }
        }
        throw new SistemaSemJogadoresException();

    }

    /**
     * Verifica se já existe um certo jogador registado, através do nome. Se
     * existir retorna esse jogador(Objecto), senão retorna null.
     *
     * @param nome
     * @return
     */
    private Jogador getJogador(String nome) {
        for (int i = 0; i < jogadores.size(); i++) {
            if (jogadores.get(i).getLoginName().equals(nome)) {
                return jogadores.get(i);
            }
        }
        return null;
    }

    /**
     * Método que consiste na consulta do historico diário de um jogador, de um
     * data especifica inserida pelo utilizador.
     *
     * @param nomeJogador
     * @param data
     */
    public void consultarHistoricoDiario(String nomeJogador, String data) {

        Historico h = getHistoricoJogador(nomeJogador);
        boolean val = h.isHistoricoEmpty();

        if (!val) {
            //metodo para verificar data
            Historico aux = h.historicoDiario(data);
            boolean val2 = aux.isHistoricoEmpty();
            if (!val2) {
                System.out.println(aux.toString());
            }
        }
    }

    /**
     * Método que consiste na consulta do historico durante um certo espaço de
     * tempo.
     *
     * @param nomeJogador
     * @param qtdDias
     * @return
     */
    public Historico consultarHistoricoUltDias(String nomeJogador, int qtdDias) {

        Historico h = getHistoricoJogador(nomeJogador);
        boolean val = h.isHistoricoEmpty();

        if (val == false && qtdDias > 0) {
            Historico aux = h.historicoUltimosDias(qtdDias);
            boolean val2 = aux.isHistoricoEmpty();
            if (val2 == false) {
                return aux;
            }
        }
        return null;
    }

    /**
     * Consulta do historico de um jogador durante o mês atual.
     *
     * @param nomeJogador
     */
    public void consultarHistoricoMes(String nomeJogador) {

        Historico h = getHistoricoJogador(nomeJogador);
        boolean val = h.isHistoricoEmpty();

        if (val == false) {
            Historico aux = h.historicoMesCorrente();
            boolean val2 = aux.isHistoricoEmpty();
            if (val2 == false) {
                System.out.println(aux.toString());
            }
        }
    }

    /**
     * Método que devolve o historico de um jogador, caso não esteja vazio.
     *
     * @param nomeJogador
     * @return
     */
    public Historico getHistoricoJogador(String nomeJogador) {
        if (nomeJogador != null) {
            Jogador j = getJogador(nomeJogador);
            if (j != null) {
                return j.getHistorico();
            }
        }
        return null;
    }

    /**
     * Metodo onde se inicia um novo jogo.
     *
     * @param opPont parametro correspondente à estrategia de pontuação.
     * @param opVariante parametro correspondente à versão que o jogador vai
     * jogar.
     * @param opLogger parametro correspondente ao tipo de log que o utilizador
     * pretende guardar em ficheiro.
     */
    public void novoJogo(int opPont, int opVariante, int opLogger) {

        //jogadorAtual = loginJogo(nomeJogador);
        EstrategiaPontuacao estPont = null;

        if (opPont == 1) {
            estPont = new EstrategiaPontuacao1();
        } else if (opPont == 2) {
            estPont = new EstrategiaPontuacao2();
        }

        if (opVariante == 1) {
            jogoAtual = new JogoSolitarioBase(estPont);
        } else if (opVariante == 2) {
            jogoAtual = new JogoSolitarioSaiTres(estPont);
        } else if (opVariante == 3) {
            jogoAtual = new JogoSolitarioInfantil(estPont);
        }

        this.opLogger = opLogger;
        //1-tipo de jogo 2-tempo das jogadas 3-jogadas
        if (opLogger == 1) {
            logTipoJogo = new ReporterTipoJogo();
            jogoAtual.addObserver(logTipoJogo);

            String aux = null;
            switch (opVariante) {
                case 1:
                    aux = "Jogo do solitário versão base";
                    break;
                case 2:
                    aux = "Jogo do solitário versão sai três";
                    break;
                case 3:
                    aux = "Jogo do solitário versão infantil";
                    break;

            }
            jogoAtual.hasChanged();
            jogoAtual.notifyObservers();
            logTipoJogo.update(jogoAtual, aux);

        } else if (opLogger == 2) {
            logTempoJogadas = new ReporterTempoJogadas();
            jogoAtual.addObserver(logTempoJogadas);

        } else if (opLogger == 3) {
            logJogadas = new ReporterJogadas();
            jogoAtual.addObserver(logJogadas);

        }

        jogoAtual.distribuirCartas();
        mostrarJogo();
    }

    /**
     * Metodo para retirar o carta do topo do monte de lixo.
     *
     * @return
     */
    public Carta retirarCartaMonteLixo() {

        Carta ct = jogoAtual.retirarCartaMonteLixo();
        String x = "";

        jogoAtual.hasChanged();
        jogoAtual.notifyObservers();

        switch (opLogger) {
            case 2:
                timer = System.currentTimeMillis();
                break;
            case 3:
                x = "A carta " + ct.toString() + " foi retirada do monte lixo.";
                logJogadas.update(jogoAtual, x);
                break;
        }

        return ct;
    }

    /**
     * Método para retirar uma carta de um coluna especifica
     *
     * @param nrColuna
     * @return
     */
    public Carta retirarCartaColuna(int nrColuna) {

        Carta ct = jogoAtual.retirarCartaColuna(nrColuna);
        String x = "";

        jogoAtual.hasChanged();
        jogoAtual.notifyObservers();

        switch (opLogger) {
            case 2:
                timer = System.currentTimeMillis();
                break;
            case 3:
                x = "A carta " + ct.toString() + " foi retirada da coluna " + nrColuna;
                logJogadas.update(jogoAtual, x);
                break;
        }

        return ct;
    }

    /**
     * Metodo onde se remove uma monte especifica de uma coluna especifica.
     *
     * @param nrColuna
     * @param nrCartas
     * @return
     */
    public MonteDeCartasSequencial retirarMonteColuna(int nrColuna, int nrCartas) {

        MonteDeCartasSequencial mt = jogoAtual.retirarMonteColuna(nrColuna, nrCartas);

        String x = "";

        jogoAtual.hasChanged();
        jogoAtual.notifyObservers();

        switch (opLogger) {
            case 2:
                timer = System.currentTimeMillis();
                break;
            case 3:
                x = "Um monte com as cartas : " + mt.toString() + " foi retirado da coluna " + nrColuna;
                logJogadas.update(jogoAtual, x);
                break;
        }

        return mt;
    }

    /**
     * Metodo para adicionar uma certa carta a uma casa de naipe
     * @param ct
     * @param nrCasa
     * @return 
     */
    public boolean adicionarCartaCasaNaipe(Carta ct, int nrCasa) {

        boolean val = jogoAtual.adicionarCartaCasaNaipe(ct, nrCasa);

        if (val) {
            String x = "";

            jogoAtual.hasChanged();
            jogoAtual.notifyObservers();

            switch (opLogger) {
                case 2:
                    long curTime = (System.currentTimeMillis() - timer) / 1000;
                    x = "Jogada com a duração de " + curTime + " segundos.";
                    logTempoJogadas.update(jogoAtual, x);
                    break;
                case 3:
                    x = "A carta " + ct.toString() + " foi adicionada à casa de naipe " + nrCasa;
                    logJogadas.update(jogoAtual, x);
                    break;
            }
        }

        return val;
    }

    /**
     * Metodo para adicionar uma carta a uma certa coluna.
     * @param ct
     * @param nrColuna
     * @return 
     */
    public boolean adicionarCartaColuna(Carta ct, int nrColuna){
        
        boolean val = jogoAtual.adicionarCartaColuna(ct, nrColuna);

        if (val) {
            String x = "";

            jogoAtual.hasChanged();
            jogoAtual.notifyObservers();

            switch (opLogger) {
                case 2:
                    long curTime = (System.currentTimeMillis() - timer) / 1000;
                    x = "Jogada com a duração de " + curTime + " segundos.";
                    logTempoJogadas.update(jogoAtual, x);
                    break;
                case 3:
                    x = "A carta " + ct.toString() + " foi adicionada à coluna " + nrColuna;
                    logJogadas.update(jogoAtual, x);
                    break;
            }
        }

        return val;
        
    }
    
    /**
     * Método para adicionar um monte a uma certa coluna
     * @param mt
     * @param nrColuna
     * @return 
     */
    public boolean adicionarMonteColuna(MonteDeCartasSequencial mt, int nrColuna){
        
        boolean val = jogoAtual.adicionarMonteColuna(mt, nrColuna);

        if (val) {
            String x = "";

            jogoAtual.hasChanged();
            jogoAtual.notifyObservers();

            switch (opLogger) {
                case 2:
                    long curTime = (System.currentTimeMillis() - timer) / 1000;
                    x = "Jogada com a duração de " + curTime + " segundos.";
                    logTempoJogadas.update(jogoAtual, x);
                    break;
                case 3:
                    x = "O monte " + mt.toString() + " foi adicionada à coluna " + nrColuna;
                    logJogadas.update(jogoAtual, x);
                    break;
            }
        }
        return val;
        
    }

    /**
     *
     */
    public void virarCarta() {
        jogoAtual.virarCarta();

        jogoAtual.hasChanged();
        jogoAtual.notifyObservers();

        String x = null;
        switch (opLogger) {
            case 2:
                break;
            case 3:
                x = "Foi virada uma carta";
                logJogadas.update(jogoAtual, x);
                break;
        }

        //mostrarJogo();
    }

    /**
     * Método para imprimir as colunas de jogo.
     */
    private void mostrarJogo() {
        System.out.println(jogoAtual.toString());
    }

    /**
     * Método que desenha todas as cartas em cada um dos montes
     */
    public void drawGame() {
        jogoAtual.desenharCartas(areaDeJogo.getGraphicsContext2D());
        mostrarJogo();
    }
    
    public int getColunaEscolhida(double x, double y) {

        for (int i = 0; i < 7; i++) {
            if (jogoAtual.getColunas()[i].tamanhoMonteVisto() > 0) {
                if (x > jogoAtual.getColunas()[i].getXUltimaCarta()
                        && x < jogoAtual.getColunas()[i].getXUltimaCarta() + 72
                        && y > jogoAtual.getColunas()[i].getYUltimaCarta()
                        && y < jogoAtual.getColunas()[i].getYUltimaCarta() + 96) {

                    return i + 1;
                }
            } else if (jogoAtual.getColunas()[i].tamanhoMonteVisto() == 0) {
                if (x > 70 && x < 142 + (100 * i) && y > 190 && y < 190 + 96) {
                    return i + 1;
                }
            }
        }
        return -1;
    }

    /**
     * Método para guardar toda a informação num ficheiro. Guarda a lista de
     * jogadores registados e o ranking num ficheiro.
     */
    public void lerFile() throws IOException {
        
//        if(myFile == null){
//            return;
//        }
        
        ArrayList ar = new ArrayList();
        
        try {
            inputstream = new ObjectInputStream(new FileInputStream(myFile));
            if(inputstream.readObject().equals(null)){
                return;
            }
            ar = (ArrayList) inputstream.readObject();
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (inputstream != null) {
                    inputstream.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        
        ranking = (Ranking) ar.get(0);//posicao onde e guardada o ranking
        jogadores = (ArrayList<Jogador>) ar.get(1);//posicao onde e guarda a lista de jogadores

    }

    /**
     * Método para ir buscar a informação guardada em ficheiro.
     */
    public void escreverFile() {

//        if(myFile == null){
//            myFile = new File(FILE);
//            //return;
//        }
//        
        ArrayList ar = new ArrayList();
        ar.add(ranking);
        ar.add(jogadores);
        
        try {
            outputstream = new ObjectOutputStream(new FileOutputStream(myFile));
            outputstream.writeObject(ar);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (outputstream != null) {
                    outputstream.flush();
                    outputstream.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        
    }
}

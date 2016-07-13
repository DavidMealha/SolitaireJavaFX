/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import PadroesSoftware.Iterator;
import TadDeque.Deque;
import TadDeque.DequeCircular;
import TadDeque.DequeDinamic;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author David
 */
public class Historico<E> implements InterfaceHistorico<E>, Serializable {

    private Deque<ObjectoHistorico> adaptee;

    public Historico() {
        adaptee = new DequeDinamic<>();
    }

    public Deque<ObjectoHistorico> getHistorico() {
        return adaptee;
    }

    /**
     * Método auxiliar que devolver a atual data do sistema.
     *
     * @return
     */
    private String getDataSistema() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        return df.format(data);
    }
    
    /**
     * Método que verifica se o historico está vazio ou não
     * @return true se estiver vazio, caso contrario retorna false.
     */
    @Override
    public boolean isHistoricoEmpty(){
        return adaptee.isEmpty();
    }

    /**
     * Neste método adiciona-se um elemento ao historico.
     *
     * @param elem
     */
    @Override
    public void adicionarAoHistorico(E elem) {
        if (elem != null) {

            String data = getDataSistema();
            ObjectoHistorico e = new ObjectoHistorico(elem, data);
            adaptee.addFirst(e);

        }
    }

    /**
     * Método que irá fornecer todos os elementos no histórico de um determinado
     * dia.
     *
     * @param data
     * @return
     */
    @Override
    public Historico historicoDiario(String data) throws RuntimeException {

        if (!isDataValida(data) && !isFormatoDataCorrecto(data)) {
            throw new RuntimeException("Data escrita de maneira incorrecta");
        }

        Historico temp = new Historico();

        Iterator it = adaptee.getIteratorInverted();
        ObjectoHistorico oh;

        while (it.hasNext()) {
            oh = (ObjectoHistorico) it.next();
            if (oh.getDataEntrada().equals(data)) {
                temp.adicionarAoHistorico(oh);
            }
        }

        return temp;

    }

    /**
     * Método que irá fornecer todos os elementos no histórico dos últimos dias.
     * Havendo a possibilidade de escolher a quantidade dias.
     *
     * @param quantidadeDias
     * @return
     */
    @Override
    public Historico historicoUltimosDias(int quantidadeDias) {

        String dataAtual = getDataSistema();
        int diaAtual = Integer.valueOf(dataAtual.substring(0, 2));
        String mesAtual = dataAtual.substring(3, 5);
        String anoAtual = dataAtual.substring(6, 10);

        int diaAnterior = 0, diaDataEntrada, y = 0;
        Historico historicoDiasAnteriores = new Historico();
        String dataObtida;

        Iterator it = adaptee.getIterator();
        
        // São verificados todos os elementos quando a quantidade de dias é 
        // menor ao dia atual.
        if (diaAtual >= quantidadeDias) {
            while (it.hasNext()) {
                // vai buscar a data de cada elemento do historico
                dataObtida = ((ObjectoHistorico) it.next()).getDataEntrada(); 
                
                if (diaAnterior > 0) {
                    //anda para tras, de modo a ver quais são os dias que devem estar no histórico
                    diaAnterior = diaAtual - y; 
                    y++;
                }
                
                //é guardada num inteiro o dia da data de entrada de cada elemento do historico
                diaDataEntrada = Integer.valueOf(dataObtida.substring(0, 2)); 
                
                if (diaDataEntrada == diaAnterior && dataObtida.substring(3, 5).equals(mesAtual) && dataObtida.substring(6, 10).equals(anoAtual)) {
                    historicoDiasAnteriores.adicionarAoHistorico(it.next());
                }
            }
            return historicoDiasAnteriores;
            
        }else {

            int diasRestantes = quantidadeDias - diaAtual;

        }

        return null;

    }

    /**
     * Método que irá fornecer todos os elementos no histórico do presente mês.
     *
     * @return
     */
    @Override
    public Historico historicoMesCorrente() {

        String dataAtual = getDataSistema();
        String mesCorrente = dataAtual.substring(3, 5);
        String anoCorrente = dataAtual.substring(6, 10);
        String dataEntrada;
        
        Historico historicoMesCorrente = new Historico();
        ObjectoHistorico obAtual;
        Iterator it = adaptee.getIterator();

        while(it.hasNext()){
            obAtual = (ObjectoHistorico) it.next();
            dataEntrada = obAtual.getDataEntrada();
            if(dataEntrada.substring(3, 5).equals(mesCorrente) && dataEntrada.substring(6, 10).equals(anoCorrente)){
                historicoMesCorrente.adicionarAoHistorico(obAtual);
            }
            
        }

        return historicoMesCorrente;

    }

    private boolean isFormatoDataCorrecto(String data) {

        // DD/MM/AAAA
        // 0123456789
        String dia = data.substring(0, 2);
        String traco1 = data.substring(2, 3);
        String mes = data.substring(3, 5);
        String traco2 = data.substring(5, 6);
        String ano = data.substring(6, 10);

        if (ano.matches("[0-9]+$")) {
            if (traco1.matches("[/]+$")) {
                if (mes.matches("[0-9]+$")) {
                    if (traco2.matches("[/]+$")) {
                        if (dia.matches("[0-9]+")) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isDataValida(String data) {

        // DD/MM/AAAA
        // 0123456789
        String dia = data.substring(0, 2);
        String traco1 = data.substring(2, 3);
        String mes = data.substring(3, 5);
        String traco2 = data.substring(5, 6);
        String ano = data.substring(6, 10);

        int ano2 = Integer.valueOf(ano);
        int mes2 = Integer.valueOf(mes);
        int dia2 = Integer.valueOf(dia);

        if (ano2 >= 1970) {
            return true;
        }
        if (ano2 % 4 == 0)//ano bissexto
        {
            if (mes2 == 2) {
                if (1 <= dia2 && dia2 <= 29) {
                    return true;
                } else//ano nÃ£o bissexto
                if (mes2 == 2) {
                    if (1 <= dia2 && dia2 <= 28) {
                        return true;
                    }
                }
            }
        }

        if (mes2 == 1 || mes2 == 3 || mes2 == 5 || mes2 == 7 || mes2 == 8 || mes2 == 10 || mes2 == 12)//Meses com 31 dias
        {
            if (1 <= dia2 && dia2 <= 31) {
                return true;
            }
        }

        if (mes2 == 4 || mes2 == 6 || mes2 == 9 || mes2 == 11)//Meses com 30 dias
        {
            if (1 <= dia2 && dia2 <= 30) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        
        String mostrar = null ;
        
        Iterator it = adaptee.getIterator();
        while(it.hasNext()){
            mostrar += it.next().toString() + "\n";
        }
        return mostrar;
    }
    
    

}

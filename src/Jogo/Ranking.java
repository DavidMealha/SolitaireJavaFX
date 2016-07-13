/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import PadroesSoftware.Iterator;
import TadList.List;
import TadList.ListArray;
import java.util.ArrayList;
import Jogo.Enumerados.OpcaoEstrategiaUtilizada;
import Jogo.Enumerados.OpcaoOrdenacaoRanking;
import Jogo.Enumerados.OpcaoVarianteUtilizada;
import java.io.Serializable;

/**
 *
 * @author David
 */
public class Ranking<E> implements InterfaceRanking<E>, Serializable{

    private ArrayList<ObjectoRanking> adaptee;
    private OpcaoOrdenacaoRanking op;

    public Ranking() {
        adaptee = new ArrayList<>();
        //adaptee = new ListArray(50);
    }

    public ArrayList<ObjectoRanking> getRankingJogadores() {
        return adaptee;
    }

    @Override
    public void setOrdenacao(Enumerados.OpcaoOrdenacaoRanking op) {
        this.op = op;
    }

    @Override
    public void adicionarAoRanking(E elem, OpcaoEstrategiaUtilizada estrategiaUtilizada, OpcaoVarianteUtilizada varianteUtilizada) {

        ObjectoRanking ob = new ObjectoRanking(elem, estrategiaUtilizada, varianteUtilizada);

        adaptee.add(adaptee.size(), ob);

//        if(op == OpcaoOrdenacaoRanking.ORDENAR_POR_PONTUACAO){
//            ordenarPorEstrategia();
//        }else if(op == OpcaoOrdenacaoRanking.ORDENAR_POR_VARIANTE){
//            ordenarPorVariante();
//        }
    }

    /**
     * Método onde é feita a ordenação por variante de jogo, do ranking dos
     * jogadores.
     */
    @Override
    public void ordenarPorVariante() {

        ArrayList<ObjectoRanking> first = new ArrayList<>();
        ArrayList<ObjectoRanking> second = new ArrayList<>();
        ArrayList<ObjectoRanking> third = new ArrayList<>();
        
        for (int i = 0; i < adaptee.size(); i++) {
            if (adaptee.get(i).getVarianteUtilizada() == OpcaoVarianteUtilizada.BASE) {
                first.add(adaptee.get(i));
            } else if (adaptee.get(i).getVarianteUtilizada() == OpcaoVarianteUtilizada.INFANTIL) {
                second.add(adaptee.get(i));
            } else if (adaptee.get(i).getVarianteUtilizada() == OpcaoVarianteUtilizada.MAO_DE_TRES) {
                third.add(adaptee.get(i));
            }
        }

        for (int i = 0; i < second.size(); i++) {
            first.add(second.get(i));
        }
        for (int i = 0; i < third.size(); i++) {
            first.add(third.get(i));
        }

        for (int i = 0; i < adaptee.size(); i++) {
            adaptee.set(i, first.get(i));
        }
    }

    /**
     * Método do algoritmo de orndenação por estrategia escolhida, do ranking
     * dos jogadores.
     */
    @Override
    public void ordenarPorEstrategia() {

        ArrayList<ObjectoRanking> first = new ArrayList<>();

        ArrayList<ObjectoRanking> second = new ArrayList<>();

        for (int i = 0; i < adaptee.size(); i++) {
            if (adaptee.get(i).getEstrategiaUtilizada() == OpcaoEstrategiaUtilizada.PONTUACAO_1) {
                first.add(adaptee.get(i));
            } else if (adaptee.get(i).getEstrategiaUtilizada() == OpcaoEstrategiaUtilizada.PONTUACAO_2) {
                second.add(adaptee.get(i));
            }
        }

        for (int i = 0; i < second.size(); i++) {
            first.add(second.get(i));
        }

        for (int i = 0; i < adaptee.size(); i++) {
            adaptee.set(i, first.get(i));
        }

    }

}

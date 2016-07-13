/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import PadroesSoftware.Iterator;

/**
 *
 * @author David
 */
public interface InterfaceRanking<E> {
    
    public void setOrdenacao(Enumerados.OpcaoOrdenacaoRanking op);
    
    public void adicionarAoRanking(E elem, Enumerados.OpcaoEstrategiaUtilizada estrategiaUtilizada, Enumerados.OpcaoVarianteUtilizada varianteUtilizada);
    
    public void ordenarPorVariante();
    
    public void ordenarPorEstrategia();

}

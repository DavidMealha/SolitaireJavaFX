/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import Jogo.Enumerados.OpcaoEstrategiaUtilizada;
import Jogo.Enumerados.OpcaoVarianteUtilizada;

/**
 *
 * @author David
 */
public class ObjectoRanking<E> {
    
    private E elemento;
    private OpcaoEstrategiaUtilizada estrategiaUtilizada;
    private OpcaoVarianteUtilizada varianteUtilizada;

    public ObjectoRanking(E elemento, OpcaoEstrategiaUtilizada estrategiaUtilizada, OpcaoVarianteUtilizada varianteUtilizada) {
        this.elemento = elemento;
        this.estrategiaUtilizada = estrategiaUtilizada;
        this.varianteUtilizada = varianteUtilizada;
    }

    public E getElemento() {
        return elemento;
    }

    public OpcaoEstrategiaUtilizada getEstrategiaUtilizada() {
        return estrategiaUtilizada;
    }

    public OpcaoVarianteUtilizada getVarianteUtilizada() {
        return varianteUtilizada;
    }

    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    public void setEstrategiaUtilizada(OpcaoEstrategiaUtilizada estrategiaUtilizada) {
        this.estrategiaUtilizada = estrategiaUtilizada;
    }

    public void setVarianteUtilizada(OpcaoVarianteUtilizada varianteUtilizada) {
        this.varianteUtilizada = varianteUtilizada;
    }

    @Override
    public String toString() {
        return elemento +  " | Tipo pontuação : " + estrategiaUtilizada.toString() + " |  Variante : " + varianteUtilizada.toString() ;
    }
    
    
}

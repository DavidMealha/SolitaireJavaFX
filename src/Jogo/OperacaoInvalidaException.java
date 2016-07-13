/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

/**
 *
 * @author David
 */
public class OperacaoInvalidaException extends RuntimeException{

    public OperacaoInvalidaException() {
        super("Operacao invalida");
    }
    
}

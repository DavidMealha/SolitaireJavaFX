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
public class JogadorNaoRegistadoException extends RuntimeException{

    public JogadorNaoRegistadoException() {
        super("O jogador não se encontra no sistema.");
    }
    
    
    
}

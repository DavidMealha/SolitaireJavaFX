/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TadDeque;

/**
 *
 * @author David
 */
public class FullDequeException extends RuntimeException{
    
    public FullDequeException(){
        super("Cheia!");
    }
}

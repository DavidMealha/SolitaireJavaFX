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
public class EmptyDequeException extends RuntimeException{
    
    public EmptyDequeException(){
        super("Deque vazia");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TadList;

/**
 *
 * @author David
 */
public class OutOfBoundsException extends RuntimeException {
    
    public OutOfBoundsException(){
        super("Lista excede o seu limite");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PadroesSoftware;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author David
 */
public class ReporterTipoJogo implements Observer {

    Logger logger = Logger.getLogger("MyLog");
    
    public ReporterTipoJogo() {
    
        FileHandler fh;

        try {

            // This block configure the logger with handler and formatter  
            fh = new FileHandler("MyLog.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages  
            logger.info("Tipo de jogo jogado:");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Guarda em ficheiro qual foi o tipo de jogo guardado
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        logger.info((String) arg);
    }
}

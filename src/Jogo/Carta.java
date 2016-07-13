/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author David
 */
public class Carta {
    
    private String naipe;
    private String face;
    private int valorFace;
    private int valorNaipe;
    private double posX;
    private double posY;

    public Carta(String naipe, String face, int valorFace, int valorNaipe) {
        this.naipe = naipe;
        this.face = face;
        this.valorFace = valorFace;
        this.valorNaipe = valorNaipe;
    }

    public String getFace() {
        return face;
    }

    public String getNaipe() {
        return naipe;
    }
    
    public int getValorFace() {
        return valorFace;
    }

    public int getValorNaipe() {
        return valorNaipe;
    }
    
    public void setFace(String face) {
        this.face = face;
    }

    public void setNaipe(String naipe) {
        this.naipe = naipe;
    }

    public void setValorFace(int valorFace) {
        this.valorFace = valorFace;
    }

    public void setValorNaipe(int valorNaipe) {
        this.valorNaipe = valorNaipe;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }
  
    @Override
    public String toString() {
        
        String m = "";
        if(valorFace == 1 || valorFace > 10){
            m += "("+face.substring(0, 1) + "-" + naipe+")";
        }else{
            m += "("+valorFace + "-" + naipe+")";
        }
        
        return m;
    }
    
    public void desenhar(GraphicsContext gc, double x, double y) {
        
        String url = "/images/" + valorNaipe + valorFace + ".png";
        gc.drawImage(new Image(url), x, y);
        posX = x;
        posY = y;
     
    }
    
    public void desenharOculta(GraphicsContext gc, double x, double y){
        gc.drawImage(new Image("/images/0.png"), x, y);
        posX = x;
        posY = y;
    }
    
}

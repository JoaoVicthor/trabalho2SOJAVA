/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2so;

/**
 *
 * @author joaov
 */
public class Caçador extends Thread{
    private final String cor;
    int moedas;
    Bosque bosque;
    
    public Caçador(String cor, Bosque bosque){
        this.cor = cor;
        moedas = 0;
        this.bosque = bosque;
    }

    @Override
    public void run() {
        new Cachorro(this, bosque.getPotes()[0], 1);
    }
    
    

    @Override
    public String toString() {
        return "caçador " + cor;
    }
    
    public void addMoedas(int i){
        moedas += i;
    }

    int getMoedas() {
        return moedas;
    }

    void createCachorroDois() {
        new Cachorro(this, bosque.getPotes()[0], 2);
    }
    
    void finalizar(){
        bosque.finalizar();
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2so;

import java.util.Random;

/**
 *
 * @author joaov
 */
public class Pote {
    static int id = 1;
    int numero;
    int moedas;
    Pote[] ligacao = new Pote[3];
    int ligacoes = 0;
    Random rdm = new Random();
    Cachorro cachorro = null;
    
    public Pote(){
        moedas = 4;
        numero = id;
        id ++;
    }
    
    public boolean addLigacao(Pote pote){
        if(ligacoes < 3){
            ligacao[ligacoes] = pote;
            ligacoes++;
            return true;
        }
        else{
            return false;
        }
    }
    public synchronized Pote getLigacao(){
        if(ligacoes > 1){
            return ligacao[rdm.nextInt(ligacoes-1)];
        }
        else{
          return ligacao[0];  
        }
        
        
    }
    
    public synchronized int getMoedas(){
        return moedas;
    }
    
    public void putCachorro(Cachorro cao){
        cachorro = cao;
    }
    
    public boolean isOcupado(){
        return cachorro != null;
    }
    
    public synchronized void saiCachorro(){
        cachorro = null;
    }
    
    public Cachorro getCachorro(){
        return cachorro;
    }
    
    public synchronized int takeMoedas(){
        if(moedas == 0){
            return 0;
        }
        else{
            int m = rdm.nextInt(moedas)+1;
            moedas -= m;
            return m;
        }
    }
    
    public int getNumero(){
        return numero;
    }

    @Override
    public String toString() {
        return "Pote número: " + numero;
    }

    void addMoeda() {
        moedas++;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2so;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaov
 */
public class Cachorro extends Thread{
    int numero;
    Caçador dono;
    Pote poteAtual;
    int moedas;
    
    public Cachorro(Caçador owner, Pote pote, int number){
        dono = owner;
        moedas = 0;
        numero = number;
        poteAtual = pote;
        run();
    }
    
    @Override
    public void run() {
        super.run();
        while(moedas < 20){
            pegarMoedas();
            ocuparPote();
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cachorro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        dono.addMoedas(moedas);
        if(dono.getMoedas() < 40){
            dono.createCachorroDois();
            try {
                this.finalize();
            } catch (Throwable ex) {
                Logger.getLogger(Cachorro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            System.out.println("FIM DE JOGO!");
            System.out.println("O vencedor foi " + dono.toString());
            dono.finalizar();
        }
    }
    
    public void pegarMoedas(){
        String retorno = toString() + " ";
        int take = poteAtual.takeMoedas();
        moedas += take;
        retorno += "entrou no " + poteAtual.toString();
        if(take == 0){
            try {
                retorno += " e dormiu.";
                System.out.println(retorno);
                sleep(6000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        else{
            try {
                retorno += " e pegou " + take+ " moedas.";
                System.out.println(retorno);
                sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
    }
    
    private void getLigacao(){
        
    }
    
    private synchronized void ocuparPote(){
        poteAtual.saiCachorro();
        Pote pote2 = poteAtual.getLigacao();
        while(pote2.isOcupado()){
            pote2 = poteAtual.getLigacao();
        }
        poteAtual = pote2;
        poteAtual.putCachorro(this);
        
    }

    @Override
    public String toString() {
        return "Cachorro " + numero + " do " + dono.toString();
    }
    
    
}

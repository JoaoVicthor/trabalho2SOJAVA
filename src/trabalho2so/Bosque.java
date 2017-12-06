/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2so;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author joaov
 */
public class Bosque {

    volatile Pote[] potes = new Pote[20];
    boolean acontecendo = true;
    protected static ScheduledExecutorService lifeSaver;

    public Bosque() {
        initializePotes();       

        Caçador amarelo = new Caçador("amarelo", this);
        Caçador verde = new Caçador("verde", this);
        Caçador azul = new Caçador("azul", this);
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
                
                
        executor.execute(amarelo);
        executor.execute(verde);
        executor.execute(azul);
        CachorroVermelho cachorroVermelho = new CachorroVermelho(this);
        lifeSaver = Executors.newScheduledThreadPool(1);
        lifeSaver.scheduleAtFixedRate(cachorroVermelho, 0, 400, TimeUnit.MILLISECONDS);

    }

    private void initializePotes() {
        Random rdm = new Random();
        potes[0] = new Pote();
        potes[1] = new Pote();
        potes[1].addLigacao(potes[0]);
        potes[0].addLigacao(potes[1]);
        for (int i = 2; i < potes.length; i++) {
            boolean temLigacao = false;
            potes[i] = new Pote();
            int ligacao = 0;
            while (!temLigacao) {
                ligacao = rdm.nextInt(i - 1);
                temLigacao = potes[ligacao].addLigacao(potes[i]);
            }
            potes[i].addLigacao(potes[ligacao]);
        }
    }

    public Pote[] getPotes() {
        return potes;
    }
    
    public boolean acontecendo(){
        return acontecendo;
    }
    
    public void finalizar(){
        acontecendo = false;
        System.exit(0);
    }
}

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
public class CachorroVermelho extends Thread {

    private Bosque bosque;

    public CachorroVermelho(Bosque bosque) {
        this.bosque = bosque;
        run();
    }

    @Override
    public void run() {
        while (bosque.acontecendo()) {
            for (Pote pote : bosque.getPotes()) {
                if (pote.getMoedas() == 0) {
                    pote.addMoeda();
                    System.out.println("Cachorro Vermelho adicionou 1 moeda ao " + pote.toString());
                }
                if (pote.isOcupado()) {
                    if (pote.getCachorro().tahDormindo()) {
                        pote.getCachorro().interrupt();
                        System.out.println("Cachorro Vermelho acordou o " + pote.getCachorro().toString());
                    }
                }
            }
            try {
                sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(CachorroVermelho.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

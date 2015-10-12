/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaphore;

/**
 *
 * @author Andrea
 */
public class SimpleSemaphore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pool pool = new Pool(4);
        Thread t;
        for (int i = 0; i < 10; i++) {
            t = new Thread(new Visitor(pool, "Utente " + (i + 1)));
            t.start();
        }

    }

}

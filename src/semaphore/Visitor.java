/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaphore;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea
 */
class Visitor implements Runnable {

    private final Pool p;
    private final String name;

    public Visitor(Pool pool, String name) {
        this.p = pool;
        this.name = name;
    }

    @Override
    public void run() {
        Random randomGenerator = new Random();
        while (true) {
            int pause;
            try {
                pause = randomGenerator.nextInt(500);
                Thread.sleep(pause);
            } catch (InterruptedException ex) {
                System.out.println("consumer " + name + " failed to wait");
            }
            try {
                String stand = p.read();
                System.out.println(name + " getting " + stand);
                pause = randomGenerator.nextInt(500);
                Thread.sleep(pause);
                p.leave(stand);
            } catch (InterruptedException ex) {
                Logger.getLogger(Visitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

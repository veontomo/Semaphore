/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Andrea
 */
class Pool {

    private final Semaphore available;
    /**
     * list of items in the pool
     */
    protected String[] items;

    protected boolean[] used;
    /**
     * number of slots
     */
    private final int slotNum;

    public Pool(int slotNum) {
        this.slotNum = slotNum;
        this.available = new Semaphore(slotNum, true);
        this.used = new boolean[slotNum];
        this.items = new String[slotNum];
        for (int i = 0; i < slotNum; i++) {
            items[i] = "brochure " + (i + 1);
            used[i] = false;
            System.out.println("Stand " + i + " contains " + items[i]);

        }
    }

    public String read() throws InterruptedException {
        Random randomGenerator = new Random();
        int pause = randomGenerator.nextInt(500);
        try {
            pause = randomGenerator.nextInt(500);
            Thread.sleep(pause);
        } catch (InterruptedException ex) {
            System.out.println("pool failed to wait");
        }
        available.acquire();
        return getAvailableStand();
    }

    public void leave(String item) {
        if (markAsUnused(item)) {
            System.out.println("releasing " + item);
            available.release();
        }
    }

    protected synchronized String getAvailableStand() {
        System.out.println("search for free stand");
        for (int i = 0; i < slotNum; ++i) {
            if (!used[i]) {
                used[i] = true;
                return items[i];
            }
        }
        return null;
    }

    protected synchronized boolean markAsUnused(String item) {
        for (int i = 0; i < slotNum; i++) {
            if (items[i].equals(item)) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

}

// QueuingSystem.java
// Centralized Queuing System logic for Pag-Ibig Office

import java.util.LinkedList;
import java.util.Queue;

public class QueuingSystem {
    private static QueuingSystem instance;

    private int nextNumber;           // Next queue number to issue
    private Queue<Integer> queue;     // Shared queue for all desks
    private int[] desks;              // Current number being served at each desk

    private QueuingSystem() {
        nextNumber = 0;
        queue = new LinkedList<>();
        desks = new int[3]; // Three desks
    }

    // Singleton: ensures one centralized queue system
    public static synchronized QueuingSystem getInstance() {
        if (instance == null) {
            instance = new QueuingSystem();
        }
        return instance;
    }

    // Issue a queue number for a new visitor
    public void issueQueueNumber() {
        nextNumber++;
        queue.add(nextNumber);
        System.out.println(" Issued Queue Number: " + nextNumber);
        displayQueueStatus();
    }

    // Help desk serves the next person in the centralized queue
    public void callNextNumber(int deskId) {
        if (deskId < 1 || deskId > 3) {
            System.out.println(" Invalid Desk ID. Please choose 1, 2, or 3.");
            return;
        }

        if (queue.isEmpty()) {
            System.out.println(" No more people in the queue for Desk " + deskId + ".");
            return;
        }

        int nextToServe = queue.poll();  // Take next from centralized queue
        desks[deskId - 1] = nextToServe;

        System.out.println(" Desk " + deskId + " is now serving Queue Number " + nextToServe);
        displayQueueStatus();
    }

   
    public void resetQueue(int newStartNumber) {
        if (newStartNumber < 0) {
            System.out.println(" Invalid reset number. Must be positive.");
            return;
        }

        queue.clear();
        nextNumber = newStartNumber;
        for (int i = 0; i < desks.length; i++) {
            desks[i] = 0;
        }

        System.out.println(" Queue system has been reset to start from number: " + newStartNumber);
        displayQueueStatus();
    }

   
    public void displayQueueStatus() {
        System.out.println("\n===== ONLINE QUEUE MONITOR =====");
        for (int i = 0; i < desks.length; i++) {
            if (desks[i] == 0) {
                System.out.println("Desk " + (i + 1) + ": Idle");
            } else {
                System.out.println("Desk " + (i + 1) + ": Now Serving " + desks[i]);
            }
        }

        if (queue.isEmpty()) {
            System.out.println("Next in Line: None");
        } else {
            System.out.println("Next in Line: " + queue.peek());
        }

        System.out.println("People Waiting: " + queue.size());
        System.out.println("Last Issued Number: " + nextNumber);
        System.out.println("=================================\n");
    }
}

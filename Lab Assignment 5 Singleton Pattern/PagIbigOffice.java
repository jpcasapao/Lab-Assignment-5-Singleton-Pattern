public class PagIbigOffice {
    public static void main(String[] args) {
        QueuingSystem queue = QueuingSystem.getInstance();

        System.out.println("===== Welcome to Pag-IBIG Centralized Queuing System =====\n");

        
        queue.issueQueueNumber(); // Queue 1
        queue.issueQueueNumber(); // Queue 2
        queue.issueQueueNumber(); // Queue 3

        
        queue.callNextNumber(1); // Desk 1 serves Queue 1
        queue.callNextNumber(2); // Desk 2 serves Queue 2
        queue.callNextNumber(3); // Desk 3 serves Queue 3

        
        queue.resetQueue(100);

        
        queue.issueQueueNumber(); 
        queue.issueQueueNumber(); 
        queue.callNextNumber(2); 

       
        queue.displayQueueStatus();
    }
}

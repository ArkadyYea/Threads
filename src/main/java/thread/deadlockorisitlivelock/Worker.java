package thread.deadlockorisitlivelock;

public class Worker extends Thread {
    
	private int number; // worker's number
    private Bin bin;  // knows what bin to use

    public Worker(Bin bin, int number) {
        this.bin = bin;
        this.number = number;
        this.setName("Worker Thread");
    }

    public void run() {
        String part = "";
        for (int i = 0; i < 10; i++) {
            part = bin.getContents(); // get part from bin
            System.out.println("Worker #" + this.number + "   got " + part + "\n");
            try {
                sleep(125);  // take a break - Union worker...
            } catch (InterruptedException e) { 
                e.printStackTrace();
            }
        }
    }
}


import java.util.concurrent.Semaphore;

public class GestorSemaforo {
    private Semaphore s1;
    private Semaphore s2;
    private Semaphore s3;
    private Semaphore s4;

    public GestorSemaforo(){
        this.s1 = new Semaphore(1);;
        this.s2 = new Semaphore(1);;
        this.s3 = new Semaphore(1);;
        this.s4 = new Semaphore(1);;
    }

    public Semaphore getSemaphoreByIndex(int index){
        return switch (index) {
            case 0 -> s1;
            case 1 -> s2;
            case 2 -> s3;
            case 3 -> s4;
            default -> s1;
        };
    }
}

package readpackets;

/**
 * A task for reading and deleting a Packet from the buffer
 */
public class ConsumerTask implements Runnable{
    private final MyBuffer buffer;
    public ConsumerTask(MyBuffer buffer) {
        this.buffer = buffer;
    }
    @Override
    public void run() {
        while (true) {
            System.out.println(buffer.read());
            try {
                Thread.sleep((int) (Math.random() * 10000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

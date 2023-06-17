package readpackets;

/**
 * A task for adding a Packet to the buffer
 *
 */
public class ProducerTask implements Runnable{
    private final MyBuffer buffer;

    public ProducerTask(MyBuffer buffer) {
        this.buffer = buffer;
    }
    @Override
    public void run() {
        while (true) {
            try {
                buffer.write();
                Thread.sleep((int) (Math.random() * 10000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

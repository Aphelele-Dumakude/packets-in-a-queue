package readpackets;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Aphelele Dumakude
 */
public class MyBuffer {
    private final Queue<Packet> queue = new LinkedList<>();
    private final Queue<Packet> packets;
    private static final int CAPACITY = 1;
    private static final Lock lock = new ReentrantLock();
    private static final Condition notEmpty = lock.newCondition();
    private static final Condition notFull = lock.newCondition();

    public MyBuffer(Queue<Packet> packets) {
        this.packets = packets;
    }
    /**
     *
     * @return the packet in the queue
     */
    public Packet read() {
        Packet packet;
        lock.lock();
        try {
            while (queue.isEmpty()) {
                System.out.println("Waiting for notEmpty condition before reading from the buffer");
                notEmpty.await();
            }
            packet = queue.poll();
            notFull.signal();
            return packet;
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            lock.unlock();
        }
    }
    /**
     * Adds a packet to the queue
     */
    public void write() {
        lock.lock();
        try {
            while (queue.size() == CAPACITY) {
                System.out.println("Waiting for the notFull condition before writing to the buffer");
                notFull.await();
            }
            if (packets.isEmpty()) {
                System.out.println("Packets consumption is DONE!!!!");
                System.exit(0);
            }
            queue.offer(packets.poll());
            notEmpty.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            lock.unlock();
        }
    }
}

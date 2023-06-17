import readpackets.*;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        String filename = "C:\\development\\packets-in-a-queue\\read-packets\\packets.json";
        Queue<Packet> packets = ReadFileContents.readFileContents(filename);
        MyBuffer buffer = new MyBuffer(packets);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new ProducerTask(buffer));
        executorService.execute(new ConsumerTask(buffer));
        executorService.shutdown();
    }
}
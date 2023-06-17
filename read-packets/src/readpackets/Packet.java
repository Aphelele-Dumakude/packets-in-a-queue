package readpackets;

/**
 * @author Aphelele Dumakude
 */
public class Packet {
    private final int index;
    private final String data;

    public Packet(int index, String data) {
        this.index = index;
        this.data = data;
    }
    public int getIndex() {
        return index;
    }

    public String getData() {
        return data;
    }
    @Override
    public String toString() {
        return "Packet{" +
                "index=" + index +
                ", data='" + data + '\'' +
                '}';
    }
}

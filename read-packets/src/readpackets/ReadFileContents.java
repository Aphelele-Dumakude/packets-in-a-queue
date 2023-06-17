package readpackets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Aphelele Dumakude
 */
public class ReadFileContents {
    public static Queue<Packet> readFileContents(String filename) {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(filename));
            JSONObject jsonObject = (JSONObject) obj;
            Queue<Packet> data = new LinkedList<>();

            for (Object entry : jsonObject.entrySet()) {
                JSONObject.Entry<Integer, String> pair = (JSONObject.Entry<Integer, String>) entry;
                data.offer(new Packet(Integer.parseInt(String.valueOf(pair.getKey())), pair.getValue()));
            }
            return data;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Nazli on 2016-07-10.
 */
public class DisplayTrans {

    public void showSingle(HashMap<String[], String> hash, String[] entery) {

        System.out.println("Requested transaction details: \n" +
                            "Actor: " + hash.get(entery[0]) + "Target: " +  hash.get(entery[1]) +
                            "Created_time: " + hash.get(entery) + "\n");

    }

    public void showAll(HashMap<String[], String> transHash) {
        System.out.println("Requested transaction details: \n");
        Set<String[]> keys = transHash.keySet();
        Iterator<String[]> itr = keys.iterator();
        while (itr.hasNext()) {
            System.out.println( "Actor-target: " + itr.next() + " " +
                                "Created_time: " + transHash.get(itr) + "\n");
        }
        System.out.println("");

    }
}

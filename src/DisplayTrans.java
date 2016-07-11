import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Nazli on 2016-07-10.
 */
public class DisplayTrans {

    public void showSingle(HashMap<String, String[]> hash, String entery) {

        System.out.println("Requested transaction details: \n" +
                            "Actor-Target: " + hash.get(entery) +  " " +
                            "Created_time: " + entery + "\n");

    }

    public void showAll(HashMap<String, String[]> transHash) {
        System.out.println("Requested transaction details: \n");
        Set<String> keys = transHash.keySet();
        Iterator<String> itr = keys.iterator();
        while (itr.hasNext()) {
            System.out.println( "Actor-target: " + transHash.get(itr) + " " +
                                "Created_time: " + itr.next() + "\n");
        }
        System.out.println("");

    }
}

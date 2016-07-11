import java.io.*;
//import javax.*;


import java.lang.reflect.Array;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.Iterator;
import java.util.Set;

//import jdk.nashorn.internal.parser.JSONParser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;


public class ReadTrans {

    private HashMap<String, String> transHash = new HashMap<String, String>();
    private  Hashtable<String, String> actorTargetList = new Hashtable<String, String>();


    JSONParser jsonParser = new JSONParser();

    public  int read_file() throws FileNotFoundException {

        DisplayTrans displayTrans = new DisplayTrans();

        String actor = new String();
        String target = new String();
        String created_time = new String();

        //  try (FileReader file_read = new FileReader("venmo_input/venmo-trans.txt")) {
        try (FileReader file_read = new FileReader("data-gen/venmo-trans.txt")) {
            // HashMap<String, String[]> transHash = new HashMap<String, String[]>();
            String[] trans = new String[2];
            BufferedReader br = new BufferedReader(file_read);

            System.out.println("File opened ...");
            String line;
            System.out.println("Read Trans ---- Last read trans ...");
            while ( (line = br.readLine()) != null){
                JSONObject jsonObject = (JSONObject) jsonParser.parse(line);
                created_time = (String) jsonObject.get("created_time");
                target = (String) jsonObject.get("target");
                actor = (String) jsonObject.get("actor");


                System.out.print("actor= " + actor + ",\t" +
                                "target= " + target + ",\t" +
                                "created_time: " + created_time + "\n");

                trans[0] = actor;
                trans[1] = target;

                transHash.put(trans[0] + "," + trans[1], created_time);

    //                   displayTrans.showAll(transHash);
            }
                paymentGraph();
                return 0;
            } catch (IOException e) {
                e.printStackTrace();

            } catch (ParseException e) {
                e.printStackTrace();
            }
        // not successful
        return 1;
    }

    public String getData(int n){
        return transHash.get(n);
    }

    public  Hashtable<String, String> getActorTarget(){
        return actorTargetList;
    }


    public void paymentGraph() {
        Set<String> keySets = transHash.keySet();
        Iterator<String> itr = keySets.iterator();

        Collection<String> timeSteps = transHash.values();

        System.out.println("\nList all actor-targets ... ");
        while (itr.hasNext()){
            String str = itr.next();
            String delimActorTarget = ",";
            String[] tokens = str.split(delimActorTarget);
            str.split(delimActorTarget);
            String a = tokens[0];
            String t = tokens[1];
            System.out.println(a + "<->" + t);
            parseCreatedTime(timeSteps.toString());
        }
    }

    private void parseCreatedTime(String str) {

        ArrayList<String> edgeList = new ArrayList<String>();

        String delimDate = "T";
        String[] tokens = str.split(delimDate);

        String date = tokens[0];
        System.out.println(date);

        String delimTime = "Z";
        String[] timeTokens = tokens[1].split(delimTime);
        String time = timeTokens[0];

        edgeList.add(0, date);
        edgeList.add(1, time);

        System.out.println(edgeList.get(0) + "  " + edgeList.get(1));
//        changeFormat(time, date);

    }

    private void changeFormat(String timeStr, String dateStr){
        DateTimeFormatter timeFormat  = DateTimeFormatter.ofPattern(ISO_DATE_TIME.toString());
        Date date = (Date) timeFormat.parse(timeStr);

    }

    public void write() {
        try {
            FileWriter fileWriter = new FileWriter("./venmo_output/output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
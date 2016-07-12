import java.io.*;
//import javax.*;


import java.lang.reflect.Array;
import java.sql.Time;
import java.text.SimpleDateFormat;
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

import static java.time.format.DateTimeFormatter.ISO_DATE;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;


public class ReadTrans {

    private HashMap<String, String> transHash = new HashMap<String, String>();
    private Hashtable<String, String> actorTargetList = new Hashtable<String, String>();
    private Hashtable<String, Date> dateHashtable = new Hashtable<String, Date>();


    private String delay = null;
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

                write("actor= " + actor + ",\t" +
                        "target= " + target + ",\t" +
                        "created_time: " + created_time + "\n", " ");
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
            } catch (java.text.ParseException e) {
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


    public void paymentGraph() throws java.text.ParseException {
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
            write(a + "<->" + t + "\n", "List all transactions with details: \n");
            parseCreatedTime(itr.toString(), timeSteps.toString().substring(1));

        }

    }

    private void parseCreatedTime(String itr, String str) {

        ArrayList<String> edgeList = new ArrayList<String>();

        String delimDate = "T";
        String[] tokens = str.split(delimDate);

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        String date = tokens[0].substring(1);
//        System.out.println(date);

        String delimTime = "Z";
        String[] timeTokens = tokens[1].split(delimTime);
        String time = timeTokens[0];

        edgeList.add(0, date);
        edgeList.add(1, time);

        changeFormat(itr, str);
        System.out.println(edgeList.get(0) + "  " + edgeList.get(1));
        write( edgeList.get(0) + "  " + edgeList.get(1) + "\n", "List of transactions datetime: \n");

    }

    private void changeFormat(String itr, String dateStr){

        // change the time datatype from string to date-time ISO8601
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss.SSSZ");

        Date date = null;
        Date lastDate = new Date();
        try {
            date = simpleFormat.parse(dateStr);
            dateHashtable.put(itr, date);

            write("Date in ISO-DATE_TIME format ....\t" + date, "DateTime data type of transactions: \n");

            if (date.compareTo(lastDate) > 0){
                delay = "later";
                lastDate = date;
            }

            else if (lastDate.compareTo(date) < 0)
                delay = "sooner";
            else
                delay = "simultanuous";


        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        System.out.print("Date in ISO-DATE_TIME format ....\t" + date);

    }

    public void write(String input, String msg) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter("./venmo_output/output.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(msg + " \n");
            bufferedWriter.write(input);

//            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
import java.io.*;
//import javax.*;


import java.lang.Object.*;
import java.nio.Buffer;
import java.nio.file.*;
import java.time.OffsetDateTime;

import java.util.*;
import java.util.Iterator;
import java.util.Set;

import jdk.nashorn.api.scripting.JSObject;
//import jdk.nashorn.internal.parser.JSONParser;

import org.json.JSONException;
import org.json.JSONWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sun.tools.java.ScannerInputReader;

import static com.sun.javafx.tools.resource.DeployResource.Type.data;


public class ReadTrans {

    private HashMap<String, String> data = new HashMap<String, String>();

    private String actor = new String();
    private String target = new String();
    private String created_time = new String();
    JSONParser jsonParser = new JSONParser();

    public void read_file() throws FileNotFoundException {

        DisplayTrans displayTrans = new DisplayTrans();

          //  try (FileReader file_read = new FileReader("venmo_input/venmo-trans.txt")) {
                try (FileReader file_read = new FileReader("data-gen/venmo-trans.txt")) {

                HashMap<String[], String> transHash = new HashMap<String[], String>();
                String[] trans = new String[2];
                BufferedReader br = new BufferedReader(file_read);

                System.out.println("File opened ...");

                String line;
                while ( (line = br.readLine()) != null){
                    JSONObject jsonObject = (JSONObject) jsonParser.parse(line);
                    created_time = (String) jsonObject.get("created_time");
                    target = (String) jsonObject.get("target");
                    actor = (String) jsonObject.get("actor");
                    System.out.print("Actor: " + actor + " " +
                                        "Target: " + target + " " +
                                        "Created At: " + created_time + "\n");

                    trans[0] = actor;
                    trans[1] = target;

                    transHash.put(trans, created_time);
                    System.out.println("Read Trans ---- Last read trans ...");
//                    displayTrans.showAll(transHash);

                }



            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

    }

    public String getData(int n){
        return data.get(n);
    }

    public void write() {
        try {
            FileWriter fileWriter = new FileWriter("./venmo_output/output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
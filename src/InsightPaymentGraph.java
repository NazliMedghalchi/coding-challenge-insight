
/*
    This class reads from venmo-trans.txt
    Creates graph of payements
    Calculates median of paymenets regarding 60 seconds difference

*/

import java.io.*;
import java.util.*;


public class InsightPaymentGraph {


    public static void main(String[] args){

        ReadTrans readTrans = new ReadTrans();
        try {
            readTrans.read_file();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

}
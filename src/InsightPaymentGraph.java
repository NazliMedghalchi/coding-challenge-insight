
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
            int openCode = readTrans.read_file();
            if (openCode == 0){
//                readTrans.paymentGraph();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

}
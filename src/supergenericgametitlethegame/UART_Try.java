/*
 *  © 2019 by Patrick Matthew Chan
 *  File: UART_Try.java
 *  Package: uart_try
 *  Description: The UART_Try class.
 */
package supergenericgametitlethegame;

import java.util.logging.Level;
import java.util.logging.Logger;

/* @author Patrick Matthew J. Chan [LBYCP24-EQ1]*/
public class UART_Try{
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Main Class ~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        //byte a = (byte)0b11111111, b = (byte)0b00000011;
        //System.out.println("test = " + (a+b)); //sum is 0b00000010. It works.
        //PlayerState pp = new PlayerState();                       //debug
        //System.out.println("pp.toString = " + pp.toString());     //debug
        
        UCMI test = new UCMI();
        test.init();
        int i = 0;
        while(true){
            //System.out.println(test.p[1].toString());     //debug
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                System.out.println("Wow, an interrupt exception occured. Like there's a point in it here anyways.");
            }
            //test.send("hi: "+ (++i) +"\n");
            i++;
            test.ReqPlayer((i%4) + 1);
            test.send("\t");
            test.ReqPlayerByte((i+2)%4 +1, (i+4)%9 +1);
            test.send("\r\n");
        }
    }
}
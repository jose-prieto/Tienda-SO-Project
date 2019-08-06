/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienda1;

import java.io.*;
import java.lang.management.ManagementFactory;

/**
 *
 * @author jose
 */
public class PIDwriter {
    
    public static void write(){
        
        File file = new File("../pidT1.txt");
        String lines[];
        String pid = ManagementFactory.getRuntimeMXBean().getName();
        lines = pid.split("@");
        
        try{
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferW = new BufferedWriter(fileWriter);
            bufferW.write(lines[0]);
            bufferW.close();
            fileWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}

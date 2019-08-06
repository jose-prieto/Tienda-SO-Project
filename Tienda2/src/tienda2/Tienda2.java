/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda2;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author jose
 */
public class Tienda2 {

    public static void main(String[] args) throws IOException, InterruptedException{
        
        PIDwriter.write();
        Archivo comprar = new Archivo();
        comprar.Logica();
        
    }
    
    
}

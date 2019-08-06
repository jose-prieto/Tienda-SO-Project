/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventario;

import java.io.*;

/**
 *
 * @author jose
 */
public class Product {
    String producto = "";
    String cantidad = "";

    public void Productos(String direccion) {
        File file = new File(direccion);
        String line = "",lines[];        
        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferR = new BufferedReader(fileReader);
            line = bufferR.readLine();
            lines = line.split("#");
            this.producto = lines[0];
            this.cantidad = lines[1];
            BorrarFile(file);
            bufferR.close();
                       
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static  void BorrarFile(File archivo){
        try {
            if(archivo.exists()){  
              archivo.delete(); 
              //System.out.println("Fichero Borrado con Exito");
            }
        } catch (Exception ex) { 
             System.out.println(ex.getMessage());
        }
    }

    public String getProducto() {
        return this.producto;
    }

    public String getCantidad() {
        return this.cantidad;
    }   
}

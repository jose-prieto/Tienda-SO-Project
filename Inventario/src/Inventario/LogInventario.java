package Inventario;

import java.io.*;
import java.util.*;

public class LogInventario {
    //Atributos
    HashMap inventario = new HashMap();
        
    public LogInventario() {
        this.CargarInventario();
    }
                
    //Metodo para mostrar lista de productos en el inventario
    public void ListarInventario(){
        Set<String> keys = this.inventario.keySet();
        System.out.println("\nInventario Actual");
        for(String key:keys){
            System.out.println(key + "-" + this.inventario.get(key) );
        }
    }
    
    //modifica cantidad en key de hashmap
    public int Actualizar(String codigo, String cantidad){
        
        if (this.inventario.containsKey(codigo) == true){
            if ((int)this.inventario.get(codigo) >= Integer.parseInt(cantidad)){
                this.inventario.put(codigo, (int)this.inventario.get(codigo) - Integer.parseInt(cantidad));
                ReescribirTxt();
                return 1;
            }
            else {
                return 2;
            }
        }else{
            return 0;
        }
    }
    
    public void ReescribirTxt(){
        FileWriter archivo = null;
        try
        {
            archivo = new FileWriter("Inventario.txt");
            final PrintWriter pw = new PrintWriter(archivo);

            this.inventario.keySet().forEach(key -> pw.println(key + "#" + this.inventario.get(key))); 

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (archivo != null)
              archivo.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
    //Metodo para cargar productos de txt de inventarios
    public void CargarInventario(){
        File file = new File("Inventario.txt");
        String line = "",lines[];        
        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferR = new BufferedReader(fileReader);
            while((line = bufferR.readLine()) != null){
                lines = line.split("#");
                this.inventario.put(lines[0], Integer.parseInt(lines[1]));
            }
            bufferR.close();     
        }catch(Exception e){
            e.printStackTrace();
        }   
    }
}
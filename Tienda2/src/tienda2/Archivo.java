/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda2;

import java.io.*;
import static java.lang.Thread.sleep;
import java.util.*;

/**
 *
 * @author jose
 */
public class Archivo {
    
    public static int inventario;
    Scanner sc = new Scanner(System.in);
    int codigo;
    int cantidad;
    
    public void AgregarEnArchivo() throws IOException{
        
        BufferedWriter out = null;   
        try {   
            out = new BufferedWriter(new FileWriter("../tienda2.txt", true));   
            out.write(this.codigo + "#" + this.cantidad + "\n");   
        } catch (IOException e) {
        } finally {   
            if (out != null) {   
                out.close();
            }   
        } 
    }
    
    public int LeerPID(){
        int PID = 0;
        File file = new File("../pid.txt");
        String line = "",lines[];        
        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferR = new BufferedReader(fileReader);
            PID = Integer.parseInt(bufferR.readLine());
            bufferR.close();
                       
        }catch(Exception e){
            e.printStackTrace();
        }
        return PID;
    }
    
    public int Logica() throws IOException, InterruptedException{
        int opcion;
        DebugSignalHandler.listenTo("TERM");
        
        while(true){
            menu();
            opcion = sc.nextInt();
            switch(opcion){
                case (1):
                    System.out.println("\nIngrese Código del producto a comprar");
                    codigo = sc.nextInt();
                    System.out.println("\nIngrese cantidad del producto a comprar");
                    cantidad = sc.nextInt();
                    AgregarEnArchivo();
                    Runtime.getRuntime().exec("kill -15 " + LeerPID());
                    sleep(500);
                    File archivo = new File("../tienda2.txt");
                    if (archivo.exists()){
                        System.out.println("\nFalla en el sistema, inténtelo más tarde");
                        archivo.delete(); 
                    }
                    break;
                case (2):
                    Runtime.getRuntime().exec("kill -1 " + LeerPID());
                    break;
                case (3):
                    Runtime.getRuntime().exec("kill -9 " + LeerPID());
                    break;
                case (0):
                    return 0;
                default:
                    System.out.println("\nOpcion no valida");
                    break;
            }
        }
    }
    
    public void menu(){
        System.out.println("\n1. Comprar");
        System.out.println("2. Listar");
        System.out.println("0. Cerrar");
    }
}

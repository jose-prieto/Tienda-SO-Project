/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventario;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 *
 * @author jose
 */
class DebugSignalHandler implements SignalHandler {
    
    static LogInventario logica = new LogInventario();
    Product producto = new Product();
        
    public static void listenTo(String name) {
       Signal signal = new Signal(name);
       Signal.handle(signal, new DebugSignalHandler());
    }
    
    public int LeerPID(String direccion){
        int PID = 0;
        File file = new File(direccion);
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
    
    public void Acciones(String direccion){
        try {
            producto.Productos(direccion);
            switch(logica.Actualizar(producto.getProducto(), producto.getCantidad())){
                case (0):
                    Thread.sleep(10000);
                    System.out.println("\nCodigo " + producto.getProducto() + " no existente");
                    break;
                case (2):
                    Thread.sleep(10000);
                    System.out.println("\nEl producto " + producto.getProducto() + " esta agotado");
                    break;
                default:
                    Thread.sleep(10000);
                    System.out.println("\nCompra exitosa");
            }
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
   
    public void handle(Signal signal) {
        //System.out.println("Signal: " + signal);
       
        //recibe llamada para listar sighup
        if (signal.toString().trim().equals("SIGHUP")) {
        
            this.logica.ListarInventario();
        
            //recibe llamada de tienda 1
        }else if (signal.toString().trim().equals("SIGPIPE")){
            try {
                Runtime.getRuntime().exec("kill -13 " + LeerPID("../pidT1.txt"));
            } catch (IOException ex) {
                Logger.getLogger(DebugSignalHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            Thread Comprar = new Thread(() -> {
                Acciones("../tienda1.txt");
            });

            Comprar.start();

            try {
                Comprar.join(10000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }                
            
            //recibe llamada de tienda 2
        }else if (signal.toString().trim().equals("SIGTERM")){
            try {
                Runtime.getRuntime().exec("kill -15 " + LeerPID("../pidT2.txt"));
            } catch (IOException ex) {
                Logger.getLogger(DebugSignalHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            Thread Comprar = new Thread(() -> {
                Acciones("../tienda2.txt");
            });

            Comprar.start();

            try {
                Comprar.join(10000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }       
        }
    }
}
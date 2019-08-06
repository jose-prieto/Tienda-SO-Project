/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda2;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 *
 * @author jose
 */
class DebugSignalHandler implements SignalHandler {
    
        
    public static void listenTo(String name) {
       Signal signal = new Signal(name);
       Signal.handle(signal, new DebugSignalHandler());
    }
   
    public void handle(Signal signal) {
       
        //recibe llamada para listar sighup
        if (signal.toString().trim().equals("SIGTERM")) {
        
            System.out.println("\nSeñal recibida en inventario.");
            
        }
    }
}
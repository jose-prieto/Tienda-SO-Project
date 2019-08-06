package Inventario;

import static java.lang.Thread.sleep;

public class InventarioMain{

    public static void main(String[] args) throws InterruptedException {
        
        PIDwriter.write();
        DebugSignalHandler.listenTo("HUP");
        DebugSignalHandler.listenTo("PIPE");
        DebugSignalHandler.listenTo("TERM");
        
        while(true){
            sleep(500);                                    
        }
    }
}
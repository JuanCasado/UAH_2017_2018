
package pecl3.src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import pecl3.screens.FuelStationInterface;

/**
 *
 * @author mr.blissfulgrin
 */
public class Log
{
    private final ConcurrentHashMap <String,String> log;
    private final LinkedBlockingQueue <String> evoluvionGasolinera;
    private final long initialTime;
    private final FuelStationInterface visual;
    private final boolean debugState;
    
    /**
     * Crea un log para cada clase que lo necesita y uno general de lo que escribieron todas
     * 
     * @param visual Donde aplicar el texto
     * @param debugState Indica si modo pasivo
     */
    public Log (FuelStationInterface visual, boolean debugState)
    {
        this.evoluvionGasolinera = new LinkedBlockingQueue <>();
        this.log = new ConcurrentHashMap<>();
        this.initialTime = new Date().getTime();
        evoluvionGasolinera.offer("--------------------------------------------------------------\n");
        this.visual = visual;
        this.debugState = debugState;
    }
    
    /**
     * Crea un log para cada clase que lo necesita y uno general de lo que escribieron todas
     * @param key Clase que escribe
     * @param txt Datos a escribir
     */
    public void write(String key, String txt) 
    {
        if(!log.containsKey(key))
        {
            log.put(key, "--------------------------------------------------------------\n");
        }
        String str = " - "+relativeTime()+" -" + txt+"\n";
        evoluvionGasolinera.offer(key + " " + str);
        log.put(key,  log.get(key) + str);
        if (debugState)
            visual.write(retriveLog());
        //System.out.println(key + " - " + str);
    }
    
    /**
     * Borra el registro de datos anterior y guarda el registro de la nueva simulación
     */
    public void save ()
    {
        try
        {
            //Elimina el registro anterior
            String listado[] = new File("./src/txt").list();

            for (String archivo : listado)
            {
                if(archivo.charAt(0)!='.')
                {
                    new File("./src/txt/"+archivo).delete();
                }
            }

            //Guarda el registro nuevo
            for (String key : log.keySet())
            {   
                log.put(key, log.get(key) + "--------------------------------------------------------------\n");
                try(FileWriter stream = new FileWriter("./src/txt/"+key+".txt"))
                { 
                    try(BufferedWriter buffer = new BufferedWriter(stream))
                    {
                        try (PrintWriter writer = new PrintWriter(buffer))
                        {
                            writer.print(log.get(key));
                            writer.close();
                        }
                        buffer.close();
                    } 
                    stream.close();
                }
            }
            evoluvionGasolinera.offer("--------------------------------------------------------------\n");
            try(FileWriter stream = new FileWriter("./src/txt/evoluvionGasolinera.txt"))
                { 
                    try(BufferedWriter buffer = new BufferedWriter(stream))
                    {
                        try (PrintWriter writer = new PrintWriter(buffer))
                        {
                            writer.print(retriveLog());
                            writer.close();
                        }
                        buffer.close();
                    } 
                    stream.close();
                }
        }
        catch (IOException e)
        {
            System.out.println("ERROR DE GUARDADO" + e.toString());
        }
    }
    
    /**
     * Tiempo transcurrido desde que se inició el log
     * @return tiempo en ms
     */
    public long relativeTime()
    {
        return new Date().getTime() - initialTime;
    }
    
    /**
     * Retorna el log general del conjunto de lo que todas las clases escribieron
     * @return String log
     */
    public String retriveLog ()
    {
        String str = "";
        str = evoluvionGasolinera.stream().map((s) -> s).reduce(str, String::concat);
        return str;
    }
}

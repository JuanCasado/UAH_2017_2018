
package pecl3.screens;

/**
 *
 * @author mr.blissfulgrin
 */
public interface FuelStationInterface
{
    //Métodos que toda interface de un servidor debe cumplir
    
    //USADOS EN EL MODO ACTIVO
    /**
     * Muestra un coche en el buffer
     * @param number 
     */
    public void carArrivesFuelStation(int number);
    /**
     * Elimina al coche del buffer de espera
     * @param number 
     */
    public void carLeavesFuelStationQueue(int number);
    /**
     * Pone al coche en un surtidor
     * @param carNumber
     * @param pumpNumber 
     */
    public void carReachesPump (int carNumber, int pumpNumber);
    /**
     * Pone al trabajador en un surtidor
     * @param workerNumber
     * @param pumpNumber 
     */
    public void workerStartFueling (int workerNumber, int pumpNumber);
    /**
     * Elimina a un trabajador de un surtidor
     * @param workerNumber
     * @param pumpNumber 
     */
    public void workerEndsFueling (int workerNumber, int pumpNumber);
    /**
     * Elimina un coche de un surtidor
     * @param carNumber
     * @param pumpNumber 
     */
    public void carLeavesPump (int carNumber, int pumpNumber);
    /**
     * Muestra gráficamente que la simulación ha finalizado
     */
    public void endSimulation ();
    
    /**
     * Pone el texto en la pantalla
     * En el Servidor lo pone tal cual y en el cliente lo añade al que había
     * @param texto 
     */
    public void write(String texto);
    
    /**
     * Usado por la comunicación, los clientes dice en qué estado querrían la barrera
     * y por ende si quieren pausar o reanudafr la simulación
     * @param state 
     */
    public void barrera(boolean state);
    
    //USADOS EN EL MODO PASIVO
    /**
     * Borra todos los datos de la interface
     */
    public void reset();
    /**
     * Extyrae los dartos de la interface a partidr de un array que los contiene codificados
     * La sintaxis de codificación es idéntica para todas las interfaces
     * @param data 
     */
    public void setData(String [] data);
}

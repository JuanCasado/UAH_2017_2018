
package pecl3.src;

/**
 *
 * @author mr.blissfulgrin
 */
public class Controller
{
    //VALORES RECONOCIDOS POR EL CONTROLADOR
    public static final String CONTINUE = "CONTINUE";
    public static final String PAUSE = "PAUSE";
    public static final String END = "END";
    
    private String state;
    
    public Controller ()
    {
        state = CONTINUE;
    }
    
    /**
     * Aplica un estado al controlador
     * @param state SOLO SON VALIDOS LOS ESTADOS PROPIOS
     */
    public synchronized void setState (String state)
    {
        switch (state)
        {
            case CONTINUE:
                    this.state = CONTINUE;
            break;
            case PAUSE:
                    this.state = PAUSE;
            break;
            case END:
                    this.state = END;
            break;
        }
    }
    
    /**
     * Retorna el estado del Controlador
     * @return state
     */
    public synchronized String getState ()
    {
        return state;
    }
}

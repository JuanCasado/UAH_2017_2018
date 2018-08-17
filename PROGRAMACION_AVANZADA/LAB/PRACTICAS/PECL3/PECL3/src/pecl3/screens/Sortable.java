
package pecl3.screens;

/**
 *
 * @author mr.blissfulgrin
 */
public class Sortable implements Comparable <Sortable>
{
    private final String str;

    public Sortable (String str)
    {
        this.str = str;
    }

    public String getStr ()
    {
        return str;
    }

    /**
     * Permite la ordenación los archivos por su nombre a la hora de 
     * mostrarlos en la interface gráfica
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Sortable o) 
    {
        return this.getStr().compareTo(o.getStr());
    }
}

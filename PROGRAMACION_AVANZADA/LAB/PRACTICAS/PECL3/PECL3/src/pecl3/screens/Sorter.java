
package pecl3.screens;

import java.util.Comparator;

/**
 *
 * @author mr.blissfulgrin
 */
public class Sorter implements Comparator <Sortable>
{
    /**
     * Realiza la ordenación entre objetos Sortable que implementan Comprable
     * Permite la ordenación los archivos por su nombre a la hora de 
     * mostrarlos en la interface gráfica
     * @param s1
     * @param s2
     * @return 
     */
    @Override
    public int compare (Sortable s1, Sortable s2)
    {
        String txt1 = s1.getStr();
        String txt2 = s2.getStr();
        int Ntxt1 = 0;
        int Ntxt2 = 0;
        switch (txt1.charAt(0))
        {
            case 'e':
                Ntxt1 = 0 - txt1.compareTo(txt2);
                break;
            case 'F':
                Ntxt1 = 5 - txt1.compareTo(txt2);
                break;
            case 'W':
                Ntxt1 = 10 - txt1.compareTo(txt2);
                break;
            case 'P':
                Ntxt1 = 20 - txt1.compareTo(txt2);
                break;
            case 'C':
                Ntxt1 = 30 - txt1.compareTo(txt2);
                break;
        }
        switch (txt2.charAt(0))
        {
            case 'e':
                Ntxt2 = 0 + txt1.compareTo(txt2);
                break;
            case 'F':
                Ntxt2 = 5 + txt1.compareTo(txt2);
                break;
            case 'W':
                Ntxt2 = 10 + txt1.compareTo(txt2);
                break;
            case 'P':
                Ntxt2 = 20 + txt1.compareTo(txt2);
                break;
            case 'C':
                Ntxt2 = 30 + txt1.compareTo(txt2);
                break;
        }
        

        if(Ntxt1 > Ntxt2)
        {  //Ordenar de menor a mayor (izquierda derecha)
           return 1;
        }
        else if(Ntxt1 < Ntxt2){
           return -1;
        }
        else
           return 0;
    }   
}

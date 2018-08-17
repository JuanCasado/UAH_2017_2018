/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _java;

/**
 *
 * @author mr.blissfulgrin
 */
public class _JAVA {

    
    public static boolean es_primo (int target)
        {
                for (int intento = 2; intento < target; intento++)
                        if ((target % intento) == 0)
                                return false;
                return true;
        }
    public static void main(String[] args) 
    {


        

        int target = 2;
        int count = 1;
        while (true)
        {
                if (es_primo(target))
                {
                        System.out.println("El primo "+count+" es "+target);
                        count++;
                }
                else
                        System.out.println(target+" no es primo");
                target++;
        }
    }
    
}

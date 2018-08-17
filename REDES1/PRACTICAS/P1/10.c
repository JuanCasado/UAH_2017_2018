#include <stdio.h>


void crear(void)
{
    double operando1 = 0, operando2 = 0;
    char operador, control = 73;
    
    FILE * archivo;
    
    if((archivo = fopen(".//datos_10.txt","a"))!=NULL)
    {
        while (control != 110)
        {
            puts ("Introduzca según el formato:");
            puts("--------------------------------");
            printf("OPERANDO 1: ");
            scanf("%lf", &operando1);
            printf("OPERANDO 2: ");
            scanf("%lf", &operando2);
            printf("OPERADOR ==> ");
            while(getchar()!=10);
            scanf("%c", &operador);

            switch (operador)
            {
                case 43:
                case 42:
                case 45:
                case 47:
                    puts("--------------------------------");
                    puts("Guardado");
                    fprintf(archivo,"%lf\t%lf\t%c\n",operando1,operando2,operador);
                    break;
                default:
                    puts("--------------------------------");
                    puts("Error en el operador");
                    break;
            }
            while(getchar()!=10);
            puts("--------------------------------");
            puts("Continuar? (y/n)");
            puts("--------------------------------");
            scanf("%c", &control);
        }

    }
    else
    {
        puts("--------------------------------");
        puts("Error al abrir el archivo");
        puts("--------------------------------");
    }
    fclose(archivo);
}

void procesar (void)
{
    FILE * archivo;
    FILE * archivo2;
    double operando1 = 0, operando2 = 0, resultado = 0;
    char operador;
    int contador = 1;
    
    if(((archivo = fopen(".//datos_10.txt","a+"))!=NULL)&&((archivo2 = fopen(".//resultados_10.txt","w"))!=NULL))
    {
        rewind(archivo);
        while(!feof(archivo))
        {
            fscanf(archivo,"%lf", &operando1);
            fscanf(archivo,"%lf", &operando2);
            fgetc(archivo);
            fscanf(archivo,"%c", &operador);
            fprintf(archivo2,"%lf\t%lf\t%c",operando1,operando2,operador);
            switch (operador)
            {
                case 43:
                    resultado = operando1 + operando2;
                    fprintf(archivo2,"\t==> %lf\n",resultado);
                    break;
                case 42:
                    resultado = operando1 * operando2;
                    fprintf(archivo2,"\t==> %lf\n",resultado);
                    break;
                case 45:
                    resultado = operando1 - operando2;
                    fprintf(archivo2,"\t==> %lf\n",resultado);
                    break;
                case 47:
                    resultado = operando1 / operando2;
                    fprintf(archivo2,"\t==> %lf\n",resultado);
                    break;
                default:
                    puts("Error en el operador");
                    break;
            }
            contador ++;
            
        }

    }
    else
    {
        puts("--------------------------------");
        puts("Error al abrir el archivo");
        puts("--------------------------------");
    }
    fclose(archivo);
    fclose(archivo2);
}


int main (void)
{
    int opcion = 0;
    int control =1;
    
    while (control)
    {
        puts("--------------------------------");
        puts("Seleccione acción");
        puts("1. Crear archivo");
        puts("2. Procesar archivo");
        puts("3. Salir");
        puts("--------------------------------");
        scanf ("%d", &opcion);
        puts("--------------------------------");
        
        switch (opcion)
        {
            case 1:
                crear();
                break;
            case 2:
                procesar();
                break;
            case 3:
                control = 0;
                break;
            default:
                puts("Caracter incorrecto");
                break;
        }
    }
    return 0;
}

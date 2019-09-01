package problemas;

import java.util.Scanner;

public class Ventas 
{
	static final byte DOMINGO = 5; //HACE REFERENCIA A LA POSICION 5 DEL VECTOR SEMANAS, DONDE SE GUARDA EL VALOR DEL DOMINGO
	
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		
		String[] respuestas = new String[9]; //VECTOR QUE CONTIENE LAS RESPUESTAS POSIBLES AL PROBLEMA
		respuestas[0] = "MARTES";
		respuestas[1] = "MIERCOLES";
		respuestas[2] = "JUEVES";
		respuestas[3] = "VIERNES";
		respuestas[4] = "SABADO";
		respuestas[5] = "DOMINGO";
		respuestas[6] = "EMPATE";
		respuestas[7] = "SI";
		respuestas[8] = "NO";
		
		float semana[] = new float[6]; //VECTOR DONDE SE GUARDAN LOS VALORES DE 1 SEMANA
		float valor; //VARIABLE QUE GUARDA EL INGRESO DE 1 DIA
		byte indice = 0; //INDICE QUE RECORRE LA SEMANA Y VA ASIGNANDO SU VALOR
		
		while(true)
		{
			valor = scanner.nextFloat(); //LEER VALOR
			if(indice == 0 && valor == -1) break; //SI ES EL PRIMER DIA DE LA SEMANA Y EL VALOR ES -1, TERMINAR PROGRAMA
			
			semana[indice] = valor; //ASIGNAR AL DIA SU VALOR
			
			indice++; //AUMENTAR EL INDICE
			
			if(indice == 6) //SI EL INDICE YA SOBREPASO LOS DIAS DE LA SEMANA...
			{
				//CALCULAR LOS RESULTADOS
				System.out.println(respuestas[buscarMayor(semana)] + " " + respuestas[buscarMenor(semana)] + " " + respuestas[ventasDomingo(semana)]);
				indice = 0; //Y REINCIAR EL INDICE
			}
		}
	}
	
	public static byte ventasDomingo(float[] semana)
	{
		float promedio = 0; //VARIABLE QUE GUARDA EL PROMEDIO DE VENTAS DE LA SEMANA
		
		for(byte i=0; i<6; i++) //RECORRER LA SEMANA
		{
			promedio += semana[i]; //GUARDAR LA SUMA DE TODAS LAS VENTAS
		}
		
		promedio = promedio / 6; //DIVIDIR LA SUMA POR LA CANTIDAD DE DIAS
		
		//SI LAS VENTAS DEL DOMINGO SUPERAN EL PROMEDIO...
		if(semana[DOMINGO] > promedio) return 7; //7 HACE REFERENCIA A "SI" EN EL VECTOR DE RESPUESTAS
		else return 8; //8 HACE REFERENCIA A "NO" EN EL VECTOR DE RESPUESTAS
	}
	
	public static byte buscarMenor(float[] semana)
	{
		float menor = 0; //VARIABLE QUE GUARDA EL MENOR VALOR ENCONTRADO
		float segundoMenor = 0; //VARIABLE QUE GUARDA EL SEGUNDO MENOR ENCONTRADO
		byte indiceDelMenor = 0; //VARIABLE QUE GUARDA EL INDICE DONDE ESTA UBICADO EL MENOR VALOR
		
		for(byte i=0; i<6; i++) //RECORRER LA SEMANA
		{
			if(i == 0) //SI ES LA PRIMERA ITERACION
			{
				menor = semana[i]; //TOMAR COMO MENOR AL PRIMER DIA DE LA SEMANA
				indiceDelMenor = i; //GUARDAR SU INDICE 
			}
			else //SI NO ES LA PRIMERA ITERACION
			{
				if(semana[i] <= menor) //SI EL VALOR DEL DIA SELECCIONADO ES MENOR O IGUAL AL MENOR VALOR ENCONTRADO
				{
					segundoMenor = menor; //PASAR AL MENOR COMO SEGUNDO MENOR
					menor = semana[i]; //PASAR COMO MENOR AL NUEVO VALOR
					indiceDelMenor = i; //GUARDAR SU INDICE
				}
			}
		}
		
		//SI EL MENOR ES IGUAL AL SEGUNDO MENOR, QUIERE DECIR QUE HAY 2 DIAS CON MENOR GANANCIA
		if(menor == segundoMenor) return 6; //6 HACE REFERENCIA AL VECTOR "DIAS" A SU VALOR "EMPATE"
		else return indiceDelMenor; //SI HAY UN SOLO DIA CON MENOR GANANCIA, DEVOLVER SU POSICION
	}
	
	public static byte buscarMayor(float[] semana)
	{
		float mayor = 0; //VARIABLE QUE GUARDA EL MAYOR VALOR ENCONTRADO
		float segundoMayor = 0; //VARIABLE QUE GUARDA EL SEGUNDO MAYOR ENCONTRADO
		byte indiceDelMayor = 0; //VARIABLE QUE GUARDA EL INDICE DONDE ESTA UBICADO EL MAYOR VALOR
		
		for(byte i=0; i<6; i++) //RECORRER LA SEMANA
		{
			if(i == 0) //SI ES LA PRIMERA ITERACION
			{
				mayor = semana[i]; //TOMAR COMO MAYOR AL PRIMER DIA DE LA SEMANA
				indiceDelMayor = i; //GUARDAR SU INDICE 
			}
			else //SI NO ES LA PRIMERA ITERACION
			{
				if(semana[i] >= mayor) //SI EL VALOR DEL DIA SELECCIONADO ES MAYOR O IGUAL AL MAYOR VALOR ENCONTRADO
				{
					segundoMayor = mayor; //PASAR AL MAYOR COMO SEGUNDO MAYOR
					mayor = semana[i]; //PASAR COMO MAYOR AL NUEVO VALOR
					indiceDelMayor = i; //GUARDAR SU INDICE
				}
			}
		}
		
		//SI EL MAYOR ES IGUAL AL SEGUNDO MAYOR, QUIERE DECIR QUE HAY 2 DIAS CON MAYOR GANANCIA
		if(mayor == segundoMayor) return 6; //6 HACE REFERENCIA AL VECTOR "DIAS" A SU VALOR "EMPATE"
		else return indiceDelMayor; //SI HAY UN SOLO DIA CON MAYOR GANANCIA, DEVOLVER SU POSICION
	}
}

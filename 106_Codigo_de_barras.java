package problemas;

import java.util.Scanner;

public class CodigoDeBarras 
{
	public static int[] numeros = null; //VECTOR PARA GUARDAR TODOS LOS DIGITOS DEL CODIGO
	public static String codigo; //GUARDA EL CODIGO INGRESADO
	public static boolean EAN8; //INDICA SI EL NUMERO ES DE FORMATO EAN-8
	public static boolean EAN13; //INDICA SI EL NUMERO ES DE FORMATO EAN-13
	public static int resultado; //GUARDA EL RESULTADO DE LA OPERACION ENTRE LOS DIGITOS
	
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		
		while(true)
		{
			resultado = 0;
			EAN8 = false;	//INICIAR VARIABLES CON VALOR POR DEFECTO
			EAN13 = false;
			
			codigo = scanner.nextLine(); //INGRESAR CODIGO
			
			if(codigo.equals("0")) break; //SI EL CODIGO ES IGUAL A 0, TERMINAR EL PROGRAMA
			
			comprobarFormato(); //COMPRUEBA QUE FORMATO TIENE EL CODIGO, SI EAN-8 Ó EAN-13
			llenarVector(); //LLENA EL VECTOR CON CADA DIGITO DEL CODIGO
			realizarOperacion(); //REALIZAR OPERACION ENTRE LOS DIGITOS
			comprobarResultado(); //COMPRUEBA Y MUESTRA POR PANTALLA EL RESULTADO
		}
	}
	
	public static void comprobarResultado()
	{
		if((resultado % 10) == 0) //SI EL RESULTADO ES MULTIPLO DE 10...
		{
			if(EAN8) //Y EL CODIGO ES FORMATO EAN-8
			{
				System.out.println("SI");
			}
			
			if(EAN13) //Y EL CODIGO ES FORMATO EAN-13
			{
				System.out.println("SI" + " " + devolverPais()); //DEVOLVER PAIS COMPRUEBA LOS PRIMEROS DIGITOS DEL CODIGO
			}
		}
		else //SI EL RESULTADO NO ES MULTIPLO DE 10...
		{
			System.out.println("NO");
		}
	}
	public static void realizarOperacion()
	{
		for(int i=1; i<numeros.length; i++) //RECORRER EL VECTOR CON LOS DIGITOS. "i" EMPIEZA POR 1 PARA NO CONTAR EL DIGITO CONTROL
		{
			if(i % 2 == 0) //SI EL INDICE ES PAR
			{
				resultado += numeros[numeros.length - 1 - i] * 1; //MULTIPLICAR EL DIGITO POR 1
			}
			else //SI ES IMPAR
			{
				resultado += numeros[numeros.length - 1 - i] * 3; //MULTIPICAR EL DIGITO POR 3
			}
		}
		
		resultado += numeros[numeros.length - 1]; //AL RESULTADO TOTAL SUMARLE EL DIGITO CONTROL
	}
	
	public static void llenarVector()
	{
		for(int i=0; i<codigo.length(); i++) //RECORRER EL CODIGO INGRESADO
		{
			//UBICAR CADA NUMERO DEL CODIGO EMPEZANDO DESDE DERECHA A IZQUIERDA
			numeros[numeros.length - 1 - i] = Character.getNumericValue(codigo.charAt(codigo.length() - 1 - i)); 
		}
	}
	
	public static void comprobarFormato()
	{
		if(codigo.length() <= 8)  //SI EL CODIGO TIENE 8 NUMEROS O MENOS
		{
			EAN8 = true; //ES DE TIPO EAN-8
			numeros = new int[8]; //DECLARAR EL VECTOR CON TAMAÑO 8
		}
		else if(codigo.length() <= 13) //SI TIENE MAS DE 8 NUMEROS PERO 13 O MENOS
		{
			EAN13 = true; //ES DE TIPO EAN-13
			numeros = new int[13]; //DECLARAR VECTOR CON TAMAÑO 13
		}
	}
	
	public static String devolverPais()
	{
		if(numeros[0] == 0) return "EEUU";
		if(numeros[0] == 5 && numeros[1] == 0) return "Inglaterra";
		if(numeros[0] == 7 && numeros[1] == 0) return "Noruega";
		if(numeros[0] == 3 && numeros[1] == 8 && numeros[2] == 0) return "Bulgaria";
		if(numeros[0] == 5 && numeros[1] == 3 && numeros[2] == 9) return "Irlanda";
		if(numeros[0] == 5 && numeros[1] == 6 && numeros[2] == 0) return "Portugal";
		if(numeros[0] == 7 && numeros[1] == 5 && numeros[2] == 9) return "Venezuela";
		if(numeros[0] == 8 && numeros[1] == 5 && numeros[2] == 0) return "Cuba";
		if(numeros[0] == 8 && numeros[1] == 9 && numeros[2] == 0) return "India";
			
		return "Desconocido";
	}
}

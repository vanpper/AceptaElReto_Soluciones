package practica;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
	
	Scanner scanner = new Scanner(System.in); //VARIABLE PARA LEER ENTRADA POR TECLADO
	String numero; //VARIABLE QUE GUARDA EL NUMERO INGRESADO
	int num_casos; //VARIABLE QUE GUARDA EL NUMERO DE CASOS INGRESADO
	int mayor; //VARIABLE QUE GUARDA EL NUMERO ORDENADO DE MAYOR A MENOR
	int menor; //VARIABLE QUE GUARDA EL NUMERO ORDENADO DE MENOR A MAYOR
	int vueltas = 0; //VARIABLE QUE GUARDA EL NUMERO DE VUELTAS QUE TARDO LA CONSTANTE DE KAPREKAR
		
	num_casos = scanner.nextInt(); //INGRESAR NUMERO DE CASOS
		
	for(int i=0; i<num_casos; i++) //LAS VUELTAS QUE SE DARAN SERAN IGUAL QUE EL NUMERO DE CASOS
	{
		numero = scanner.next(); //INGRESAR EL NUMERO
		numero = RellenarEspaciosVacios(numero); //RELLENAR CON 0 LOS ESPACIOS VACIOS, PARA QUE SIEMPRE SEA UN NUMERO DE 4 CIFRAS
			
		if(numero != "6174") //SI EL NUMERO NO ES LA PROPIA CONSTANTE...
		{
			if(!(numero.charAt(0) == numero.charAt(1) && numero.charAt(0) == numero.charAt(2) && numero.charAt(0) == numero.charAt(3))) //SI LAS 4 CIFRAS NO SON EL MISMO NUMERO...
			{
				while(Integer.parseInt(numero) != 6174) //PROCESAR MIENTRAS EL NUMERO NO SEA LA CONSTANTE
				{	
					mayor = ObtenerMayor(numero); //ORDENAR EL NUMERO DE MAYOR A MENOR
					menor = ObtenerMenor(numero); //ORDENAR EL NUMERO DE MENOR A MAYOR
					numero = Integer.toString(mayor - menor); //RESTAR EL MENOR AL MAYOR
					numero = RellenarEspaciosVacios(numero); //RELLENAR CON 0 LOS ESPACIOS VACIOS, PARA QUE SIEMPRE SEA UN NUMERO DE 4 CIFRAS
					vueltas++; //SUMAR UNA VUELTA
				}
					
				System.out.println(vueltas); //MOSTRAR CUANTAS VUELTAS SE TARDO EN LLEGAR A LA CONSTANTE
				vueltas = 0; //REINICIAR CONTADOR
			}
			else //SI LAS 4 CIFRAS SON EL MISMO NUMERO...
			{
				System.out.println(8); //IMPRIMIR "8"
			}
		}
		else //SI EL NUMERO ES LA PROPIA CONSTANTE...
		{
			System.out.println(0); //IMPRIMIR "0"
		}
	}
}
	
	public static String RellenarEspaciosVacios(String numero) 
	{
		if(numero.length() == 1) numero = "000" + numero; //SI SE INGRESO UNA SOLA CIFRA = 0001
		if(numero.length() == 2) numero = "00" + numero; //SI SE INGRESRON DOS CIFRAS = 0012
		if(numero.length() == 3) numero = "0" + numero; //SI SE INGRESRON TRES CIFRAS = 0123
		return numero; //DE ESTA MANERA SE DEVUELVE UN NUMERO DE 4 CIFRAS
	}
	
	public static int ObtenerMayor(String numero) 
	{
		char[] numeros = new char[4]; //VECTOR DONDE SE VAN A GUARDAR LAS 4 CIFRAS
		int multiplicador = 1000; //VARIABLE QUE MULTIPLICA EL VECTOR PARA OBTENER UNIDAD, DECENA, CENTENA, UNIDAD DE MIL
		int mayor = 0; //VARIABLE QUE GUARDA EL NUMERO FINAL ORDENADO DE MAYOR A MENOR
		
		for(int i=0; i<numero.length(); i++) 
		{
			numeros[i] = numero.charAt(i); //SEPARAR EL NUMERO INGRESADO Y GUARDAR CADA CIFRA EN EL VECTOR
		} 
		
		for(int i=0; i<numero.length(); i++) //ORDENAMIENTO DEL VECTOR DE MAYOR A MENOR
		{	
			for(int j=0; j<numero.length(); j++) 
			{
				if(numeros[i] > numeros[j]) 
				{
					char aux = numeros[i];
					numeros[i] = numeros[j];
					numeros[j] = aux;
				}
			}
		}
		
		for(int i=0; i<numero.length(); i++) 						//ESTE FOR TRANSFORMA EL VECTOR EN UN UNICO NUMERO ENTERO. HACIENDO LAS SIGUIENTES OPERACIONES
		{										//numeros[0] * 1000
			mayor += Character.getNumericValue(numeros[i]) * multiplicador; 	//numeros[1] * 100
			multiplicador /= 10;							//numeros[2] * 10
		}										//numeros[3] * 1
												//SUMA CADA RESULTADO Y LO GUARDA EN LA VARIABLE "MAYOR"
		return mayor; //DEVOLVER EL NUMERO ORDENADO DE MAYOR A MENOR
	}

	public static int ObtenerMenor(String numero) 
	{
		char[] numeros = new char[4]; //VECTOR DONDE SE VAN A GUARDAR LAS 4 CIFRAS
		int multiplicador = 1000; //VARIABLE QUE MULTIPLICA EL VECTOR PARA OBTENER UNIDAD, DECENA, CENTENA, UNIDAD DE MIL
		int menor = 0; //VARIABLE QUE GUARDA EL NUMERO FINAL ORDENADO DE MAYOR A MENOR
		
		for(int i=0; i<numero.length(); i++) 
		{
			numeros[i] = numero.charAt(i); //SEPARAR EL NUMERO INGRESADO Y GUARDAR CADA CIFRA EN EL VECTOR
		} 
		
		for(int i=0; i<numero.length(); i++) //ORDENAMIENTO DEL VECTOR DE MENOR A MAYOR
		{	
			for(int j=0; j<numero.length(); j++) 
			{
				if(numeros[i] < numeros[j]) 
				{
					char aux = numeros[i];
					numeros[i] = numeros[j];
					numeros[j] = aux;
				}
			}
		}
		
		for(int i=0; i<numero.length(); i++) 						//ESTE FOR TRANSFORMA EL VECTOR EN UN UNICO NUMERO ENTERO. HACIENDO LAS SIGUIENTES OPERACIONES
		{										//numeros[0] * 1000
			menor += Character.getNumericValue(numeros[i]) * multiplicador; 	//numeros[1] * 100
			multiplicador /= 10;							//numeros[2] * 10
		}										//numeros[3] * 1
												//SUMA CADA RESULTADO Y LO GUARDA EN LA VARIABLE "MENOR"
		return menor; //DEVOLVER EL NUMERO ORDENADO DE MENOR A MAYOR
	}
}


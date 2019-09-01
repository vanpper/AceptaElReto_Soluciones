package problemas;

import java.util.Scanner;

public class Moviles {

	static Scanner scanner = new Scanner(System.in);
	
	//CONSTANTES SOLO PARA REFERENCIA
	static final byte Pi = 0;	
	static final byte Di = 1;
	static final byte Pd = 2;
	static final byte Dd = 3;
	static final byte PesoTotal = 4;
	
	public static void main(String[] args) 
	{
		while(true)
		{
			int[] numeros =  new int[5]; //VECTOR PARA GUARDAR EL MOVIL
			
			//EL VECTOR SE ORDENA DE LA SIGUIENTE MANERA
			//numeros[0] PESO IZQUIERDO
			//numeros[1] DISTANCIA IZQUIERDA
			//numeros[2] PESO DERECHO
			//numeros[3] DISTANCIA DERECHA
			//numeros[4] PESO TOTAL
			
			llenarVariables(numeros); //LLENAR EL VECTOR
			
			if(numeros[Pi] + numeros[Di] + numeros[Pd] + numeros[Dd] == 0) break; //SI TODOS LOS VALORES SON 0, TERMINAR EL PROGRAMA
			
			if(estaBalanceado(numeros)) //SI EL MOVIL ESTA BALANCEADO...
			{
				System.out.println("SI");
			}
			else //SI NO LO ESTA...
			{
				System.out.println("NO");
			}
		}
	}
	
	public static boolean estaBalanceado(int[] numeros)
	{
		if(numeros[Pi] != 0 && numeros[Pd] != 0) //SI EL MOVIL YA NO TIENE HIJOS...
		{
			if((numeros[Pi] * numeros[Di]) == (numeros[Pd] * numeros[Dd])) //CALCULAR SI ESTA EQUILIBRADO...
			{
				numeros[PesoTotal] = numeros[Pi] + numeros[Pd]; //OBTENER SU PESO TOTAL
				return true; //Y DEVOLVER QUE SI ESTA EQUILIBRADO
			}
			else return false; //SI NO LO ESTA, SIMPLEMENTE DEVOLVER QUE NO ESTA EQUILIBRADO
		}
		else //SI EL MOVIL TIENE SUBMOVILES
		{
			//VARIABLES PARA SABER SI LOS SUBMOVILES QUE TIENE ESTAN BALANCEADOS
			boolean IzqBalanceado = true;
			boolean DerBalanceado = true;
			
			if(numeros[Pi] == 0) //SI TIENE UN SUBMOVIL EN EL LADO IZQUIERDO
			{
				int[] hijoIzq =  new int[5]; //DECLARAR EL NUEVO SUBMOVIL
				llenarVariables(hijoIzq); //LLENAR EL SUBMOVIL CON SUS DATOS
				IzqBalanceado = estaBalanceado(hijoIzq); //OBTENER SI ESTA BALANCEADO, LLAMANDO A ESTA MISMA FUNCION
				numeros[Pi] = hijoIzq[PesoTotal]; //OBTENER EL PESO TOTAL DEL SUBMOVIL Y ASIGNARLO COMO EL PESO IZQUIERDO DE ESTE MOVIL
			}
			
			if(numeros[Pd] == 0) //SI TIENE UN SUBMOVIL EN EL LADO DERECHO
			{
				int[] hijoDer =  new int[5]; //DECLARAR EL NUEVO SUBMOVIL
				llenarVariables(hijoDer); //LLENAR EL SUBMOVIL CON SUS DATOS
				DerBalanceado = estaBalanceado(hijoDer); //OBTENER SI ESTA BALANCEADO, LLAMANDO A ESTA MISMA FUNCION
				numeros[Pd] = hijoDer[PesoTotal]; //OBTENER EL PESO TOTAL DEL SUBMOVIL Y ASIGNARLO COMO EL PESO DERECHO DE ESTE MOVIL
			}
			
			numeros[PesoTotal] = numeros[Pi] + numeros[Pd]; //ASIGNAR EL PESO TOTAL COMO LA SUMA DE SUS 2 PESOS
			
			//SI SUS 2 SUBMOVILES ESTAN BALANCEADOS, Y EL MOVIL MISMO TAMBIEN LO ESTA...
			if(IzqBalanceado == true && DerBalanceado == true && ((numeros[Pi] * numeros[Di]) == (numeros[Pd] * numeros[Dd])))
			{
				return true; //DEVOLVER VERDADERO
			}
			else //SI ALGUNA CONDICION FALLA...
			{
				return false; //DEVOLVER FALSO
			}
		}
	}
	
	public static void llenarVariables(int[] numeros)
	{
		String cadena; //VARIABLE QUE GUARDA EL INGRESO
		String[] partes; //VARIABLE QUE OBTIENE CADA NUMERO INGRESADO POR SEPARADO
		
		cadena = scanner.nextLine(); //INGRESAR NUMEROS
		partes = cadena.split(" "); //OBTENER CADA NUMERO POR SEPARADO
		
		numeros[Pi] = Integer.parseInt(partes[Pi]); //ASIGNAR AL VECTOR EL PESO IZQUIERDO
		numeros[Di] = Integer.parseInt(partes[Di]); //ASIGNAR AL VECTOR LA DISTANCIA IZQUIERDA
		numeros[Pd] = Integer.parseInt(partes[Pd]); //ASIGNAR AL VECTOR EL PESO DERECHO
		numeros[Dd] = Integer.parseInt(partes[Dd]); //ASIGNAR AL VECTOR LA DISTANCIA DERECHA
	}
}
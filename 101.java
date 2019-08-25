package Practica;
import java.util.*;

public class Main
{
	static Scanner scanner = new Scanner(System.in);
	static int n;
	static int cuadrado[][];
	static boolean test[];
	static short i;
	static short j;
	
	public static void main(String[] args) 
	{	
		while(true)
		{
			n = scanner.nextShort(); //INGRESAR EL TAMAÑO DEL CUADRADO
			scanner.nextLine(); //LIMPIAR BUFFER
			
			if(n == 0) break;
			
			LlenarMatriz(); //LLENAR LA MATRIZ
			
			if(EsDiabolico()) //SI ES DIABOLICO
			{
				if(EsEsoterico()) //FIJARSE TAMBIEN SI ES ESOTERICO. SI LO ES...
				{
					System.out.println("ESOTERICO"); //MOSTAR QUE ES ESOTERICO
				}
				else //SI NO ES ESOTERICO
				{
					System.out.println("DIABOLICO"); //MOSTRAR QUE ES SOLO DIABOLICO
				}
			}
			else //SI NO ES DIABOLICO
			{
				System.out.println("NO"); //MOSTAR "NO"
			}
		}
	}
	
	public static boolean EsEsoterico()
	{
		int CM = 0; //VARIABLE QUE GUARDA LA CONSTANTE MAGICA
		int CM2; //VARIABLE QUE GUARDA LA CONSTANTE MAGICA 2
		int sumaCasillas = 0; //VARIABLE QUE GUARDA LA SUMA DE LAS ESQUINAS
		
		for(i=0; i<test.length; i++)
		{
			if(test[i] == false) return false; //SI FALTO AGREGAR ALGUN NUMERO DE 1 HASTA N*N, DEVOLVER FALSO
		}
		
		for(i=0; i<n; i++) {CM += cuadrado[0][i];} //OBTENEMOS LA CONSTANTE MAGICA
		CM2 = (short) ((4 * CM) / n); //OBTENEMOS LA CONSTANTE MAGICA 2
		
		sumaCasillas += cuadrado[0][0]; //ESQUINA SUPERIOR IZQUIERDA
		sumaCasillas += cuadrado[n - 1][0]; //ESQUINA INFERIOR IZQUIERDA
		sumaCasillas += cuadrado[0][n - 1]; //ESQUINA SUPERIOR DERECHA
		sumaCasillas += cuadrado[n -1][n -1]; //ESQUINA INFERIOR DERECHA
		
		if(sumaCasillas != CM2) return false; //SI LA SUMA NO ES IGUAL A LA CONSTANTE MAGICA 2
		sumaCasillas = 0; //REINICIAR SUMA
		
		if((n % 2) == 1) //SI "n" ES IMPAR
		{
			sumaCasillas += cuadrado[0][n / 2]; //CASILLA CENTRAL SUPERIOR
			sumaCasillas += cuadrado[n / 2][n - 1]; //CASILLA CENTRAL DERECHA
			sumaCasillas += cuadrado[n - 1][n / 2]; //CASILLA CENTRAL INFERIOR
			sumaCasillas += cuadrado[n / 2][0]; //CASILLA CENTRAL IZQUIERDA
			
			if(sumaCasillas != CM2) return false; //SI LA SUMA NO ES IGUAL A LA CONSTANTE MAGICA 2
			sumaCasillas = 0; //REINICIAR SUMA
			
			if((cuadrado[n / 2][n / 2] * 4) != CM2) return false; //SI LA CASILLA CENTRAL DEL CUADRADO * 4 NO ES IGUAL A LA CONSTANTE MAGICA 2
		}
		
		if((n % 2) == 0) //SI "n" ES PAR
		{
			sumaCasillas += cuadrado[0][(n / 2) - 1]; //CASILLA CENTRAL SUPERIOR 1
			sumaCasillas += cuadrado[0][(n / 2)]; //CASILLA CENTRAL SUPERIOR 2
			sumaCasillas += cuadrado[(n / 2) - 1][n - 1]; //CASILLA CENTRAL DERECHA 1
			sumaCasillas += cuadrado[(n / 2)][n - 1]; //CASILLA CENTRAL DERECHA 2
			sumaCasillas += cuadrado[n - 1][(n / 2) - 1]; //CASILLA CENTRAL INFERIOR 1
			sumaCasillas += cuadrado[n - 1][(n / 2)]; //CASILLA CENTRAL INFERIOR 2
			sumaCasillas += cuadrado[(n / 2) - 1][0]; //CASILLA CENTRAL IZQUIERDA 1
			sumaCasillas += cuadrado[(n / 2)][0]; //CASILLA CENTRAL IZQUIERDA 2
			
			if(sumaCasillas != (CM2 * 2)) return false; //SI LA SUMA NO ES IGUAL AL DOBLE DE LA CONSTANTE MAGICA 2
			sumaCasillas = 0; //REINICIAR SUMA
			
			sumaCasillas += cuadrado[(n / 2) - 1][(n / 2) - 1]; //CASILLA CENTRAL 1
			sumaCasillas += cuadrado[(n / 2) - 1][(n / 2)]; //CASILLA CENTRAL 2
			sumaCasillas += cuadrado[(n / 2)][(n / 2) - 1]; //CASILLA CENTRAL 3
			sumaCasillas += cuadrado[(n / 2)][(n / 2)]; //CASILLA CENTRAL 4
			
			if(sumaCasillas != CM2) return false; //SI LA SUMA NO ES IGUAL A LA CONSTANTE MAGICA 2
		}
		
		return true; //SI SE LLEGO HASTA ACA ES PORQUE ES ESOTERICO. LAS CORRESPONDIENTES SUMAS COINCIDEN CON LA CONSTANTE MAGICA
	}
	
 	public static boolean EsDiabolico()
	{
		int suma = 0; //VARIABLE QUE GUARDA LA SUMA DE LA FILA, COLUMNA, DIAGONAL
		int referencia = 0; //REFERENCIA PARA COMPARAR LA SUMA
		
		for(i=0; i<n; i++) {referencia += cuadrado[0][i];} //TOMAMOS LA SUMA DE UNA SOLA FILA COMO REFERENCIA PARA COMPARAR

		for(i=0; i<n; i++) //SUMAR FILAS HORIZONTALES
		{
			for(j=0; j<n; j++)
			{
				suma += cuadrado[i][j];
			}

			if(suma != referencia) {return false;} //SI LA SUMA DE LOS VALORES DE LA FILA NO ES IGUAL A LA REFERENCIA...
			suma = 0; //REINICIAR SUMA
		}

		for(i=0; i<n; i++) //SUMAR COLUMNAS VERTICALES
		{
			for(j=0; j<n; j++)
			{
				suma += cuadrado[j][i];
			}

			if(suma != referencia) {return false;} //SI LA SUMA DE LOS VALORES DE LA COLUMNA NO ES IGUAL A LA REFERENCIA...
			suma = 0; //REINICIAR SUMA
		}

		for(i=0; i<n; i++) //SUMAR DIAGONAL DE IZQUIERDA A DERECHA, ARRIBA HACIA ABAJO
		{
			suma += cuadrado[i][i];
		}
		
		if(suma != referencia) {return false;} //SI LA SUMA DE LOS VALORES DE LA DIAGONAL NO ES IGUAL A LA REFERENCIA...
		suma = 0; //REINCIAR SUMA
		
		for(i=0; i<n; i++) //SUMAR DIAGONAL DE IZQUIERDA A DERECHA, ABAJO HACIA ARRIBA
		{
			suma += cuadrado[n - 1 - i][i];
		}
		
		if(suma != referencia) {return false;} //SI LA SUMA DE LOS VALORES DE LA DIAGONAL NO ES IGUAL A LA REFERENCIA...
		
		return true; //SI SE LLEGO HASTA ACA ES PORQUE LA SUMA DE LOS VALORES POR COLUMNA, FILA Y DIAGONALES PRINCIPALES, ES LA MISMA
	}
 	
 	public static void LlenarMatriz()
	{
 		cuadrado = new int[n][n];
		test = new boolean[n*n];
		
		for(i=0; i<n; i++)
 		{
			for(j=0; j<n; j++)
			{
				cuadrado[i][j] = scanner.nextInt(); //GUARDAR EL NUMERO INGRESADO EN LA MATRIZ
				
				if(cuadrado[i][j] <= (n*n)) test[cuadrado[i][j] - 1] = true; //SI EL NUMERO ESTA COMPRENDIDO ENTRE 1 HASTA N*N, MARCARLO COMO QUE YA ESTA INGRESADO
			}
 		}
	}
}

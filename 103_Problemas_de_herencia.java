package problemas;

import java.util.Scanner;

public class ProblemasDeHerencia 
{
	static Scanner scanner = new Scanner(System.in);
	static final byte coheficiente = 0;
	static final byte exponente = 1;
	static final byte tamañoTerreno = 1;
	
	public static void main(String[] args) 
	{
		byte gradoPolinomio; //CONTIENE EL GRADO DEL POLINOMIO
		short[][] polinomio; //CONTIENE LOS COHEFICIENTES INGRESADOS Y SU RESPECTIVO EXPONENTE. [COHEFICIENTE][EXPONENTE]
		int cantidadRectangulos; //CONTIENE LA CANTIDAD DE RECTANGULOS EN QUE SE DIVIDIRA EL AREA
		
		while(true)
		{
			gradoPolinomio = scanner.nextByte(); //INGRESAR GRADO DE POLINOMIO
			if(gradoPolinomio == 20) break; //SI EL GRADO ES IGUAL A 20, TERMINAR EL WHILE
			
			polinomio = new short[gradoPolinomio + 1][2]; //GRADO + 1 ES PORQUE SE NECESITA UN ESPACIO MAS PARA EL TERMINO INDEPENDIENTE
			
			for(byte i=0; i<gradoPolinomio + 1; i++) //INGRESO DE LOS TERMINOS
			{
				polinomio[i][coheficiente] = scanner.nextShort(); //GUARDAR COHEFICIENTE
				polinomio[i][exponente] = (short) (gradoPolinomio - i); //GUARDAR SU RESPECTIVO EXPONENTE 
			}
			
			cantidadRectangulos = scanner.nextInt(); //INGRESAR LA CANTIDAD DE RECTANGULOS EN QUE SE DIVIDIRA EL AREA
			
			//FINALIZACION DE INGRESO DE DATOS
			//COMIENZO DEL CALCULO
			
			float baseRectangulos = 0; //VARIABLE QUE GUARDA LA BASE QUE TENDRAN TODOS LOS RECTANGULOS
			float X = 0; //VARIABLE QUE GUARDA CUANTO VALE X EN EL EJE X DEL GRAFICO
			float Y = 0; //VARIABLE QUE GUARDA CUANTO VALE Y EN EL EJE Y DEL GRAFICO, OSEA LA ALTURA DE LOS RECTANGULOS
			float areaTotalCain = 0; //VARIABLE QUE GUARDA EL AREA DEL TERRENO QUE LE PERTENECE A CAIN
			float areaTotalAbel = 0; //VARIABLE QUE GUARDA EL AREA DEL TERRENO QUE LE PERTENECE A ABEL
			float exceso = 0; //VARIABLE QUE GUARDA CUANTO SE PASO "Y" DEL LIMITE SUPERIOR DEL TERRENO, PARA NO PISAR TERRENOS VECINOS
			float diferenciaTerreno = 0; //VARIABLE QUE GUARDA CUANTA DIFERENCIA HAY ENTRE EL TERRENO DE CAIN Y ABEL 
			
			baseRectangulos = (float) tamañoTerreno / cantidadRectangulos; //CALCULAR LA BASE QUE TENDRA CADA RECTANGULO
			
			for(int i=0; i<cantidadRectangulos; i++) //RECORRER TODOS LOS RECTANGULOS
			{
				X = baseRectangulos * i; //GUARDAR EN QUE POSICION DE "X" ESTA EL RECTANGULO
				
				for(int j=0; j<polinomio.length; j++) //RECORRER EL POLINOMIO INGRESADO
				{
					if(j == gradoPolinomio) //SI "J" ES IGUAL AL GRADO DEL POLINOMIO, QUIERE DECIR QUE ESTA PARADO EN EL TERMINO INDEPENDIENTE, EL CUAL NO POSEE EXPONENTE
					{
						Y += polinomio[j][0]; //SUMAR A "Y" EL TERMINO INDEPENDIENTE
					}
					else
					{
						Y += polinomio[j][coheficiente] * Math.pow(X, polinomio[j][exponente]); //MULTIPLICAR COHECIENTE POR "X" POTENCIADO
					}
				}
				
				if(Y > 0) //TENER EN CUENTA SOLAMENTE LOS VALORES DE "Y" POSITIVOS, PARA NO PISAR TERRENO VECINO
				{
					if(Y > tamañoTerreno) //SI "Y" SUPERA EL LIMITE DEL TERRENO
					{
						exceso = Y - (float) tamañoTerreno; //CALCULAR EL EXCESO
						Y = Y - exceso; //Y RESTARSELO A "Y"
					}
					
					areaTotalCain += baseRectangulos * Y; //CALCULAR EL AREA DEL RECTANGULO Y SUMARLA AL AREA TOTAL DE CAIN
				}
				
				Y = 0; //REINICIAR "Y" PARA EL PROXIMO RECTANGULO
			}
			
			areaTotalAbel = tamañoTerreno - areaTotalCain; //OBTENER EL AREA DE ABEL
			diferenciaTerreno = areaTotalAbel - areaTotalCain; //OBTENER LA DIFERENCIA ENTRE LOS TERRENOS
			
			if(diferenciaTerreno < 0) //SOLO QUEREMOS EL VALOR ABSOLUTO. SI LA DIFERENCIA ES NEGATIVA...
			{
				diferenciaTerreno = diferenciaTerreno * -1; //PASARLA A POSITIVO
			}
			
			if(diferenciaTerreno <= 0.001) //SI LA DIFERENCIA ES MENOR O IGUAL A 0.001 HM2
			{
				System.out.println("JUSTO"); //CONSIDERARLO COMO QUE SON IGUALES
			}
			else //SI LOS TERRENOS NO SON IGUALES
			{
				if(areaTotalCain > areaTotalAbel) //SI EL AREA DE CAIN ES MAS GRANDE
				{
					System.out.println("CAIN");
				}
				else //SI EL AREA DE ABEL ES MAS GRANDE
				{
					System.out.println("ABEL");
				}
			}
		}
	}
}

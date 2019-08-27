package problemas;

import java.util.Scanner;

public class EncriptacionDeMensajes {

	public static void main(String[] args) 
	{
		//LOS CODIGOS DE LAS LETRAS SE REFIERE A SU EQUIVALENTE EN ASCII
		
		Scanner scanner = new Scanner(System.in);
		String texto; //VARIABLE DONDE SE GUARDA EL TEXTO INGRESADO
		
		while(true)
		{
			texto = scanner.nextLine(); //INGRESAR TEXTO
			texto = DesplazarTexto(texto); //DESPLAZAR EL TEXTO
			if(texto.equals("pFIN")) break; //SI EL TEXTO ES "FIN", CORTAR WHILE
			System.out.println(ContarVocales(texto)); //MOSTRAR CUANTAS VOCALES NO ACENTUADAS TIENE
		}
	}
	
	static public int ContarVocales(String textoDesplazado)
	{
		int cantidadVocales = 0; //CONTADOR PARA LA CANTIDAD DE VOCALES NO ACENTUADAS
		
		for(int i=0; i<textoDesplazado.length(); i++) //RECORRER EL TEXTO
		{
			switch (textoDesplazado.charAt(i)) //SI EL CARACTER ES ALGUNA VOCAL, AUMENTAR EL CONTADOR
			{
				case 'a': cantidadVocales++;
				break;
				case 'e': cantidadVocales++;
				break;
				case 'i': cantidadVocales++;
				break;
				case 'o': cantidadVocales++;
				break;
				case 'u': cantidadVocales++;
				break;
				case 'A': cantidadVocales++;
				break;
				case 'E': cantidadVocales++;
				break;
				case 'I': cantidadVocales++;
				break;
				case 'O': cantidadVocales++;
				break;
				case 'U': cantidadVocales++;
				break;
			}
		}
		
		return cantidadVocales; //DEVOLVER CANTIDAD DE VOCALES ENCONTRADAS
	}
	
	static public String DesplazarTexto(String textoOriginal)
	{
		String textoDesplazado = ""; //VARIABLE DONDE SE GUARDA TODO EL TEXTO PROCESADO
		int codigoPrimerCaracter; //CODIGO DEL PRIMER CARACTER DE LA CADENA, QUIEN DEFINIRA EL DESPLAZAMIENTO
		int desplazamiento; //VARIABLE QUE GUARDA EL DESPLAZAMIENTO
		int codigoLetraDesplazada; //VARIABLE DONDE SE GUARDA EL CODIGO DE LA LETRA DESPLAZADA
		int exceso;
		
		codigoPrimerCaracter = (int) textoOriginal.charAt(0); //GUARDAR EL CODIGO DEL PRIMER CARACTER
		desplazamiento = (int) (112 - codigoPrimerCaracter); //OBTENER EL DESPLAZAMIENTO RESTANDO EL CODIGO DEL PRIMER CARACTER AL CODIGO DE LA RETRA 'p' = 112
		
		for(int i=0; i<textoOriginal.length(); i++) //RECORRER CADA CARACTER DEL TEXTO INGRESADO
		{
			if(textoOriginal.charAt(i) >= 'a' && textoOriginal.charAt(i) <= 'z' ) //SI EL CARACTER ESTA ENTRE 'a' y 'z', ES UNA LETRA MINUSCULA
			{
				codigoLetraDesplazada = (int) (textoOriginal.charAt(i) + desplazamiento); //DESPLAZAR EL CARACTER
				
				if(codigoLetraDesplazada < 97 ) //SI EL CARACTER DESPLAZADO ES MENOR QUE LA LETRA 'a'
				{
					exceso = 97 - codigoLetraDesplazada; //CALCULAR EL EXCESO
					codigoLetraDesplazada = 123 - exceso; //POSICIONARSE UNA POSICION MAS DE LA 'z' Y RESTARLE EL EXCESO
				}
				
				if(codigoLetraDesplazada > 122 ) //SI EL CARACTER DESPLAZADO ES MAYOR QUE LA LETRA 'z'
				{
					exceso = codigoLetraDesplazada - 122; //CALCULAR EL EXCESO
					codigoLetraDesplazada = 96 + exceso; //POSICIONARSE UNA POSICION MENOS DE LA 'a' Y SUMARLE EL EXCESO
				}
				
				textoDesplazado += (char) codigoLetraDesplazada; //AGREGARLE AL TEXTO FINAL EL NUEVO CARACTER
			}
			else if(textoOriginal.charAt(i) >= 'A' && textoOriginal.charAt(i) <= 'Z' ) //SI NO ES MINUSCULA, PERO EL CARACTER ESTA ENTRE 'A' y 'Z', ES UNA LETRA MAYUSCULA
			{	
				codigoLetraDesplazada = (int) (textoOriginal.charAt(i) + desplazamiento); //DESPLAZAR EL CARACTER
				
				if(codigoLetraDesplazada < 65 ) //SI EL CARACTER DESPLAZADO ES MENOR QUE LA LETRA 'A'
				{
					exceso = 65 - codigoLetraDesplazada; //CALCULAR EL EXCESO
					codigoLetraDesplazada = 91 - exceso; //POSICIONARSE UNA POSICION MAS DE LA 'Z' Y RESTARLE EL EXCESO
				}
				
				if(codigoLetraDesplazada > 90 ) //SI EL CARACTER DESPLAZADO ES MAYOR QUE LA 'Z'
				{
					exceso = codigoLetraDesplazada - 90; //CALCULAR EL EXCESO
					codigoLetraDesplazada = 64 + exceso; //POSICIONARSE UNA POSICION MENOS QUE LA 'A' Y SUMARLE EL EXCESO
				}
				
				textoDesplazado += (char) codigoLetraDesplazada; //AGREGARLE AL TEXTO FINAL EL NUEVO CARACTER
			}
			else //SI EL CARACTER NO ESTA ENTRE LAS LETRAS MINUSCULAS, NI LAS MAYUSCULAS...
			{
				textoDesplazado += textoOriginal.charAt(i); //SIMPLEMENTE AGREGAR EL CARACTER ORIGINAL, SIN DESPLAZAMIENTO.
			}
		}
		
		return textoDesplazado; //DEVOLVER EL TEXTO DESPLAZADO
	}
}

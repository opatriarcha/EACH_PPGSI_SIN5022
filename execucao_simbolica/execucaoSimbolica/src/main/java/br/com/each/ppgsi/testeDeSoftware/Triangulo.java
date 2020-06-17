/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.each.ppgsi.testeDeSoftware;

public class Triangulo {
	//(LA (LB LC +) <)
	public static String classificaTriangulo(int LA, int LB, int LC) throws RuntimeException{
		String resposta="";
		if (LA<=0 || LB <=0 || LC <0)
			throw new RuntimeException("lado invalido");
		
		if ( (LA >= LB + LC) || (LB >= LA + LC) || (LC > LA + LB))
			resposta = "NAO FORMA TRIANGULO";
		else 
		{
			if (LA==LB && LB==LC)
				resposta = "EQUILATERO";
			else
			{
				if (LA==LB || LB==LC || LA==LC)
					resposta = "ISOSCELES";
				else
					resposta = "ESCALENO";
			}
		}
		return resposta;
	}

}

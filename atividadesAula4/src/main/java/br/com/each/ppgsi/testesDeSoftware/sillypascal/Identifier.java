package br.com.each.ppgsi.testesDeSoftware.sillypascal;

public class Identifier {

    public static boolean validaIdentificador(String identificador){

        //1
        if (identificador==null)
            //2
            return false;

        //2a                              2b
        if (identificador.length()==0 || identificador.length()>6)
            return false;//3

        //4
        Character primeiroChar = identificador.charAt(0);
        if (Character.isDigit(primeiroChar))
            //5
            return false;

        //6
        for (Character c: identificador.toCharArray()){
            //6a                                //6b
            if (!(Character.isAlphabetic(c) || Character.isDigit(c)))
                //7
                return false;
        }

        //8
        return true;
    }

}

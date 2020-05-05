package sillypascal;

public class Identifier {

    public static boolean validaIdentificador(String identificador){
        if (identificador==null)
            return false;
        if (identificador.length()==0 || identificador.length()>6)
            return false;
        Character primeiroChar = identificador.charAt(0);
        if (Character.isDigit(primeiroChar))
            return false;
        for (Character c: identificador.toCharArray()){
            if (!(Character.isAlphabetic(c) || Character.isDigit(c)))
                return false;
        }
        return true;
    }
}

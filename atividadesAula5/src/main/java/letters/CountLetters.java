package letters;

public class CountLetters {

    public int countWords(String str) {
        int words = 0;
        String word = "";
        char last = ' ';
        if (str==null) return -1;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i))){
                if (last == 's' || last == 'r') {
                    if (word.length() > 1)
                        words++;
                }
                word = "";
            }
            else{
                last = str.charAt(i);
                word = word+last;
            }
        }

        if (last == 'r' || last == 's')
            if (word.length() > 1)
                words++;
        return words;
    }

    public static void main (String args[]){
        int x = new CountLetters().countWords("As letras r e s são muito usadas atualmente");
        System.out.println(x);

    }

}

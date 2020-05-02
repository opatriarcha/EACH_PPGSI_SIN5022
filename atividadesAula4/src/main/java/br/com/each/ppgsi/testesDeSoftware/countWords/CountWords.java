package br.com.each.ppgsi.testesDeSoftware.countWords;

public class CountWords {

    public int countWords(String str) {
        int words = 0; //1
        String word = ""; //1
        char last = ' '; //1
        if (str==null)                                        //1
            return -1;                                        //1.1

        for (int i = 0; i < str.length(); i++) {              //2

            if (!Character.isLetter(str.charAt(i))){          //3
                if (last == 's' || last == 'r') {             //4.1 - 4.2
                    if (word.length() > 1)                    //5
                        words++;
                }
                word = "";                                    //6
            }
            else{
                last = str.charAt(i);                         //7
                word = word+last;                             //8
            }
        }
        if (last == 'r' || last == 's')                      //9.1 e 9.2
            if (word.length() > 1)                           //10
                words++;                                     //11
        return words;                                        //12
    }
}



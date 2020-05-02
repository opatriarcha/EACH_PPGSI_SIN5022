package br.com.each.ppgsi.testesDeSoftware.countWords;

public class CountWords {

    public int countWords(String str) {
        int words = 0; //1
        String word = ""; //1
        char last = ' '; //1
        if (str==null)                                        //1
            return -1;                                        //2

        for (int i = 0; i < str.length(); i++) {              //3

            if (!Character.isLetter(str.charAt(i))){          //4
                if (last == 's' || last == 'r') {             //5 - 6
                    if (word.length() > 1)                    //7
                        words++;                              //8
                }
                word = "";                                    //9
            }
            else{
                last = str.charAt(i);                         //10
                word = word+last;                             //11
            }
        }
        if (last == 'r' || last == 's')                      //12 e 13
            if (word.length() > 1)                           //14
                words++;                                     //15
        return words;                                        //16
    }                                                        //VX
}

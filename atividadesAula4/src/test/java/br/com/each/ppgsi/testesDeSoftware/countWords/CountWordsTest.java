package br.com.each.ppgsi.testesDeSoftware.countWords;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountWordsTest {

    private CountWords subject = new CountWords();

    //ID1 and ID7
    @Test //Spec nao trata nulo
    public void nullInputMustreturn0(){
        assertEquals(-1, subject.countWords(null));
    }

    //ID2 and ID6
    @Test
    public void emptyInputMustreturn0(){
        assertEquals(0, subject.countWords(""));
    }

    //ID3
    @Test
    public void stringWithoutRandSMustReturn0(){
        assertEquals(0, subject.countWords("ad"));
    }

    //ID4
    @Test
    public void oneCharacterWordMustReturn0(){
        assertEquals(0, subject.countWords("a"));
    }

    //ID5
    @Test
    public void asWordWordMustReturn1(){
        assertEquals(1, subject.countWords("as"));
    }

    //ID8
    @Test
    public void singleSWordMustReturn0(){
        assertEquals(0, subject.countWords("s"));
    }

    //ID9
    @Test
    public void thisPhraseMustReturn3(){
        assertEquals(3, subject.countWords("Testes Fortes error"));
    }

    //ID10
    @Test
    public void rAndSWithSpaceMustReturn0(){
        assertEquals(0, subject.countWords("r s"));
    }
    //ID11
    @Test
    public void emptyTwoSpacesInputMustreturn0(){
        assertEquals(0, subject.countWords("  "));
    }

}



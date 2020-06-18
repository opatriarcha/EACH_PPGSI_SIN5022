package br.com.each.ppgsi.testeDeSoftware.commons;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;


 public class CasterHelper<T> {

    private final Class<T> clazz;

    public CasterHelper(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T cast(String subject) {
        T t = null;
        /*
        if(subject == null || subject.isEmpty() ){
        return null;
        }
         */
        if (clazz.equals(String.class)) {
            if (subject == null || subject.isEmpty()) {
                return (T) "";
            } else {
                return (T) subject;
            }
        }

//        if (clazz.equals(Date.class)) {
//            if (subject != null) {
//                t = convertToDate(subject);
//                return t;
//            } else {
//                //return (T) new Date();
//                return null;
//            }
//
//        }

        if (clazz.equals(Character.class)) {
            if (subject != null && !subject.isEmpty()) {
                try {
                    //System.out.println(subject);
                    t = (T) (Character.valueOf(subject.charAt(0)));
                    //t = (T)  subject;
                } catch (Exception ex) {
                    System.out.println("Character : "+subject);
                    return (T) new Character(' ');
                }
                return t;
            } else {
                return (T) new Character(' ');
            }

        }

        if (clazz.equals(Long.class)) {
            if (subject == null || subject.toString().trim().equals("")) {
                //t = (T) new Long(0);
                return null;
            } else {
                return (T) Long.valueOf(subject);
            }
        }
        
        if (clazz.equals(Double.class)) {
            if (subject == null || subject.toString().trim().equals("")) {
                return null;
            } else {
                return (T) Double.valueOf(subject);
            }
        }

        if (clazz.equals(Integer.class)) {
            if (subject == null || subject.toString().trim().equals("")) {
                t = (T) new Integer(0);
                return t;
            } else {
                return (T) Integer.valueOf(subject);
            }
        }

        if (clazz.equals(BigDecimal.class)) {
            if (subject == null || subject.trim().equals("") || subject.trim().equals(" ")) {
                return (T) BigDecimal.ZERO;
            }
            
            String subjectContent = subject.trim();
            subjectContent = subject.replace(',', '.');
            return (T) new BigDecimal(subjectContent);
        }
        
        if (clazz.equals(BigInteger.class)) {
            if (subject == null || subject.equals("") || subject.trim().equals("")) {
                return (T) BigInteger.ZERO;
            }
            //String subjectContent = subject.replace(',', '.');
            return (T) new BigInteger(subject);
        }

        if (clazz.equals(Short.class)) {
            if (subject != null && !subject.isEmpty()) {
                try {
                    return (T) Short.valueOf(subject);
                } catch (Exception ex) {
                    //System.out.println("Short : "+subject);
                    return null;
                }
            } else {
                return (T) new Short("0");
            }
        }

        try {
            Constructor constructors[] = clazz.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                if (constructor.getParameterTypes().length == 1 && constructor.getParameterTypes()[0].equals(String.class)) {
                    t = (T) constructor.newInstance(subject);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return t;
    }
    
    public static String removeSpecialCharacters(String text) {

        return text.replaceAll("[ãâàáä]", "a").replaceAll("[êèéë]", "e").replaceAll("[îìíï]", "i").replaceAll("[õôòóö]", "o").replaceAll("[ûúùü]", "u").replaceAll("[ÃÂÀÁÄ]", "A").replaceAll("[ÊÈÉË]", "E").replaceAll("[ÎÌÍÏ]", "I").replaceAll("[ÕÔÒÓÖ]", "O").replaceAll("[ÛÙÚÜ]", "U").replace('ç', 'c').replace('Ç', 'C').replace('ñ', 'n').replace('Ñ', 'N').replaceAll("!", "").replaceAll("\\[\\´\\`\\?!\\@\\#\\$\\%\\¨\\*", "").replaceAll("\\(\\)\\=\\{\\}\\[\\]\\~\\^\\]", "").replaceAll("[\\.\\;\\-\\_\\+\\'\\ª\\º\\:\\;\\/]", "");
    }
}
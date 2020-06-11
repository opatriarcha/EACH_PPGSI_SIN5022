/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.each.ppgsi.testeDeSoftware.resolucaoBasica.exemplo;

/**
 *
 * @author orlando
 */
public class ClassForSimpleTest {

    public int testMe(int x, int y) {
        if (y != 0) {

            int N = x / y;
            if (N > 10) {
                return N;
            } else {
                N = 2 * x * y;
                return N;
            }
        } else {
            return x;
        }
    }
}

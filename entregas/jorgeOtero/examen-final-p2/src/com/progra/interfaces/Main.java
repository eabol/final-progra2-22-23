package com.progra.interfaces;

public class Main {
    public static void main(String[] args) throws Exception {
        /*
         * Acá debe de probar la implementación del ejercicio dos, tal
         * como se detalla en el enunciado.
         */
        System.out.println("==================================");
        System.out.println("===   Ejercicio 2: Interfaces  ===");
        System.out.println("==================================");


        Calc calc = new Calc();
        System.out.println(calc.divisorSum(1000));
        calc.describeDivisorSum(1000);



    }
}

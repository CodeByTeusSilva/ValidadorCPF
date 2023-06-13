package ValidadorCPF_v1;

import java.math.*;
import java.util.*;

public class ValidadorCPF {

    public String cpf;
    
    public ValidadorCPF ( String cpf ) {
        if ( cpf == null ) {
            System.out.println("O CPF informado é nulo.");
        }
        if ( cpf.length() != 11 ) {
            System.out.println("O CPF informado deve conter 11 digitos.");
        }
        if (!cpf.matches("\\d+")) {
            System.out.println("O CPF deve conter apenas números.");
        }
        if ( digitosIguais(cpf) == true ) {
            System.out.println("O CPF é inválido. Pois todos os digitos são iguais.");
        }

        this.cpf = cpf;
    }


    public static boolean digitosIguais ( String cpf ) {
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                return false; // Retorna false se os digitos forem diferentes
            }
        }
        return true; // Retorna True se todos os digitos forem iguais
    }

    public int[] separarEmArray(String cpf) {
        if ( cpf == null ) {
            throw new IllegalStateException("O CPF não foi inicializado corretamente.");
        }
        String[] arrayString = cpf.split("");
        int[] arrayInt = new int[arrayString.length];
        for (int i = 0; i < arrayString.length; i++) {
            arrayInt[i] = Integer.parseInt(arrayString[i]);
        }
        return arrayInt;
    }

    public boolean verificarPrimeiroDigito () {
        int[] array = separarEmArray(cpf);
        int resultado = 0;
        for (int i=0; i<9; i++) {
            resultado += (10 - i) * array[i];
        }
        resultado = 11 - (resultado % 11);
        if ( resultado >= 10 ) {
            resultado = 0;
        }
        if (resultado == array[9]) {
            return true;
        }
        return false;
    } 

    public boolean verificarSegundoDigito () {
        int[] array = separarEmArray(cpf);
        int resultado = 0;
        for (int i=0; i<10; i++) {
            resultado += (11 - i) * array[i];
        }
        resultado = 11 - (resultado % 11);
        if ( resultado >= 10 ) {
            resultado = 0;
        }
        if (resultado == array[10]) {
            return true;
        }
        return false;
    } 

    public boolean validacaoFinal() {
        boolean valido = false;
        if (verificarPrimeiroDigito()) {
            if (verificarSegundoDigito()) {
                valido = true;
            }
        }
        return valido;
    }
}

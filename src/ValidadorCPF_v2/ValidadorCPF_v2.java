package ValidadorCPF_v2;

import java.util.*;

public class ValidadorCPF_v2 {

    private final String cpf;

    public ValidadorCPF_v2(String cpf) {
        if ( cpf == null || cpf.isEmpty()) throw new IllegalArgumentException("CPF não informado.");
        cpf = cpf.replaceAll("\\p{Punct}", "");
        if (!cpf.matches("\\d+")) throw new RuntimeException("O CPF deve conter apenas números.");
        if ( cpf.length() != 11 ) throw new IllegalArgumentException("CPF incorreto.");
        if ( verificarTodosNumerosIguais(cpf) ) throw new RuntimeException("CPF inválido");

        this.cpf = cpf;
    }

    boolean verificarTodosNumerosIguais(String cpf) {
        return cpf.chars().allMatch(c -> c == cpf.charAt(0));
    }

    public List<Integer> transformarInt(String cpf) {
        List<String> arrayList = new ArrayList<>(List.of(cpf.split("")));
        return new ArrayList<>(arrayList.stream()
                .map(Integer::parseInt)
                .toList());
    }

    public boolean verificarDigitoUm() {
        List<Integer> arrayIntegerList = new ArrayList<>(transformarInt(cpf));
        Integer digitoUm = 0;
        for (int i = 0; i < 9; i++) {
            digitoUm += (10 - i) * arrayIntegerList.get(i);
        }
        digitoUm = 11 - (digitoUm % 11);
        if (digitoUm >= 10) digitoUm = 0;
        return digitoUm.equals(arrayIntegerList.get(9));
    }

    public boolean verificarDigitoDois() {
        List<Integer> arrayIntegerList = new ArrayList<>(transformarInt(cpf));
        Integer digitoDois = 0;
        for (int i = 0; i < 10; i++) {
            digitoDois += (11 - i) * arrayIntegerList.get(i);
        }
        digitoDois = 11 - (digitoDois % 11);
        if (digitoDois >= 10) digitoDois = 0;
        return digitoDois.equals(arrayIntegerList.get(10));
    }

    public boolean validacaoFinal() {
        boolean cpfValido = false;
        if ( verificarDigitoUm() ) {
            if ( verificarDigitoDois() ) return !cpfValido;
        }
        return cpfValido;
    }
}
package com.danieldev.mvc.util;

public class CPFValidator {

    public static boolean isValid(String cpf) {
        if (cpf == null) return false;
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        return calcularDigito(cpf, 9) == (cpf.charAt(9) - '0') &&
                calcularDigito(cpf, 10) == (cpf.charAt(10) - '0');
    }

    private static int calcularDigito(String cpf, int posicao) {
        int soma = 0;
        int peso = posicao + 1;
        for (int i = 0; i < posicao; i++) {
            soma += (cpf.charAt(i) - '0') * peso--;
        }

        int resultado = 11 - (soma % 11);
        return (resultado > 9) ? 0 : resultado;
    }
}
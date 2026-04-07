package com.danieldev.mvc.util;

public class MaskUtil {

    public static String cpf(String cpf) {
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})",
                "$1.$2.$3-$4");
    }

    public static String cep(String cep) {
        return cep.replaceAll("(\\d{5})(\\d{3})",
                "$1-$2");
    }

    public static String telefone(String numero) {
        if (numero.length() == 11) {
            return numero.replaceAll("(\\d{2})(\\d{5})(\\d{4})",
                    "($1) $2-$3");
        }
        return numero.replaceAll("(\\d{2})(\\d{4})(\\d{4})",
                "($1) $2-$3");
    }

    public static String remover(String valor) {
        return valor.replaceAll("\\D", "");
    }
}
package com.br.compol.getnfe.core.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtils {
    /**
     * Formata um valor BigDecimal como uma string de moeda no formato Real Brasileiro (R$).
     * Se o valor de entrada for nulo, retorna "R$ 0,00".
     *
     * @param value o valor BigDecimal a ser formatado como moeda
     * @return uma string representando a moeda formatada no locale "pt-BR"
     */
    public static String formatMoney(BigDecimal value){
        if (value == null) {
            return "R$ 0,00";
        }
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        return nf.format(value);

    }

    public static String formatCNPJ(String cnpj) {
    if (cnpj == null || cnpj.length() != 14) {
        return cnpj; // retorna como está se for nulo ou tamanho inválido
    }
    return cnpj.substring(0, 2) + "." +
           cnpj.substring(2, 5) + "." +
           cnpj.substring(5, 8) + "/" +
           cnpj.substring(8, 12) + "-" +
           cnpj.substring(12, 14);
}
public static String formatNumeroDaNota(String numero) {
    if (numero == null) {
        return null;
    }
    // Remove qualquer caractere não numérico
    String digits = numero.replaceAll("\\D", "");
    // Preenche com zeros à esquerda para garantir 9 dígitos
    digits = String.format("%09d", Long.parseLong(digits));
    return "Nº. " + digits.substring(0, 3) + "." + digits.substring(3, 6) + "." + digits.substring(6, 9);
}
}

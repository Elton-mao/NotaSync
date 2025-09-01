package com.br.compol.getnfe.core.utils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe utilitária para manipulação de operações com data e hora.
 */
public class DataUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private static final DateTimeFormatter OFFSET_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public static LocalDateTime parseDataEmissao(String data) {
        return OffsetDateTime.parse(data, OFFSET_FORMATTER).toLocalDateTime();
    }

    public static String formatarDataEmissao(LocalDateTime data) {
        return data.format(FORMATTER);
    }

    public static String formataDataHoraBr(LocalDateTime data) {
        DateTimeFormatter formatterBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatterBr);
    }
}

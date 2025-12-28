package com.br.compol.getnfe.core.utils;

public class XmlUtils {
/**
 * Extrai a tag <nfeProc> e todo o seu conteúdo a partir da string XML informada.
 *
 * @param xml a string XML da qual a tag <nfeProc> e seu conteúdo serão extraídos
 * @return uma substring contendo a tag <nfeProc> e todo o seu conteúdo, ou {@code null} caso a tag não seja encontrada
 */

public static String extrairTagNfeProc(String xml) {
    int inicio = xml.indexOf("<nfeProc");
    int fim = xml.indexOf("</nfeProc>");
    if (inicio == -1 || fim == -1) {
        return null;
    }
    fim += "</nfeProc>".length();
    return xml.substring(inicio, fim);
}

}




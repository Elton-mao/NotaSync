package com.br.compol.getnfe.core.utils;

public class XmlUtils {

    /**
 * Extracts the <nfeProc> tag and its content from the given XML string.
 *
 * @param xml the XML string from which the <nfeProc> tag and its content will be extracted
 * @return a substring containing the <nfeProc> tag and its content, or {@code null} if the tag is not found
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




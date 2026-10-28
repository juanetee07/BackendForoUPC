package com.upc.demo.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public final class HtmlSanitizer {

    private static final Safelist LISTA_PERMITIDA = Safelist.basic()
            .addTags("h2", "h3")
            .addAttributes("a", "target")
            .addEnforcedAttribute("a", "rel", "noopener noreferrer")
            .addProtocols("a", "href", "http", "https");

    private HtmlSanitizer() {
    }

    public static String sanitizar(String html) {
        if (html == null) {
            return "";
        }

        return Jsoup.clean(html, LISTA_PERMITIDA);
    }
}

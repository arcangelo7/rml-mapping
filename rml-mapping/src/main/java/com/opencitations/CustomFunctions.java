package com.opencitations;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomFunctions {
    private static final Logger logger = LoggerFactory.getLogger(CustomFunctions.class);
    private static final Map<String, String> labelToUriMap = new HashMap<>();
    static {
        labelToUriMap.put("book", "http://purl.org/spar/fabio/Book");
        labelToUriMap.put("book chapter", "http://purl.org/spar/fabio/BookChapter");
        labelToUriMap.put("journal article", "http://purl.org/spar/fabio/JournalArticle");
    }

    public static String precise_match(String s, String p, String r) {    
        if (p.length() < 2 || !p.startsWith("/") || !p.endsWith("/")) {
            logger.error("Invalid regex pattern: '{}'. A regex in GREL must start and end with '/'.", p);
            throw new InvalidParameterException("A regex in GREL must start and end with '/'");
        }
    
        // Rimuove i delimitatori '/' dall'espressione regolare
        String pattern = p.substring(1, p.length() - 1);
    
        Matcher m = Pattern.compile(pattern).matcher(s);
        
        if (m.find()) {
            // Costruisce la nuova stringa con solo la parte desiderata e la sostituzione applicata
            String result = r + m.group(1);
            return result;
        } else {
            logger.error("No match found for pattern '{}' in string '{}'", pattern, s);
            return s; // Restituisce la stringa originale se non ci sono corrispondenze
        }
    }

    public static String dateWithDatatype(String dateString) {
        String xsdDate = "http://www.w3.org/2001/XMLSchema#date";
        String xsdGYearMonth = "http://www.w3.org/2001/XMLSchema#gYearMonth";
        String xsdGYear = "http://www.w3.org/2001/XMLSchema#gYear";
    
        // Tenta il parsing per il formato completo di data
        try {
            LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
            return "\"" + dateString + "\"^^<" + xsdDate + ">";
        } catch (DateTimeParseException e) {
            // Tenta il parsing per il formato anno-mese
            try {
                LocalDate.parse(dateString + "-01", DateTimeFormatter.ISO_LOCAL_DATE);
                return "\"" + dateString + "\"^^<" + xsdGYearMonth + ">";
            } catch (DateTimeParseException e2) {
                // Tenta il parsing per il formato solo anno
                try {
                    LocalDate.parse(dateString + "-01-01", DateTimeFormatter.ISO_LOCAL_DATE);
                    return "\"" + dateString + "\"^^<" + xsdGYear + ">";
                } catch (DateTimeParseException e3) {
                    logger.error("Invalid date format: '{}'", dateString);
                    return dateString; // Restituisce la stringa originale se il formato non è valido
                }
            }
        }
    }

    public static String labelToURI(String label) {
        String uri = labelToUriMap.get(label.toLowerCase());
        if (uri != null) {
            return uri;
        } else {
            logger.error("No URI found for label: '{}'", label);
            return ""; // Restituisce una stringa vuota o un valore predefinito se l'etichetta non è mappata
        }
    }
}
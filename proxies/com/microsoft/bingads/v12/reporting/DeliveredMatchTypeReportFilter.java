package com.microsoft.bingads.v12.reporting;

/**
 * Enum class for DeliveredMatchTypeReportFilter.
 */
public enum DeliveredMatchTypeReportFilter {

    EXACT("Exact"),
    PHRASE("Phrase"),
    BROAD("Broad");
        
    private final String value;

    DeliveredMatchTypeReportFilter(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DeliveredMatchTypeReportFilter fromValue(String v) {
        for (DeliveredMatchTypeReportFilter c : DeliveredMatchTypeReportFilter.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
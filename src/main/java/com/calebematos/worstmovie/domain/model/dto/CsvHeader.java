package com.calebematos.worstmovie.domain.model.dto;

public enum CsvHeader {


    YEAR("year"),
    TITLE("title"),
    STUDIOS("studios"),
    PRODUCERS("producers"),
    WINNER("winner");

    final String headerName;

    CsvHeader(String headerName) { this.headerName = headerName; };

    public String getHeaderName() {
        return this.headerName;
    }
}

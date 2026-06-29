package es.daw.extra_api_peliculas.enums;

import lombok.Getter;

@Getter
public enum GenreType {
    SCI_FI("SCI_FI", "Ciencia Ficción"),
    DRAMA("DRAMA", "Drama"),
    ACTION("ACTION", "Acción"),
    WAR("WAR", "Guerra"),;

    private final String code;
    private final String fullName;

    GenreType(String code, String fullName) {
        this.code = code;
        this.fullName = fullName;
    }

}

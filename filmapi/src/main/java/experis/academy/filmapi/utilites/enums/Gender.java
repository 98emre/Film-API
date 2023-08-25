package experis.academy.filmapi.utilites.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE, FEMALE, OTHER;

    @JsonValue
    public String toValue() {
        return this.name();
    }

    @JsonCreator
    public static Gender forValue(String value) {
        return Gender.valueOf(value.toUpperCase());
    }
}

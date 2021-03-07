package pl.pzu.transaction.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.Validate;

@EqualsAndHashCode
@ToString
public class Firstname {

    private final String value;

    private Firstname(String value) {
        Validate.notBlank(value);
        Validate.matchesPattern(value, "[A-Z][a-z]*");
        this.value = value;
    }

    public static Firstname of(String value) {
        return new Firstname(value);
    }

    public String getAsString() {
        return value;
    }
}

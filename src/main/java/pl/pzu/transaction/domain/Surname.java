package pl.pzu.transaction.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.Validate;

@EqualsAndHashCode
@ToString
public class Surname {

    private final String value;

    private Surname(String value) {
        Validate.notBlank(value);
        Validate.matchesPattern(value, "[A-Z][a-z]*");
        this.value = value;
    }

    public static Surname of(String value) {
        return new Surname(value);
    }

    public String getAsString() {
        return value;
    }

}

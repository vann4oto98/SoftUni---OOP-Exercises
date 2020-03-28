package domain.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Money {
    private final BigDecimal value;
    private final String currency;

    public Money(BigDecimal value, String currency){
        this.value = value;
        this.currency = currency;
        this.validate();
    }

    public BigDecimal getValue() {
        return value;
    }

    private void validate(){
        List<String> invalidFields = new ArrayList<>();

        if (this.value == null || this.value.compareTo(BigDecimal.ZERO) <= 0){
            invalidFields.add("value must be non-null non-negative!");
        }

        if (this.currency == null || this.currency.length() != 3){
            invalidFields.add("currency must be non-null string of size 3!");
        }

        if (!invalidFields.isEmpty()) {
            throw new IllegalArgumentException("Invalid fields " + invalidFields);
        }
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return this.value + " " + this.currency;
    }
}

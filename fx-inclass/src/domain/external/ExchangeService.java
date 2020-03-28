package domain.external;

import domain.entities.Money;

public interface ExchangeService {
    Money convert(Money from, String toCurrency);


}

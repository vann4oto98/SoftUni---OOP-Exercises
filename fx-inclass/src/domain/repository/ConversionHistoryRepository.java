package domain.repository;

import domain.entities.Money;

import java.util.List;

public interface ConversionHistoryRepository {

    List<String> getLast(int n);
    void save(Money from, Money converted);
}

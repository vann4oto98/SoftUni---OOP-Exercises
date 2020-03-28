package domain.entities.commands;

import domain.entities.Money;
import domain.external.ExchangeService;
import domain.io.Logger;
import domain.repository.ConversionHistoryRepository;

public class HistorySavingConvertCommand extends ConvertCommand {
    private ConversionHistoryRepository repo;

    public HistorySavingConvertCommand(ExchangeService exchangeService, Logger logger, ConversionHistoryRepository repo) {
        super(exchangeService, logger);
        this.repo = repo;
    }

    @Override
    public void execute(Input input) {
       Money converted = exchangeAndLog(input);
        repo.save(input.getFrom(), converted);
    }
}

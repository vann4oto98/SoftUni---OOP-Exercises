package console;

import domain.external.ExchangeService;
import domain.io.Logger;
import domain.repository.ConversionHistoryRepository;
import external.CurrConvExchangeService;
import repository.InMemoryConversionHistoryRepository;

import java.util.Scanner;

public class ConsoleRunner {



    public void run(){
        Scanner sc = new Scanner (System.in);
        ExchangeService exchangeService = new CurrConvExchangeService();
        Logger logger = new ConsoleLogger();
        ConversionHistoryRepository conversionHistoryRepo = new InMemoryConversionHistoryRepository();

        ConsoleCommandExecutor executor = new ConsoleCommandExecutor(conversionHistoryRepo, logger, exchangeService);

        while (true) {
            String line  = sc.nextLine();
            String[] split = line.split("\\s+");
            executor.execute(split);
        }
    }

}

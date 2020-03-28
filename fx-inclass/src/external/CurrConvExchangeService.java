package external;

import domain.entities.Money;
import domain.external.ExchangeService;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrConvExchangeService implements ExchangeService {

    private static final String API_KEY = System.getenv("API_KEY");

    @Override
    public Money convert(Money from, String toCurrency) {
        BigDecimal rate = fetchExchangeRateFor(from.getCurrency(), toCurrency);
        BigDecimal exchangedValue = from.getValue().multiply(rate);
        return new Money(exchangedValue, toCurrency);
    }

    private BigDecimal fetchExchangeRateFor(String currencyFrom, String currencyTo) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(exchangeRateUrlForCurrencies(currencyFrom, currencyTo))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return extractExchangeRate(response.body());
        } catch (Exception e) {
           throw new RuntimeException("something went wrong during HTTP request to external API");
        }
    }

    private BigDecimal extractExchangeRate(String response) {
        int columnIdx = response.lastIndexOf(':');
        int closingBracketIdx = response.lastIndexOf('}');
        String exchangeRate = response.substring(columnIdx + 1, closingBracketIdx);
        return new BigDecimal(exchangeRate);
    }


    private URI exchangeRateUrlForCurrencies(String from, String to){
        String url = String.format("https://free.currconv.com/api/v7/convert?q=%s_%s&compact=ultra&apiKey=%s", from, to, API_KEY);
        try {

            return new URI(url);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Failed to build URI");
        }
    }

}

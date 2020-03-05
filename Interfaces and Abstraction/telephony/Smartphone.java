import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Smartphone implements Callable, Browsable{
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile("([0-9])");
        for (String url: this.urls) {
            Matcher m = p.matcher(url);
            if (m.find()){
                sb.append("Invalid URL!").append(System.lineSeparator());
            } else {
                sb.append("Browsing: ").append(url).append("!").append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile("([^0-9])");
        for (String number: this.numbers) {
            Matcher m = p.matcher(number);
            if (m.find()){
                sb.append("Invalid number!").append(System.lineSeparator());
            } else {
            sb.append("Calling... ").append(number).append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }
}

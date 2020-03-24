import jdk.jshell.spi.ExecutionControl;

import java.util.*;
import java.util.stream.Collectors;

public class Instock implements ProductStock {

    private Map<String, Product> products;

    public Instock(){
        this.products = new LinkedHashMap<>();
    }

    @Override
    public int getCount() {
       return this.products.size();
    }

    @Override
    public boolean contains(Product product) {
        return this.products.containsKey(product.getLabel());
    }

    @Override
    public void add(Product product) {
        this.products.put(product.getLabel(), product);
    }

    @Override
    public void changeQuantity(String product, int quantity) {
        if (this.products.containsKey(product)) {
            this.products.get(product).setQuantity(quantity);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Product find(int index) {
        if (index >= 0 && index < this.products.size()){
           int i = 0;
            for (Product value: this.products.values()) {
                if (i++ == index){
                    return value;
                }
            }
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public Product findByLabel(String label) {
        if (this.products.containsKey(label)) {
            return this.products.get(label);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        if (count > this.products.size() || count <= 0){
            return new ArrayList<>();
        }
      return this.products.values()
                .stream()
                .limit(count)
                .sorted(Comparator.comparing(Product::getLabel))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
       return this.products.values().stream().filter(p -> p.getQuantity() == quantity)
               .collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {
       return this.products.values().iterator();
    }
}

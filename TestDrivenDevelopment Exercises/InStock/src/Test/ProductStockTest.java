import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

public class ProductStockTest {

    ProductStock productStock;

    @Before
    public void init(){
        this.productStock = new Instock();
    }


    @Test
    public void addOneProductShouldIncreaseSize(){
        Product product = this.createProducts(1).get(0);
        productStock.add(product);
        Assert.assertEquals(1, productStock.getCount());
    }

    @Test
    public void addTenProductShouldIncreaseSize(){
        List<Product> products = this.createProducts(10);
        for (Product product:products) {
            productStock.add(product);
        }
        Assert.assertEquals(products.size(), productStock.getCount());
    }

    @Test
    public void addShouldContainCorrectReference(){
        Product product = this.createProducts(1).get(0);
        productStock.add(product);
        Assert.assertTrue(productStock.contains(product));
    }

    @Test
    public void containsShouldReturnTrueIfProductExist(){
        List<Product> products = this.createProducts(10);
        products.forEach(p -> this.productStock.add(p));

        Product lastProduct = products.get(products.size()-1);
        Assert.assertTrue(this.productStock.contains(lastProduct));
    }

    @Test
    public void containsShouldReturnFalseIfProductDoesntExist(){
        Product product = this.createProducts(1).get(0);

        Assert.assertFalse(this.productStock.contains(product));
    }

    @Test
    public void countShouldBeZeroIfEmptyCollection(){
        Assert.assertEquals(0, this.productStock.getCount());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void findShouldThrowExceptionIfIndexNotValid(){
        this.productStock.find(this.productStock.getCount());
    }

    @Test
    public void findShouldReturnCorrectProductByIndex(){
        Product product = this.createProducts(1).get(0);
        this.productStock.add(product);
        Assert.assertEquals(product, this.productStock.find(0));
    }

    @Test
    public void changeQuantityAddingShouldWorkCorrectly(){
        Product product = this.createProducts(1).get(0);
        this.productStock.add(product);
        this.productStock.changeQuantity(product.getLabel(), 0);
        Assert.assertEquals(0, product.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeQuantityShouldThrowExceptionIfDoesntExist(){
        Product product = this.createProducts(1).get(0);
        this.productStock.changeQuantity(product.getLabel(), 0);
        Assert.assertEquals(0, product.getQuantity());
    }

    @Test
    public void findByLabelShouldReturnCorrectReference(){
        Product product = this.createProducts(1).get(0);
        this.productStock.add(product);
        Assert.assertEquals(product, this.productStock.findByLabel(product.getLabel()));
    }

    @Test(expected =IllegalArgumentException.class)
    public void findByLabelShouldThrowExceptionIfDoesntExist(){
        Product product = this.createProducts(1).get(0);
        Assert.assertEquals(product, this.productStock.findByLabel(product.getLabel()));
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnCorrectOrder(){
        List<Product> products = this.createProducts(3);
        products.get(0).setLabel("A");
        products.get(1).setLabel("B");
        products.get(2).setLabel("C");
        products.forEach(p -> this.productStock.add(p));
        Iterable<Product> firstByAlphabeticalOrder = this.productStock.findFirstByAlphabeticalOrder(3);
        Product[] expected = products.toArray(new Product[0]);
        Product[] actual = StreamSupport.stream(firstByAlphabeticalOrder.spliterator(), false)
                .toArray(Product[]::new);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void findFirstByAlphabeticalOrderShouldReturnEmptyCollectionOnEmptyStock(){

        Iterable<Product> firstByAlphabeticalOrder = this.productStock.findFirstByAlphabeticalOrder(3);
        Product[] expected = new Product[0];
        Product[] actual = StreamSupport.stream(firstByAlphabeticalOrder.spliterator(), false)
                .toArray(Product[]::new);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnEmptyCollectionWithIllegalArgument(){
        List<Product> products = this.createProducts(3);
        products.get(0).setLabel("A");
        products.get(1).setLabel("B");
        products.get(2).setLabel("C");
        products.forEach(p -> this.productStock.add(p));
        Iterable<Product> firstByAlphabeticalOrder = this.productStock.findFirstByAlphabeticalOrder(6);
        Product[] expected = new Product[0];
        Product[] actual = StreamSupport.stream(firstByAlphabeticalOrder.spliterator(), false)
                .toArray(Product[]::new);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void getIterableShouldReturnAllProducts(){
        List<Product> products = this.createProducts(20);
        products.forEach(p -> this.productStock.add(p));

        Product[] expected = products.toArray(new Product[0]);
        final Iterator<Product> productIterator = this.productStock.iterator();
        Iterable<Product> productIterable = () -> productIterator;
        Product[] actual = StreamSupport.stream(productIterable.spliterator(), false).toArray(Product[]::new);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void findAllByQuantityShouldReturnCorrectValues(){
        List<Product> products = this.createProducts(3);
        products.get(0).setQuantity(10);
        products.get(1).setQuantity(10);
        products.get(2).setQuantity(10);
        products.forEach(p -> this.productStock.add(p));
        Iterable<Product> firstByAlphabeticalOrder = this.productStock.findAllByQuantity(10);
        Product[] expected = products.toArray(new Product[0]);
        Product[] actual = StreamSupport.stream(firstByAlphabeticalOrder.spliterator(), false)
                .toArray(Product[]::new);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void findAllByQuantityShouldReturnEmptyCollection(){
        List<Product> products = this.createProducts(3);
        products.get(0).setQuantity(10);
        products.get(1).setQuantity(10);
        products.get(2).setQuantity(10);
        products.forEach(p -> this.productStock.add(p));
        Iterable<Product> firstByAlphabeticalOrder = this.productStock.findAllByQuantity(20);
        Product[] expected = new Product[0];
        Product[] actual = StreamSupport.stream(firstByAlphabeticalOrder.spliterator(), false)
                .toArray(Product[]::new);
        Assert.assertArrayEquals(expected, actual);
    }

    private List<Product> createProducts(int count){
        List<Product> products = new ArrayList<>();

        while (count > 0){
            Product product = new Product(UUID.randomUUID().toString(), 12.5 * count, 10* count);
            products.add(product);
            count--;
        }
        return products;
    }

}
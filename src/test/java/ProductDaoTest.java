import com.pedrojvdv.jdbc.dao.ProductDao;
import com.pedrojvdv.jdbc.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductDaoTest {

    @Mock
    private ProductDao productDao;

    @Test
    void findAllProductsTest() throws SQLException {

        List<Product> productTest = List.of(new Product("Cama", new BigDecimal("1249.99"), 25, true));

        when(productDao.findAllProducts()).thenReturn(productTest);

        List<Product> result = productDao.findAllProducts();

        assertEquals(1, result.size());
        assertEquals("Cama", result.get(0).getName());
    }

    @Test
    void findByIdTest()throws SQLException{

        Product productTest = new Product("botas de chuva", new BigDecimal("50.00"), 21, true);

        when(productDao.findById(1L)).thenReturn(productTest);

        Product result = productDao.findById(1L);

        assertEquals("botas de chuva", result.getName());

    }

    @Test
    void findByCategory() throws SQLException {

        String category = "Carros";

        List<Product> productTest = List.of(
                new Product("celta 1.0 1999", new BigDecimal("22399.99"), 1, true),
                new Product("Corsa 1.4 2007", new BigDecimal("32450.00"), 1, true));

        when(productDao.findProductByCategory("Carros")).thenReturn(productTest);

        List<Product> resultList = productDao.findProductByCategory("Carros");

        assertEquals(2, resultList.size());
        assertEquals("celta 1.0 1999", resultList.get(0).getName());
    }

    @Test
    void findWithDiscount() throws SQLException {

        List<Product> productTest = List.of(
                new Product("camiseta seleção brasileira masculina - 2026", new BigDecimal("500.00"), 10, true),
                new Product("camiseta seleção brasileira feminina - 2026", new BigDecimal("500.00"), 10, true));

        when(productDao.findProductWithDiscount()).thenReturn(productTest);

        List<Product> resultList = productDao.findProductWithDiscount();

        assertEquals(2, resultList.size());
        assertEquals("camiseta seleção brasileira masculina - 2026", resultList.get(0).getName());
        assertEquals(new BigDecimal("500.00"), resultList.get(0).getPrice());
        assertEquals("camiseta seleção brasileira feminina - 2026", resultList.get(1).getName());
        assertEquals(new BigDecimal("500.00"), resultList.get(1).getPrice());
    }

    @Test
    void findByDate() throws SQLException {

        LocalDate date = LocalDate.of(2026, 7, 26);

        List<Product> productTest = List.of(new Product("iphone 17 pro max", new BigDecimal("7600"), 5, true));

        when(productDao.findProductsByDate(date)).thenReturn(productTest);

        List<Product> resultList = productDao.findProductsByDate(date);

        assertEquals(1, resultList.size());
        assertEquals("iphone 17 pro max", resultList.get(0).getName());
        assertEquals(new BigDecimal("7600"), resultList.get(0).getPrice());
        assertEquals(5, resultList.get(0).getStock());
    }

    @Test
    void findSoldout() throws SQLException {

        List<Product> productTest = List.of(new Product("televisao samsung oled 64' ", new BigDecimal("4299.99"), 0, false));

        when(productDao.findSoldOutProducts()).thenReturn(productTest);

        List<Product> resultList = productDao.findSoldOutProducts();

        assertEquals(1, resultList.size());
        assertEquals("televisao samsung oled 64' ", resultList.get(0).getName());
    }

    @Test
    void findByName() throws SQLException {

        String name = "carrinho hotwheels limitado";

        List<Product> productTest = List.of(new Product("carrinho hotwheels limitado", new BigDecimal("400"), 2, true));

        when(productDao.findProductByName(name)).thenReturn(productTest);

        List<Product> resultList = productDao.findProductByName(name);

        assertEquals(1, resultList.size());
        assertEquals(new BigDecimal("400"), resultList.get(0).getPrice());
        assertEquals("carrinho hotwheels limitado", resultList.get(0).getName());
        assertEquals(2, resultList.get(0).getStock());
        assertEquals(true, resultList.get(0).getAvailable());
    }

    @Test
    void findMaxPrice() throws SQLException {

        BigDecimal maxPrice = BigDecimal.valueOf(9700);

        List<Product> productTest = List.of(
                new Product("Placa de video RTX 5080", new BigDecimal("9700"), 1, true),
                new Product("Placa de video RTX 2060", new BigDecimal("1900"), 1, true),
                new Product("Placa de video RTX 3060", new BigDecimal("2300"), 1, true));

        when(productDao.findByMaxPrice(maxPrice)).thenReturn(productTest);

        List<Product> resultList = productDao.findByMaxPrice(maxPrice);


        assertEquals(3, resultList.size());
        assertEquals("Placa de video RTX 5080", resultList.get(0).getName());
        assertEquals(new BigDecimal("9700"), resultList.get(0).getPrice());
        assertEquals(1, resultList.get(0).getStock());
        assertEquals(true, resultList.get(0).getAvailable());

        assertEquals("Placa de video RTX 2060", resultList.get(1).getName());
        assertEquals(new BigDecimal("1900"), resultList.get(1).getPrice());
        assertEquals(1, resultList.get(1).getStock());
        assertEquals(true, resultList.get(1).getAvailable());

        assertEquals("Placa de video RTX 3060", resultList.get(2).getName());
        assertEquals(new BigDecimal("2300"), resultList.get(2).getPrice());
        assertEquals(1, resultList.get(2).getStock());
        assertEquals(true, resultList.get(2).getAvailable());
    }

    @Test
    void findByRange()throws SQLException {

        BigDecimal maxPrice = BigDecimal.valueOf(5600);
        BigDecimal minPrice = BigDecimal.valueOf(1400);

        List<Product> productTest = List.of(new Product("Mesa de tênis de mesa", new BigDecimal("2500"), 3, true),
                new Product("Tênir Air Jordan", new BigDecimal("3500"), 2, true),
                new Product("Skateboard", new BigDecimal("1400"), 3, true),
                new Product("Notebook", new BigDecimal("5600"), 1, true),
                new Product("Celular", new BigDecimal("4300"), 2, true));

        when(productDao.findPriceByRange(minPrice, maxPrice)).thenReturn(productTest);

        List<Product> resultList = productDao.findPriceByRange(minPrice, maxPrice);

        assertEquals(5, resultList.size());
        assertEquals("Mesa de tênis de mesa", resultList.get(0).getName());
        assertEquals(new BigDecimal("2500"), resultList.get(0).getPrice());
        assertEquals(3, resultList.get(0).getStock());
        assertEquals(true, resultList.get(0).getAvailable());

        assertEquals("Tênir Air Jordan", resultList.get(1).getName());
        assertEquals(new BigDecimal("3500"), resultList.get(1).getPrice());
        assertEquals(2, resultList.get(1).getStock());
        assertEquals(true, resultList.get(1).getAvailable());

        assertEquals("Skateboard", resultList.get(2).getName());
        assertEquals(new BigDecimal("1400"), resultList.get(2).getPrice());
        assertEquals(3, resultList.get(2).getStock());
        assertEquals(true, resultList.get(2).getAvailable());

        assertEquals("Notebook", resultList.get(3).getName());
        assertEquals(new BigDecimal("5600"), resultList.get(3).getPrice());
        assertEquals(1, resultList.get(3).getStock());
        assertEquals(true, resultList.get(3).getAvailable());

        assertEquals("Celular", resultList.get(4).getName());
        assertEquals(new BigDecimal("4300"), resultList.get(4).getPrice());
        assertEquals(2, resultList.get(4).getStock());
        assertEquals(true, resultList.get(4).getAvailable());

    }
}

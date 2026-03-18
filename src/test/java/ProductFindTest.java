import com.pedrojvdv.jdbc.dao.ProductDao;
import com.pedrojvdv.jdbc.model.Category;
import com.pedrojvdv.jdbc.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductFindTest {

    @Mock(strictness = Mock.Strictness.LENIENT)
    private Connection connection;

    private ProductDao productDao;


    @BeforeEach
    void beforeSetUp() {
        connection = mock(Connection.class);
        productDao = new ProductDao(connection);
    }

    @Test
    void findAllProductsTest() throws SQLException {
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(connection.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getLong("id")).thenReturn(1L);
        when(rs.getString("name")).thenReturn("teste");
        when(rs.getBigDecimal("price")).thenReturn(new BigDecimal("1249.99"));
        when(rs.getInt("stock")).thenReturn(25);
        when(rs.getBoolean("available")).thenReturn(true);
        when(rs.getString("description")).thenReturn("teste");
        when(rs.getString("category")).thenReturn(Category.TESTE.name());
        when(rs.getTimestamp("created_at")).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(rs.getBigDecimal("discount_percentage")).thenReturn(new BigDecimal("0.10"));
        when(rs.getBigDecimal("final_price")).thenReturn(new BigDecimal("1124.99"));

        List<Product> result = productDao.findAllProducts();

        verify(stmt).executeQuery();

        assertEquals(1, result.size());
        assertEquals("teste", result.get(0).getName());
        assertEquals("teste", result.get(0).getDescription());
        assertEquals(Category.TESTE, result.get(0).getCategory());
    }

    @Test
    void findByIdTest() throws SQLException {
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(connection.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getLong("id")).thenReturn(1L);
        when(rs.getString("name")).thenReturn("teste");
        when(rs.getBigDecimal("price")).thenReturn(new BigDecimal("1249.99"));
        when(rs.getInt("stock")).thenReturn(25);
        when(rs.getBoolean("available")).thenReturn(true);
        when(rs.getString("description")).thenReturn("teste");
        when(rs.getString("category")).thenReturn(Category.TESTE.name());
        when(rs.getTimestamp("created_at")).thenReturn(Timestamp.valueOf(LocalDateTime.now()));
        when(rs.getBigDecimal("discount_percentage")).thenReturn(new BigDecimal("0.10"));
        when(rs.getBigDecimal("final_price")).thenReturn(new BigDecimal("1124.99"));

        Product result = productDao.findById(1L);

        assertEquals("teste", result.getName());
    }

    @Test
    void findByCategory() throws SQLException {
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class, withSettings().strictness(Strictness.LENIENT));

        when(connection.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);

        when(rs.findColumn("name")).thenReturn(1);
        when(rs.findColumn("price")).thenReturn(2);
        when(rs.findColumn("category")).thenReturn(3);
        when(rs.findColumn("description")).thenReturn(4);
        when(rs.findColumn("stock")).thenReturn(5);
        when(rs.findColumn("discount_percentage")).thenReturn(6);
        when(rs.findColumn("final_price")).thenReturn(7);

        when(rs.next()).thenReturn(true).thenReturn(false);

        when(rs.getString("name")).thenReturn("teste");
        when(rs.getBigDecimal("price")).thenReturn(new BigDecimal("1249.99"));
        when(rs.getInt("stock")).thenReturn(25);
        when(rs.getString("description")).thenReturn("teste");
        when(rs.getString("category")).thenReturn(Category.TESTE.name());
        when(rs.getBigDecimal("discount_percentage")).thenReturn(new BigDecimal("10.00"));
        when(rs.getBigDecimal("final_price")).thenReturn(new BigDecimal("1124.99"));

        when(rs.findColumn("created_at")).thenThrow(SQLClientInfoException.class);

        List<Product> resultList = productDao.findProductByCategory(Category.TESTE);

        assertEquals(1, resultList.size());
        assertEquals("teste", resultList.get(0).getName());
    }

    @Test
    void findWithDiscount() throws SQLException {
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class, withSettings().strictness(Strictness.LENIENT));

        when(connection.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);

        when(rs.findColumn("name")).thenReturn(1);
        when(rs.findColumn("price")).thenReturn(2);
        when(rs.findColumn("available")).thenReturn(3);
        when(rs.findColumn("discount_percentage")).thenReturn(4);
        when(rs.findColumn("price")).thenReturn(5);

        when(rs.next()).thenReturn(true).thenReturn(false);

        when(rs.getString("name")).thenReturn("teste");
        when(rs.getBigDecimal("price")).thenReturn(new BigDecimal("1249.99"));
        when(rs.getBoolean("available")).thenReturn(true);
        when(rs.getBigDecimal("discount_percentage")).thenReturn(new BigDecimal("10.00"));
        when(rs.getBigDecimal("final_price")).thenReturn(new BigDecimal("1124.99"));

        when(rs.findColumn("created_at")).thenThrow(SQLClientInfoException.class);
        when(rs.findColumn("category")).thenThrow(SQLClientInfoException.class);

        List<Product> resultList = productDao.findProductWithDiscount();

        assertEquals(1, resultList.size());
        assertEquals("teste", resultList.get(0).getName());
        assertEquals(new BigDecimal("1249.99"), resultList.get(0).getPrice());
        assertEquals(new BigDecimal("1124.99"), resultList.get(0).getFinalPrice());
    }

    @Test
    void findByDate() throws SQLException {

        LocalDate date = LocalDate.of(2026, 7, 26);
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class, withSettings().strictness(Strictness.LENIENT));

        when(connection.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);

        when(rs.findColumn("name")).thenReturn(1);
        when(rs.findColumn("price")).thenReturn(2);
        when(rs.findColumn("available")).thenReturn(3);
        when(rs.findColumn("stock")).thenReturn(4);
        when(rs.findColumn("discount_percentage")).thenReturn(5);
        when(rs.findColumn("final_price")).thenReturn(6);
        when(rs.findColumn("created_at")).thenReturn(7);

        when(rs.next()).thenReturn(true).thenReturn(false);

        when(rs.getString("name")).thenReturn("teste");
        when(rs.getBigDecimal("price")).thenReturn(new BigDecimal("1249.99"));
        when(rs.getBoolean("available")).thenReturn(true);
        when(rs.getInt("stock")).thenReturn(10);
        when(rs.getBigDecimal("discount_percentage")).thenReturn(new BigDecimal("10.00"));
        when(rs.getBigDecimal("final_price")).thenReturn(new BigDecimal("1124.99"));
        when(rs.getTimestamp("created_at")).thenReturn(Timestamp.valueOf(LocalDateTime.now()));

        when(rs.findColumn("category")).thenThrow(SQLClientInfoException.class);
        when(rs.findColumn("created_at")).thenThrow(SQLClientInfoException.class);

        List<Product> resultList = productDao.findProductsByDate(date);

        assertEquals(1, resultList.size());
        assertEquals("teste", resultList.get(0).getName());
        assertEquals(new BigDecimal("1249.99"), resultList.get(0).getPrice());
        assertEquals(10, resultList.get(0).getStock());
    }

    @Test
    void findSoldout() throws SQLException {

        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class, withSettings().strictness(Strictness.LENIENT));

        when(connection.prepareStatement(anyString())).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);

        when(rs.findColumn("name")).thenReturn(1);
        when(rs.findColumn("price")).thenReturn(2);
        when(rs.findColumn("stock")).thenReturn(3);
        when(rs.findColumn("discount_percentage")).thenReturn(4);
        when(rs.findColumn("final_price")).thenReturn(5);

        when(rs.next()).thenReturn(true).thenReturn(false);

        when(rs.getString("name")).thenReturn("teste");
        when(rs.getBigDecimal("price")).thenReturn(new BigDecimal("1249.99"));
        when(rs.getInt("stock")).thenReturn(10);
        when(rs.getBigDecimal("discount_percentage")).thenReturn(new BigDecimal("10.00"));
        when(rs.getBigDecimal("final_price")).thenReturn(new BigDecimal("1124.99"));

        when(rs.findColumn("category")).thenThrow(SQLClientInfoException.class);

        List<Product> resultList = productDao.findSoldOutProducts();

        assertEquals(1, resultList.size());
        assertEquals("teste", resultList.get(0).getName());
    }

    @Test
    void findByName() throws SQLException {

        String name = "carrinho hotwheels limitado";

        List<Product> productTest = List.of(new Product("carrinho hotwheels limitado", new BigDecimal("400"), 2, true, "teste", Category.TESTE));

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
                new Product("Placa de video RTX 5080", new BigDecimal("9700"), 1, true, "teste", Category.TESTE),
                new Product("Placa de video RTX 2060", new BigDecimal("1900"), 1, true, "teste", Category.TESTE),
                new Product("Placa de video RTX 3060", new BigDecimal("2300"), 1, true, "teste", Category.TESTE));

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
    void findByRange() throws SQLException {

        BigDecimal maxPrice = BigDecimal.valueOf(5600);
        BigDecimal minPrice = BigDecimal.valueOf(1400);

        List<Product> productTest = List.of(new Product("Mesa de tênis de mesa", new BigDecimal("2500"), 3, true, "teste", Category.TESTE),
                new Product("Tênis Air Jordan", new BigDecimal("3500"), 2, true, "teste", Category.TESTE),
                new Product("Skateboard", new BigDecimal("1400"), 3, true, "teste", Category.TESTE),
                new Product("Notebook", new BigDecimal("5600"), 1, true, "teste", Category.TESTE),
                new Product("Celular", new BigDecimal("4300"), 2, true, "teste", Category.TESTE));

        when(productDao.findPriceByRange(minPrice, maxPrice)).thenReturn(productTest);

        List<Product> resultList = productDao.findPriceByRange(minPrice, maxPrice);

        assertEquals(5, resultList.size());
        assertEquals("Mesa de tênis de mesa", resultList.get(0).getName());
        assertEquals(new BigDecimal("2500"), resultList.get(0).getPrice());
        assertEquals(3, resultList.get(0).getStock());
        assertEquals(true, resultList.get(0).getAvailable());

        assertEquals("Tênis Air Jordan", resultList.get(1).getName());
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

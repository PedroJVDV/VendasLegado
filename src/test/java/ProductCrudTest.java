
import com.pedrojvdv.jdbc.dao.ProductDao;
import com.pedrojvdv.jdbc.model.Category;
import com.pedrojvdv.jdbc.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductCrudTest {

    private Connection connection;
    private ProductDao productDao;

    @BeforeEach
    void beforeSetUp() {
        connection = mock(Connection.class);
        productDao = new ProductDao(connection);
    }

    @Test
    void create() throws SQLException {
        PreparedStatement stmtUpdate = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(stmtUpdate);

        Product productTest = new Product("teste", new BigDecimal("3600"), 1, true, "teste", Category.TESTE);
        productDao.createProduct(productTest);

        verify(stmtUpdate).setString(1, productTest.getName());
        verify(stmtUpdate).setBigDecimal(2, productTest.getPrice());
        verify(stmtUpdate).setString(3, productTest.getDescription());
        verify(stmtUpdate).setString(4, productTest.getCategory().name());
        verify(stmtUpdate).setInt(5, productTest.getStock());
        verify(stmtUpdate).setBoolean(6, productTest.getAvailable());
        verify(stmtUpdate).executeUpdate();
    }

    @Test
    void update() throws SQLException {

        PreparedStatement stmtUpdate = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(stmtUpdate);

        Product productTest = new Product("teste", new BigDecimal("6500"), 1, true, "teste", Category.TESTE);
        productTest.setId(1L);
        productDao.updateProducts(productTest);

        verify(stmtUpdate).setObject(1, productTest.getName());
        verify(stmtUpdate).setObject(2, productTest.getPrice());
        verify(stmtUpdate).setObject(3, productTest.getDescription());
        verify(stmtUpdate).setObject(4, productTest.getCategory());
        verify(stmtUpdate).setObject(5, productTest.getStock());
        verify(stmtUpdate).setObject(6, productTest.getAvailable());
        verify(stmtUpdate).executeUpdate();

    }

    @Test
    void delete() throws SQLException {

        PreparedStatement stmtUpdate = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(stmtUpdate);

        Product productTest = new Product("teste", new BigDecimal("1200"), 3, true, "teste", Category.TESTE);
        productTest.setId(1L);
        productDao.deleteProduct(productTest);
    }

    @Test
    void softDelete() throws SQLException {

        PreparedStatement stmtUpdate = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(stmtUpdate);

        Product productTest = new Product("teste", new BigDecimal("7200"), 2, true, "teste", Category.TESTE);
        productTest.setId(1L);
        productDao.softDeleteProduct(productTest);
    }
}

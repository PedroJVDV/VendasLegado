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

}

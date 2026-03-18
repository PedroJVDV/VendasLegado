import com.pedrojvdv.jdbc.dao.DiscountDao;
import com.pedrojvdv.jdbc.dao.ProductDao;
import com.pedrojvdv.jdbc.model.Category;
import com.pedrojvdv.jdbc.model.Discount;
import com.pedrojvdv.jdbc.model.DiscountType;
import com.pedrojvdv.jdbc.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DiscountCrudTest {

    private Connection connection;
    private DiscountDao discountDao;

    @BeforeEach
    void beforeSetUp() {
        connection = mock(Connection.class);
        discountDao = new DiscountDao(connection);
    }

    @Test
    void create() throws SQLException {
        PreparedStatement stmt = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(stmt);

        Discount discount = new Discount(1L, new BigDecimal("0.30"), DiscountType.FLASH_SALE, true, LocalDate.of(2026, 3, 31), LocalDate.of(2026, 4, 11));
        discountDao.createDiscount(discount);

        verify(stmt).setLong(1, discount.getProductId());
        verify(stmt).setBigDecimal(2, discount.getPercentage());
        verify(stmt).setObject(3, discount.getType());
        verify(stmt).setBoolean(4, discount.isActive());
        verify(stmt).setDate(5, Date.valueOf(discount.getStartDate()));
        verify(stmt).setDate(6, Date.valueOf(discount.getEndDate()));
        verify(stmt).executeUpdate();
    }

    @Test
    void Update() throws SQLException {
        PreparedStatement stmt = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(stmt);

        Discount discount = new Discount(2L, new BigDecimal("0.50"), DiscountType.FLASH_SALE, true, LocalDate.of(2026, 4, 20), LocalDate.of(2026, 5, 10));
        discount.setId(2L);
        discountDao.updateDiscount(discount);

        verify(stmt).setObject(1, discount.getProductId());
        verify(stmt).setBigDecimal(2, discount.getPercentage());
        verify(stmt).setObject(3, discount.getType());
        verify(stmt).setBoolean(4, discount.isActive());
        verify(stmt).setDate(5, Date.valueOf(discount.getStartDate()));
        verify(stmt).setDate(6, Date.valueOf(discount.getEndDate()));
        verify(stmt).setLong(7, discount.getId());
        stmt.executeUpdate();
    }

    @Test
    void updateEndDate() throws SQLException {
        PreparedStatement stmt = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(stmt);

        Discount discount = new Discount(2L, new BigDecimal("0.50"), DiscountType.FLASH_SALE, true, LocalDate.of(2026, 4, 20), LocalDate.of(2026, 5, 10));
        discount.setId(2L);
        discountDao.updateEndDate(discount);

        verify(stmt).setDate(1, Date.valueOf(discount.getEndDate()));
        verify(stmt).setLong(2, discount.getId());
        stmt.executeUpdate();
    }

    @Test
    void delete()throws SQLException{
        PreparedStatement stmt = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(stmt);

        Discount discount = new Discount(3L, new BigDecimal("0.45"), DiscountType.FLASH_SALE, true, LocalDate.of(2026, 5, 20), LocalDate.of(2026, 6, 10));
        discount.setId(3L);
        discountDao.deleteDiscount(discount);

        verify(stmt).setLong(1, discount.getId());
        verify(stmt).executeUpdate();
    }

    @Test
    void softDelete()throws SQLException{
        PreparedStatement stmt = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(stmt);

        Discount discount = new Discount(1L, new BigDecimal("0.45"), DiscountType.FLASH_SALE, true, LocalDate.of(2026, 5, 20), LocalDate.of(2026, 6, 10));
        discount.setId(2L);
        discountDao.softDeleteDiscount(discount);

        verify(stmt).setLong(1, discount.getId());
        verify(stmt).executeUpdate();
    }

    @Test
    void deactivateExpired()throws SQLException{
        PreparedStatement stmt = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(stmt);

        discountDao.deactivateExpiredDiscount();

        verify(stmt).executeUpdate();
    }
}

import com.pedrojvdv.jdbc.dao.DiscountDao;
import com.pedrojvdv.jdbc.dao.ProductDao;
import com.pedrojvdv.jdbc.model.Discount;
import com.pedrojvdv.jdbc.model.DiscountType;
import com.pedrojvdv.jdbc.queries.DiscountQueries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiscountFindTest {

    @Mock
    private Connection connection;

    private DiscountDao discountDao;

    @BeforeEach
    void beforeSetUp() {
        connection = mock(Connection.class);
        discountDao = new DiscountDao(connection);
    }

    @Test
    void findActive() throws SQLException {

        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(connection.prepareStatement(DiscountQueries.FIND_ALL_ACTIVE_DISCOUNTS)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);

        when(rs.findColumn("product_name")).thenReturn(1);
        when(rs.findColumn("product_price")).thenReturn(2);
        when(rs.findColumn("percentage")).thenReturn(3);
        when(rs.findColumn("type")).thenReturn(4);
        when(rs.findColumn("start_date")).thenReturn(5);
        when(rs.findColumn("end_date")).thenReturn(6);
        when(rs.findColumn("duration_hours")).thenReturn(7);
        when(rs.findColumn("active")).thenReturn(8);

        when(rs.next()).thenReturn(true).thenReturn(false);

        when(rs.getString("product_name")).thenReturn("teste");
        when(rs.getBigDecimal("product_price")).thenReturn(new BigDecimal("1249.99"));
        when(rs.getBigDecimal("percentage")).thenReturn(new BigDecimal("10.00"));
        when(rs.getString("type")).thenReturn(DiscountType.TEMPORARY.name());
        when(rs.getDate("start_date")).thenReturn(Date.valueOf(LocalDate.now()));
        when(rs.getDate("end_date")).thenReturn(Date.valueOf(LocalDate.of(2026, 5, 2)));
        when(rs.getInt("duration_hours")).thenReturn(2);
        when(rs.getBoolean("active")).thenReturn(true);

        when(rs.findColumn("id")).thenThrow(SQLClientInfoException.class);
        when(rs.findColumn("product_id")).thenThrow(SQLClientInfoException.class);

        List<Discount> result = discountDao.findActiveDiscounts();

        assertEquals(1, result.size());
        assertEquals(new BigDecimal("10.00"), result.get(0).getPercentage());
        assertEquals(DiscountType.TEMPORARY, result.get(0).getType());
        assertTrue(result.get(0).isActive());
        assertEquals(LocalDate.now(), result.get(0).getStartDate());
        assertEquals(LocalDate.of(2026, 5, 2), result.get(0).getEndDate());
    }

    @Test
    void findFlashSalesAdmin() throws SQLException {
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(connection.prepareStatement(DiscountQueries.FIND_FLASH_SALES_ADMIN)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);

        when(rs.findColumn("product_name")).thenReturn(1);
        when(rs.findColumn("product_price")).thenReturn(2);
        when(rs.findColumn("percentage")).thenReturn(3);
        when(rs.findColumn("type")).thenReturn(4);
        when(rs.findColumn("start_date")).thenReturn(5);
        when(rs.findColumn("end_date")).thenReturn(6);
        when(rs.findColumn("duration_hours")).thenReturn(7);
        when(rs.findColumn("active")).thenReturn(8);

        when(rs.next()).thenReturn(true).thenReturn(false);

        when(rs.getString("product_name")).thenReturn("teste");
        when(rs.getBigDecimal("product_price")).thenReturn(new BigDecimal("1249.99"));
        when(rs.getBigDecimal("percentage")).thenReturn(new BigDecimal("10.00"));
        when(rs.getString("type")).thenReturn(DiscountType.FLASH_SALE.name());
        when(rs.getDate("start_date")).thenReturn(Date.valueOf(LocalDate.now()));
        when(rs.getDate("end_date")).thenReturn(Date.valueOf(LocalDate.of(2026, 5, 2)));
        when(rs.getInt("duration_hours")).thenReturn(2);
        when(rs.getBoolean("active")).thenReturn(true);

        when(rs.findColumn("id")).thenThrow(SQLClientInfoException.class);
        when(rs.findColumn("product_id")).thenThrow(SQLClientInfoException.class);

        List<Discount> result = discountDao.findFlashSalesAdmin();

        assertEquals(1, result.size());
        assertEquals(new BigDecimal("10.00"), result.get(0).getPercentage());
        assertEquals(DiscountType.FLASH_SALE, result.get(0).getType());
        assertTrue(result.get(0).isActive());
        assertEquals(LocalDate.now(), result.get(0).getStartDate());
        assertEquals(LocalDate.of(2026, 5, 2), result.get(0).getEndDate());
    }

    @Test
    void findFlashSaleCustomer() throws SQLException {
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(connection.prepareStatement(DiscountQueries.FIND_FLASH_SALES_CUSTOMER)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);

        when(rs.findColumn("percentage")).thenReturn(1);
        when(rs.findColumn("duration_hours")).thenReturn(2);
        when(rs.findColumn("active")).thenReturn(3);
        when(rs.findColumn("type")).thenReturn(4);

        when(rs.next()).thenReturn(true).thenReturn(false);

        when(rs.getBigDecimal("percentage")).thenReturn(new BigDecimal("10.00"));
        when(rs.getInt("duration_hours")).thenReturn(3);
        when(rs.getBoolean("active")).thenReturn(true);
        when(rs.getString("type")).thenReturn(DiscountType.FLASH_SALE.name());

        when(rs.findColumn("start_date")).thenThrow(SQLClientInfoException.class);
        when(rs.findColumn("end_date")).thenThrow(SQLClientInfoException.class);
        when(rs.findColumn("product_name")).thenThrow(SQLClientInfoException.class);
        when(rs.findColumn("product_price")).thenThrow(SQLClientInfoException.class);
        when(rs.findColumn("id")).thenThrow(SQLClientInfoException.class);
        when(rs.findColumn("product_id")).thenThrow(SQLClientInfoException.class);

        List<Discount> result = discountDao.findFlashSaleCustomer();

        assertEquals(1, result.size());
        assertEquals(new BigDecimal("10.00"), result.get(0).getPercentage());
        assertTrue(result.get(0).isActive());
    }

    @Test
    void findActiveDiscountId()throws SQLException{
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(connection.prepareStatement(DiscountQueries.FIND_ACTIVE_DISCOUNTS_BY_ID)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);

        when(rs.findColumn("id")).thenReturn(1);
        when(rs.findColumn("product_id")).thenReturn(2);
        when(rs.findColumn("percentage")).thenReturn(3);
        when(rs.findColumn("type")).thenReturn(4);
        when(rs.findColumn("start_date")).thenReturn(5);
        when(rs.findColumn("end_date")).thenReturn(6);
        when(rs.findColumn("duration_hours")).thenReturn(7);
        when(rs.findColumn("active")).thenReturn(8);

        when(rs.next()).thenReturn(true).thenReturn(false);

        when(rs.getLong("id")).thenReturn(1L);
        when(rs.getLong("product_id")).thenReturn(2L);
        when(rs.getBigDecimal("percentage")).thenReturn(new BigDecimal("10.00"));
        when(rs.getString("type")).thenReturn(DiscountType.FLASH_SALE.name());
        when(rs.getDate("start_date")).thenReturn(Date.valueOf(LocalDate.now()));
        when(rs.getDate("end_date")).thenReturn(Date.valueOf(LocalDate.of(2026, 5, 2)));
        when(rs.getBoolean("active")).thenReturn(true);
        when(rs.getInt("duration_hours")).thenReturn(3);

        List<Discount> result = discountDao.findActiveDiscountById(rs.getLong(1));

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(0).getProductId());
    }

    @Test
    void findExpiredDiscount()throws SQLException{
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(connection.prepareStatement(DiscountQueries.FIND_EXPIRED_DISCOUNTS)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);

        when(rs.findColumn("id")).thenReturn(1);
        when(rs.findColumn("product_id")).thenReturn(2);
        when(rs.findColumn("percentage")).thenReturn(3);
        when(rs.findColumn("type")).thenReturn(4);
        when(rs.findColumn("start_date")).thenReturn(5);
        when(rs.findColumn("end_date")).thenReturn(6);
        when(rs.findColumn("duration_hours")).thenReturn(7);
        when(rs.findColumn("active")).thenReturn(8);

        when(rs.next()).thenReturn(true).thenReturn(false);

        when(rs.getLong("id")).thenReturn(1L);
        when(rs.getLong("product_id")).thenReturn(2L);
        when(rs.getBigDecimal("percentage")).thenReturn(new BigDecimal("10.00"));
        when(rs.getString("type")).thenReturn(DiscountType.FLASH_SALE.name());
        when(rs.getDate("start_date")).thenReturn(Date.valueOf(LocalDate.now()));
        when(rs.getDate("end_date")).thenReturn(Date.valueOf(LocalDate.of(2026, 5, 2)));
        when(rs.getBoolean("active")).thenReturn(true);
        when(rs.getInt("duration_hours")).thenReturn(3);

        List<Discount> result = discountDao.findExpiredDiscounts();

        assertEquals(1, result.size());
        assertEquals(LocalDate.of(2026, 5, 2), result.get(0).getEndDate());
    }
}

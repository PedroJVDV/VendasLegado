import com.pedrojvdv.jdbc.dao.DiscountDao;
import com.pedrojvdv.jdbc.model.Discount;
import com.pedrojvdv.jdbc.model.DiscountType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiscountFindTest {

    @Mock
    private DiscountDao discountDao;

    @Test
    void findActive()throws SQLException {

        List<Discount> discountTest = List.of(new Discount(2L, new BigDecimal("0.45"), DiscountType.TEMPORARY, true, LocalDate.of(2026, 4, 21), LocalDate.of(2026, 5, 11)));

        when(discountDao.findActiveDiscounts()).thenReturn(discountTest);

        List<Discount> result = discountDao.findActiveDiscounts();

        assertEquals(1, result.size());
        assertEquals((2L), result.get(0).getProductId());
        assertEquals(new BigDecimal("0.45"), result.get(0).getPercentage());
        assertEquals(DiscountType.TEMPORARY, result.get(0).getType());
        assertTrue(result.get(0).isActive());
        assertEquals(LocalDate.of(2026, 4, 21), result.get(0).getStartDate());
        assertEquals(LocalDate.of(2026, 5, 11), result.get(0).getEndDate());
    }


}

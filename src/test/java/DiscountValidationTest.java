import com.pedrojvdv.jdbc.exception.DiscountValidationException;
import com.pedrojvdv.jdbc.model.Discount;
import com.pedrojvdv.jdbc.model.DiscountType;
import com.pedrojvdv.jdbc.validation.DiscountValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DiscountValidationTest {

    DiscountValidation discountValidation = new DiscountValidation();
    LocalDate today = LocalDate.now();

    @Test
    void flashSaleExceptions() {

        Discount discount = new Discount();
        discount.setType(DiscountType.FLASH_SALE);

        discount.setDurationHours(3);
        discount.setPercentage(BigDecimal.valueOf(19));

        assertThrows(DiscountValidationException.class, () -> discountValidation.validate(discount, 25));
    }

    @Test
    void scheduledExceptions(){

        Discount discount = new Discount();
        discount.setType(DiscountType.SCHEDULED);

        discount.setStartDate(today.plusDays(10));
        discount.setEndDate(today.plusDays(25));

        discount.setPercentage(BigDecimal.valueOf(32.3));

        assertThrows(DiscountValidationException.class, () -> discountValidation.validate(discount, 70));
    }

    @Test
    void temporaryExceptions(){

        Discount discount = new Discount();

        discount.setType(DiscountType.TEMPORARY);

        discount.setStartDate(today.plusDays(1));
        discount.setEndDate(today.plusDays(32));

        discount.setPercentage(BigDecimal.valueOf(12.24));

        assertThrows(DiscountValidationException.class, () -> discountValidation.validate(discount, 59));
    }


    @Test
    void flashSalePass(){
        Discount discount = new Discount();
        discount.setType(DiscountType.FLASH_SALE);

        discount.setDurationHours(4);
        discount.setPercentage(BigDecimal.valueOf(70));

        assertDoesNotThrow(() -> discountValidation.validate(discount,43));
    }

    @Test
    void scheduledPass(){
        Discount discount = new Discount();
        discount.setType(DiscountType.SCHEDULED);

        discount.setStartDate(today.plusDays(30));
        discount.setEndDate(today.plusDays(45));

        discount.setPercentage(BigDecimal.valueOf(40));

        assertDoesNotThrow(() -> discountValidation.validate(discount, 31));
    }

    @Test
    void temporaryPass(){
        Discount discount = new Discount();
        discount.setType(DiscountType.TEMPORARY);

        discount.setStartDate(today.plusDays(1));
        discount.setEndDate(today.plusDays(30));

        discount.setPercentage(BigDecimal.valueOf(16));

        assertDoesNotThrow(() -> discountValidation.validate(discount,60));

    }

}

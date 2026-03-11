
import com.pedrojvdv.jdbc.exception.ProductValidationException;
import com.pedrojvdv.jdbc.model.Category;
import com.pedrojvdv.jdbc.model.DiscountType;
import com.pedrojvdv.jdbc.model.Product;
import com.pedrojvdv.jdbc.validation.ProductValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


public class ProductValidationTest {

    ProductValidation productValidation = new ProductValidation();

    @Test
    void productException(){

        Product product = new Product();

        product.setName("Teste");
        product.setStock(0);
        product.setDiscountType(DiscountType.TEMPORARY);
        product.setCategory(Category.ALIMENTOS);
        product.setDescription("MAÇÃ VERDE DO HIMALAYA");

        assertThrows(ProductValidationException.class, ()-> productValidation.validate(product));
    }

    @Test
    void productPass(){
        Product product = new Product();

        product.setName("Teste 2");
        product.setStock(250);
        product.setDiscountType(DiscountType.PERMANENT);
        product.setCategory(Category.ALIMENTOS);
        product.setDescription("OVO");

        assertDoesNotThrow(() -> productValidation.validate(product));
    }
}

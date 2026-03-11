package com.pedrojvdv.jdbc.validation;

import com.pedrojvdv.jdbc.exception.ProductValidationException;
import com.pedrojvdv.jdbc.model.Category;
import com.pedrojvdv.jdbc.model.DiscountType;
import com.pedrojvdv.jdbc.model.Product;

public class ProductValidation {

    public void validate(Product product){

        validateName(product.getName());
        validateCategory(product.getCategory());
        validateDescription(product.getDescription());
        validateStock(product);
        validateAvailable(product);
    }
    private void validateAvailable(Product product) {
       if (!product.getAvailable()){
           throw new ProductValidationException("Produto não está disponível!");
       }
    }
    private void validateStock(Product product) {
        if (product.getStock() > 300){
            throw new ProductValidationException("Stock não pode ultrapassar 300 unidades!");
        }
        if (product.getStock() > 100 && product.getDiscountType() != DiscountType.PERMANENT){
            throw new ProductValidationException("Stock acima de 100 unidades só é permitido em DESCONTOS PERMANENTES!");
        }
    }
    private void validateDescription(String description) {
        if (description == null || description.isBlank()){
            throw new ProductValidationException("Descrição do produto não pode ser nula/vazia!");
        }
    }
    private void validateCategory(Category category) {
        if (category == null){
            throw new ProductValidationException("Categoria não pode ser nula/vazia!");
        }
    }
    private void validateName(String name) {
        if(name == null || name.isBlank()){
            throw new ProductValidationException("Nome não pode ser nulo/vazio");
        }
    }

}

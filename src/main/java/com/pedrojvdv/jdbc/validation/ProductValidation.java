package com.pedrojvdv.jdbc.validation;

import com.pedrojvdv.jdbc.exception.ProductValidationException;
import com.pedrojvdv.jdbc.model.Category;
import com.pedrojvdv.jdbc.model.DiscountType;
import com.pedrojvdv.jdbc.model.Product;

import java.math.BigDecimal;

public class ProductValidation {

    public void validate(Product product){

        validateName(product.getName());
        validateCategory(product.getCategory());
        validateDescription(product.getDescription());
        validateStock(product);
        validateAvailable(product);
    }

    public void validateId(Long id){
        if (id == null || id <= 0){
            throw new ProductValidationException("ID inválido!");
        }
    }

    public void validateAvailable(Product product) {
       if (!product.getAvailable()){
           throw new ProductValidationException("Produto não está disponível!");
       }
    }
    public void validateStock(Product product) {
        if (product.getStock() > 300){
            throw new ProductValidationException("Stock não pode ultrapassar 300 unidades!");
        }
        if (product.getStock() > 100 && product.getDiscountType() != DiscountType.PERMANENT){
            throw new ProductValidationException("Stock acima de 100 unidades só é permitido em DESCONTOS PERMANENTES!");
        }
    }
    public void validateDescription(String description) {
        if (description == null || description.isBlank()){
            throw new ProductValidationException("Descrição do produto não pode ser nula/vazia!");
        }
    }
    public void validateCategory(Category category) {
        if (category == null){
            throw new ProductValidationException("Categoria não pode ser nula/vazia!");
        }
    }
    public void validateName(String name) {
        if(name == null || name.isBlank()){
            throw new ProductValidationException("Nome não pode ser nulo/vazio");
        }
    }

    public void validateByRange(BigDecimal minPrice, BigDecimal maxPrice){
        if (minPrice == null || maxPrice == null){
            throw new ProductValidationException("O valor minimo e máximo não pode ser nulo!");
        }
    }
    public void validateMaxPrice(BigDecimal maxPrice){
        if (maxPrice == null ){
            throw new ProductValidationException("Preço máximo não pode ser nulo");
        }
    }
}

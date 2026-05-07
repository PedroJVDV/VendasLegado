package com.pedrojvdv.jdbc.service;

import com.pedrojvdv.jdbc.dao.ProductDao;
import com.pedrojvdv.jdbc.exception.ProductValidationException;
import com.pedrojvdv.jdbc.model.Product;
import com.pedrojvdv.jdbc.validation.ProductValidation;

import java.math.BigDecimal;
import java.sql.SQLException;

public class ProductService {

    private final ProductDao productDao;
    private final ProductValidation productValidation;

    public ProductService(){
        this.productDao = new ProductDao();
        this.productValidation = new ProductValidation();
    }

    public void createProduct(Product product) throws ProductValidationException, SQLException{
        productValidation.validate(product);
        productDao.createProduct(product);
    }

    public void updateProduct(Product product) throws ProductValidationException, SQLException{
        productValidation.validate(product);
        productDao.updateProducts(product);
    }

    public void deleteProduct(Long id)throws ProductValidationException, SQLException{
        productValidation.validateId(id);
        productDao.deleteProduct(id);
    }

    public void softDeleteProduct(Long id)throws ProductValidationException, SQLException{
        productValidation.validateId(id);
        productDao.softDeleteProduct(id);
    }

    public void getById(Long id)throws ProductValidationException, SQLException{
        productValidation.validateId(id);
        productDao.findById(id);
    }

    public void getProductByName(String name)throws ProductValidationException, SQLException{
        productValidation.validateName(name);
        productDao.findProductByName(name);
    }

    public void getByMaxPrice(BigDecimal maxPrice)throws ProductValidationException, SQLException{
        productValidation.validateMaxPrice(maxPrice);
        productDao.findByMaxPrice(maxPrice);
    }

    public void getPriceByRange(BigDecimal minPrice, BigDecimal maxPrice)throws ProductValidationException, SQLException{
        productValidation.validateByRange(minPrice, maxPrice);
        productDao.findPriceByRange(minPrice, maxPrice);
    }

}

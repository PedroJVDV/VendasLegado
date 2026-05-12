package com.pedrojvdv.jdbc.service;

import com.pedrojvdv.jdbc.dao.ProductDao;
import com.pedrojvdv.jdbc.exception.ProductValidationException;
import com.pedrojvdv.jdbc.model.Product;
import com.pedrojvdv.jdbc.validation.ProductValidation;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private final ProductDao productDao;
    private final ProductValidation productValidation;

    public ProductService() {
        this.productDao = new ProductDao();
        this.productValidation = new ProductValidation();
    }

    public void createProduct(Product product) throws ProductValidationException, SQLException {
        productValidation.validate(product);
        productDao.createProduct(product);
    }

    public void updateProduct(Product product) throws ProductValidationException, SQLException {
        productValidation.validate(product);
        productDao.updateProducts(product);
    }

    public void deleteProduct(Long id) throws ProductValidationException, SQLException {
        productValidation.validateId(id);
        productDao.deleteProduct(id);
    }

    public void softDeleteProduct(Long id) throws ProductValidationException, SQLException {
        productValidation.validateId(id);
        productDao.softDeleteProduct(id);
    }

    public void getById(Long id) throws ProductValidationException, SQLException {
        productValidation.validateId(id);
        productDao.findById(id);
    }

    public List<Product> getProductByName(String name) throws ProductValidationException, SQLException {
        productValidation.validateName(name);
        List<Product> products = productDao.findProductByName(name);
        return products != null ? products : new ArrayList<>();
    }

    public List<Product> getByMaxPrice(BigDecimal maxPrice) throws ProductValidationException, SQLException {
        productValidation.validateMaxPrice(maxPrice);
        List<Product> products = productDao.findByMaxPrice(maxPrice);
        return products !=null ? products : new ArrayList<>();
    }

    public List<Product> getPriceByRange(BigDecimal minPrice, BigDecimal maxPrice) throws ProductValidationException, SQLException {
        productValidation.validateByRange(minPrice, maxPrice);
        List<Product> products = productDao.findPriceByRange(minPrice, maxPrice);
        return products != null ? products : new ArrayList<>();
    }

}

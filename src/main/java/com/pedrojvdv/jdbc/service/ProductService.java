package com.pedrojvdv.jdbc.service;

import com.pedrojvdv.jdbc.dao.ProductDao;
import com.pedrojvdv.jdbc.exception.ProductValidationException;
import com.pedrojvdv.jdbc.model.Product;
import com.pedrojvdv.jdbc.validation.ProductValidation;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

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

    public void  updateProduct(Product product) throws ProductValidationException, SQLException{
        productValidation.validate(product);
        productDao.updateProducts(product);
    }

    public void deleteProduct(Product product)throws ProductValidationException, SQLException{
        productValidation.validate(product);
        productDao.deleteProduct(product);
    }

    public void softDeleteProduct(Product product)throws ProductValidationException, SQLException{
        productValidation.validate(product);
        productDao.softDeleteProduct(product);
    }

    public void getById(Long id)throws ProductValidationException, SQLException{
        productValidation.validateId(id);
        productDao.findById(id);
    }

    public List<Product> getAllProducts()throws ProductValidationException, SQLException{
        return productDao.findAllProducts();
    }

    public List<Product> getProductByName(String name)throws ProductValidationException, SQLException{
        productValidation.validateName(name);
        return productDao.findProductByName(name);
    }

    public List<Product> getByMaxPrice(BigDecimal maxPrice)throws ProductValidationException, SQLException{
        productValidation.validateMaxPrice(maxPrice);
        return productDao.findByMaxPrice(maxPrice);
    }

    public List<Product> getPriceByRange(BigDecimal minPrice, BigDecimal maxPrice)throws ProductValidationException, SQLException{
        productValidation.validateByRange(minPrice, maxPrice);
        return productDao.findPriceByRange(minPrice, maxPrice);
    }

}

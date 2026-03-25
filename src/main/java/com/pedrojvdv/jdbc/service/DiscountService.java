package com.pedrojvdv.jdbc.service;

import com.pedrojvdv.jdbc.dao.DiscountDao;
import com.pedrojvdv.jdbc.exception.DiscountValidationException;
import com.pedrojvdv.jdbc.model.Discount;
import com.pedrojvdv.jdbc.validation.DiscountTypeValidation;
import com.pedrojvdv.jdbc.validation.DiscountValidation;

import java.sql.SQLException;
import java.util.List;

public class DiscountService {

    private final DiscountDao discountDao;
    private final DiscountValidation discountValidation;

    public DiscountService(){
        this.discountDao = new DiscountDao();
        this.discountValidation = new DiscountValidation();
    }

    public void createDiscount(Discount discount, Integer stock)throws DiscountValidationException, SQLException {
        discountValidation.validate(discount, stock);
        discountDao.createDiscount(discount);
    }

    public void updateDiscount(Discount discount, Integer stock)throws DiscountValidationException,SQLException {
        discountValidation.validate(discount, stock);
        discountDao.updateDiscount(discount);
    }

    public void updateEndDate(Discount discount, Integer stock)throws DiscountValidationException, SQLException{
        discountValidation.validate(discount,stock);
        discountDao.updateEndDate(discount);
    }

    public void deleteDiscount(Long id)throws DiscountValidationException, SQLException{
        discountDao.deleteDiscount(id);
    }

    public void softDeleteDiscount(Long id)throws DiscountValidationException, SQLException{
        discountDao.softDeleteDiscount(id);
    }

    public void deactivateExpiredDiscount()throws DiscountValidationException, SQLException{
        discountDao.deactivateExpiredDiscount();
    }

    public List<Discount> findActiveDiscounts()throws DiscountValidationException, SQLException{
        return discountDao.findActiveDiscounts();
    }

    public List<Discount> findFlashSalesAdmin() throws DiscountValidationException, SQLException{
        return discountDao.findFlashSalesAdmin();
    }

    public List<Discount> findFlashSaleCustomer()throws DiscountValidationException, SQLException{
        return discountDao.findFlashSaleCustomer();
    }

    public List<Discount> findActiveDiscountById(Long id)throws DiscountValidationException, SQLException{
       discountValidation.validateId(id);
       return discountDao.findActiveDiscountById(id);
    }

    public List<Discount> findExpiredDiscounts() throws DiscountValidationException, SQLException{
        return discountDao.findExpiredDiscounts();
    }

    public List<Discount> findByType()throws DiscountValidationException, SQLException{
        return discountDao.findByType();
    }

}

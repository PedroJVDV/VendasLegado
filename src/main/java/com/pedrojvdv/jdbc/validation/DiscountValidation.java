package com.pedrojvdv.jdbc.validation;

import com.pedrojvdv.jdbc.exception.DiscountValidationException;
import com.pedrojvdv.jdbc.exception.ProductValidationException;
import com.pedrojvdv.jdbc.model.Discount;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DiscountValidation {


    public void validate(Discount discount, Integer stock) {

        switch (discount.getType()) {

            case FLASH_SALE -> validateFlashSale(discount, stock);
            case PERMANENT -> validatePermanent(discount);
            case SCHEDULED -> validateScheduled(discount);
            case TEMPORARY -> validateTemporary(discount);

            default -> throw new DiscountValidationException("Não corresponde a nenhum tipo de desconto!");
        }
    }

    private void validateFlashSale(Discount discount, Integer stock) {
        int duration = discount.getDurationHours();
        BigDecimal discountPercentage = discount.getPercentage();


        if (duration < 1 || duration > 6) {
            throw new DiscountValidationException("Ofertas relâmpagos precisam ter duração minima de 1 hora e máxima de 6 horas!");
        }

        if (discountPercentage.compareTo(BigDecimal.valueOf(20)) < 0 || discountPercentage.compareTo(BigDecimal.valueOf(70)) > 0) {
            throw new DiscountValidationException("Ofertas relâmpagos precisam ter desconto mínimo de 20% e máximo de 70%");
        }

        if (duration <= 2) {
            if (stock != 15) {
                throw new DiscountValidationException("Ofertas de 2 horas exigem estoque de 15 unidades!");
            }
            if (discountPercentage.compareTo(BigDecimal.valueOf(60)) != 0) {
                throw new DiscountValidationException("Desconto para oferta de 2 horas deve ser exatamente 60%");
            }
        }
        if (duration == 3){
            if (stock < 20 || stock > 30) {
                throw new ProductValidationException("O estoque para ofertas relâmpago de 3 horas devem ser entre 20 e 30 unidades!");
            }
        if (discountPercentage.compareTo(BigDecimal.valueOf(40)) < 0 || discountPercentage.compareTo(BigDecimal.valueOf(55)) > 0) {
            throw new DiscountValidationException("Ofertas com 3 horas de duração devem ter entre 40% e 55% de desconto!");
        }
    }

    // TODO: freight logic
}

private void validateScheduled(Discount discount) {
    LocalDate today = LocalDate.now();
    LocalDate startDate = discount.getStartDate();
    LocalDate endDate = discount.getEndDate();
    BigDecimal discountPercentage = discount.getPercentage();

    if (ChronoUnit.DAYS.between(today, startDate) < 10){
        throw new DiscountValidationException("Desconto agendado precisa ter no mínimo 10 dias de antecedência");
    }
    if (ChronoUnit.DAYS.between(today, startDate) > 31){
        throw new DiscountValidationException("Desconto não pode ter mais de 31 dias de antecedência");
    }
    if (ChronoUnit.DAYS.between(startDate, endDate) !=15) {
        throw new DiscountValidationException("Duração máxima de um desconto agendado é de 15 dias!");
    }
    if (discountPercentage.compareTo(BigDecimal.valueOf(20)) < 0 || discountPercentage.compareTo(BigDecimal.valueOf(50)) > 0){
        throw new DiscountValidationException("Desconto agendado possui desconto mínimo de 20% e máximo de 50%!");
    }
}

private void validateTemporary(Discount discount) {
      LocalDate today = LocalDate.now();
      LocalDate startDate = discount.getStartDate();
      LocalDate endDate = discount.getEndDate();
      BigDecimal discountPercentage = discount.getPercentage();

      long duration = ChronoUnit.DAYS.between(startDate, endDate);

      if (duration < 15) {
          throw new DiscountValidationException("Duração minima do desconto temporário tem que ser de 15 dias!");
      }
      if (duration > 31){
          throw new DiscountValidationException("Duração máxima do desconto temporário não pode ser maior que 31 dias!");
      }
      if (discountPercentage.compareTo(BigDecimal.valueOf(10)) < 0 || discountPercentage.compareTo(BigDecimal.valueOf(50)) > 0 ){
          throw new DiscountValidationException("Desconto mínimo temporário é de 10%, e o desconto máximo temporário é de 50%!");
      }
}

private void validatePermanent(Discount discount) {
    // TODO: cart products -> make the DB -> productCount --> Freight logic

}

}

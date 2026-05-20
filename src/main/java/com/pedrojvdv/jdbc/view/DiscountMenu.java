package com.pedrojvdv.jdbc.view;

import com.pedrojvdv.jdbc.dao.DiscountDao;
import com.pedrojvdv.jdbc.model.Discount;
import com.pedrojvdv.jdbc.model.User;
import com.pedrojvdv.jdbc.model.enums.DiscountType;
import com.pedrojvdv.jdbc.service.DiscountService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DiscountMenu {

    private final DiscountDao discountDao;
    private final DiscountService discountService;
    private final Scanner scanner;
    private final User user;

    public DiscountMenu(Scanner scanner, User user) {
        this.scanner = scanner;
        this.user = user;
        this.discountDao = new DiscountDao();
        this.discountService = new DiscountService();
    }

    private void findDiscountByType() throws SQLException {
        System.out.println("FITLRE SUA OFERTA:");
        System.out.println("------------------");
        System.out.println();
        System.out.println("OFERTAS DISPONÍVEIS: ");
        for (DiscountType discountType : DiscountType.values()) {
            System.out.println("--" + discountType.name());
        }
        System.out.println();
        System.out.println("------------------");
        System.out.println("INFORME O DESCONTO DESEJADO: ");
        String insert = scanner.nextLine().trim();
        DiscountType discountType = DiscountType.valueOf(insert.toUpperCase());

        System.out.println();
        System.out.println("------------------ ");
        System.out.println("PRODUTOS COM O DESCONTO INFORMADO: ");
        System.out.println();
        List<Discount> discounts = discountService.findByType(discountType);
        for (Discount discount : discounts){
            //            System.out.println(discount.toString());
//            @TODO: toString ++
        }
        System.out.println();
    }

    private void findExpiredDiscounts() throws SQLException {
        System.out.println("DESCONTOS EXPIRADOS: ");
        System.out.println();

        List<Discount> discounts = discountService.findExpiredDiscounts();

        for (Discount discount : discounts) {
            //            System.out.println(discount.toString());
//            @TODO: toString ++
        }
        System.out.println();
    }

    private void findFlashSaleCustomer() throws SQLException {
        System.out.println("OFERTAS RELÂMPAGOS: ");
        System.out.println("--------------------");

        List<Discount> discounts = discountService.findFlashSaleCustomer();

        for (Discount discount : discounts) {
            //            System.out.println(discount.toString());
//            @TODO: toString ++
        }
        System.out.println();
    }

    private void findFlashSalesAdmin() throws SQLException {
        System.out.println("DISCONTOS RELÂMPAGO: ");
        System.out.println();

        List<Discount> discounts = discountService.findFlashSalesAdmin();
        for (Discount discount : discounts) {
            //            System.out.println(discount.toString());
//            @TODO: toString ++
        }
        System.out.println();
    }

    private void findActiveDiscountById() throws SQLException {
        System.out.println("DIGITE O ID DO DESCONTO: ");
        System.out.println("----------------------");

        Long id = Long.parseLong(scanner.nextLine());

        List<Discount> discounts = discountService.findActiveDiscountById(id);
        for (Discount discount : discounts) {
            //            System.out.println(discount.toString());
//            @TODO: toString ++
        }
        System.out.println();
    }

    private void findActiveDiscounts() throws SQLException {
        System.out.println("PRODUTOS COM DESCONTO:");
        System.out.println("----------------------");

        List<Discount> discounts = discountService.findActiveDiscounts();
        for (Discount discount : discounts) {
//            System.out.println(discount.toString());
//            @TODO: toString ++
        }
        System.out.println();
    }

    private void deactivateExpiredDiscount() throws SQLException {
        System.out.println("DESATIVANDO PRODUTOS ESGOTADOS!");
        System.out.println("-------------------------------");

        discountService.deactivateExpiredDiscount();
        System.out.println("DESCONTOS EXPIRADOS DESATIVADOS COM SUCESSO!");
    }

    private void softDeleteDiscount() throws SQLException {
        System.out.println("DIGITE O ID DO DESCONTO A SER DELETADO DO SOFTWARE:");
        System.out.println("---------------------------------------");

        Long id = Long.parseLong(scanner.nextLine());
        Discount discount = buildDiscount();
        discount.setId(id);
        discountService.softDeleteDiscount(id);
    }

    private void deleteDiscount() throws SQLException {
        System.out.println("DIGITE O ID DO DESCONTO A SER DELETADO DO BANCO DE DADOS:");
        System.out.println("---------------------------------------");

        Long id = Long.parseLong(scanner.nextLine());
        Discount discount = buildDiscount();
        discount.setId(id);
        discountService.deleteDiscount(id);

    }

    private void updateEndDiscount(Integer stock) throws SQLException {
        System.out.println("INSIRA A DATA FINAL A SER ALTERADA: ");
        System.out.println("------------------------------------");
        System.out.println("FORMATO DESEJADO: ANO-MES-DIA");
        System.out.println("------------------------------------");

        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        Discount discount = buildDiscount();
        discount.setEndDate(endDate);
        discountService.updateEndDate(discount, stock);
    }

    private void updateDiscount(Integer stock) throws SQLException {
        System.out.println("INSIRA O ID DO DESCONTO A SER ALTERADO:");
        System.out.println("---------------------------------------");

        Long id = Long.parseLong(scanner.nextLine());
        Discount discount = buildDiscount();

        discount.setProductId(id);
        discountService.updateDiscount(discount, stock);
    }

    private void createDiscount(Integer stock) throws SQLException {
        Discount discount = buildDiscount();
        discountService.createDiscount(discount, stock);
    }

    private Discount buildDiscount() {
        Long productId = readProductId();
        DiscountType discountType = readDiscountType();
        BigDecimal discountPercentage = readDiscountPercentage();
        LocalDate discountStartDate = readDiscountStartDate();
        LocalDate discountEndDate = readDiscountEndDate();

        return new Discount(productId, discountPercentage, discountType, discountStartDate, discountEndDate);
    }

    private LocalDate readDiscountEndDate() {
        System.out.println("DATA FINAL DO DESCONTO:");
        System.out.println("---------------------------");
        System.out.println("FORMATO DESEJADO: ANO-MES-DIA");
        System.out.println("===========================");

        return LocalDate.parse(scanner.nextLine());
    }

    private LocalDate readDiscountStartDate() {
        System.out.println("DATA DE INICIO DO DESCONTO:");
        System.out.println("---------------------------");
        System.out.println("FORMATO DESEJADO: ANO-MES-DIA");
        System.out.println("===========================");

        return LocalDate.parse(scanner.nextLine());
    }

    private BigDecimal readDiscountPercentage() {
        System.out.println("DIGITE A PORCENTAGEM DE DESCONTO:");
        System.out.println("---------------------------------");

        return new BigDecimal(scanner.nextLine());
    }

    private Long readProductId() {
        System.out.println("DIGITE O ID DO PRODUTO: ");
        System.out.println("------------------------");

        return scanner.nextLong();
    }

    private DiscountType readDiscountType() {
        System.out.println("TIPOS DE DESCONTOS DISPONIVEIS:");
        System.out.println();
        System.out.println("-----------------------");
        System.out.println("PERMANENTE");
        System.out.println("TEMPORARIO");
        System.out.println("AGENDADO");
        System.out.println("OFERTA RELAMPAGO");
        System.out.println("=======================");
        System.out.println("DIGITE SEU TIPO DE DESCONTO: ");

        return DiscountType.valueOf(scanner.nextLine().toUpperCase());
    }

}



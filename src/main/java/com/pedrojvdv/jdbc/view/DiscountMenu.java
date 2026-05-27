package com.pedrojvdv.jdbc.view;

import com.pedrojvdv.jdbc.dao.DiscountDao;
import com.pedrojvdv.jdbc.model.Discount;
import com.pedrojvdv.jdbc.model.Product;
import com.pedrojvdv.jdbc.model.User;
import com.pedrojvdv.jdbc.model.enums.DiscountType;
import com.pedrojvdv.jdbc.service.DiscountService;
import com.pedrojvdv.jdbc.service.ProductService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DiscountMenu {

    private final ProductService productService;
    private final DiscountService discountService;
    private final Scanner scanner;
    private final User user;

    public DiscountMenu(User user, Scanner scanner) {
        this.user = user;
        this.scanner = scanner;
        this.discountService = new DiscountService();
        this.productService = new ProductService();
    }

    public void show() throws SQLException {
        boolean running = true;

        while (running) {

            System.out.println("- MENU DE DESCONTOS -");
            System.out.println();
            System.out.println("1 - OFERTAS RELÂMPAGOS ");
            System.out.println("2 - FILTRO DE DESCONTOS ");
            System.out.println("3 - PRODUTOS COM DESCONTO ");
            System.out.println("4 - VOLTAR");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {

                case 1:
                    findFlashSaleCustomer();
                    break;
                case 2:
                    findDiscountByType();
                    break;
                case 3:
                    findActiveDiscounts();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA!");
            }
        }
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
        for (Discount discount : discounts) {
            System.out.println(discount.toCatalogString());
        }
        System.out.println();
    }

    private void findExpiredDiscounts() throws SQLException {
        System.out.println("DESCONTOS EXPIRADOS: ");
        System.out.println();

        List<Discount> discounts = discountService.findExpiredDiscounts();

        for (Discount discount : discounts) {
            System.out.println(discount.toAdminDiscountString());
        }
        System.out.println();
    }

    private void findFlashSaleCustomer() throws SQLException {
        System.out.println("OFERTAS RELÂMPAGOS: ");
        System.out.println("--------------------");

        List<Discount> discounts = discountService.findFlashSaleCustomer();

        for (Discount discount : discounts) {
            System.out.println(discount.toCatalogString());
        }
        System.out.println();
    }

    private void findFlashSalesAdmin() throws SQLException {
        System.out.println("DESCONTOS RELÂMPAGO: ");
        System.out.println();

        List<Discount> discounts = discountService.findFlashSalesAdmin();
        for (Discount discount : discounts) {
            System.out.println(discount.toAdminDiscountString());
        }
        System.out.println();
    }

    private void findActiveDiscountById() throws SQLException {
        System.out.println("DIGITE O ID DO DESCONTO: ");
        System.out.println("----------------------");

        Long id = Long.parseLong(scanner.nextLine());

        List<Discount> discounts = discountService.findActiveDiscountById(id);
        for (Discount discount : discounts) {
            System.out.println(discount.toAdminDiscountString());
        }
        System.out.println();
    }

    private void findActiveDiscounts() throws SQLException {
        System.out.println("PRODUTOS COM DESCONTO:");
        System.out.println("----------------------");

        List<Discount> discounts = discountService.findActiveDiscounts();
        for (Discount discount : discounts) {
            System.out.println(discount.toCatalogString());
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
        discountService.softDeleteDiscount(id);
    }

    private void deleteDiscount() throws SQLException {
        System.out.println("DIGITE O ID DO DESCONTO A SER DELETADO DO BANCO DE DADOS:");
        System.out.println("---------------------------------------");

        Long id = Long.parseLong(scanner.nextLine());
        discountService.deleteDiscount(id);
    }

    private void updateEndDiscount() throws SQLException {
        System.out.println("INSIRA A DATA FINAL A SER ALTERADA: ");
        System.out.println("------------------------------------");
        System.out.println("FORMATO DESEJADO: ANO-MES-DIA");
        System.out.println("------------------------------------");

        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        Discount discount = buildDiscount();
        Product product = readProduct();
        Integer stock = product.getStock();
        discountService.updateEndDate(discount, stock);
    }

    private void updateDiscount() throws SQLException {
        System.out.println("INSIRA O ID DO DESCONTO A SER ALTERADO:");
        System.out.println("---------------------------------------");

        Long id = Long.parseLong(scanner.nextLine());
        Discount discount = buildDiscount();

        discount.setProductId(id);
        Product product = readProduct();
        Integer stock = product.getStock();
        discountService.updateDiscount(discount, stock);
    }

    private void createDiscount() throws SQLException {
        Product product = readProduct();
        Integer stock = product.getStock();

        Discount discount = buildDiscount();
        discount.setProductId(product.getId());

        if (discount.getType() == DiscountType.FLASH_SALE){
            System.out.println("ESCOLHA A DURAÇÃO DA OFERTA RELÂMPAGO: ");
            int hours = Integer.parseInt(scanner.nextLine());
            discount.setDurationHours(hours);
        }
            
        discountService.createDiscount(discount, stock);
    }

    protected void searchMenuAdmin() throws SQLException {
        boolean running = true;

        while (running) {
            System.out.println("=================");
            System.out.println("-_ MENU DE CONSULTAS _-");
            System.out.println("=================");
            System.out.println("1 - CONSULTAR DESCONTOS EXPIRADOS ");
            System.out.println("2 - CONSULTAR OFERTAS RELAMPAGOS ");
            System.out.println("3 - CONSULTAR DESCONTOS ATIVOS POR |ID| ");
            System.out.println("4 - VOLTAR ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    findExpiredDiscounts();
                    break;
                case 2:
                    findFlashSalesAdmin();
                    break;
                case 3:
                    findActiveDiscountById();
                    break;
                case 4:
                    running = false;
                    break;
            }
        }
    }

    protected void crudAdminMenu() throws SQLException {
        boolean running = true;

        while (running) {
            System.out.println("=================");
            System.out.println("-_ MENU DE CONSULTAS _-");
            System.out.println("=================");
            System.out.println("1 - CRIAR DESCONTO ");
            System.out.println("2 - ATUALIZAR DESCONTO ");
            System.out.println("3 - ATUALIZAR DATA FINAL DE DESCONTO ");
            System.out.println();
            System.out.println("------");
            System.out.println("4 - DESATIVAR DESCONTOS EXPIRADOS");
            System.out.println("------");
            System.out.println();
            System.out.println("5 - DELETAR DESCONTO - BANCO ");
            System.out.println("6 - DELETAR DESCONTO - SOFTWARE ");
            System.out.println();
            System.out.println("===========");
            System.out.println("7 - VOLTAR ");
            System.out.println("===========");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    createDiscount();
                    break;
                case 2:
                    updateDiscount();
                    break;
                case 3:
                    updateEndDiscount();
                    break;
                case 4:
                    deactivateExpiredDiscount();
                    break;
                case 5:
                    deleteDiscount();
                    break;
                case 6:
                    softDeleteDiscount();
                    break;
                case 7:
                    running = false;
                    break;
            }
        }
    }

    private Discount buildDiscount() {
        DiscountType discountType = readDiscountType();
        BigDecimal discountPercentage = readDiscountPercentage();
        LocalDate discountStartDate = readDiscountStartDate();
        LocalDate discountEndDate = readDiscountEndDate();

        return new Discount(discountPercentage, discountType, discountStartDate, discountEndDate);
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

    private Product readProduct() throws SQLException {
        System.out.println("DIGITE O ID DO PRODUTO:");
        System.out.println("----------------------");
        Long id = Long.parseLong(scanner.nextLine());
        return productService.getById(id);
    }

    private DiscountType readDiscountType() {
        System.out.println("TIPOS DE DESCONTOS DISPONIVEIS:");
        System.out.println("-----------------------");
        System.out.println("1 - PERMANENTE");
        System.out.println("2 - TEMPORARIO");
        System.out.println("3 - AGENDADO");
        System.out.println("4 - OFERTA RELAMPAGO");
        System.out.println("=======================");
        System.out.println("DIGITE SEU TIPO DE DESCONTO: ");

        String option = scanner.nextLine().trim();

        return switch (option) {
            case "1" -> DiscountType.PERMANENT;
            case "2" -> DiscountType.TEMPORARY;
            case "3" -> DiscountType.SCHEDULED;
            case "4" -> DiscountType.FLASH_SALE;
            default -> throw new IllegalArgumentException("OPÇÃO INVÁLIDA");
        };
    }

}



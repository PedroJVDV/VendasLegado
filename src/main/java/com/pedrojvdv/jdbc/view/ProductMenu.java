package com.pedrojvdv.jdbc.view;

import com.pedrojvdv.jdbc.dao.ProductDao;
import com.pedrojvdv.jdbc.model.Product;
import com.pedrojvdv.jdbc.model.User;
import com.pedrojvdv.jdbc.model.enums.Category;
import com.pedrojvdv.jdbc.model.enums.UserRole;
import com.pedrojvdv.jdbc.service.ProductService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ProductMenu {

    private final ProductDao productDao;
    private final Scanner scanner;
    private final ProductService productService;
    private final User user;

    public ProductMenu(User user, Scanner scanner) {
        this.user = user;
        this.scanner = scanner;
        this.productDao = new ProductDao();
        this.productService = new ProductService();
    }

    public void show() throws SQLException {
        boolean running = true;

        while (running) {

            System.out.println(" |--- MENU DE PRODUTOS ---| ");
            System.out.println();
            System.out.println(" 1 - PRODUTOS COM DESCONTO ");
            System.out.println(" 2 - PESQUISE SEU PRODUTO ");
            System.out.println(" 3 - FILTRAR POR PREÇO ");
            System.out.println(" 4 - PROCURAR POR CATEGORIA ");
            System.out.println(" 5 - CATALOGO GERAL DE PRODUTOS ");
            System.out.println(" 6 - FILTRAR POR PREÇO MÁXIMO ");
            System.out.println(" 9 - SAIR");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    productDao.findProductWithDiscount();
                    break;
                case 2:
                    searchProduct();
                    break;
                case 3:
                    searchByPriceRange();
                    break;
                case 4:
                    searchByCategory();
                    break;
                case 5:
                    showCatalogMenu();
                    break;
                case 6:
                    searchByMaxPrice();
                case 9:
                    running = false;
                    break;
                default:
                    System.out.println("Opção Inválida, tente novamente!");
            }
        }
    }

    private void searchProduct() throws SQLException {
        System.out.println("Digite o nome do produto: ");
        String productName = scanner.nextLine().trim();

        if (productName.equals("9")) {
            return;
        }

        List<Product> products = productService.getProductByName(productName);
        for (Product product : products) {
            System.out.println(product.toCatalogString());
        }

        System.out.println();
        System.out.println("9 - VOLTAR");
    }

    private void searchByCategory() throws SQLException {
        while (true) {
            System.out.println("Categorias disponíveis:");

            for (Category c : Category.values()) {
                System.out.println("- " + c.name());
            }
            System.out.println(" Digite a categoria desejada: ");
            String insertC = scanner.nextLine().trim();

            if (insertC.equals("9")) {
                return;
            }

            Category category = Category.valueOf(insertC.toUpperCase());

            System.out.println();
            System.out.println(" --> | 9 - VOLTAR | ");
            System.out.println("----------------------");
            System.out.println(" | CATALOGO DE PRODUTOS | ");
            System.out.println("----------------------");
            List<Product> products = productDao.findProductByCategory(category);
            for (Product product : products) {
                System.out.println(product.toCatalogString());
            }
            System.out.println();
            System.out.println();
        }
    }

    private void searchByPriceRange() throws SQLException {
        boolean running = true;
        while (running) {
            System.out.println("--> 9 - VOLTAR  ");
            System.out.println();
            System.out.println(" | FILTRAR POR PREÇO | ");

            System.out.println("Preço minimo: ");
            String minInput = scanner.nextLine().trim();

            if (minInput.equals("9")) {
                return;
            }

            System.out.println("Preço máximo: ");
            String maxInput = scanner.nextLine().trim();

            if (maxInput.equals("9")) {
                return;
            }

            BigDecimal minPrice = new BigDecimal(minInput);
            BigDecimal maxPrice = new BigDecimal(maxInput);

            List<Product> products = productService.getPriceByRange(minPrice, maxPrice);
            for (Product product : products) {
                System.out.println(product.toCatalogString());
            }

            System.out.println();
        }
    }

    protected void showCatalogMenu() throws SQLException {
        while (true) {
            System.out.println("CATALOGO");
            List<Product> products = productDao.findAllProducts();

            for (Product product : products) {
                System.out.println(product.toCatalogString());
            }

            System.out.println("9 - VOLTAR");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 9) {
                return;
            }
        }
    }

    protected void showAdminSearchMenu() throws SQLException {
        boolean running = true;
        while (running) {
            System.out.println(" -| BUSCA COMPLETA DE PRODUTOS |- ");
            System.out.println();
            System.out.println(" 2 - CONSULTAR PRODUTOS POR CATEOGORIA ");
            System.out.println(" 3 - CONSULTAR PRODUTOS COM DESCONTO ");
            System.out.println();
            System.out.println(" 5 - CONSULTAR PRODUTOS ESGOTADOS");
            System.out.println(" 4 - CONSULTAR PRODUTOS POR DATA DE CRIAÇÃO ");
            System.out.println(" 6 - CONSULTAR PRODUTOS POR NOME");
            System.out.println();
            System.out.println(" 7 - FILTRAR POR PREÇO MÁXIMO");
            System.out.println(" 8 - FILTRAR POR PREÇOS");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 9:
                    running = false;
                    break;
                case 2:
                    searchByCategory();
                    break;
                case 3:
                    searchByDiscount();
                    break;
                case 4:
                    searchByDate();
                    break;
                case 5:
                    searchBySoldOut();
                    break;
                case 6:
                    searchProduct();
                case 7:
                    maxPriceAdmin();
                    break;
                case 8:
                    searchByPriceRange();
                    break;
            }
        }
    }

    private void maxPriceAdmin() throws SQLException {
        System.out.println();
        System.out.println("Preço máximo: ");

        BigDecimal maxPrice = new BigDecimal(scanner.nextLine());
        if (maxPrice.equals("9")) {
            return;
        }

        List<Product> products = productService.getByMaxPrice(maxPrice);
        catalogByRole(products);

        System.out.println();
        System.out.println("9 - VOLTAR");
    }

    private void searchByMaxPrice() throws SQLException {
        System.out.println(" - FILTRO DE PREÇO MÁXIMO - ");
        System.out.println();
        System.out.println("Preço máximo: ");

        BigDecimal maxPrice = new BigDecimal(scanner.nextLine());
        if (maxPrice.equals("9")) {
            return;
        }

        List<Product> products = productService.getByMaxPrice(maxPrice);
        catalogByRole(products);

        System.out.println();
        System.out.println("9 - VOLTAR");
    }

    private void searchBySoldOut() throws SQLException {
        System.out.println(" | PRODUTOS ESGOTADOS |");
        List<Product> products = productDao.findSoldOutProducts();
        catalogByRole(products);

        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 9) {
            return;
        }

        System.out.println();
        System.out.println("9 - VOLTAR");
    }

    private void searchByDate() throws SQLException {
        try {
            System.out.println(" | PRODUTOS DISPONIVEIS COM SUA DATA DE CRIAÇÃO INFORMADA | ");
            System.out.println();
            System.out.println("INFORME A DATA DESEJADA - (ano-MES-dia): ");

            String insert = scanner.nextLine();
            if (insert.equals("9")) {
                return;
            }
            LocalDate date = LocalDate.parse(insert);
            List<Product> products = productDao.findProductsByDate(date);
            catalogByRole(products);

            System.out.println();
            System.out.println("9 - VOLTAR");
        } catch (DateTimeParseException e) {
            System.out.println("DATA INVÁLIDA. Use o formato: yyyy-MM-dd. ");
        }
    }

    private void searchByDiscount() throws SQLException {
        System.out.println(" | PRODUTOS EM DESCONTO | ");
        List<Product> products = productDao.findProductWithDiscount();
        catalogByRole(products);

        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 9) {
            return;
        }

        System.out.println();
        System.out.println("9 - VOLTAR");
    }

    protected void showManageMenu() throws SQLException {
        boolean running = true;

        while (running) {
            System.out.println(" | GERENCIAMENTO DE PRODUTOS | ");
            System.out.println();
            System.out.println(" 1 - CONSULTAR PRODUTOS POR REGISTRO |ID|");
            System.out.println(" 2 - CRIAR PRODUTO");
            System.out.println(" 3 - ATUALIZAR PRODUTO");
            System.out.println(" 4 - DELETAR PRODUTO COMPLETAMENTE");
            System.out.println(" 5 - DELETAR PRODUTO DO SOFTWARE");
            System.out.println(" 9 - SAIR");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("DIGITE O NUMERO DE REGISTRO DO PRODUTO -> |ID|");
                    Long id = Long.parseLong(scanner.nextLine());
                    productService.getById(id);
                    break;
                case 2:
                    createProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    System.out.print("ID do produto que deseja deletar PERMANENTEMENTE: ");
                    Long idDelete = Long.parseLong(scanner.nextLine());
                    productService.deleteProduct(idDelete);
                    break;
                case 5:
                    System.out.print("ID do produto que deseja deletar do SOFTWARE ");
                    Long idSoftDelete = Long.parseLong(scanner.nextLine());
                    productService.softDeleteProduct(idSoftDelete);
                    break;
                case 9:
                    running = false;
                    break;
            }
        }
    }

    private void createProduct() throws SQLException {
        Product product = buildProduct();
        productService.createProduct(product);
    }

    private void updateProduct() throws SQLException {
        System.out.println("Digite o registro do produto que deseja atualizar -> |ID|");
        long id = Long.parseLong(scanner.nextLine());
        Product product = buildProduct();
        product.setId(id);
        productService.updateProduct(product);
    }

    private void catalogByRole(List<Product> products) {
        for (Product product : products) {
            if (user.getRole() == UserRole.USER) {
                System.out.println(product.toCatalogString());
            } else {
                System.out.println(product.toAdminString());
            }
        }
    }

    private Product buildProduct() {
        String name = readName();
        String description = readDescription();
        Category category = readCategory();
        BigDecimal price = readPrice();
        int stock = readStock();
        boolean available = readAvailable();

        return new Product(name, price, stock, available, description, category);
    }

    private String readName() {
        System.out.print("Nome: ");
        return scanner.nextLine();
    }

    private BigDecimal readPrice() {
        System.out.print("Preço: ");
        return new BigDecimal(scanner.nextLine());
    }

    private String readDescription() {
        System.out.print("Descrição: ");
        return scanner.nextLine();
    }

    private Category readCategory() {
        System.out.print("Categoria: ");
        return Category.valueOf(scanner.nextLine().toUpperCase());
    }

    private int readStock() {
        System.out.print("Estoque: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private boolean readAvailable() {
        System.out.print("Disponível (true/false): ");
        return Boolean.parseBoolean(scanner.nextLine());
    }

}

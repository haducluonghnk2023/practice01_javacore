package presentation;

import entity.Categories;
import entity.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ShopManagement {
    private static List<Categories> categories = new ArrayList<>();
    private static List<Product> products = new ArrayList<>();
    public static Scanner scanner =  new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    categoryManagement();
                    break;
                case 2:
                    productManagement();
                    break;
                case 3:
                    System.out.println("Thoát chương trình.");
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("*********************SHOP MENU*********************");
        System.out.println("1. Quản lý danh mục");
        System.out.println("2. Quản lý sản phẩm");
        System.out.println("3. Thoát");
        System.out.println("**************************************************");
        System.out.print("Chọn chức năng: ");
    }

    private static void categoryManagement() {
        while (true) {
            displayCategoryMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayCategories();
                    break;
                case 2:
                    addCategory();
                    break;
                case 3:
                    updateCategory();
                    break;
                case 4:
                    deleteCategory();
                    break;
                case 5:
                    searchCategoryByName();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void displayCategoryMenu() {
        System.out.println("******************CATEGORIE MANAGEMENT*********************");
        System.out.println("1. Danh sách danh mục");
        System.out.println("2. Thêm mới danh mục");
        System.out.println("3. Cập nhật danh mục");
        System.out.println("4. Xóa danh mục");
        System.out.println("5. Tìm kiếm danh mục theo tên");
        System.out.println("6. Thoát");
        System.out.println("***********************************************************");
        System.out.print("Chọn chức năng: ");
    }

    private static void productManagement() {
        while (true) {
            displayProductMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    searchProductsByNameOrTitle();
                    break;
                case 6:
                    searchProductsByPriceRange();
                    break;
                case 7:
                    sortProductsByPrice();
                    break;
                case 8:
                    sellProduct();
                    break;
                case 9:
                    countProductsByCategory();
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void displayProductMenu() {
        System.out.println("************************PRODUCT MANAGEMENT*******************");
        System.out.println("1. Danh sách sản phẩm");
        System.out.println("2. Thêm mới sản phẩm");
        System.out.println("3. Cập nhật sản phẩm");
        System.out.println("4. Xóa sản phẩm");
        System.out.println("5. Tìm kiếm sản phẩm theo tên hoặc tiêu đề");
        System.out.println("6. Tìm kiếm sản phẩm theo khoảng giá bán");
        System.out.println("7. Sắp xếp sản phẩm theo giá bán tăng dần");
        System.out.println("8. Bán sản phẩm");
        System.out.println("9. Thống kê số lượng sản phẩm theo danh mục");
        System.out.println("10. Thoát");
        System.out.println("***********************************************************");
        System.out.print("Chọn chức năng: ");
    }

    private static void displayCategories() {
        if (categories.isEmpty()) {
            System.out.println("Danh sách danh mục trống.");
            return;
        }
        System.out.println("Danh sách danh mục:");
        for (Categories category : categories) {
            category.displayData();
            System.out.println("--------------------");
        }
    }

    private static void addCategory() {
        Categories newCategory = new Categories();
        newCategory.inputData(scanner);
        categories.add(newCategory);
        System.out.println("Thêm mới danh mục thành công.");
    }

    private static void updateCategory() {
        System.out.print("Nhập mã danh mục cần cập nhật: ");
        int categoryId = Integer.parseInt(scanner.nextLine());
        for (Categories category : categories) {
            if (category.getCatalogId() == categoryId) {
                category.inputData(scanner);
                System.out.println("Cập nhật danh mục thành công.");
                return;
            }
        }
        System.out.println("Không tìm thấy danh mục với mã đã nhập.");
    }

    private static void deleteCategory() {
        System.out.print("Nhập mã danh mục cần xóa: ");
        int categoryId = Integer.parseInt(scanner.nextLine());

        // Kiểm tra xem danh mục có sản phẩm nào không
        boolean hasProducts = products.stream().anyMatch(product -> product.getCategoryId() == categoryId);
        if (hasProducts) {
            System.out.println("Không thể xóa danh mục vì có sản phẩm thuộc danh mục này.");
            return;
        }

        categories.removeIf(category -> category.getCatalogId() == categoryId);
        System.out.println("Xóa danh mục thành công.");
    }

    private static void searchCategoryByName() {
        System.out.print("Nhập tên danh mục cần tìm: ");
        String searchName = scanner.nextLine();
        List<Categories> foundCategories = categories.stream()
                .filter(category -> category.getCategoryName().toLowerCase().contains(searchName.toLowerCase()))
                .collect(Collectors.toList());
        if (foundCategories.isEmpty()) {
            System.out.println("Không tìm thấy danh mục nào có tên chứa \"" + searchName + "\".");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            for (Categories category : foundCategories) {
                category.displayData();
                System.out.println("--------------------");
            }
        }
    }

    // Quản lý sản phẩm (tương tự như quản lý danh mục)
    private static void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.");
            return;
        }
        System.out.println("Danh sách sản phẩm:");
        for (Product product : products) {
            product.displayData();
            System.out.println("--------------------");
        }
    }

    private static void addProduct() {
        Product newProduct = new Product();
        newProduct.inputData(scanner);
        products.add(newProduct);
        System.out.println("Thêm mới sản phẩm thành công.");
    }

    private static void updateProduct() {
        System.out.print("Nhập mã sản phẩm cần cập nhật: ");
        String productId = scanner.nextLine();
        for (Product product : products) {
            if (product.getProductId().equalsIgnoreCase(productId)) {
                product.inputData(scanner);
                System.out.println("Cập nhật sản phẩm thành công.");
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm với mã đã nhập.");
    }

    private static void deleteProduct() {
        System.out.print("Nhập mã sản phẩm cần xóa: ");
        String productId = scanner.nextLine();
        products.removeIf(product -> product.getProductId().equalsIgnoreCase(productId));
        System.out.println("Xóa sản phẩm thành công.");
    }

    private static void searchProductsByNameOrTitle() {
        System.out.print("Nhập tên hoặc tiêu đề sản phẩm cần tìm: ");
        String search = scanner.nextLine();
        List<Product> foundProducts = products.stream()
                .filter(product -> product.getProductName().toLowerCase().contains(search.toLowerCase()) ||
                        product.getTitle().toLowerCase().contains(search.toLowerCase()))
                .collect(Collectors.toList());
        if (foundProducts.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào có tên hoặc tiêu đề chứa \"" + search + "\".");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            for (Product product : foundProducts) {
                product.displayData();
                System.out.println("--------------------");
            }
        }
    }

    private static void searchProductsByPriceRange() {
        System.out.print("Nhập giá bán thấp nhất: ");
        float minPrice = Float.parseFloat(scanner.nextLine());
        System.out.print("Nhập giá bán cao nhất: ");
        float maxPrice = Float.parseFloat(scanner.nextLine());
        List<Product> foundProducts = products.stream()
                .filter(product -> product.getExPrice() >= minPrice && product.getExPrice() <= maxPrice)
                .collect(Collectors.toList());
        if (foundProducts.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào có giá bán trong khoảng " + minPrice + " đến " + maxPrice + ".");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            for (Product product : foundProducts) {
                product.displayData();
                System.out.println("--------------------");
            }
        }
    }

    private static void sortProductsByPrice() {
        List<Product> sortedProducts = new ArrayList<>(products);
        sortedProducts.sort(Comparator.comparing(Product::getExPrice));
        System.out.println("Danh sách sản phẩm sau khi sắp xếp theo giá bán tăng dần:");
        for (Product product : sortedProducts) {
            product.displayData();
            System.out.println("--------------------");
        }
    }

    private static void sellProduct() {
        System.out.print("Nhập mã sản phẩm cần bán: ");
        String productId = scanner.nextLine();
        for (Product product : products) {
            if (product.getProductId().equalsIgnoreCase(productId)) {
                if (product.getQuantity() > 0) {
                    product.setQuantity(product.getQuantity() - 1);
                    System.out.println("Bán sản phẩm thành công.");
                    return;
                } else {
                    System.out.println("Sản phẩm đã hết hàng.");
                    return;
                }
            }
        }
        System.out.println("Không tìm thấy sản phẩm với mã đã nhập.");
    }

    private static void countProductsByCategory() {
        System.out.println("Thống kê số lượng sản phẩm theo danh mục:");
        for (Categories category : categories) {
            int count = (int) products.stream()
                    .filter(product -> product.getCategoryId() == category.getCatalogId())
                    .count();
            System.out.println("Danh mục \"" + category.getCategoryName() + "\": " + count + " sản phẩm.");
        }
    }
}

package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Product implements IApp{
    private String productId;
    private String productName;
    private float interestPrice;
    private float exPrice;
    private String title;
    private String description;
    private int quantity;
    private int categoryId;
    private int status;

    private static List<String> productIds = new ArrayList<>();
    private static List<String> productNames = new ArrayList<>();

    public Product() {
    }

    public Product(String productId, String productName, float interestPrice, float exPrice, String title, String description, int quantity, int categoryId, int status) {
        this.productId = productId;
        this.productName = productName;
        this.interestPrice = interestPrice;
        this.exPrice = exPrice;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.status = status;
        productIds.add(productId);
        productNames.add(productName);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getInterestPrice() {
        return interestPrice;
    }

    public void setInterestPrice(float interestPrice) {
        this.interestPrice = interestPrice;
    }

    public float getExPrice() {
        return exPrice;
    }

    public void setExPrice(float exPrice) {
        this.exPrice = exPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner scanner) {
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Mã sản phẩm (5 ký tự, bắt đầu 'C', 'E', 'T', không trùng lặp): ");
            this.productId = scanner.nextLine().toUpperCase();
            if (productId.length() == 5 && (productId.startsWith("C") || productId.startsWith("E") || productId.startsWith("T")) && !productIds.contains(productId)) {
                validInput = true;
                productIds.add(productId);
            } else {
                System.out.println("Mã sản phẩm không hợp lệ hoặc đã tồn tại.");
            }
        }
        validInput = false;

        while (!validInput) {
            System.out.print("Tên sản phẩm (10-100 ký tự, không trùng lặp): ");
            this.productName = scanner.nextLine();
            if (productName.length() >= 10 && productName.length() <= 100 && !productNames.contains(productName)) {
                validInput = true;
                productNames.add(productName);
            } else {
                System.out.println("Tên sản phẩm không hợp lệ hoặc đã tồn tại.");
            }
        }

        validInput = false;

        while (!validInput) {
            System.out.print("Giá nhập (lớn hơn 0): ");
            this.interestPrice = Float.parseFloat(scanner.nextLine());
            if (interestPrice > 0) {
                validInput = true;
            } else {
                System.out.println("Giá nhập phải lớn hơn 0.");
            }
        }
        validInput = false;

        while (!validInput) {
            System.out.print("Giá xuất (lớn hơn giá nhập * " + INTEREST + "): ");
            this.exPrice = Float.parseFloat(scanner.nextLine());
            if (exPrice > interestPrice * INTEREST) {
                validInput = true;
            } else {
                System.out.println("Giá xuất phải lớn hơn giá nhập * " + INTEREST + ".");
            }
        }

        System.out.print("Tiêu đề sản phẩm (tối đa 200 ký tự): ");
        this.title = scanner.nextLine();

        System.out.print("Mô tả sản phẩm: ");
        this.description = scanner.nextLine();

        System.out.print("Số lượng sản phẩm: ");
        this.quantity = Integer.parseInt(scanner.nextLine());

        System.out.print("Mã danh mục: ");
        this.categoryId = Integer.parseInt(scanner.nextLine());

        System.out.print("Trạng thái sản phẩm (0: Đang hoạt động, 1: Hết hàng, 2: Không hoạt động): ");
        this.status = Integer.parseInt(scanner.nextLine());
    }

    @Override
    public void displayData() {
        System.out.println("Mã sản phẩm: " + productId);
        System.out.println("Tên sản phẩm: " + productName);
        System.out.println("Giá nhập: " + interestPrice);
        System.out.println("Giá xuất: " + exPrice);
        System.out.println("Tiêu đề: " + title);
        System.out.println("Mô tả: " + description);
        System.out.println("Số lượng: " + quantity);
        System.out.println("Mã danh mục: " + categoryId);
        System.out.println("Trạng thái: " + getStatusString(status));
    }

    private String getStatusString(int status) {
        switch (status) {
            case 0:
                return "Đang hoạt động";
            case 1:
                return "Hết hàng";
            case 2:
                return "Không hoạt động";
            default:
                return "Không xác định";
        }
    }
}

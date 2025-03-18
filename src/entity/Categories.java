package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Categories implements IApp{
    private static int  nextCategory = 1;
    private int catalogId;
    private String categoryName;
    private int priority;
    private String description;
    private boolean status;
    private static List<String> categoryNames = new ArrayList<>();

    public Categories() {
        this.catalogId = nextCategory++;
    }

    public Categories(int catalogId, String categoryName, String description, boolean status, int priority) {
        this.catalogId = nextCategory++;
        this.categoryName = categoryName;
        this.description = description;
        this.status = status;
        this.priority = priority;
        categoryNames.add(categoryName);
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner scanner) {
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Tên danh mục (6-50 ký tự, không trùng lặp): ");
            this.categoryName = scanner.nextLine();
            if (categoryName.length() >= 6 && categoryName.length() <= 50) {
                if (!categoryNames.contains(categoryName)) {
                    validInput = true;
                    categoryNames.add(categoryName);
                } else {
                    System.out.println("Tên danh mục đã tồn tại. Vui lòng nhập tên khác.");
                }
            } else {
                System.out.println("Tên danh mục phải từ 6 đến 50 ký tự.");
            }
        }

        System.out.print("Độ ưu tiên: ");
        this.priority = Integer.parseInt(scanner.nextLine());

        System.out.print("Mô tả danh mục (tối đa 255 ký tự): ");
        this.description = scanner.nextLine();

        System.out.print("Trạng thái (true/false): ");
        this.status = Boolean.parseBoolean(scanner.nextLine());
    }

    @Override
    public void displayData() {
        System.out.println("Mã danh mục: " + catalogId);
        System.out.println("Tên danh mục: " + categoryName);
        System.out.println("Độ ưu tiên: " + priority);
        System.out.println("Mô tả: " + description);
        System.out.println("Trạng thái: " + (status ? "Hoạt động" : "Không hoạt động"));
    }
}

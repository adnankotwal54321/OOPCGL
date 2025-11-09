mport java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Product {
    int pid;
    String name;
    double price;
    int stock;
    double costPrice;

    Product(int pid, String name, double price, int stock) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.costPrice = price * 0.7;
    }
}

class Inventory {
    HashMap<Integer, Product> products = new HashMap<>();
    double totalPurchase = 0;
    double totalSales = 0;

    void addProduct(int pid, String name, double price, int stock) {
        products.put(pid, new Product(pid, name, price, stock));
    }

    void listProducts() {
        System.out.println("\n--- Product List ---");
        System.out.printf("%-5s %-15s %-10s %-10s\n", "ID", "Name", "Price", "Stock");
        for (Product p : products.values()) {
            System.out.printf("%-5d %-15s %-10.2f %-10d\n", p.pid, p.name, p.price, p.stock);
        }
    }

    void showProduct(int pid) {
        Product p = products.get(pid);
        if (p != null) {
            System.out.println("\nProduct Info:");
            System.out.println("ID: " + p.pid);
            System.out.println("Name: " + p.name);
            System.out.println("Price: ₹" + p.price);
            System.out.println("Stock: " + p.stock);
        } else {
            System.out.println("Product not found!");
        }
    }

    void purchase(int pid, int qty) {
        Product p = products.get(pid);
        if (p != null) {
            p.stock += qty;
            totalPurchase += qty * p.costPrice;
            System.out.println(qty + " units of " + p.name + " purchased.");
        } else {
            System.out.println("Invalid Product ID!");
        }
    }

    void checkStock() {
        System.out.println("\n--- Stock Summary ---");
        for (Product p : products.values()) {
            System.out.println(p.name + ": " + p.stock + " units");
        }
    }

    void profitLoss() {
        double profit = totalSales - totalPurchase;
        System.out.println("\nTotal Purchase Cost: ₹" + totalPurchase);
        System.out.println("Total Sales Revenue: ₹" + totalSales);
        System.out.println("Net Profit/Loss: ₹" + profit);
    }

    Product searchProduct(int pid) {
        return products.get(pid);
    }

    void buyAndPrintBill(Scanner sc) {
        System.out.print("Enter Product ID to buy: ");
        int id = sc.nextInt();
        Product p = searchProduct(id);
        if (p == null) {
            System.out.println("Product not found!");
            return;
        }
        System.out.print("Enter quantity to buy: ");
        int qty = sc.nextInt();
        if (qty > p.stock) {
            System.out.println("Not enough stock available!");
            return;
        }

        double total = qty * p.price;
        p.stock -= qty;
        totalSales += total;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        System.out.println("\n+--------------------------------------+");
        System.out.println("|           BILL RECEIPT               |");
        System.out.println("+--------------------------------------+");
        System.out.printf("| Date & Time : %-22s |\n", formattedDateTime);
        System.out.println("|--------------------------------------|");
        System.out.printf("| Product Name : %-21s |\n", p.name);
        System.out.printf("| Quantity     : %-21d |\n", qty);
        System.out.printf("| Price/Item   : ₹%-20.2f |\n", p.price);
        System.out.printf("| Total Amount : ₹%-20.2f |\n", total);
        System.out.println("+--------------------------------------+");
        System.out.println("|      Thank you for shopping!         |");
        System.out.println("+--------------------------------------+");
    }
}

public class InventoryMiniProject {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inv = new Inventory();

        inv.addProduct(101, "Laptop", 50000, 10);
        inv.addProduct(102, "Phone", 20000, 25);
        inv.addProduct(103, "Tablet", 30000, 15);
        inv.addProduct(104, "Headphones", 2000, 40);
        inv.addProduct(105, "Keyboard", 1500, 35);
        inv.addProduct(106, "Mouse", 800, 50);
        inv.addProduct(107, "Smart Watch", 7000, 20);
        inv.addProduct(108, "Monitor", 15000, 12);
        inv.addProduct(109, "Printer", 12000, 8);
        inv.addProduct(110, "Speaker", 3500, 25);

        while (true) {
            System.out.println("\n===== INVENTORY MENU =====");
            System.out.println("1. Show All Products");
            System.out.println("2. View Product Info");
            System.out.println("3. Purchase Product");
            System.out.println("4. Check Stock");
            System.out.println("5. Profit/Loss Report");
            System.out.println("6. Buy and Print Bill");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> inv.listProducts();
                case 2 -> {
                    System.out.print("Enter Product ID: ");
                    inv.showProduct(sc.nextInt());
                }
                case 3 -> {
                    System.out.print("Enter Product ID: ");
                    int pid = sc.nextInt();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    inv.purchase(pid, qty);
                }
                case 4 -> inv.checkStock();
                case 5 -> inv.profitLoss();
                case 6 -> inv.buyAndPrintBill(sc);
                case 7 -> {
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

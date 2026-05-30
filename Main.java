import java.io.*;
import java.util.*;

class Expense {
    String name;
    double amount;
    String category;
    String date;

    Expense(String name, double amount, String category, String date) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }
}

public class Main {

    static ArrayList<Expense> list = new ArrayList<>();
    static final String FILE_NAME = "expenses.txt";
    static Scanner sc = new Scanner(System.in);

    // LOAD DATA
    static void loadData() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return;

            Scanner fileSc = new Scanner(file);
            while (fileSc.hasNextLine()) {
                String[] data = fileSc.nextLine().split(",");
                list.add(new Expense(
                        data[0],
                        Double.parseDouble(data[1]),
                        data[2],
                        data[3]
                ));
            }
            fileSc.close();
        } catch (Exception e) {
            System.out.println("Error loading data");
        }
    }

    // SAVE DATA
    static void saveData() {
        try {
            FileWriter fw = new FileWriter(FILE_NAME);
            for (Expense e : list) {
                fw.write(e.name + "," + e.amount + "," + e.category + "," + e.date + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error saving data");
        }
    }

    // ADD EXPENSE
    static void addExpense() {
        sc.nextLine();

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter category (Food/Travel/Bills): ");
        String category = sc.nextLine();

        System.out.print("Enter date (dd-mm-yyyy): ");
        String date = sc.nextLine();

        list.add(new Expense(name, amount, category, date));
        saveData();

        System.out.println("Expense added successfully!");
    }

    // VIEW EXPENSES
    static void viewExpenses() {
        System.out.println("\n--- ALL EXPENSES ---");
        for (Expense e : list) {
            System.out.println(e.date + " | " + e.name + " | " + e.category + " | " + e.amount);
        }
    }

    // TOTAL EXPENSE
    static void totalExpense() {
        double total = 0;
        for (Expense e : list) {
            total += e.amount;
        }
        System.out.println("Total Expense = " + total);
    }

    // SEARCH BY CATEGORY
    static void searchByCategory() {
        sc.nextLine();
        System.out.print("Enter category: ");
        String cat = sc.nextLine();

        System.out.println("\n--- Results ---");
        for (Expense e : list) {
            if (e.category.equalsIgnoreCase(cat)) {
                System.out.println(e.date + " | " + e.name + " | " + e.amount);
            }
        }
    }

    public static void main(String[] args) {

        loadData();

        while (true) {
            System.out.println("\n===== EXPENSE TRACKER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Total Expense");
            System.out.println("4. Search by Category");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addExpense();
                case 2 -> viewExpenses();
                case 3 -> totalExpense();
                case 4 -> searchByCategory();
                case 5 -> {
                    saveData();
                    System.out.println("Exiting... Data saved.");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
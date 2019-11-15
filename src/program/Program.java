package program;

import models.Product;
import operations.FileOpeations;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static constants.Constants.LAPTOP_CATEGORY;
import static constants.Constants.PHONE_CATEGORY;
import static operations.FileOpeations.writeLaptopsToFile;
import static operations.FileOpeations.writePhonesToFile;
import static operations.LaptopOperations.*;

public class Program {

    // change the way a read from console -> if a insert something like "Note 10" the scanner will consider that there a two different inputs (hint: try nextLine() method)
    // little changes to text

    public static void main(String[] args) throws IOException {
        final Scanner scanner = new Scanner(System.in);
        Map<String, List<Map<Integer, Product>>> productsMap = new HashMap<>();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        FileOpeations.readProductsFromFiles(productsMap);
        printInitialMenu();
        boolean isRunning = true;
        while (isRunning) {
            String input = scanner.nextLine();
            switch (input) {
                case "See laptops":
                    printProducts(productsMap,LAPTOP_CATEGORY);
                    break;
                case "Add laptop":
                    addLaptop(scanner, productsMap);
                    break;
                case "Remove laptop":
                    removeLaptop(scanner, productsMap);
                    break;
                case "Update laptop":
                    updateLaptop(scanner, productsMap);
                    break;
                case "Laptops report":
                    printLaptopReport(productsMap, decimalFormat);
                    break;
                case "See phones":
                    printProducts(productsMap,PHONE_CATEGORY);
                    break;
                case "Add phone":
                    addPhone(scanner, productsMap);
                    break;
                case "Remove phone":
                    removePhone(scanner,productsMap);
                    break;
                case "Update phone":
                    updatePhone(scanner,productsMap);
                    break;
                case "Phone report":
                    printPhoneReport(productsMap,decimalFormat);
                    break;
                case "Quit":
                    writeLaptopsToFile(productsMap);
                    writePhonesToFile(productsMap);
                    isRunning = false;
                    scanner.close();
                    break;
                default:
                    System.out.println("Option doesn't exist. Please try again!");
                    break;
            }
        }
    }


    private static void printInitialMenu() {
        System.out.println("Welcome to the store.");
        System.out.println("----- Options -----");
        System.out.println("1. See laptops -> prints all the available laptops in the store.");
        System.out.println("2. Add laptop -> adds a new laptop to the store.");
        System.out.println("3. Remove laptop -> remove a laptop from the store by it's id. To see the id's of the laptops use the command at line 1.");
        System.out.println("4. Update laptop -> update a property of a specified laptop.");
        System.out.println("5. Laptops report -> see the laptop report.");
        System.out.println("5. See phones -> prints all the available phones in the store.");
        System.out.println("6. Add phone -> adds a new phone to the store.");
        System.out.println("7. Remove phone -> remove a phone from the store by it's id. To see the id's of the phones use the command at line 6.");
        System.out.println("8. Update phone -> update a property of a specified phone.");
        System.out.println("9. Phones report -> see the phone report.");
        System.out.println("10. Quit -> close the application.");
    }


}

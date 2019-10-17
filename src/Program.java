import models.Laptop;
import models.Product;
import operations.FileOpeations;
import operations.LaptopOperations;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static constants.Constants.LAPTOPS_FILE;
import static constants.Constants.LAPTOP_CATEGORY;

public class Program {

    public static void main(String[] args) throws IOException {
        final Scanner scanner = new Scanner(System.in);
        Map<String, List<Map<Integer, Product>>> productsMap = new HashMap<>();
        FileOpeations.readLaptopsFromFile(productsMap);
        printInitialMenu();
        boolean isRunning = true;
        while (isRunning) {
            String input = scanner.nextLine();
            switch (input) {
                case "See laptops":
                    LaptopOperations.printLaptops(productsMap);
                    break;
                case "Add laptop":
                    addLaptop(scanner, productsMap);
                    break;
                case "Remove laptop":
                    removeLaptop(scanner, productsMap);
                    break;
                case "Update laptop":
                    System.out.print("Specify the position of the laptop : ");
                    //TODO: validation
                    int position = scanner.nextInt();
                    System.out.print("Specify the filed of the laptop that you want to update: [Name, Price, Ram, OS, availability] : ");
                    enterLaptopValues(scanner, productsMap, position);
                    break;
                case "Quit":
                    FileOpeations.writeLaptopsToFile(productsMap);
                    isRunning = false;
                    scanner.close();
                    break;
                default:
                    System.out.println("Option doesn't exist. Please try again!");
                    break;
            }
        }
    }

    private static void enterLaptopValues(Scanner scanner, Map<String, List<Map<Integer, Product>>> productsMap, int position) {
        boolean isModified = true;
        while(isModified) {
            String filed = scanner.next();
            switch (filed) {
                case "Name":
                    System.out.print("New name: ");
                    String newLaptopName = scanner.next();
                    LaptopOperations.updateLaptopName(productsMap, position, newLaptopName);
                    isModified = false;
                    System.out.println("You successfully updated the name!");
                    break;
                case "Price":
                    System.out.print("New price: ");
                    String newLaptopPrice = scanner.next();
                    while(NumberUtils.toDouble(newLaptopPrice, 0) == 0){
                        System.out.println("The price should be numeric. Please try again!");
                        System.out.print("Price: ");
                        newLaptopPrice = scanner.next();
                    }
                    LaptopOperations.updateLaptopPrice(productsMap, position, newLaptopPrice);
                    isModified = false;
                    System.out.println("You successfully updated the price!");
                    break;
                case "Ram":
                    System.out.print("New ram: ");
                    String newLaptopRam = scanner.next();
                    while(NumberUtils.toInt(newLaptopRam, 0) == 0){
                        System.out.println("The ram should be numeric. Please try again!");
                        System.out.print("Ram : ");
                        newLaptopRam = scanner.next();
                    }
                    LaptopOperations.updateLaptopRam(productsMap, position, newLaptopRam);
                    isModified = false;
                    System.out.println("You successfully updated the ram!");
                    break;
                case "OS":
                    System.out.print("New os: ");
                    String newLaptopOs = scanner.next();
                    LaptopOperations.updateLaptopOS(productsMap, position, newLaptopOs);
                    isModified = false;
                    System.out.println("You successfully updated the os!");
                    break;
                case "availability":
                    System.out.print("New availability: ");
                    String newLaptopAcailability = scanner.next();
                    while(NumberUtils.toInt(newLaptopAcailability, 0) == 0){
                        System.out.println("The availability should be numeric. Please try again!");
                        System.out.println("Availability : ");
                        newLaptopAcailability = scanner.next();
                    }
                    LaptopOperations.updateLaptopAvailability(productsMap, position, newLaptopAcailability);
                    isModified = false;
                    System.out.println("You successfully updated the availability!");
                    break;
                default:
                    System.out.println("Wrong filed, please choose one from [Name, Price, Ram, OS, availability]");
                    break;
            }
        }
    }

    private static void removeLaptop(Scanner scanner, Map<String, List<Map<Integer, Product>>> productsMap) {
        System.out.print("Specify the position of the laptop : ");
        String userinput = scanner.next();
        int laptopPosition = checkIfInputIsInteger(scanner, userinput, productsMap);
        final String[] laptopName = {""};
        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopList.forEach(map -> {
                    laptopName[0] = String.valueOf(map.get(laptopPosition));
                });
            }
        });
        System.out.println("Laptop " + laptopName[0] + " was successfully deleted!");
        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopList.forEach(map -> map.remove(laptopPosition));
            }
        });
    }

    private static void addLaptop(Scanner scanner, Map<String, List<Map<Integer, Product>>> productsMap) throws IOException {
        System.out.print("Laptop name = ");
        String name = scanner.next();
        System.out.print("Laptop price = ");
        String price = scanner.next();
        while(NumberUtils.toDouble(price,0) == 0){
            System.out.println("The price should be numberic! Please try again");
            System.out.print("Price: ");
            price = scanner.next();
        }
        System.out.print("Laptop ram = ");
        String ram = scanner.next();
        while(NumberUtils.toInt(ram,0) == 0){
            System.out.println("The ram should be numberic! Please try again");
            System.out.print("Ram: ");
            ram = scanner.next();
        }
        System.out.print("Laptop os = ");
        String os = scanner.next();
        System.out.print("Laptop availability = ");
        String availability = scanner.next();
        while(NumberUtils.toInt(availability, 0) == 0){
            System.out.println("The availability should be numeric! Please try again");
            System.out.println("Availability: ");
            availability = scanner.next();
        }

        createLaptop(productsMap, name, price, ram, os, availability);
        System.out.println("Laptop added successfully");
    }

    private static void createLaptop(Map<String, List<Map<Integer, Product>>> productsMap, String name, String price, String ram, String os, String availability) throws IOException {
        AtomicInteger numberOfLinesInFile = new AtomicInteger(FileOpeations.getNumberOfLinesInFile());
        String finalPrice = price;
        String finalRam = ram;
        String finalAvailability = availability;

        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopList.stream().forEach(map -> map.put(numberOfLinesInFile.get(), new Laptop()
                        .withName(name)
                        .withPrice(Double.parseDouble(finalPrice))
                        .withRam(Integer.parseInt(finalRam))
                        .withOs(os)
                        .withAvailability(Integer.parseInt(finalAvailability))));
            }
        });
    }

    private static void printInitialMenu() {
        System.out.println("Welcome to the store.");
        System.out.println("----- Options -----");
        System.out.println("1. See laptops -> prints all the available laptops in the store.");
        System.out.println("2. Add laptops -> adds a new laptop to the store.");
        System.out.println("3. Remove laptop -> remove a laptop from the store by it's id. To see the id's of the laptops use the command at line 1.");
        System.out.println("4. Update laptop -> update a property of a specified laptop.");
        System.out.println("5. Quit -> close the application.");
    }

    private static int checkIfInputIsInteger(Scanner scanner, String userInput, Map<String, List<Map<Integer, Product>>> productsMap) {
        List<Integer> indexList = new ArrayList<>();
        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopList.stream().forEach(laptopMap -> {
                    indexList.addAll(laptopMap.keySet());
                });
            }
        });
        // TODO : iese din while in cazul in care introduc un Integer, dar care nu este in lista de indexuri
        while((NumberUtils.toInt(userInput, 0) == 0) && !indexList.contains(userInput)) {
            System.out.println(NumberUtils.toInt(userInput, 0) == 0);
            System.out.println(!indexList.contains(userInput));
            System.out.println("The position you entered doesn't exist or your value was not numeric. Please check step 1 to see the available positions.");
            System.out.print("Position: ");
            userInput = scanner.next();
        }
        return Integer.parseInt(userInput);
    }

    private static void saveLaptopToFile(String name, double price, int ram, String os, int unitatiDisponibile) throws IOException {
        FileWriter fileWriter = new FileWriter(LAPTOPS_FILE,true);
        fileWriter.append("\n" + name + "," + price + "," + ram + "," + os + "," + unitatiDisponibile);
        fileWriter.close();
    }

}

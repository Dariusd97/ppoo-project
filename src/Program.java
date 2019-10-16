import models.Laptop;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {
        final Scanner scanner = new Scanner(System.in);
        List<Laptop> laptopList = new ArrayList<>();
        readLaptopProducts(laptopList);
        printInitialMenu();
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "See laptops":
                    printLaptops(laptopList);
                    break;
                case "Add laptop":
                    addLaptop(scanner, laptopList);
                    break;
                case "Remove laptop":
                    removeLaptop(scanner, laptopList);
                    break;
                case "Update laptop":
                    System.out.print("Specify the position of the laptop : ");
                    //TODO: validation
                    int position = scanner.nextInt();
                    System.out.print("Specify the filed of the laptop that you want to update: [Name, Price, Ram, OS, availability] : ");
                    enterLaptopValues(scanner, laptopList, position);
                    break;
                case "Quit":
                    scanner.close();
                    break;
                default:
                    System.out.println("Option doesn't exist. Please try again!");
                    break;
            }
        }
    }

    private static void enterLaptopValues(Scanner scanner, List<Laptop> laptopList, int position) {
        boolean isModified = true;
        while(isModified) {
            String filed = scanner.next();
            switch (filed) {
                case "Name":
                    System.out.print("New name: ");
                    String newLaptopName = scanner.next();
                    laptopList.get(position).setName(newLaptopName);
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
                    laptopList.get(position).setPrice(Double.parseDouble(newLaptopPrice));
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
                    laptopList.get(position).setRam(Integer.parseInt(newLaptopRam));
                    isModified = false;
                    System.out.println("You successfully updated the ram!");
                    break;
                case "OS":
                    System.out.print("New os: ");
                    String newLaptopOs = scanner.next();
                    laptopList.get(position).setOs(newLaptopOs);
                    isModified = false;
                    System.out.println("You successfully updated the os!");
                    break;
                case "availability":
                    System.out.print("New availability: ");
                    String newLaptopDisponibilitate = scanner.next();
                    while(NumberUtils.toInt(newLaptopDisponibilitate, 0) == 0){
                        System.out.println("The availability should be numeric. Please try again!");
                        System.out.println("Availability : ");
                        newLaptopDisponibilitate = scanner.next();
                    }
                    laptopList.get(position).setAvailability(Integer.parseInt(newLaptopDisponibilitate));
                    isModified = false;
                    System.out.println("You successfully updated the availability!");
                    break;
                default:
                    System.out.println("Wrong filed, please choose one from [Name, Price, Ram, OS, availability]");
                    break;
            }
        }
    }

    private static void removeLaptop(Scanner scanner, List<Laptop> laptopList) {
        System.out.print("Specify the position of the laptop : ");
        String userinput = scanner.next();
        int laptopPosition = checkIfInputIsInteger(scanner, userinput, laptopList);
        System.out.println("Laptop " + laptopList.get(laptopPosition).getName() + " was successfully deleted!");
        laptopList.remove(laptopPosition);
    }

    private static void addLaptop(Scanner scanner, List<Laptop> laptopList) {
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
        laptopList.add(new Laptop(name, Double.parseDouble(price),Integer.parseInt(ram),os,Integer.parseInt(availability)));
        System.out.println("Laptop added successfully");
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

    private static int checkIfInputIsInteger(Scanner scanner, String userInput, List<Laptop> laptopList) {
        List<Integer> indexList = new ArrayList<>();
        for(int i = 0; i < laptopList.size(); i++){
            indexList.add(i);
        }
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

    private static void printLaptops(List<Laptop> laptopList) {
        for (int i = 0; i < laptopList.size(); i++) {
            Laptop laptop = laptopList.get(i);
            System.out.println("index: " + i + " , " + " " +laptop);
        }
    }

    private static void saveLaptopToFile(String name, double price, int ram, String os, int unitatiDisponibile) throws IOException {
        FileWriter fileWriter = new FileWriter("laptops.txt",true);
        fileWriter.append("\n" + name + "," + price + "," + ram + "," + os + "," + unitatiDisponibile);
        fileWriter.close();
    }

    private static void readLaptopProducts(List<Laptop> laptopList) throws IOException {
        FileReader fileReader = new FileReader("laptops.txt");
        Scanner fileScanner = new Scanner(fileReader);
        fileScanner.useDelimiter(",");

        while(fileScanner.hasNextLine()){
            String[] laptopDetails = fileScanner.nextLine().split(",");
            Laptop laptop = new Laptop();
            laptop.setName(laptopDetails[0]);
            laptop.setPrice(Double.parseDouble(laptopDetails[1]));
            laptop.setRam(Integer.parseInt(laptopDetails[2]));
            laptop.setOs(laptopDetails[3]);
            laptop.setAvailability(Integer.parseInt(laptopDetails[4]));
            laptopList.add(laptop);
        }
        fileReader.close();
        fileScanner.close();
    }
}

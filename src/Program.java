import models.Laptop;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {
        final Scanner scanner = new Scanner(System.in);
        List<Laptop> laptopList = new ArrayList<>();
        readLaptopProducts(laptopList);
        System.out.println("Welcome to the store.");
        System.out.println("----- Options -----");
        System.out.println("1. See laptops -> prints all the available laptops in the store.");
        System.out.println("2. Add laptops -> adds a new laptop to the store.");
        System.out.println("3. Remove laptop -> remove a laptop from the store by it's id. To see the id's of the laptops use the command at line 1.");
        System.out.println("4. Update laptop -> update a property of a specified laptop.");
        System.out.println("5. Quit -> close the application.");
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "See laptops":
                    printLaptops(laptopList);
                    break;
                case "Add laptop":
                    System.out.print("Laptop name = ");
                    String name = scanner.next();
                    System.out.print("Laptop price = ");
                    double price = scanner.nextDouble();
                    System.out.print("Laptop ram = ");
                    int ram = scanner.nextInt();
                    System.out.print("Laptop os = ");
                    String os = scanner.next();
                    System.out.print("Laptop availability = ");
                    int availability = scanner.nextInt();
                    laptopList.add(new Laptop(name, price,ram,os,availability));
                    System.out.println("Laptop added successfully");
                    //saveLaptopToFile(name, price, ram, os, availability);// move to quit case
                    break;
                case "Remove laptop":
                    System.out.print("Specify the position of the laptop : ");
                    String userinput = scanner.next();
                    int laptopPosition = checkIfInputIsInteger(scanner, userinput);
                    System.out.println("Laptop " + laptopList.get(laptopPosition).getName() + " was successfully deleted!");
                    laptopList.remove(laptopPosition);
                    break;
                case "Update laptop":
                    System.out.print("Specify the position of the laptop : ");
                    int position = scanner.nextInt();
                    System.out.print("Specify the filed of the laptop that you want to update: [Name, Price, Ram, OS, availability] : ");
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
                                double newLaptopPrice = scanner.nextDouble();
                                laptopList.get(position).setPrice(newLaptopPrice);
                                isModified = false;
                                System.out.println("You successfully updated the price!");
                                break;
                            case "Ram":
                                System.out.print("New ram: ");
                                int newLaptopRam = scanner.nextInt();
                                laptopList.get(position).setRam(newLaptopRam);
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
                                int newLaptopDisponibilitate = scanner.nextInt();
                                laptopList.get(position).setAvailability(newLaptopDisponibilitate);
                                isModified = false;
                                System.out.println("You successfully updated the availability!");
                                break;
                            default:
                                System.out.println("Wrong filed, please choose one from [Name, Price, Ram, OS, availability]");
                                break;
                        }
                    }
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

    private static int checkIfInputIsInteger(Scanner scanner, String laptopPosition) {
        while(NumberUtils.toInt(laptopPosition, 0) == 0) {
            System.out.println("The position should be numberic! Please try again");
            System.out.print("Position: ");
            laptopPosition = scanner.next();
        }

        return Integer.parseInt(laptopPosition);
    }

    private static void printLaptops(List<Laptop> laptopList) {
        for (int i = 0; i < laptopList.size(); i++) {
            Laptop laptop = laptopList.get(i);
            System.out.println(i + " " +laptop);
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

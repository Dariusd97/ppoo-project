package operations;

import models.Laptop;
import models.Phone;
import models.Product;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static constants.Constants.LAPTOP_CATEGORY;
import static constants.Constants.PHONE_CATEGORY;

public class LaptopOperations {

    public static final String POSITION_STRING = "Position: ";
    public static final String PRICE_STRING = "Price: ";

    private LaptopOperations() {
    }

    public static void printProducts(Map<String, List<Map<Integer, Product>>> productsMap, String productCategory) {
        productsMap.forEach((category,productList) -> {
            if(productCategory.equals(category)) {
                productList.forEach(mapWithProducts -> mapWithProducts.forEach((index, product) -> {
                    System.out.println("index: " + index + " , " + product);
                }));
            }
        });
    }

    // LAPTOP UPDATE

    public static void updateLaptop(Scanner scanner, Map<String, List<Map<Integer, Product>>> productsMap) {
        System.out.print("Specify the position of the laptop : ");
        String position = scanner.nextLine();
        List<Integer> indexList = getListOfIdexes(productsMap, LAPTOP_CATEGORY);
        while(!indexList.contains(Utils.toInt(position, -1))){
            System.out.println("The position you entered doesn't exist or your value was not numeric. Please check step 1 to see the available positions.");
            System.out.print(POSITION_STRING);
            position = scanner.nextLine();
        }
        System.out.print("Specify the filed of the laptop that you want to update: [Name, Price, Ram, OS, Availability] : ");
        enterLaptopValues(scanner, productsMap, Integer.parseInt(position));
    }

    private static List<Integer> getListOfIdexes(Map<String, List<Map<Integer, Product>>> productsMap, String productCategory) {
        List<Integer> indexList = new ArrayList<>();
        productsMap.forEach((category, productList) -> {
            if(productCategory.equals(category)){
                productList.forEach(productMap -> indexList.addAll(productMap.keySet()));
            }
        });
        return indexList;
    }

    private static void enterLaptopValues(Scanner scanner, Map<String, List<Map<Integer, Product>>> productsMap, int position) {
        boolean isModified = true;
        while(isModified) {
            String filed = scanner.nextLine();
            switch (filed) {
                case "Name":
                    System.out.print("New name: ");
                    String newLaptopName = scanner.nextLine();
                    updateLaptopName(productsMap, position, newLaptopName);
                    isModified = false;
                    System.out.println("You successfully updated the name!");
                    break;
                case "Price":
                    System.out.print("New price: ");
                    String newLaptopPrice = scanner.nextLine();
                    while(Utils.toDouble(newLaptopPrice, -1) == -1){
                        System.out.println("The price should be numeric and not negative. Please try again!");
                        System.out.print(PRICE_STRING);
                        newLaptopPrice = scanner.nextLine();
                    }
                    updateLaptopPrice(productsMap, position, newLaptopPrice);
                    isModified = false;
                    System.out.println("You successfully updated the price!");
                    break;
                case "Ram":
                    System.out.print("New ram: ");
                    String newLaptopRam = scanner.nextLine();
                    while(Utils.toInt(newLaptopRam, -1) == -1){
                        System.out.println("The ram should be numeric and not negative. Please try again!");
                        System.out.print("Ram : ");
                        newLaptopRam = scanner.nextLine();
                    }
                    updateLaptopRam(productsMap, position, newLaptopRam);
                    isModified = false;
                    System.out.println("You successfully updated the ram!");
                    break;
                case "OS":
                    System.out.print("New os: ");
                    String newLaptopOs = scanner.nextLine();
                    updateLaptopOS(productsMap, position, newLaptopOs);
                    isModified = false;
                    System.out.println("You successfully updated the os!");
                    break;
                case "Availability":
                    System.out.print("New availability: ");
                    String newLaptopAcailability = scanner.nextLine();
                    while(Utils.toInt(newLaptopAcailability, -1) == -1){
                        System.out.println("The availability should be numeric and not negative. Please try again!");
                        System.out.println("Availability : ");
                        newLaptopAcailability = scanner.nextLine();
                    }
                    updateLaptopAvailability(productsMap, position, newLaptopAcailability);
                    isModified = false;
                    System.out.println("You successfully updated the availability!");
                    break;
                default:
                    System.out.println("Wrong filed, please choose one from [Name, Price, Ram, OS, availability]");
                    break;
            }
        }
    }

    private static void updateLaptopOS(Map<String, List<Map<Integer, Product>>> productsMap, int position, String newLaptopOs) {
        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopList.forEach(map -> map.forEach((index, product) -> {
                    if(index.equals(position)){
                        Laptop laptop = (Laptop) product;
                        laptop.setOs(newLaptopOs);
                        map.put(position,laptop);
                    }
                }));
            }
        });
    }

    private static void updateLaptopAvailability(Map<String, List<Map<Integer, Product>>> productsMap, int position, String newLaptopAcailability) {
        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopList.forEach(map -> map.forEach((index, product) -> {
                    if(index.equals(position)){
                        Laptop laptop = (Laptop) product;
                        laptop.setAvailability(Integer.parseInt(newLaptopAcailability));
                        map.put(position,laptop);
                    }
                }));
            }
        });
    }

    private static void updateLaptopName(Map<String, List<Map<Integer, Product>>> productsMap, int position, String newLaptopName) {
        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopList.forEach(map -> map.forEach((index, product) -> {
                    if(index.equals(position)){
                        Laptop laptop = (Laptop) product;
                        laptop.setName(newLaptopName);
                        map.put(position,laptop);
                    }
                }));
            }
        });
    }

    private static void updateLaptopPrice(Map<String, List<Map<Integer, Product>>> productsMap, int position, String newLaptopPrice) {
        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopList.forEach(map -> map.forEach((index, product) -> {
                    if(index.equals(position)){
                        Laptop laptop = (Laptop) product;
                        laptop.setPrice(Double.parseDouble(newLaptopPrice));
                        map.put(position,laptop);
                    }
                }));
            }
        });
    }

    private static void updateLaptopRam(Map<String, List<Map<Integer, Product>>> productsMap, int position, String newLaptopRam) {
        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopList.forEach(map -> map.forEach((index, product) -> {
                    if(index.equals(position)){
                        Laptop laptop = (Laptop) product;
                        laptop.setRam(Integer.parseInt(newLaptopRam));
                        map.put(position,laptop);
                    }
                }));
            }
        });
    }

    // LAPTOP REPORT

    public static void printLaptopReport(Map<String, List<Map<Integer, Product>>> productsMap, DecimalFormat decimalFormat) {
        AtomicInteger numberOfElements = new AtomicInteger();
        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopList.forEach(map -> numberOfElements.set(map.size()));
            }
        });

        System.out.println(numberOfElements);

        Laptop[] laptopsArray = new Laptop[numberOfElements.get()];
        int[] numberOfAvailableProductsArray = new int[numberOfElements.get()];
        double[] laptopsValueArray = new double[numberOfElements.get()];
        Set<Laptop> availableBrands = new HashSet<>();

        double totalLaptopsValue = 0.0;
        int totalAvailableLaptops = 0;

        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopList.forEach(map -> {
                    int index = 0;
                    for(Map.Entry<Integer, Product> entry : map.entrySet()){
                        laptopsArray[index++] = (Laptop) entry.getValue();
                        availableBrands.add((Laptop) entry.getValue());
                    }
                });
            }
        });
        for(int i = 0; i < laptopsArray.length; i++){
            numberOfAvailableProductsArray[i] = laptopsArray[i].getAvailability();
            laptopsValueArray[i] = laptopsArray[i].getPrice();
        }
        totalLaptopsValue = getTotalLaptopsValue(laptopsValueArray, totalLaptopsValue);
        totalAvailableLaptops = getTotalAvailableLaptops(numberOfAvailableProductsArray, totalAvailableLaptops);
        printReport(decimalFormat, availableBrands, totalLaptopsValue, totalAvailableLaptops);
    }

    private static int getTotalAvailableLaptops(int[] numberOfAvailableProductsArray, int totalAvailableLaptops) {
        for(int i = 0; i < numberOfAvailableProductsArray.length; i++){
            totalAvailableLaptops += numberOfAvailableProductsArray[i];
        }
        return totalAvailableLaptops;
    }

    private static double getTotalLaptopsValue(double[] laptopsValueArray, double totalLaptopsValue) {
        for(int i = 0; i < laptopsValueArray.length; i++){
            totalLaptopsValue += laptopsValueArray[i];
        }
        return totalLaptopsValue;
    }
    private static void printReport(DecimalFormat decimalFormat, Set<Laptop> availableBrands, double totalLaptopsValue, int totalAvailableLaptops) {
        System.out.println(" ------------------ Summary report ------------------");
        System.out.println("                    --------------                   ");
        System.out.println("| Total value             = " + decimalFormat.format(totalLaptopsValue));
        System.out.println("| Total available laptops = " + totalAvailableLaptops);
        System.out.print("| Available brands        =");
        availableBrands.forEach(laptop -> System.out.print(" " + laptop.getName() + " "));
        System.out.println();
    }

    // CREATE LAPTOP

    public static void addLaptop(Scanner scanner, Map<String, List<Map<Integer, Product>>> productsMap) {
        System.out.print("Laptop name = ");
        String name = scanner.nextLine();
        System.out.print("Laptop price = ");
        String price = scanner.nextLine();
        while(Utils.toDouble(price,-1) == -1){
            System.out.println("The price should be numberic and not negative! Please try again");
            System.out.print(PRICE_STRING);
            price = scanner.nextLine();
        }
        System.out.print("Laptop ram = ");
        String ram = scanner.nextLine();
        while(Utils.toInt(ram,-1) == -1){
            System.out.println("The ram should be numberic and not negative! Please try again");
            System.out.print("Ram: ");
            ram = scanner.nextLine();
        }
        System.out.print("Laptop os = ");
        String os = scanner.nextLine();
        System.out.print("Laptop availability = ");
        String availability = scanner.nextLine();
        while(Utils.toInt(availability, -1) == -1){
            System.out.println("The availability should be numeric and not negative! Please try again");
            System.out.println("Availability: ");
            availability = scanner.nextLine();
        }

        createLaptop(productsMap, name, price, ram, os, availability);
        System.out.println("Laptop added successfully");
    }

    private static void createLaptop(Map<String, List<Map<Integer, Product>>> productsMap, String name, String price, String ram, String os, String availability) {
        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                Set<Integer> indexesSet = laptopList.get(0).keySet();
                List<Integer> indexesList = new ArrayList<>(indexesSet);
                int indexOfNewLaptop;
                if(indexesList.size() != 0){
                    indexOfNewLaptop = indexesList.get(indexesList.size() -1) + 1;
                }else{
                    indexOfNewLaptop = 0;
                }
                laptopList.forEach(map -> map.put(indexOfNewLaptop, new Laptop()
                        .withName(name)
                        .withPrice(Double.parseDouble(price))
                        .withRam(Integer.parseInt(ram))
                        .withOs(os)
                        .withAvailability(Integer.parseInt(availability))));
            }
        });
    }

    // DELETE LAPTOP

    public static void removeLaptop(Scanner scanner, Map<String, List<Map<Integer, Product>>> productsMap) {
        System.out.print("Specify the position of the laptop : ");
        String userinput = scanner.nextLine();
        int laptopPosition = checkIfInputIsInteger(scanner, userinput, productsMap, LAPTOP_CATEGORY);
        final String[] laptopName = {""};
        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopList.forEach(map -> laptopName[0] = String.valueOf(map.get(laptopPosition)));
            }
        });
        System.out.println();
        System.out.println("Laptop " + laptopName[0] + " was successfully deleted!");
        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopList.forEach(map -> map.remove(laptopPosition));
            }
        });
    }

    private static int checkIfInputIsInteger(Scanner scanner, String userInput, Map<String, List<Map<Integer, Product>>> productsMap, String productCategory) {
        List<Integer> indexList = getListOfIdexes(productsMap, productCategory);
        while(!indexList.contains(Utils.toInt(userInput, -1))){
            System.out.println("The position you entered doesn't exist or your value was not numeric. Please check step 1 to see the available positions.");
            System.out.print(POSITION_STRING);
            userInput = scanner.nextLine();
        }
        return Integer.parseInt(userInput);
    }

    // CREATE PHONE

    public static void addPhone(Scanner scanner, Map<String, List<Map<Integer, Product>>> productsMap) {
        System.out.print("Phone name = ");
        String name = scanner.nextLine();
        System.out.print("Phone price = ");
        String price = scanner.nextLine();
        while(Utils.toDouble(price,-1) == -1){
            System.out.println("The price should be numberic and not negative! Please try again");
            System.out.print(PRICE_STRING);
            price = scanner.nextLine();
        }
        System.out.print("Phone storage = ");
        String storage = scanner.nextLine();
        while(Utils.toInt(storage,-1) == -1){
            System.out.println("The storage should be numberic and not negative! Please try again");
            System.out.print("Ram: ");
            storage = scanner.nextLine();
        }
        System.out.print("Phone brand = ");
        String brand = scanner.nextLine();
        System.out.print("Phone availability = ");
        String availability = scanner.nextLine();
        while(Utils.toInt(availability, -1) == -1){
            System.out.println("The availability should be numeric and not negative! Please try again");
            System.out.println("Availability: ");
            availability = scanner.nextLine();
        }
        createPhone(productsMap, name, price, storage, brand,availability);
        System.out.println("Phone added successfully");
    }

    private static void createPhone(Map<String, List<Map<Integer, Product>>> productsMap, String name, String price, String storage, String brand, String availability) {
        productsMap.forEach((category, phonesList) -> {
            if(PHONE_CATEGORY.equals(category)){
                Set<Integer> indexesSet = phonesList.get(0).keySet();
                List<Integer> indexesList = new ArrayList<>(indexesSet);
                int indexOfNewPhone;
                if(indexesList.size() != 0 ) {
                    indexOfNewPhone = indexesList.get(indexesList.size() - 1) + 1;
                }else{
                    indexOfNewPhone = 1;
                }
                phonesList.forEach(map -> map.put(indexOfNewPhone, new Phone()
                        .withName(name)
                        .withPrice(Double.parseDouble(price))
                        .withStorage(Integer.parseInt(storage))
                        .withBrand(brand)
                        .withAvailability(Integer.parseInt(availability))));
            }
        });
    }

    // DELETE PHONE

    public static void removePhone(Scanner scanner, Map<String, List<Map<Integer, Product>>> productsMap) {
        System.out.print("Specify the position of the phone : ");
        String userinput = scanner.nextLine();
        int phonePostition = checkIfInputIsInteger(scanner, userinput, productsMap, PHONE_CATEGORY);
        final String[] phoneName = {""};
        productsMap.forEach((category, phoneList) -> {
            if(PHONE_CATEGORY.equals(category)){
                phoneList.forEach(map -> phoneName[0] = String.valueOf(map.get(phonePostition)));
            }
        });
        System.out.println("Phone " + phoneName[0] + " was successfully deleted!");
        productsMap.forEach((category, phoneList) -> {
            if(PHONE_CATEGORY.equals(category)){
                phoneList.forEach(map -> map.remove(phonePostition));
            }
        });
    }

    // UPDATE PHONE

    public static void updatePhone(Scanner scanner, Map<String, List<Map<Integer, Product>>> productsMap) {
        System.out.print("Specify the position of the phone : ");
        String position = scanner.nextLine();
        List<Integer> indexList = getListOfIdexes(productsMap, PHONE_CATEGORY);
        while(!indexList.contains(Utils.toInt(position, -1))){
            System.out.println("The position you entered doesn't exist or your value was not numeric. Please check step 6 to see the available positions.");
            System.out.print(POSITION_STRING);
            position = scanner.nextLine();
        }
        System.out.print("Specify the filed of the laptop that you want to update: [Name, Price, Storage, Brand, Availability] : ");
        enterPhoneValues(scanner, productsMap, Integer.parseInt(position));
    }

    private static void enterPhoneValues(Scanner scanner, Map<String, List<Map<Integer, Product>>> productsMap, int position) {
        boolean isModified = true;
        while(isModified) {
            String filed = scanner.nextLine();
            switch (filed) {
                case "Name":
                    System.out.print("New name: ");
                    String newPhoneName = scanner.nextLine();
                    updatePhoneName(productsMap, position, newPhoneName);
                    isModified = false;
                    System.out.println("You successfully updated the name!");
                    break;
                case "Price":
                    System.out.print("New price: ");
                    String newPhonePrice = scanner.nextLine();
                    while(Utils.toDouble(newPhonePrice, -1) == -1){
                        System.out.println("The price should be numeric and not numeric. Please try again!");
                        System.out.print(PRICE_STRING);
                        newPhonePrice = scanner.nextLine();
                    }
                    updatePhonePrice(productsMap, position, newPhonePrice);
                    isModified = false;
                    System.out.println("You successfully updated the price!");
                    break;
                case "Storage":
                    System.out.print("New storage: ");
                    String newPhoneStorage = scanner.nextLine();
                    while(Utils.toInt(newPhoneStorage, -1) == -1){
                        System.out.println("The storage should be numeric and not negative. Please try again!");
                        System.out.println("Storage : ");
                        newPhoneStorage = scanner.nextLine();
                    }
                    updatePhoneStorage(productsMap, position, newPhoneStorage);
                    isModified = false;
                    System.out.println("You successfully updated the storage!");
                    break;
                case "Brand":
                    System.out.print("New brand: ");
                    String newPhoneBrand = scanner.nextLine();
                    updatePhoneBrand(productsMap, position, newPhoneBrand);
                    isModified = false;
                    System.out.println("You successfully updated the brand!");
                    break;
                case "Availability":
                    System.out.print("New availability: ");
                    String newPhoneAvailability = scanner.nextLine();
                    while(Utils.toInt(newPhoneAvailability, -1) == -1){
                        System.out.println("The availability should be numeric and not negative. Please try again!");
                        System.out.println("Availability : ");
                        newPhoneAvailability = scanner.nextLine();
                    }
                    updatePhoneAvailability(productsMap, position, newPhoneAvailability);
                    isModified = false;
                    System.out.println("You successfully updated the availability!");
                    break;
                default:
                    System.out.println("Wrong filed, please choose one from [Name, Price, Storage, Brand]");
                    break;
            }
        }
    }

    private static void updatePhoneName(Map<String, List<Map<Integer, Product>>> productsMap, int position, String newPhoneName) {
        productsMap.forEach((category, phoneList) -> {
            if(PHONE_CATEGORY.equals(category)){
                phoneList.forEach(map -> map.forEach((index, product) -> {
                    if(index.equals(position)){
                        Phone phone = (Phone) product;
                        phone.setName(newPhoneName);
                        map.put(position,phone);
                    }
                }));
            }
        });
    }

    private static void updatePhonePrice(Map<String, List<Map<Integer, Product>>> productsMap, int position, String newPhonePrice) {
        productsMap.forEach((category, phoneList) -> {
            if(PHONE_CATEGORY.equals(category)){
                phoneList.forEach(map -> map.forEach((index, product) -> {
                    if(index.equals(position)){
                        Phone phone = (Phone) product;
                        phone.setPrice(Double.parseDouble(newPhonePrice));
                        map.put(position,phone);
                    }
                }));
            }
        });
    }

    private static void updatePhoneStorage(Map<String, List<Map<Integer, Product>>> productsMap, int position, String newPhoneStorage) {
        productsMap.forEach((category, phoneList) -> {
            if(PHONE_CATEGORY.equals(category)){
                phoneList.forEach(map -> map.forEach((index, product) -> {
                    if(index.equals(position)){
                        Phone phone = (Phone) product;
                        phone.setStorage(Integer.parseInt(newPhoneStorage));
                        map.put(position,phone);
                    }
                }));
            }
        });
    }

    private static void updatePhoneBrand(Map<String, List<Map<Integer, Product>>> productsMap, int position, String newPhoneBrand) {
        productsMap.forEach((category, phoneList) -> {
            if(PHONE_CATEGORY.equals(category)){
                phoneList.forEach(map -> map.forEach((index, product) -> {
                    if(index.equals(position)){
                        Phone phone = (Phone) product;
                        phone.setBrand(newPhoneBrand);
                        map.put(position,phone);
                    }
                }));
            }
        });
    }

    private static void updatePhoneAvailability(Map<String, List<Map<Integer, Product>>> productsMap, int position, String newPhoneAcailability) {
        productsMap.forEach((category, phoneList) -> {
            if(PHONE_CATEGORY.equals(category)){
                phoneList.forEach(map -> map.forEach((index, product) -> {
                    if(index.equals(position)){
                        Phone phone = (Phone) product;
                        phone.setAvailability(Integer.parseInt(newPhoneAcailability));
                        map.put(position,phone);
                    }
                }));
            }
        });
    }

    // PHONE REPORT

    public static void printPhoneReport(Map<String, List<Map<Integer, Product>>> productsMap, DecimalFormat decimalFormat) {

        AtomicInteger numberOfElements = new AtomicInteger();
        productsMap.forEach((category, laptopList) -> {
            if(PHONE_CATEGORY.equals(category)){
                laptopList.forEach(map -> numberOfElements.set(map.size()));
            }
        });

        Phone[] phonesArray = new Phone[numberOfElements.get()];
        int[] numberOfAvailableProductsArray = new int[numberOfElements.get()];
        double[] phonesValueArray = new double[numberOfElements.get()];
        Set<Phone> availableBrands = new HashSet<>();

        double totalPhonesValue = 0.0;
        int totalAvailablePhones = 0;

        productsMap.forEach((category, phoneList) -> {
            if(PHONE_CATEGORY.equals(category)){
                phoneList.forEach(map ->{
                    int index = 0 ;
                    for(Map.Entry<Integer, Product> entry : map.entrySet()){
                        phonesArray[index++] = (Phone) entry.getValue();
                        availableBrands.add((Phone) entry.getValue());
                    }
                });
            }
        });
        for(int i = 0; i < phonesArray.length; i++){
            numberOfAvailableProductsArray[i] = phonesArray[i].getAvailability();
            phonesValueArray[i] = phonesArray[i].getPrice();
        }
        totalPhonesValue = getTotalPhonesValue(phonesValueArray, totalPhonesValue);
        totalAvailablePhones = getTotalAvailablePhones(numberOfAvailableProductsArray, totalAvailablePhones);
        printPhoneReport(decimalFormat, availableBrands, totalPhonesValue, totalAvailablePhones);
    }

    private static int getTotalAvailablePhones(int[] numberOfAvailableProductsArray, int totalAvailablePhones) {
        for(int i = 0; i < numberOfAvailableProductsArray.length; i++){
            totalAvailablePhones += numberOfAvailableProductsArray[i];
        }
        return totalAvailablePhones;
    }

    private static double getTotalPhonesValue(double[] phonesValueArray, double totalPhonesValue) {
        for(int i = 0; i < phonesValueArray.length; i++){
            totalPhonesValue += phonesValueArray[i];
        }
        return totalPhonesValue;
    }
    private static void printPhoneReport(DecimalFormat decimalFormat, Set<Phone> availableBrands, double totalPhonesValue, int totalAvailablePhones) {
        System.out.println(" ------------------ Summary report ------------------");
        System.out.println("                    --------------                   ");
        System.out.println("| Total value             = " + decimalFormat.format(totalPhonesValue));
        System.out.println("| Total available phones  = " + totalAvailablePhones);
        System.out.print("| Available brands        =");
        availableBrands.forEach(phone -> System.out.print(" " + phone.getBrand() + " "));
        System.out.println();
    }
}

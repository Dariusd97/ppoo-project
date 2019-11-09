package operations;

import models.Laptop;
import models.Phone;
import models.Product;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static constants.Constants.*;

public class FileOpeations {

    private FileOpeations() {
    }

    public static void writeLaptopsToFile(Map<String, List<Map<Integer, Product>>> productsMap) throws IOException {
        try(FileWriter fileWriter = new FileWriter("/Users/dadobre/Applications/projects/ppoo-project/src/resources/laptops.txt")) {
            fileWriter.write("");
            productsMap.forEach((category, productList) -> {
                if (LAPTOP_CATEGORY.equals(category)) {
                    productList.forEach(map -> map.forEach((index, product) -> {
                        Laptop laptop = (Laptop) product;
                        try {
                            fileWriter.write(index + "," + laptop.getName() + "," + laptop.getPrice() + "," + laptop.getRam() + "," + laptop.getOs() + "," + laptop.getAvailability() + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }));
                }
            });
        }
    }

    public static void writePhonesToFile(Map<String, List<Map<Integer, Product>>> productsMap) throws IOException {
        try(FileWriter fileWriter = new FileWriter("/Users/dadobre/Applications/projects/ppoo-project/src/resources/phones.txt")) {
            fileWriter.write("");
            productsMap.forEach((category, productList) -> {
                if (PHONE_CATEGORY.equals(category)) {
                    productList.forEach(map -> map.forEach((index, product) -> {
                        Phone phone = (Phone) product;
                        try {
                            fileWriter.write(index + "," + phone.getName() + "," + phone.getPrice() + "," + "," + phone.getStorage() + "," + phone.getBrand() + "," + phone.getAvailability() + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }));
                }
            });
        }
    }

    public static void readProductsFromFiles(Map<String, List<Map<Integer, Product>>> productsMap) throws IOException {
        List<Map<Integer, Product>> laptopList = new ArrayList<>();
        Map<Integer, Product> laptopsMap = new HashMap<>();

        List<Map<Integer, Product>> phoneList = new ArrayList<>();
        Map<Integer, Product> phoneMap = new HashMap<>();

        FileReader fileReaderLaptop;
        Path laptopFilePath = Paths.get("/Users/dadobre/Applications/projects/ppoo-project/src/resources/laptops.txt");

        try{
            Files.createFile(laptopFilePath);
            fileReaderLaptop = new FileReader(laptopFilePath.toFile());
        }catch(FileAlreadyExistsException e){
            fileReaderLaptop = new FileReader(laptopFilePath.toFile());
        }
        try(Scanner fileScannerLaptop = new Scanner(fileReaderLaptop)) {
            fileScannerLaptop.useDelimiter(",");

            while (fileScannerLaptop.hasNextLine()) {
                String[] laptopDetails = fileScannerLaptop.nextLine().split(",");
                Laptop laptop = new Laptop();
                try {
                    laptop.setName(laptopDetails[1]);
                    laptop.setPrice(Double.parseDouble(laptopDetails[2]));
                    laptop.setRam(Integer.parseInt(laptopDetails[3]));
                    laptop.setOs(laptopDetails[4]);
                    laptop.setAvailability(Integer.parseInt(laptopDetails[5]));
                    laptopsMap.put(Integer.valueOf(laptopDetails[0]), laptop);
                }catch(Exception e){

                }
            }
            laptopList.add(laptopsMap);
            productsMap.put(LAPTOP_CATEGORY, laptopList);
        }


        FileReader fileReaderPhone;
        Path phoneFilePath = Paths.get("/Users/dadobre/Applications/projects/ppoo-project/src/resources/phones.txt");

        try{
            Files.createFile(phoneFilePath);
            fileReaderPhone = new FileReader(phoneFilePath.toFile());
        }catch(FileAlreadyExistsException e){
            fileReaderPhone = new FileReader(phoneFilePath.toFile());
        }
        try(Scanner fileScannerPhone = new Scanner(fileReaderPhone)) {
            fileScannerPhone.useDelimiter(",");

            while (fileScannerPhone.hasNextLine()) {
                String[] phoneDetails = fileScannerPhone.nextLine().split(",");
                Phone phone = new Phone();
                try {
                    phone.setName(phoneDetails[1]);
                    phone.setPrice(Double.parseDouble(phoneDetails[2]));
                    phone.setStorage(Integer.parseInt(phoneDetails[3]));
                    phone.setBrand(phoneDetails[4]);
                    phone.setAvailability(Integer.parseInt(phoneDetails[5]));
                    phoneMap.put(Integer.valueOf(phoneDetails[0]), phone);
                }catch(Exception e){

                }

            }
            phoneList.add(phoneMap);
            productsMap.put(PHONE_CATEGORY, phoneList);
        }
        fileReaderLaptop.close();
        fileReaderPhone.close();
    }

}

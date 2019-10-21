package operations;

import models.Laptop;
import models.Phone;
import models.Product;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static constants.Constants.*;

public class FileOpeations {

    public static void writeLaptopsToFile(Map<String, List<Map<Integer, Product>>> productsMap) throws IOException {
        FileWriter fileWriter = new FileWriter(LAPTOPS_FILE);
        fileWriter.write("");
        productsMap.forEach((category, productList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                productList.forEach(map -> {
                    map.forEach((index, product) -> {
                        Laptop laptop = (Laptop) product;
                        try {
                            fileWriter.write(index + "," + laptop.getName() + "," + laptop.getPrice() + "," + laptop.getRam() + "," + laptop.getOs() + "," + laptop.getAvailability() + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                });
            }
        });

        fileWriter.close();
    }

    public static void writePhonesToFile(Map<String, List<Map<Integer, Product>>> productsMap) throws IOException {
        FileWriter fileWriter = new FileWriter(PHONES_FILE);
        fileWriter.write("");
        productsMap.forEach((category, productList) -> {
            if(PHONE_CATEGORY.equals(category)){
                productList.forEach(map -> {
                    map.forEach((index, product) -> {
                        Phone phone = (Phone) product;
                        try {
                            fileWriter.write(index + "," + phone.getName() + "," + phone.getPrice() + "," + phone.getPrice() + "," + phone.getStorage() + "," + phone.getBrand() + "," + phone.getAvailability() + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                });
            }
        });

        fileWriter.close();
    }

    public static void readProductsFromFiles(Map<String, List<Map<Integer, Product>>> productsMap) throws IOException {
        List<Map<Integer, Product>> laptopList = new ArrayList<>();
        Map<Integer, Product> laptopsMap = new HashMap<>();

        List<Map<Integer, Product>> phoneList = new ArrayList<>();
        Map<Integer, Product> phoneMap = new HashMap<>();

        FileReader fileReaderLaptop = new FileReader(LAPTOPS_FILE);
        Scanner fileScannerLaptop = new Scanner(fileReaderLaptop);
        fileScannerLaptop.useDelimiter(",");

        while(fileScannerLaptop.hasNextLine()){
            String[] laptopDetails = fileScannerLaptop.nextLine().split(",");

            Laptop laptop = new Laptop();
            laptop.setName(laptopDetails[1]);
            laptop.setPrice(Double.parseDouble(laptopDetails[2]));
            laptop.setRam(Integer.parseInt(laptopDetails[3]));
            laptop.setOs(laptopDetails[4]);
            laptop.setAvailability(Integer.parseInt(laptopDetails[5]));

            laptopsMap.put(Integer.valueOf(laptopDetails[0]), laptop);
        }
        laptopList.add(laptopsMap);
        productsMap.put(LAPTOP_CATEGORY, laptopList);


        FileReader fileReaderPhone = new FileReader(PHONES_FILE);
        Scanner fileScannerPhone = new Scanner(fileReaderPhone);
        fileScannerPhone.useDelimiter(",");

        while(fileScannerPhone.hasNextLine()){
            String[] phoneDetails = fileScannerPhone.nextLine().split(",");
            Phone phone = new Phone();
            phone.setName(phoneDetails[1]);
            phone.setPrice(Double.parseDouble(phoneDetails[2]));
            phone.setStorage(Integer.parseInt(phoneDetails[3]));
            phone.setBrand(phoneDetails[4]);
            phone.setAvailability(Integer.parseInt(phoneDetails[5]));
            phoneMap.put(Integer.valueOf(phoneDetails[0]), phone);
        }
        phoneList.add(phoneMap);
        productsMap.put(PHONE_CATEGORY, phoneList);

        fileReaderLaptop.close();
        fileScannerLaptop.close();
        fileReaderPhone.close();
        fileScannerPhone.close();
    }

    public static int getNumberOfLinesInFile(String productCategory) throws IOException {
        FileReader fileReader;
        if(productCategory.equals(PHONE_CATEGORY)){
            fileReader = new FileReader(PHONES_FILE);
        }else{
            fileReader = new FileReader(LAPTOPS_FILE);
        }
        Scanner fileScanner = new Scanner(fileReader);
        fileScanner.useDelimiter(",");
        int numberOfLines = 0;
        while(fileScanner.hasNextLine()){
            fileScanner.nextLine();
            numberOfLines++;
        }
        fileReader.close();
        fileScanner.close();
        return numberOfLines;
    }
}

package operations;

import models.Laptop;
import models.Product;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static constants.Constants.LAPTOPS_FILE;
import static constants.Constants.LAPTOP_CATEGORY;

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

    public static void readLaptopsFromFile(Map<String, List<Map<Integer, Product>>> productsMap) throws IOException {
        List<Map<Integer, Product>> laptopList = new ArrayList<>();
        Map<Integer, Product> laptopsMap = new HashMap<>();

        FileReader fileReader = new FileReader(LAPTOPS_FILE);
        Scanner fileScanner = new Scanner(fileReader);
        fileScanner.useDelimiter(",");

        while(fileScanner.hasNextLine()){
            String[] laptopDetails = fileScanner.nextLine().split(",");
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
        fileReader.close();
        fileScanner.close();
    }

    public static int getNumberOfLinesInFile() throws IOException {
        FileReader fileReader = new FileReader(LAPTOPS_FILE);
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

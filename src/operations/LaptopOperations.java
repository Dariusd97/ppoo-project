package operations;

import models.Laptop;
import models.Product;

import java.util.List;
import java.util.Map;

import static constants.Constants.LAPTOP_CATEGORY;

public class LaptopOperations {



    public static void updateLaptopOS(Map<String, List<Map<Integer, Product>>> productsMap, int position, String newLaptopOs) {
        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopList.forEach(map -> {
                    map.forEach((index, product) -> {
                        if(index.equals(position)){
                            Laptop laptop = (Laptop) product;
                            laptop.setOs(newLaptopOs);
                            map.put(position,laptop);
                        }
                    });

                });
            }
        });
    }

    public static void updateLaptopAvailability(Map<String, List<Map<Integer, Product>>> productsMap, int position, String newLaptopAcailability) {
        String finalNewLaptopAcailability = newLaptopAcailability;
        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopList.forEach(map -> {
                    map.forEach((index, product) -> {
                        if(index.equals(position)){
                            Laptop laptop = (Laptop) product;
                            laptop.setAvailability(Integer.parseInt(finalNewLaptopAcailability));
                            map.put(position,laptop);
                        }
                    });

                });
            }
        });
    }

    public static void updateLaptopName(Map<String, List<Map<Integer, Product>>> productsMap, int position, String newLaptopName) {
        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopList.forEach(map -> {
                   map.forEach((index, product) -> {
                       if(index.equals(position)){
                           Laptop laptop = (Laptop) product;
                           laptop.setName(newLaptopName);
                           map.put(position,laptop);
                       }
                   });

                });
            }
        });
    }

    public static void updateLaptopPrice(Map<String, List<Map<Integer, Product>>> productsMap, int position, String newLaptopPrice) {
        String finalNewLaptopPrice = newLaptopPrice;
        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopList.forEach(map -> {
                    map.forEach((index, product) -> {
                        if(index.equals(position)){
                            Laptop laptop = (Laptop) product;
                            laptop.setPrice(Double.parseDouble(finalNewLaptopPrice));
                            map.put(position,laptop);
                        }
                    });

                });
            }
        });
    }

    public static void updateLaptopRam(Map<String, List<Map<Integer, Product>>> productsMap, int position, String newLaptopRam) {
        String finalNewLaptopRam = newLaptopRam;
        productsMap.forEach((category, laptopList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopList.forEach(map -> {
                    map.forEach((index, product) -> {
                        if(index.equals(position)){
                            Laptop laptop = (Laptop) product;
                            laptop.setRam(Integer.parseInt(finalNewLaptopRam));
                            map.put(position,laptop);
                        }
                    });

                });
            }
        });
    }

    public static void printLaptops(Map<String, List<Map<Integer, Product>>> productsMap) {
        productsMap.forEach((category,laptopsList) -> {
            if(LAPTOP_CATEGORY.equals(category)){
                laptopsList.stream().forEach(laptopsMap -> {
                    laptopsMap.forEach((index, laptop) -> {
                        System.out.println("index: " + index + " , " + laptop);
                    });
                });
            }
        });
    }
}

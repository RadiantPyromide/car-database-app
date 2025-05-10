package com.akimmin.top.car;

import java.util.Scanner;

/**
 * Класс подразумевает наличние методов для работы с объектами типа Car.
 */
public class CarService extends jdbcUtil {
    public static Scanner scanner = new Scanner(System.in);

    @Override
    void manufacturerCounter() {
        super.manufacturerCounter();
    }

    @Override
    void showCarsInManufacturerRange(int min, int max) {
        super.showCarsInManufacturerRange(min, max);
    }

    @Override
    void showCarByType() {
        super.showCarByType();
    }



    @Override
    public void showAllCarMarks() {
        super.showAllCarMarks();
    }

    @Override
    protected void printAllCars() {
        super.printAllCars();
    }

    @Override
    void addCar(String manufacturerName, String carName, float volume, int yearOfManufacturer, String color) {

    }

}

package by.tsarenkov.shop.bean.good;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.characteristic.EBookCharacteristic;
import by.tsarenkov.shop.bean.status.ProductStatus;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class EBook extends Product implements Serializable {
    public static final int ID_CATEGORY = 1;
    private String model;
    private int ROM;
    private int RAM;
    private String typeCardMemory;
    private String typeScreen;
    private int batteryCapacity;
    private double diagonal;
    private boolean WiFi;
    private boolean bluetooth;
    private String formats;


    public EBook(){}

    public EBook(EBookBuilder builder) {
        super(builder.id, builder.brand, builder.count, builder.price, builder.status, builder.path);
        this.model = builder.model;
        this.ROM = builder.ROM;
        this.RAM = builder.RAM;
        this.typeCardMemory = builder.typeCardMemory;
        this.typeScreen = builder.typeScreen;
        this.batteryCapacity = builder.batteryCapacity;
        this.diagonal = builder.diagonal;
        this.formats = builder.formats;
        this.bluetooth = builder.bluetooth;
        this.WiFi = builder.WiFi;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getROM() {
        return ROM;
    }

    public void setROM(int ROM) {
        this.ROM = ROM;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    public String getTypeCardMemory() {
        return typeCardMemory;
    }

    public void setTypeCardMemory(String typeCardMemory) {
        this.typeCardMemory = typeCardMemory;
    }

    public String getTypeScreen() {
        return typeScreen;
    }

    public void setTypeScreen(String typeScreen) {
        this.typeScreen = typeScreen;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }

    public boolean isWiFi() {
        return WiFi;
    }

    public void setWiFi(boolean wiFi) {
        WiFi = wiFi;
    }

    public boolean isBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getFormats() {
        return formats;
    }

    public void setFormats(String formats) {
        this.formats = formats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EBook eBook = (EBook) o;
        return ROM == eBook.ROM && RAM == eBook.RAM
                && batteryCapacity == eBook.batteryCapacity
                && Double.compare(eBook.diagonal, diagonal) == 0
                && WiFi == eBook.WiFi && bluetooth == eBook.bluetooth
                && model.equals(eBook.model)
                && typeCardMemory.equals(eBook.typeCardMemory)
                && typeScreen.equals(eBook.typeScreen)
                && formats.equals(eBook.formats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, ROM, RAM, typeCardMemory
                , typeScreen, batteryCapacity, diagonal, WiFi
                , bluetooth, formats);
    }

    @Override
    public String toString() {
        return "EBook{" +
                "model='" + model + '\'' +
                ", ROM=" + ROM +
                ", RAM=" + RAM +
                ", typeCardMemory='" + typeCardMemory + '\'' +
                ", typeScreen='" + typeScreen + '\'' +
                ", batteryCapacity=" + batteryCapacity +
                ", diagonal=" + diagonal +
                ", WiFi=" + WiFi +
                ", bluetooth=" + bluetooth +
                ", formats='" + formats + '\'' +
                '}' + super.toString();
    }

    public static class EBookBuilder {
        private int id;
        private double price;
        private int count;
        private ProductStatus status;
        private String path;
        private String brand;
        private String model;
        private int ROM;
        private int RAM;
        private String typeCardMemory;
        private String typeScreen;
        private int batteryCapacity;
        private double diagonal;
        private boolean WiFi;
        private boolean bluetooth;
        private String formats;

        public EBookBuilder(int id, String brand,
                            int count, double price,
                            ProductStatus status, String path) {

            this.id = id;
            this.brand = brand;
            this.count = count;
            this.path = path;
            this.price = price;
            this.status = status;
        }

        public EBookBuilder setCharacteristics(Map<String, String> characteristics) {
            this.model = characteristics
                    .get(EBookCharacteristic.MODEL.toString());
            this.ROM = Integer.parseInt(characteristics
                    .get(EBookCharacteristic.ROM.toString()));
            this.RAM = Integer.parseInt(characteristics
                    .get(EBookCharacteristic.RAM.toString()));
            System.out.println(characteristics
                          .get(EBookCharacteristic.RAM.toString()));
            this.typeCardMemory = characteristics
                    .get(EBookCharacteristic.TYPE_CARD_MEMORY.toString());
            this.typeScreen = characteristics
                    .get(EBookCharacteristic.TYPE_SCREEN.toString());
            this.batteryCapacity = Integer.parseInt(characteristics
                    .get(EBookCharacteristic.BATTERY_CAPACITY.toString()));
            this.diagonal = Double.parseDouble(characteristics
                    .get(EBookCharacteristic.DIAGONAL.toString()));
            this.WiFi = characteristics
                    .get(EBookCharacteristic.WIFI.toString()).equals("yes");
            this.bluetooth = "yes".equals(characteristics
                    .get(EBookCharacteristic.BLUETOOTH.toString()));
            this.formats = characteristics
                    .get(EBookCharacteristic.FORMATS.toString());
            return this;
        }

        public EBook getInstance() {
            return new EBook(this);
        }
    }

}

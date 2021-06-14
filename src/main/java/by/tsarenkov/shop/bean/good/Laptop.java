package by.tsarenkov.shop.bean.good;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.characteristic.LaptopCharacteristic;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class Laptop extends Product implements Serializable {
    private String model;
    private String type;
    private double diagonal;
    private String typeProcessor;
    private String serialProcessor;
    private int countCores;
    private int ROM;
    private int RAM;
    private double bluetoothVersion;
    private int countUSBPort;
    private String typeGraphicsCard;
    private String graphicsCard;
    private String screenExtension;
    private static final int idCategory = 3;

    public Laptop() {
    }

    public Laptop(LaptopBuilder builder) {

    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }

    public String getTypeProcessor() {
        return typeProcessor;
    }

    public void setTypeProcessor(String typeProcessor) {
        this.typeProcessor = typeProcessor;
    }

    public String getSerialProcessor() {
        return serialProcessor;
    }

    public void setSerialProcessor(String serialProcessor) {
        this.serialProcessor = serialProcessor;
    }

    public int getCountCores() {
        return countCores;
    }

    public void setCountCores(int countCores) {
        this.countCores = countCores;
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

    public double getBluetoothVersion() {
        return bluetoothVersion;
    }

    public void setBluetoothVersion(double bluetoothVersion) {
        this.bluetoothVersion = bluetoothVersion;
    }

    public int getCountUSBPort() {
        return countUSBPort;
    }

    public void setCountUSBPort(int countUSBPort) {
        this.countUSBPort = countUSBPort;
    }

    public String getTypeGraphicsCard() {
        return typeGraphicsCard;
    }

    public void setTypeGraphicsCard(String typeGraphicsCard) {
        this.typeGraphicsCard = typeGraphicsCard;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public String getScreenExtension() {
        return screenExtension;
    }

    public void setScreenExtension(String screenExtension) {
        this.screenExtension = screenExtension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) return false;
        Laptop laptop = (Laptop) o;
        return Double.compare(laptop.diagonal, diagonal) == 0
                && countCores == laptop.countCores
                && ROM == laptop.ROM && RAM == laptop.RAM
                && Double.compare(laptop.bluetoothVersion, bluetoothVersion) == 0
                && countUSBPort == laptop.countUSBPort
                && model.equals(laptop.model)
                && type.equals(laptop.type)
                && typeProcessor.equals(laptop.typeProcessor)
                && serialProcessor.equals(laptop.serialProcessor)
                && typeGraphicsCard.equals(laptop.typeGraphicsCard)
                && graphicsCard.equals(laptop.graphicsCard)
                && screenExtension.equals(laptop.screenExtension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), model, type, diagonal
                , typeProcessor, serialProcessor, countCores
                , ROM, RAM, bluetoothVersion, countUSBPort
                , typeGraphicsCard, graphicsCard, screenExtension);
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", diagonal=" + diagonal +
                ", typeProcessor='" + typeProcessor + '\'' +
                ", serialProcessor='" + serialProcessor + '\'' +
                ", countCores=" + countCores +
                ", ROM=" + ROM +
                ", RAM=" + RAM +
                ", bluetoothVersion=" + bluetoothVersion +
                ", countUSBPort=" + countUSBPort +
                ", typeGraphicsCard='" + typeGraphicsCard + '\'' +
                ", graphicsCard='" + graphicsCard + '\'' +
                ", screenExtension='" + screenExtension + '\'' +
                "} " + super.toString();
    }

    public static class LaptopBuilder {
        private int id;
        private String brand;
        private String model;
        private String type;
        private double diagonal;
        private String typeProcessor;
        private String serialProcessor;
        private int countCores;
        private int ROM;
        private int RAM;
        private double bluetoothVersion;
        private int countUSBPort;
        private String typeGraphicsCard;
        private String graphicsCard;
        private String screenExtension;

        public LaptopBuilder(int id, Map<String, String> characteristics) {
            this.id = id;
            this.brand = characteristics.get(LaptopCharacteristic.BRAND.toString());
        }

        public Laptop getInstance() {
            return new Laptop(this);
        }
    }
}


package by.tsarenkov.shop.bean.good;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.characteristic.LaptopCharacteristic;
import by.tsarenkov.shop.bean.status.ProductStatus;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class Laptop extends Product implements Serializable {
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

    public Laptop() {
    }

    public Laptop(LaptopBuilder builder) {
        super(builder.id, builder.brand, builder.model,
                builder.count, builder.price,
                builder.status, builder.path);
        this.type = builder.type;
        this.diagonal = builder.diagonal;
        this.typeProcessor = builder.typeProcessor;
        this.serialProcessor = builder.serialProcessor;
        this.ROM = builder.ROM;
        this.RAM = builder.RAM;
        this.bluetoothVersion = builder.bluetoothVersion;
        this.countUSBPort = builder.countUSBPort;
        this.typeGraphicsCard = builder.typeGraphicsCard;
        this.graphicsCard = builder.graphicsCard;
        this.screenExtension = builder.screenExtension;
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
                && type.equals(laptop.type)
                && typeProcessor.equals(laptop.typeProcessor)
                && serialProcessor.equals(laptop.serialProcessor)
                && typeGraphicsCard.equals(laptop.typeGraphicsCard)
                && graphicsCard.equals(laptop.graphicsCard)
                && screenExtension.equals(laptop.screenExtension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, diagonal
                , typeProcessor, serialProcessor, countCores
                , ROM, RAM, bluetoothVersion, countUSBPort
                , typeGraphicsCard, graphicsCard, screenExtension);
    }

    @Override
    public String toString() {
        return "Laptop{" +
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
        private int ROM;
        private int RAM;
        private double bluetoothVersion;
        private int countUSBPort;
        private String typeGraphicsCard;
        private String graphicsCard;
        private String screenExtension;
        private ProductStatus status;
        private String path;
        private double price;
        private int count;



        public LaptopBuilder() {
        }

        public LaptopBuilder setCharacteristics(Map<String, String> characteristics) {
            this.type = characteristics
                    .get(LaptopCharacteristic.TYPE.toString());
            this.diagonal = Double.parseDouble(characteristics
                    .get(LaptopCharacteristic.DIAGONAL.toString()));
            this.typeProcessor = characteristics
                    .get(LaptopCharacteristic.TYPE_PROCESSOR.toString());
            this.serialProcessor = characteristics
                    .get(LaptopCharacteristic.SERIAL_PROCESSOR.toString());
            this.ROM = Integer.parseInt(characteristics
                    .get(LaptopCharacteristic.ROM.toString()));
            this.RAM = Integer.parseInt(characteristics
                    .get(LaptopCharacteristic.RAM.toString()));
            this.bluetoothVersion = Double.parseDouble(characteristics
                    .get(LaptopCharacteristic.BLUETOOTH_VERSION.toString()));
            this.countUSBPort = Integer.parseInt(characteristics
                    .get(LaptopCharacteristic.COUNT_USB_PORT.toString()));
            this.typeGraphicsCard = characteristics
                    .get(LaptopCharacteristic.TYPE_GRAPHICS_CARD.toString());
            this.graphicsCard = characteristics
                    .get(LaptopCharacteristic.GRAPHICS_CARD.toString());
            this.screenExtension = characteristics
                    .get(LaptopCharacteristic.SCREEN_EXTENSION.toString());
            return this;
        }

        public int getId() {
            return id;
        }

        public LaptopBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public String getBrand() {
            return brand;
        }

        public LaptopBuilder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public String getModel() {
            return model;
        }

        public LaptopBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public String getType() {
            return type;
        }

        public LaptopBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public double getDiagonal() {
            return diagonal;
        }

        public LaptopBuilder setDiagonal(double diagonal) {
            this.diagonal = diagonal;
            return this;
        }

        public String getTypeProcessor() {
            return typeProcessor;
        }

        public LaptopBuilder setTypeProcessor(String typeProcessor) {
            this.typeProcessor = typeProcessor;
            return this;
        }

        public String getSerialProcessor() {
            return serialProcessor;
        }

        public LaptopBuilder setSerialProcessor(String serialProcessor) {
            this.serialProcessor = serialProcessor;
            return this;
        }

        public int getROM() {
            return ROM;
        }

        public LaptopBuilder setROM(int ROM) {
            this.ROM = ROM;
            return this;
        }

        public int getRAM() {
            return RAM;
        }

        public LaptopBuilder setRAM(int RAM) {
            this.RAM = RAM;
            return this;
        }

        public double getBluetoothVersion() {
            return bluetoothVersion;
        }

        public LaptopBuilder setBluetoothVersion(double bluetoothVersion) {
            this.bluetoothVersion = bluetoothVersion;
            return this;
        }

        public int getCountUSBPort() {
            return countUSBPort;
        }

        public LaptopBuilder setCountUSBPort(int countUSBPort) {
            this.countUSBPort = countUSBPort;
            return this;
        }

        public String getTypeGraphicsCard() {
            return typeGraphicsCard;
        }

        public LaptopBuilder setTypeGraphicsCard(String typeGraphicsCard) {
            this.typeGraphicsCard = typeGraphicsCard;
            return this;
        }

        public String getGraphicsCard() {
            return graphicsCard;
        }

        public LaptopBuilder setGraphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        public String getScreenExtension() {
            return screenExtension;
        }

        public LaptopBuilder setScreenExtension(String screenExtension) {
            this.screenExtension = screenExtension;
            return this;
        }

        public ProductStatus getStatus() {
            return status;
        }

        public LaptopBuilder setStatus(ProductStatus status) {
            this.status = status;
            return this;
        }

        public String getPath() {
            return path;
        }

        public LaptopBuilder setPath(String path) {
            this.path = path;
            return this;
        }

        public double getPrice() {
            return price;
        }

        public LaptopBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public int getCount() {
            return count;
        }

        public LaptopBuilder setCount(int count) {
            this.count = count;
            return this;
        }

        public Laptop getInstance() {
            return new Laptop(this);
        }
    }
}


package by.tsarenkov.shop.bean.good;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.characteristic.EBookCharacteristic;
import by.tsarenkov.shop.bean.characteristic.LaptopCharacteristic;
import by.tsarenkov.shop.bean.characteristic.SmartphoneCharacteristic;
import by.tsarenkov.shop.bean.status.ProductStatus;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class Smartphone extends Product implements Serializable {

    private String operationSystem;
    private double diagonal;
    private int RAM;
    private int ROM;
    private int countSimCard;
    private int CPU;
    private int countCores;
    private int countPixelFrontCamera;
    private int countPixelBackCamera;

    public Smartphone() {
    }

    public Smartphone(SmartphoneBuilder builder) {
        super(builder.id, builder.brand, builder.model,
                builder.count, builder.price,
                builder.status, builder.path);

        this.operationSystem = builder.operationSystem;
        this.diagonal = builder.diagonal;
        this.ROM = builder.ROM;
        this.RAM = builder.RAM;
        this.countSimCard = builder.countSimCard;
        this.CPU = builder.CPU;
        this.countCores = builder.countCores;
        this.countPixelFrontCamera = builder.countPixelFrontCamera;
        this.countPixelBackCamera = builder.countPixelBackCamera;

    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    public int getROM() {
        return ROM;
    }

    public void setROM(int ROM) {
        this.ROM = ROM;
    }

    public int getCountSimCard() {
        return countSimCard;
    }

    public void setCountSimCard(int countSimCard) {
        this.countSimCard = countSimCard;
    }

    public int getCPU() {
        return CPU;
    }

    public void setCPU(int CPU) {
        this.CPU = CPU;
    }

    public int getCountCores() {
        return countCores;
    }

    public void setCountCores(int countCores) {
        this.countCores = countCores;
    }

    public int getCountPixelFrontCamera() {
        return countPixelFrontCamera;
    }

    public void setCountPixelFrontCamera(int countPixelFrontCamera) {
        this.countPixelFrontCamera = countPixelFrontCamera;
    }

    public int getCountPixelBackCamera() {
        return countPixelBackCamera;
    }

    public void setCountPixelBackCamera(int countPixelBackCamera) {
        this.countPixelBackCamera = countPixelBackCamera;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Smartphone phone = (Smartphone) o;
        return Double.compare(phone.diagonal, diagonal) == 0
                && RAM == phone.RAM
                && ROM == phone.ROM
                && countSimCard == phone.countSimCard
                && CPU == phone.CPU
                && countCores == phone.countCores
                && countPixelFrontCamera == phone.countPixelFrontCamera
                && countPixelBackCamera == phone.countPixelBackCamera
                && operationSystem.equals(phone.operationSystem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationSystem
                , diagonal, RAM, ROM, countSimCard
                , CPU, countCores, countPixelFrontCamera
                , countPixelBackCamera);
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                ", operationSystem='" + operationSystem + '\'' +
                ", diagonal=" + diagonal +
                ", RAM=" + RAM +
                ", ROM=" + ROM +
                ", countSimCard=" + countSimCard +
                ", CPU=" + CPU +
                ", countCores=" + countCores +
                ", countPixelFrontCamera=" + countPixelFrontCamera +
                ", countPixelBackCamera=" + countPixelBackCamera +
                "} " + super.toString();
    }

    public static class SmartphoneBuilder {
        private int id;
        private String brand;
        private double price;
        private int count;
        private ProductStatus status;
        private String path;
        private String model;
        private String operationSystem;
        private double diagonal;
        private int RAM;
        private int ROM;
        private int countSimCard;
        private int CPU;
        private int countCores;
        private int countPixelFrontCamera;
        private int countPixelBackCamera;


        public SmartphoneBuilder() { }

        public int getId() {
            return id;
        }

        public SmartphoneBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public String getBrand() {
            return brand;
        }

        public SmartphoneBuilder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public double getPrice() {
            return price;
        }

        public SmartphoneBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public int getCount() {
            return count;
        }

        public SmartphoneBuilder setCount(int count) {
            this.count = count;
            return this;
        }

        public ProductStatus getStatus() {
            return status;
        }

        public SmartphoneBuilder setStatus(ProductStatus status) {
            this.status = status;
            return this;
        }

        public String getPath() {
            return path;
        }

        public SmartphoneBuilder setPath(String path) {
            this.path = path;
            return this;
        }

        public String getModel() {
            return model;
        }

        public SmartphoneBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public String getOperationSystem() {
            return operationSystem;
        }

        public SmartphoneBuilder setOperationSystem(String operationSystem) {
            this.operationSystem = operationSystem;
            return this;
        }

        public double getDiagonal() {
            return diagonal;
        }

        public SmartphoneBuilder setDiagonal(double diagonal) {
            this.diagonal = diagonal;
            return this;
        }

        public int getRAM() {
            return RAM;
        }

        public SmartphoneBuilder setRAM(int RAM) {
            this.RAM = RAM;
            return this;
        }

        public int getROM() {
            return ROM;
        }

        public SmartphoneBuilder setROM(int ROM) {
            this.ROM = ROM;
            return this;
        }

        public int getCountSimCard() {
            return countSimCard;
        }

        public SmartphoneBuilder setCountSimCard(int countSimCard) {
            this.countSimCard = countSimCard;
            return this;
        }

        public int getCPU() {
            return CPU;
        }

        public SmartphoneBuilder setCPU(int CPU) {
            this.CPU = CPU;
            return this;
        }

        public int getCountCores() {
            return countCores;
        }

        public SmartphoneBuilder setCountCores(int countCores) {
            this.countCores = countCores;
            return this;
        }

        public int getCountPixelFrontCamera() {
            return countPixelFrontCamera;
        }

        public SmartphoneBuilder setCountPixelFrontCamera(int countPixelFrontCamera) {
            this.countPixelFrontCamera = countPixelFrontCamera;
            return this;
        }

        public int getCountPixelBackCamera() {
            return countPixelBackCamera;
        }

        public SmartphoneBuilder setCountPixelBackCamera(int countPixelBackCamera) {
            this.countPixelBackCamera = countPixelBackCamera;
            return this;
        }

        public SmartphoneBuilder setCharacteristics(Map<String, String> characteristics) {
            this.ROM = Integer.parseInt(characteristics
                    .get(SmartphoneCharacteristic.ROM.toString()));
            this.RAM = Integer.parseInt(characteristics
                    .get(SmartphoneCharacteristic.RAM.toString()));
            this.operationSystem = characteristics
                    .get(SmartphoneCharacteristic.OPERATION_SYSTEM.toString());
            this.diagonal = Double.parseDouble(characteristics
                    .get(SmartphoneCharacteristic.DIAGONAL.toString()));
            this.CPU = Integer.parseInt(characteristics
                    .get(SmartphoneCharacteristic.CPU.toString()));
            this.countSimCard = Integer.parseInt(characteristics
                    .get(SmartphoneCharacteristic.COUNT_SIM_CARD.toString()));
            this.countCores = Integer.parseInt(characteristics
                    .get(SmartphoneCharacteristic.COUNT_CORES.toString()));
            this.countPixelFrontCamera = Integer.parseInt(characteristics
                    .get(SmartphoneCharacteristic.FRONT_CAMERA.toString()));
            this.countPixelBackCamera = Integer.parseInt(characteristics
                    .get(SmartphoneCharacteristic.CAMERA.toString()));

            return this;
        }
        public Smartphone getInstance() {
            return new Smartphone(this);
        }
    }
}



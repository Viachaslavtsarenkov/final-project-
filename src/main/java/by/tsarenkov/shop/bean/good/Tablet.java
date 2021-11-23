package by.tsarenkov.shop.bean.good;

import by.tsarenkov.shop.bean.Product;
import by.tsarenkov.shop.bean.characteristic.EBookCharacteristic;
import by.tsarenkov.shop.bean.characteristic.TabletCharacteristic;
import by.tsarenkov.shop.bean.status.ProductStatus;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class Tablet extends Product implements Serializable {
    private String operationSystem;
    private double diagonal;
    private int RAM;
    private int ROM;
    private String typeUSB;
    private String processor;

    public Tablet() {}

    public Tablet(TabletBuilder builder) {
        super(builder.id, builder.brand, builder.model,
                builder.count, builder.price,
                builder.status, builder.path);

        this.operationSystem = builder.operationSystem;
        this.diagonal = builder.diagonal;
        this.RAM = builder.RAM;
        this.ROM = builder.ROM;
        this.typeUSB = builder.typeUSB;
        this.processor = builder.processor;
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


    public String getTypeUSB() {
        return typeUSB;
    }

    public void setTypeUSB(String typeUSB) {
        this.typeUSB = typeUSB;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tablet tablet = (Tablet) o;
        return Double.compare(tablet.diagonal, diagonal) == 0
                && RAM == tablet.RAM && ROM == tablet.ROM
                && Objects.equals(operationSystem, tablet.operationSystem)
                && Objects.equals(typeUSB, tablet.typeUSB)
                && Objects.equals(processor, tablet.processor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), operationSystem,
                diagonal, RAM, ROM, typeUSB, processor);
    }

    public static class TabletBuilder {
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
        private String typeUSB;
        private String processor;

        public TabletBuilder() { }

        public int getId() {
            return id;
        }

        public TabletBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public String getBrand() {
            return brand;
        }

        public TabletBuilder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public double getPrice() {
            return price;
        }

        public TabletBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public int getCount() {
            return count;
        }

        public TabletBuilder setCount(int count) {
            this.count = count;
            return this;
        }

        public ProductStatus getStatus() {
            return status;
        }

        public TabletBuilder setStatus(ProductStatus status) {
            this.status = status;
            return this;
        }

        public String getPath() {
            return path;
        }

        public TabletBuilder setPath(String path) {
            this.path = path;
            return this;
        }

        public String getModel() {
            return model;
        }

        public TabletBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public String getOperationSystem() {
            return operationSystem;
        }

        public TabletBuilder setOperationSystem(String operationSystem) {
            this.operationSystem = operationSystem;
            return this;
        }

        public double getDiagonal() {
            return diagonal;
        }

        public TabletBuilder setDiagonal(double diagonal) {
            this.diagonal = diagonal;
            return this;
        }

        public int getRAM() {
            return RAM;
        }

        public TabletBuilder setRAM(int RAM) {
            this.RAM = RAM;
            return this;
        }

        public int getROM() {
            return ROM;
        }

        public TabletBuilder setROM(int ROM) {
            this.ROM = ROM;
            return this;
        }

        public String getTypeUSB() {
            return typeUSB;
        }

        public TabletBuilder setTypeUSB(String typeUSB) {
            this.typeUSB = typeUSB;
            return this;
        }

        public String getProcessor() {
            return processor;
        }

        public TabletBuilder setProcessor(String processor) {
            this.processor = processor;
            return this;
        }

        public TabletBuilder setCharacteristics(Map<String, String> characteristics) {
            this.ROM = Integer.parseInt(characteristics
                    .get(TabletCharacteristic.ROM.toString()));
            this.RAM = Integer.parseInt(characteristics
                    .get(TabletCharacteristic.RAM.toString()));
            this.operationSystem = characteristics
                    .get(TabletCharacteristic.OPERATION_SYSTEM.toString());
            this.diagonal = Double.parseDouble(characteristics
                    .get(TabletCharacteristic.DIAGONAL.toString()));
            this.typeUSB = characteristics
                    .get(TabletCharacteristic.TYPE_USB.toString());
            this.processor = characteristics
                    .get(TabletCharacteristic.PROCESSOR.toString());

            return this;
        }

        public Tablet getInstance() {
            return new Tablet(this);
        }
    }
}

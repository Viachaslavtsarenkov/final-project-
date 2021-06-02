package by.tsarenkov.shop.bean.good;

import by.tsarenkov.shop.bean.Product;

import java.io.Serializable;
import java.util.Objects;

public class Tablet extends Product implements Serializable {
    private String model;
    private String operationSystem;
    private double diagonal;
    private int RAM;
    private int ROM;
    private boolean threeGModem;
    private String typeUSB;
    private String processor;

    public Tablet() {}

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public boolean isThreeGModem() {
        return threeGModem;
    }

    public void setThreeGModem(boolean threeGModem) {
        this.threeGModem = threeGModem;
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tablet tablet = (Tablet) o;
        return Double.compare(tablet.diagonal, diagonal) == 0
                && RAM == tablet.RAM
                && ROM == tablet.ROM
                && threeGModem == tablet.threeGModem
                && model.equals(tablet.model)
                && operationSystem.equals(tablet.operationSystem)
                && typeUSB.equals(tablet.typeUSB)
                && processor.equals(tablet.processor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, operationSystem, diagonal, RAM, ROM, threeGModem, typeUSB, processor);
    }
}

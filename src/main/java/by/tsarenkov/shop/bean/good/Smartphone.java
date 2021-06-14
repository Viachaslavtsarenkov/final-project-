package by.tsarenkov.shop.bean.good;

import by.tsarenkov.shop.bean.Product;

import java.io.Serializable;
import java.util.Objects;

public class Smartphone extends Product implements Serializable {

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
    private static final int idCategory = 4;

    public Smartphone() {
    }

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
                && model.equals(phone.model)
                && operationSystem.equals(phone.operationSystem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, operationSystem
                , diagonal, RAM, ROM, countSimCard
                , CPU, countCores, countPixelFrontCamera
                , countPixelBackCamera);
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "model='" + model + '\'' +
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
}



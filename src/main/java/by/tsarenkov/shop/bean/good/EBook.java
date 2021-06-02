package by.tsarenkov.shop.bean.good;

import java.io.Serializable;
import java.util.Objects;

public class EBook implements Serializable {

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
}

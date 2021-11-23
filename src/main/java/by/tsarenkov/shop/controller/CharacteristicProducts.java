package by.tsarenkov.shop.controller;

import by.tsarenkov.shop.bean.ProductName;
import by.tsarenkov.shop.bean.characteristic.EBookCharacteristic;
import by.tsarenkov.shop.bean.characteristic.LaptopCharacteristic;
import by.tsarenkov.shop.bean.characteristic.SmartphoneCharacteristic;
import by.tsarenkov.shop.bean.characteristic.TabletCharacteristic;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CharacteristicProducts {

    public static Map<String, String> extractCharacteristic(ProductName name, HttpServletRequest request) {
        Map<String, String> characteristics = new HashMap<>();

        switch (name) {
            case EBOOK:
                EBookCharacteristic[] eBookCharacteristics = EBookCharacteristic.values();
                for (EBookCharacteristic eBook : eBookCharacteristics) {
                    String value = request.getParameter(eBook.toString().toLowerCase());
                    if (value != null) {
                        characteristics.put(eBook.toString(), value);
                    }
                }
                break;
            case TABLET:
                TabletCharacteristic[] tabletCharacteristics = TabletCharacteristic.values();
                for (TabletCharacteristic eBook : tabletCharacteristics) {
                    String value = request.getParameter(eBook.toString().toLowerCase());
                    if (value != null) {
                        characteristics.put(eBook.toString(), value);
                    }
                }
                break;
            case SMARTPHONE:
                SmartphoneCharacteristic[] smartphoneCharacteristics = SmartphoneCharacteristic.values();
                for (SmartphoneCharacteristic smartphone : smartphoneCharacteristics) {
                    String value = request.getParameter(smartphone.toString().toLowerCase());
                    if (value != null) {
                        characteristics.put(smartphone.toString(), value);
                    }
                }
                break;
            case LAPTOP:
                LaptopCharacteristic[] laptopCharacteristics = LaptopCharacteristic.values();
                for (LaptopCharacteristic laptop : laptopCharacteristics) {
                    String value = request.getParameter(laptop.toString().toLowerCase());
                    if (value != null) {
                        characteristics.put(laptop.toString(), value);
                    }
                }
                break;
        }
        return characteristics;
    }
}

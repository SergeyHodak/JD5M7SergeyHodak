package tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.lang.reflect.Type;
import java.util.List;

@Converter
public class AddressConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return new Gson().toJson(attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        Type type = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(dbData, type);
    }
}

//в такий спосіб можна зберігати в базі данних те що нею не підтримуюється, конвертуючи в підтримуючий формата, та в зворотньому напрямку
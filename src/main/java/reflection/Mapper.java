package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {
    public static Map<String, Object> getAsMap(Object object) {
        var objClass = object.getClass();

        Map<String, Object> resMap = new HashMap<>();
        var fields = objClass.getDeclaredFields();

        for (var field: fields) {
            if (!field.isAnnotationPresent(Ignore.class)) {
                var fieldName = field.getName();
                var capitalizedFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                try {
                    var getter = objClass.getDeclaredMethod("get" + capitalizedFieldName);
                    var value = getter.invoke(object);
                    if (value != null) {
                        resMap.put(fieldName, value);
                    }
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    //
                }
            }
        }

        return resMap;
    }
}

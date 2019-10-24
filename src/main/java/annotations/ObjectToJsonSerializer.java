package annotations;

import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.stream.Collectors;

public class ObjectToJsonSerializer {
  private void isSerializable(Object object) {
    if (Objects.isNull(object)) {
      throw new JsonSerializationException("The object to serialize is null");
    }

    Class<?> c = object.getClass();
    if (!c.isAnnotationPresent(JsonSerializable.class)) {
      throw new JsonSerializationException("The class " + c.getSimpleName() + " is not annotated with JsonSerializable");
    }
  }

  private void initializeObject(Object object) throws Exception {
    Class<?> c = object.getClass();
    for (Method method : c.getDeclaredMethods()) {
      if (method.isAnnotationPresent(JsonSerializationInit.class)) {
        method.setAccessible(true);
        method.invoke(object);
      }
    }
  }

  private String getKey(Field field) {
    String value = field.getAnnotation(JsonElement.class).key();
    return value.isEmpty() ? field.getName() : value;
  }

  private String getJSONKeyAndValue(Map.Entry<String, Object> entry) {
    String key = "\"" + entry.getKey() + "\":";
    String value = null;
    if (entry.getValue() != null) {
      if (entry.getValue() instanceof String) {
        value = "\"" + entry.getValue() + "\"";
      } else {
        Object entryValue = entry.getValue();
        Class<?> valueClass = entryValue.getClass();
        if (valueClass.isAnnotationPresent(JsonSerializable.class)) {
          value = convertToJson(entryValue);
        } else {
          value = entry.getValue().toString();
        }
      }
    }
    return key + value;
  }

  private String getJsonString(Object object) throws Exception {
    Class<?> c = object.getClass();
    Map<String, Object> jsonElementsMap = new HashMap<>();
    for (Field field : c.getDeclaredFields()) {
      field.setAccessible(true);
      if (field.isAnnotationPresent(JsonElement.class)) {
        jsonElementsMap.put(getKey(field), field.get(object));
      }
    }

    String jsonString = jsonElementsMap
      .entrySet()
      .stream()
      .map((entry) -> getJSONKeyAndValue(entry))
      .collect(Collectors.joining(","));

    return "{" + jsonString + "}";
  }

  public String convertToJson(Object object) throws JsonSerializationException {
    try {
      isSerializable(object);
      initializeObject(object);
      return getJsonString(object);
    } catch (Exception e) {
      throw new JsonSerializationException(e.getMessage());
    }
  }
}

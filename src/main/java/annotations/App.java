/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package annotations;

public class App {
  public static void main(String[] args) {
    Address address = new Address("Cool Street 123", "12345", "MyCity", "MyState", "MyCountry");
    Person person = new Person("ivan", "sanchez", 29, address, 1234.56f, true);
    ObjectToJsonSerializer serializer = new ObjectToJsonSerializer();
    String personJson = serializer.convertToJson(person);
    System.out.println(personJson);
  }
}

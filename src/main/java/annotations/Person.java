package annotations;

@JsonSerializable
public class Person {
  @JsonElement
  private String firstName;

  @JsonElement
  private String lastName;

  @JsonElement(key = "yearsOld")
  private String age;

  private String address;

  @JsonSerializationInit
  private void upperCaseNames() {
    this.firstName = upperCaseName(this.firstName);
    this.lastName = upperCaseName(this.lastName);
  }

  private String upperCaseName(String name) {
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }

  public Person(String firstName, String lastName, String age, String address) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.address = address;
  }

  public String firstName() {
    return this.firstName;
  }

  public String lastName() {
    return this.lastName;
  }

  public String age() {
    return this.age;
  }

  public String address() {
    return this.address;
  }
}
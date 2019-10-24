package annotations;

@JsonSerializable
public class Person {
  @JsonElement
  private String firstName;

  @JsonElement
  private String lastName;

  @JsonElement(key = "yearsOld")
  private int age;

  @JsonElement
  private float salary;

  @JsonElement
  private Address address;

  @JsonElement
  private boolean alive;

  @JsonElement
  private String nullField;

  @JsonSerializationInit
  private void upperCaseNames() {
    this.firstName = upperCaseName(this.firstName);
    this.lastName = upperCaseName(this.lastName);
  }

  private String upperCaseName(String name) {
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }

  public Person(String firstName, String lastName, int age, Address address, float salary, boolean alive) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.address = address;
    this.salary = salary;
    this.alive = alive;
  }

  // Getters
  public String firstName() {
    return this.firstName;
  }

  public String lastName() {
    return this.lastName;
  }

  public int age() {
    return this.age;
  }

  public Address address() {
    return this.address;
  }

  public float salary() {
    return this.salary;
  }

  public boolean isAlive() {
    return this.alive;
  }
}

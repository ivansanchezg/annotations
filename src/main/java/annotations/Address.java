package annotations;

@JsonSerializable
public class Address {
  @JsonElement
  final private String line1;

  @JsonElement
  final private String line2;

  @JsonElement
  final private String zipCode;

  @JsonElement
  final private String city;

  @JsonElement
  final private String state;

  @JsonElement
  final private String country;

  public Address(String line1, String zipCode, String city, String state, String country) {
    this(line1, null, zipCode, city, state, country);
  }

  public Address(String line1, String line2, String zipCode, String city, String state, String country) {
    this.line1 = line1;
    this.line2 = line2;
    this.zipCode = zipCode;
    this.city = city;
    this.state = state;
    this.country = country;
  }

  public String line1() {
    return this.line1;
  }

  public String line2() {
    return this.line2;
  }

  public String zipCode() {
    return this.zipCode;
  }

  public String city() {
    return this.city;
  }

  public String state() {
    return this.state;
  }

  public String country() {
    return this.country;
  }
}

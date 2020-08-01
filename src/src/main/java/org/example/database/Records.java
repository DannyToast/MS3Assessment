package org.example.database;

public class Records {
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String imageData;
    private String card;
    private String charge;
    private String firstBoolValue;
    private String secondBoolValue;
    private String city;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImageData() { return imageData; }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getFirstBoolValue() {
        return firstBoolValue;
    }

    public void setFirstBoolValue(String firstBoolValue) {
        this.firstBoolValue = firstBoolValue;
    }

    public String getSecondBoolValue() {
        return secondBoolValue;
    }

    public void setSecondBoolValue(String secondBoolValue) {
        this.secondBoolValue = secondBoolValue;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Overriding toString function in Records object to better represent the content of the object for debugging purposes.
     * @return Records object values in a string
     */
    @Override
    public String toString() {
        return "Records{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", imageData='" + imageData + '\'' +
                ", card='" + card + '\'' +
                ", charge='" + charge + '\'' +
                ", firstBoolValue='" + firstBoolValue + '\'' +
                ", secondBoolValue='" + secondBoolValue + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
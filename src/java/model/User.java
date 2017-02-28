
package model;
/**
 *
 * @author suhag
 */
public class User {
    private String fName;
    private String lName;
    private String salutation;
    private String email;
    private String address;
    private String pNumber;
    private String pNumber2;
    private Login login;

    public User(String fName, String lName, String salutation, String email, String address, String pNumber, String pNumber2, Login login) {
        this.fName = fName;
        this.lName = lName;
        this.salutation = salutation;
        this.email = email;
        this.address = address;
        this.pNumber = pNumber;
        this.pNumber2 = pNumber2;
        this.login = login;
    }

    public User(String fName, String lName, String salutation, String email, String address, String pNumber, String pNumber2) {
        this.fName = fName;
        this.lName = lName;
        this.salutation = salutation;
        this.email = email;
        this.address = address;
        this.pNumber = pNumber;
        this.pNumber2 = pNumber2;
    }
    

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    public String getpNumber2() {
        return pNumber2;
    }

    public void setpNumber2(String pNumber2) {
        this.pNumber2 = pNumber2;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

  

    
}

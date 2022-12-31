public class User {
    private String userName;
    private String password;
    private String phoneNumber;
    private boolean isRealtor;

    public User(String userName, String password, String phoneNumber, boolean isRealtor) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isRealtor = isRealtor;
    }
    public User (){}
    public String toString(){
        String output =  "Name: "+ this.userName + ", Phone:" + phoneNumber;
        if (isRealtor())
            output += " (,Real eastae agent)";
        return output;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isRealtor() {
        return isRealtor;
    }

    public void setRealtor(boolean realtor) {
        isRealtor = realtor;
    }
}

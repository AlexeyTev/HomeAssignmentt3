public class Property {
    private String city;
    private String street;
    private float numOfRooms;
    private float price;
    private Integer kindOfProperty;
    private boolean forRent;
    private int propertyNum;
    private Integer floor;
    private User user;

    public Integer getKindOfProperty() {
        return kindOfProperty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isForRent() {
        return forRent;
    }

    public void setForRent(boolean forRent) {
        this.forRent = forRent;
    }

    public int getPropertyNum() {
        return propertyNum;
    }

    public void setPropertyNum(int propertyNum) {
        this.propertyNum = propertyNum;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setKindOfProperty(Integer kindOfProperty) {
        this.kindOfProperty = kindOfProperty;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public float getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(float numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        String output;
        output = this.city+"-"+this.street+" " + this.propertyNum+ "\n";
        switch (this.kindOfProperty){
            case 1 -> output+="Building apartment-";
            case 2 -> output+="Penthouse-";
            case 3-> output+="Private home-";
        }
        if (this.forRent){
            output += " for rent: ";
        }else output += " for sale: ";
        output += "" + this.numOfRooms+" rooms, ";
        if (floor != null){
            output+=this.floor+" floor";
        }
        output += "\n Price:" + this.price +"$" + "\n Contact info: " + this.getUser().getUserName() +" ," + this.getUser().getPhoneNumber();
        if (this.getUser().isRealtor()){
            output+= "(real estate agent)";
        }


        return output;
    }}


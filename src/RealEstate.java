import java.util.Scanner;

public class RealEstate {
    Scanner scanner = new Scanner(System.in);
    private User [] users;
    private Property [] properties;
    private final City [] cities;
    public RealEstate () {
        users = new User[] {
                new User("M", "1234$", "0503306164", true),
                new User("A", "1234$", "0526265823", true)
        };
        cities = new City[]{
                new City("Beer Sheva", "Negev", new String[]{"Yerushalaim", "Tel Aviv"}),
                new City("Haifa", "North", new String[]{"Um rashrash", "Colosco"}),
                new City("Hod Hasharon", "Hashsaron", new String[]{"Einy", "Vayli"}),
                new City("Tel aviv", "Center", new String[]{"Jabotinsky", "Dizzingof"}),
                new City("Ashdod", "South", new String[]{"Eshkolit", "Eshel"}),
                new City("Netivot", "South", new String[]{"Eilat", "Rabin"}),
                new City("Holon", "Center", new String[]{"Begin", "HaBrosh"}),
                new City("Eilat", "Negev", new String[]{"Bialik", "HaZvi"}),
                new City("Sderot", "South", new String[]{"HaZiat", "HaMada"}),
                new City("Rehovot", "Center", new String[]{"Begin", "HaBrosh"}),





        };
    }

    public void printAllProperties(){ //O(n)
        for (int i = 0; i<properties.length;i++){
            System.out.print(i+1 + ")");
            System.out.println(properties[i]);
        }
    }
    public void deleteProperty(User loggedInUser){//O(n)
        System.out.println("What property you want to delete?");
        printProperties(loggedInUser);
        int chosenPropertyToDelete = scanner.nextInt();
        int indexOfPropertyToDelete = searchIndexOfPropertyToDelete(loggedInUser,chosenPropertyToDelete);
        if (indexOfPropertyToDelete!=Constants.NO_PROPERTIES_PUBLISHED){
            Property[]tempArray = new Property[properties.length-1];
            for (int i=0;i<tempArray.length;i++)
            {
                if (i<indexOfPropertyToDelete){
                    tempArray[i]=properties[i];
                }else if (i==indexOfPropertyToDelete){
                    i++;
                    tempArray[i-1]=properties[i];}
            else {tempArray[i-1]=properties[i];
            }
            }
            properties=tempArray;
        }

    }
    private int searchIndexOfPropertyToDelete (User loggedInUser, int chosenProperty){//O(n)
        int propertyIndex=Constants.NO_PROPERTIES_PUBLISHED;
        int userPropertyCount=0;
        for (int i = 0;i<properties.length;i++){
            if (properties[i].getUser()==loggedInUser){
                userPropertyCount++;
                if (userPropertyCount==chosenProperty){
                    propertyIndex=i;
                }
            }
        }
        if (userPropertyCount==0){
            System.out.println("U havent posted any properties");
        }
        return propertyIndex;
    }
    public User login (){//O(n)
        User loggedInUser;
        String loggedInUserName;
        String loggedInPassword;
        int userIndexInArray;
        do {
            System.out.println("User name:");
            loggedInUserName = scanner.nextLine();
        }while (loggedInUserName.equals(""));
        do {
            System.out.println("Password:");
            loggedInPassword = scanner.nextLine();
        }while (loggedInPassword.equals(""));
            userIndexInArray=checkIfValidToLogin(loggedInUserName,loggedInPassword);
            if (userIndexInArray==Constants.INVALID_TO_LOGIN){
                loggedInUser=null;
            }else loggedInUser=users[userIndexInArray];

        return loggedInUser;
    }
    private int checkIfValidToLogin (String userName, String password){//O(n)
        int userLoginIndex=Constants.INVALID_TO_LOGIN;
        for (int i=0;i<this.users.length;i++){
            if (this.users[i].getUserName().equals(userName) && this.users[i].getPassword().equals(password)){
                userLoginIndex=i;
            break;}
        }
        return userLoginIndex;
    }
    public void createUser() {
        User newUser = new User();
        setUserNameIfAvailable(newUser);
        setPasswordIfValid(newUser);
        setPhoneNumberIfValid(newUser);
        setIfUserRealtor(newUser);
        users = insertNewUser(newUser,users);
    }
    private void setUserNameIfAvailable(User newUser){
        String userName;
        do {
            System.out.println("Insert your desired user name:");
            userName = scanner.nextLine();
            if (checkIfUserNameAvailable(userName)){
                newUser.setUserName(userName);
            }else System.out.println("This username is not available");
        }while (newUser.getUserName()==null);
    }
    private boolean checkIfUserNameAvailable(String userName){
        boolean isAvailable = true;
        if (users!=null) {
            for (int i=0; i <users.length;i++){
                if (users[i].getUserName().equals(userName)){
                    isAvailable=false;
                    break;
                }
            }
        }
        return isAvailable;
    }
    private void setPasswordIfValid (User newUser){
        String password;
        do {
            System.out.println("""
                    Create strong password\s
                     Strong password is:\s
                     1.At least 5 characters\s
                     2.Must contain one of those: $,%,_\s
                     3.Must contain at least 1 digit""");
            password = scanner.nextLine();
            if (checkIfValidPassword(password))
                newUser.setPassword(password);
        }while (newUser.getPassword()==null);
    }
    private boolean checkIfValidPassword (String password){
        boolean isValid = false;
        if (password.length()>=Constants.MIN_PASS_LENGTH){
            if (password.contains("$")||password.contains("%")||password.contains("_")){
                if (password.matches(".*\\d+.*")){
                    isValid = true;
                }else System.out.println("Must contain at least 1 digit");
            }else System.out.println("Must contain one of the signs: $,%,_");
        }else System.out.println("Must be at least 5 characters long");
        return isValid;
    }
    private void setPhoneNumberIfValid (User newUser){
        String phoneNumber;
        do {
            System.out.println("Enter phone number: \n 1.Must start with 05 \n 2.Must be 10 digits long");
            phoneNumber= scanner.nextLine();
            if (checkIfPhoneNumberValid(phoneNumber))
                newUser.setPhoneNumber(phoneNumber);
        }while (newUser.getPhoneNumber()==null);
    }
    private boolean checkIfPhoneNumberValid (String phoneNumber){
        boolean isValid = false;
        if (phoneNumber.length()==10){
            if (phoneNumber.startsWith(Constants.PHONE_NUMBER_STARTING_DIGITS)){
                isValid=true;
            }else System.out.println("Must start with 05");
        }else System.out.println("Must be 10 digits long");
        return isValid;
    }
    private void setIfUserRealtor (User newUser){
        int isRealtor;
        do{
        System.out.println("Insert 1 if you are a real estate agent \n Insert 2 if you are not");
        isRealtor=scanner.nextInt();
    }while (isRealtor>Constants.NOT_REALTOR || isRealtor < Constants.IS_REALTOR);
        if (isRealtor==Constants.IS_REALTOR)
            newUser.setRealtor(true);
        else newUser.setRealtor(false);
    }
    private User[] insertNewUser (User newUser, User [] users){
        int userAmtIndex;
        if (users==null){
            userAmtIndex=0;
        }else {
            userAmtIndex=users.length;
        }
            User [] tempArray = new User[userAmtIndex+1];
        if (tempArray.length != 1) {
            for (int i = 0; i < users.length; i++) {
                tempArray[i] = users[i];
            }
        }
        tempArray[userAmtIndex]=newUser;
        return tempArray;
    }

    public void printProperties(User currentUser){ //O(n)
        int amountPublishedByUser=0;
        for (int i=0;i<this.properties.length;i++){
            if (this.properties[i].getUser()==currentUser){
                amountPublishedByUser++;
                System.out.println(amountPublishedByUser + "." +this.properties[i]);
            }
        }
        System.out.println("You have posted total of: "+amountPublishedByUser);
    }
    public boolean publishNewProperty(User currentUser){
        boolean validToPublish=true;
        int maxAmountToPublish;
        if (currentUser.isRealtor())
        {
            maxAmountToPublish=Constants.MAX_AMOUNT_REALTOR-1;
        }else {
            maxAmountToPublish=Constants.MAX_AMOUNT_NOT_REALTOR-1;
        }
        if (properties!=null) {
            int publishedByUserCounter = 0;
            for (int i = 0; i < properties.length; i++) {
                if (properties[i].getUser() == currentUser && publishedByUserCounter < maxAmountToPublish)
                    publishedByUserCounter++;
                else if (publishedByUserCounter>=maxAmountToPublish){
                    System.out.println("You already have: " + (publishedByUserCounter+1)+
                            " active publishes so you maxed out. Delete old properties in order to be available to post new one");
                    validToPublish = false;
                    break;
                }
            }
        }
        if (validToPublish) {
            Property newProperty = new Property();
            newProperty.setUser(currentUser);
            String cityName;
            System.out.println("Enter the city name from the least below:");
            for (int i = 0; i < cities.length; i++) {
                System.out.println(cities[i].getCityName());
            }
            do {
                cityName = scanner.nextLine();
            }while (cityName.equals(""));

            int cityIndex = isCityValid(cityName);
            if (cityIndex != Constants.NOT_VALID_CITY) {
                newProperty.setCity(cityName);
            } else {
                System.out.println("Error the city is not in our database");
                validToPublish = false;
            }
            if (validToPublish) {
                System.out.println("Enter the street from the list below:");
                String[] validStreets = cities[cityIndex].getStreets();
                for (int i = 0; i < validStreets.length; i++) {
                    System.out.println(validStreets[i]);
                }
                String streetName = scanner.nextLine();
                if (isStreetValid(streetName, validStreets)) {
                    newProperty.setStreet(streetName);
                } else validToPublish = false;
                if (validToPublish) {
                    newProperty = setAllDataForProperty(newProperty);
                }
                if (validToPublish) {
                    properties = insertNewProperty(newProperty);
                }
            }
        }




        return validToPublish;
    }
    private Property setAllDataForProperty(Property newProperty){
        System.out.println("Enter the property type: \n 1.Apartment in building \n 2.Penthouse \n 3.Private");
        int propertyType=scanner.nextInt();
        if (checkPropertyType(propertyType)) {
            newProperty.setKindOfProperty(propertyType);
            if (propertyType!=Constants.PRIVATE_HOME){
            newProperty.setFloor(setFloorFromUser());}
            newProperty.setNumOfRooms(setNumOfRoomsFromUser());
            newProperty.setForRent(setForRentFromUser());
            newProperty.setPrice(setPriceFromUser());
            newProperty.setPropertyNum(setPropertyNumFromUser());
        }
        return newProperty;
    }
    private Property[] insertNewProperty(Property newProperty){
        int totalAmountOfProperties;
        if (this.properties==null){
            totalAmountOfProperties=0;
        }else {
            totalAmountOfProperties=this.properties.length;
        }
        Property[]tempArray = new Property[totalAmountOfProperties+1];
        if (tempArray.length!=1){
        for (int i=0; i<this.properties.length;i++){
            tempArray[i]=this.properties[i];
        }
        }
        tempArray[totalAmountOfProperties]=newProperty;
        return tempArray;
    }
    private int setPropertyNumFromUser(){
        System.out.println("Enter the property number");
        int propertyNum = scanner.nextInt();
        return propertyNum;
    }
    private float setPriceFromUser(){
        float price;
        do {
            System.out.println("What is the price");
            price= scanner.nextFloat();
        }while (price<0);
        return price;
    }
    private boolean setForRentFromUser(){
        boolean forRent=true;
        int userChoice;
        do {
            System.out.println("Is the apartment for rent \n 1.yes \n 2.no");
            userChoice = scanner.nextInt();
        }while (userChoice>2 || userChoice<1);
        if (userChoice==2)
            forRent=false;

        return forRent;
    }
    private float setNumOfRoomsFromUser(){
        float numOfRooms;
        do {
            System.out.println("Enter number of rooms");
            numOfRooms = scanner.nextFloat();
        }while (numOfRooms<1);
        return numOfRooms;
    }
    private int setFloorFromUser(){
        int floor;
        do {
            System.out.println("Enter the floor number");
            floor = scanner.nextInt();
        }while (floor<0);
        return floor;
    }
    private boolean checkPropertyType(int propertyType){
        boolean isValidType=true;
        if (propertyType<Constants.BUILDING_APARTMENT || propertyType>Constants.PRIVATE_HOME){
            isValidType=false;
        }
        return isValidType;
    }
    private boolean isStreetValid(String streetName, String [] cityStreets){
        boolean isValid = false;
        for (int i=0; i<cityStreets.length;i++){
            if (cityStreets[i].equals(streetName)){
                isValid=true;
                break;
            }
        }
        return isValid;
    }
    private int isCityValid (String city){
        int cityIndex=Constants.NOT_VALID_CITY;
        for (int i=0;i<cities.length;i++){
            if (cities[i].getCityName().equals(city)){
                cityIndex=i;
                break;
            }
        }
        return cityIndex;
    }
    public Property[] search(){ //O(n)
        int[] filteredPropertiesIndex=null;
        if (properties!=null) {
            filteredPropertiesIndex = new int[this.properties.length];
            int rentFilter;
            do {
                System.out.println(Constants.FOR_RENT + ")for rent \n " + Constants.FOR_SALE + ")for sale \n" + Constants.IRRELEVANT_FILTER + ")irrelevant");
                rentFilter = scanner.nextInt();
            } while (rentFilter != Constants.IRRELEVANT_FILTER && (rentFilter > 2 || rentFilter < 1));
            if (rentFilter != Constants.IRRELEVANT_FILTER) {
                filteredPropertiesIndex = indexOfRentByFilter(rentFilter);

            }
            int kindFilter;
            do {
                System.out.println(Constants.BUILDING_APARTMENT + ")Apartment Building \n" + Constants.PENTHOUSE + ")Penthouse \n" + Constants.PRIVATE_HOME + ")Private home \n " + Constants.IRRELEVANT_FILTER + ")irrelevant");
                kindFilter = scanner.nextInt();
            } while (kindFilter != Constants.IRRELEVANT_FILTER && (kindFilter > 3 || kindFilter < 1));
            if (kindFilter != Constants.IRRELEVANT_FILTER) {
                filteredPropertiesIndex = indexOfFilterByKind(kindFilter, filteredPropertiesIndex);

            }
            int numberOfRoomsRequired;
            do {
                System.out.println("How many rooms? (if irrelevant insert " + Constants.IRRELEVANT_FILTER + ")");
                numberOfRoomsRequired = scanner.nextInt();
            } while (numberOfRoomsRequired < 1 && numberOfRoomsRequired != Constants.IRRELEVANT_FILTER);
            if (numberOfRoomsRequired != Constants.IRRELEVANT_FILTER) {
                filteredPropertiesIndex = indexOfFilterByRoom(numberOfRoomsRequired, filteredPropertiesIndex);

            }
            System.out.println("If you want to filter by price insert (" + Constants.RELEVANT + ") if it is irrelevant for you insert (" + Constants.IRRELEVANT_FILTER + ")");
            int relevantFilter = scanner.nextInt();
            if (relevantFilter == Constants.RELEVANT) {
                System.out.println("Enter the minimum price range:");
                int minPriceRange = scanner.nextInt();
                System.out.println("Enter the maximum price range:");
                int maxPriceRange = scanner.nextInt();
                filteredPropertiesIndex = indexOfFilterByPrice(minPriceRange, maxPriceRange, filteredPropertiesIndex);
            }
        }
        if (filteredPropertiesIndex!=null){
            Property[] filtered = new Property[filteredPropertiesIndex.length];
            for (int i = 0; i < filtered.length; i++) {
                filtered[i] = properties[filteredPropertiesIndex[i]];
            }}

        return filtered;

    }

    private int [] indexOfFilterByPrice (int min, int max, int [] indexes){ //O(n)
        int filteredCounter=0;
        int [] indexToReturn;
        for (int i = 0; i <indexes.length;i++){
            if (properties[indexes[i]].getPrice()>=min && properties[indexes[i]].getPrice()<=max){
                filteredCounter++;
            }
        }
        int [] filtered = new int [filteredCounter];
        for (int i = 0 ; i < indexes.length;i++){
            if (properties[indexes[i]].getPrice()>=min && properties[indexes[i]].getPrice()<=max){
                filteredCounter--;
                filtered[filteredCounter]=indexes[i];
            }
        }
        indexToReturn=filtered;
        return indexToReturn;

    }
    private int [] indexOfFilterByRoom (int rooms, int [] indexes){ //O(n)
        int filteredCounter =0;
        int [] indexToReturn;
        for (int i = 0 ; i<indexes.length;i++){
            if (properties[indexes[i]].getNumOfRooms()==rooms){
                filteredCounter++;
            }
        }
        int [] filtered = new int [filteredCounter];
        for (int i = 0;i<indexes.length;i++){
            if (properties[indexes[i]].getNumOfRooms()==rooms){
                filteredCounter--;
                filtered[filteredCounter]=indexes[i];
            }
        }
        indexToReturn=filtered;
        return indexToReturn;
    }
    private int [] indexOfFilterByKind(int kind, int [] indexes){ //O(n)
        int [] indexToReturn;
        int filteredByKind =0 ;
        for (int i = 0 ; i<indexes.length ; i++){
            if (properties[indexes[i]].getKindOfProperty()==kind){
                filteredByKind++;
            }
        }
        int [] filtered = new int [filteredByKind];
        for (int i = 0; i <indexes.length;i++){
            if (properties[indexes[i]].getKindOfProperty()==kind){
                filteredByKind--;
                filtered[filteredByKind]=indexes[i];
            }
        }
        indexToReturn=filtered;
        return indexToReturn;
    }


    private int [] indexOfRentByFilter (int filter){ // O(n)
        int amountForRent=0;
        int amountForSale=0;
            for (int i=0;i<this.properties.length;i++){
                if (this.properties[i].isForRent()){
                    amountForRent++;
                }else amountForSale++;
            }
            int [] indexForRent = new int[amountForRent];
            int [] indexForSale = new int [amountForSale];
           for (int i =0;i<properties.length;i++){
               if (properties[i].isForRent()){
                   amountForRent-=1;
                   indexForRent[amountForRent]=i;
               }else {
                   amountForSale-=1;
                   indexForSale[amountForSale]=i;
               }
           }
           int [] indexForReturn;
        if (filter==Constants.FOR_RENT) {
            indexForReturn=indexForRent;
        }else indexForReturn=indexForSale;
        return indexForReturn;
    }}





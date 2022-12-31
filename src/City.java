import java.util.Arrays;

public class City {
    private String cityName;
    private String geographicalArea;
    private String [] streets;

    public City(String cityName, String geographicalArea, String[] streets) {
        this.cityName = cityName;
        this.geographicalArea = geographicalArea;
        this.streets = streets;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getGeographicalArea() {
        return geographicalArea;
    }

    public void setGeographicalArea(String geographicalArea) {
        this.geographicalArea = geographicalArea;
    }

    public String[] getStreets() {
        return streets;
    }

    public void setStreets(String[] streets) {
        this.streets = streets;
    }

    @Override
    public String toString() {
        return "" + cityName + '\'' +
                ", geographicalArea='" + geographicalArea + '\'' +
                ", streets=" + Arrays.toString(streets);
    }
}

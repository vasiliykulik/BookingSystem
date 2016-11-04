package ua.goit.booking.entity;


public class Hotel {
    private long id;
    private String hotelName;
    private String cityName;
   // private RoomDAO roomDao;

    public Hotel(long id, String hotelName, String cityName) {
        this.id = id;
        this.hotelName = hotelName;
        this.cityName = cityName;
        //this.roomDAO = new RoomDAO;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

   /* public RoomDAO getRoomDAO() {
        return roomDAO;
    }*/


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        return id == hotel.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}

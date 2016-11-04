package ua.goit.booking.entity.hotel;


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

        if (id != hotel.id) return false;
        if (hotelName != null ? !hotelName.equals(hotel.hotelName) : hotel.hotelName != null) return false;
        return cityName != null ? cityName.equals(hotel.cityName) : hotel.cityName == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (hotelName != null ? hotelName.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        return result;
    }
}

package academic.example.com.academicsfinder;

import java.io.Serializable;

/**
 * Created by mani on 14/12/2017.
 */

public class Academics implements Serializable {

    int id;
    String name;
    String contactInfo;
    String address;
    String mLat;
    String mLong;
    String type;
    String sector;
    String gender;
    String fee;
    String admissionDate;
    String isFav;
    String [] route;


    public void Academics(int id, String name, String contactInfo, String address,
                          String mLat, String mLong, String type , String sector
                          , String gender, String fee, String admissionDate,
                          String isFav, String[] route
    ){

        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
        this.mLat = mLat;
        this.mLong = mLong;
        this.type = type;
        this.sector = sector;
        this.gender = gender;
        this.fee = fee;
        this.admissionDate = admissionDate;
        this.isFav = isFav;
        this.route = route;

    }
}

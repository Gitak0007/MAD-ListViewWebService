package academic.example.com.academicsfinder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Helper extends  SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AcademicDB";

    public Helper(Context context) {


        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_TABLE = "CREATE TABLE Academics ( " +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "name varchar(100) NULL,"+
                "contactInfo varchar(20) NULL,"+
                "address varchar(255) NULL,"+
                "mLat varchar(50) NULL,"+
                "mLong varchar(50) NULL,"+
                "type varchar(1) NULL,"+
                "sector varchar(1) NULL,"+
                "gender varchar(1) NULL,"+
                "fee varchar(20) NULL,"+
                "admissionDate varchar(12) NULL, "+
                "isFav varchar(1) NULL,"+
                "route varchar(200) NULL "+

                ")";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Academics");
        this.onCreate(db);
    }

}













//

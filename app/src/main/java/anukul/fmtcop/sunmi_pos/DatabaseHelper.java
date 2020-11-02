package anukul.fmtcop.sunmi_pos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_name = "SunmiUser.db";
    public static final String table_name = "SunmiUser_table";
    public static final String col_1 = "id";
    public static final String col_2 = "Email";
    public static final String col_3 = "Password";
    public static final String col_4 = "Businessname";
    public static final String col_5 = "PhoneNumber";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_name,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS SunmiUser_table(id INTEGER PRIMARY KEY AUTOINCREMENT,Email TEXT, Password TEXT, Businessname TEXT, PhoneNumber INTEGER  )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public boolean AddSunmiUser(String Email, String Password, String Businessname, Integer PhoneNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,Email);
        contentValues.put(col_3,Password);
        contentValues.put(col_4,Businessname);
        contentValues.put(col_5,PhoneNumber);
        long result = db.insert(table_name,null,contentValues);
        if (result==0){
            return false;
        }else {
            return  true;
        }
    }

    public boolean LoginApp(String Email,String Password) {
        String sql = "Select count(*) from SunmiUser_table where Email='" + Email + "' and Password='" + Password + "'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        statement.close();

        if (l == 1) {
            return true;
        } else {
            return false;
        }
    }

}


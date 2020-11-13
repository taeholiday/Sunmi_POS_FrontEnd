package anukul.fmtcop.sunmi_pos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class register_Activity extends AppCompatActivity {
    DatabaseHelper dbhelper;
    EditText eEmail, ePasswoed, eBusinessname, ePhoneNumber;
    Button btnregister;
    TextView txtloginpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        //toolbar
        ImageView backIcon = findViewById(R.id.back_icon);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(register_Activity.this,MainActivity.class);
                startActivity(i);
            }
        });

        //edit_text;
        eEmail = findViewById(R.id.editText_addemail);
        ePasswoed = findViewById(R.id.editText_addpassword);
        eBusinessname = findViewById(R.id.editText_businessname);
        ePhoneNumber = findViewById(R.id.editText_phonNumber);
        //Button;
        btnregister = findViewById(R.id.btn_login);
        txtloginpage = findViewById(R.id.txt_registerpage);
        //databaseconf;
        dbhelper = new DatabaseHelper(this);
        //Event Button;
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataEntered();

            }


        });
        txtloginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(register_Activity.this, Login_Activity.class);
                startActivity(i);
            }
        });
    }

    boolean isEmail(EditText text){
        CharSequence eEmail = text.getText().toString();
        return (!TextUtils.isEmpty(eEmail) && Patterns.EMAIL_ADDRESS.matcher(eEmail).matches());
    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    void checkDataEntered() {
        if (isEmail(eEmail) == false){
            Toast.makeText(this,"กรุณากรอกEmail",Toast.LENGTH_SHORT).show();
        }
        else if (isEmpty(ePasswoed)){
            Toast.makeText(this,"กรุณากรอกpassword",Toast.LENGTH_SHORT).show();
        }
        else if (isEmpty(eBusinessname)){
            Toast.makeText(this,"กรุณากรอกชื่อธุรกิจของท่าน",Toast.LENGTH_SHORT).show();
        }
        else if (isEmpty(ePhoneNumber)){
            Toast.makeText(this,"กรุณากรอกเบอร์โทรศัพท์",Toast.LENGTH_SHORT).show();
            ePhoneNumber.setError();
        }
        else{
            int mphonenumber = Integer.parseInt(ePhoneNumber.getText().toString());
            boolean checkadduser = dbhelper.AddSunmiUser( eEmail.getText().toString(), ePasswoed.getText().toString(), eBusinessname.getText().toString(),mphonenumber );
            if (checkadduser){
                Intent intent = new Intent(register_Activity.this, Login_Activity.class);
                startActivity(intent);
            }else {
                ShowInfo("SUNMI POS","ลงทะเบียนไม่สำเร็จ");
            }
        }
    }
    protected void ShowInfo(String title, String msg){
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setCancelable(true);
        ab.setTitle(title);
        ab.setMessage(msg);
        ab.show();
    }
}
package anukul.fmtcop.sunmi_pos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {
    DatabaseHelper dbhelper;
    EditText eEmail, ePassword;
    Button btnlogin;
    TextView txtgoregisterpage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        //edittext
        eEmail = findViewById(R.id.EditText_email);
        ePassword = findViewById(R.id.EditText_password);
        //Button
        btnlogin = findViewById(R.id.btn_login);
        txtgoregisterpage = findViewById(R.id.txt_registerpage);
        //databaseconf;
        dbhelper = new DatabaseHelper(this);
        //EventButton
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailValue = eEmail.getText().toString();
                String passwordValue = ePassword.getText().toString();

                if (dbhelper.LoginApp(emailValue, passwordValue)){
                    Intent intent = new Intent(Login_Activity.this, Sales_Activity.class);
                    startActivity(intent);
                    Toast.makeText(Login_Activity.this, "ลงชื่อเข้าใช้SUNMI POS", Toast.LENGTH_SHORT).show();
                }else{
                    ShowInfo("SUNMI POS","Email หรือ Passwordไม่ถูกต้องกรุณตวจสอบอีกครั้ง");
                }
            }
        });
        txtgoregisterpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_Activity.this, register_Activity.class);
                startActivity(i);
            }
        });
    }
    protected void ShowInfo(String title, String msg){
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setCancelable(true);
        ab.setTitle(title);
        ab.setMessage(msg);
        ab.show();
    }
}
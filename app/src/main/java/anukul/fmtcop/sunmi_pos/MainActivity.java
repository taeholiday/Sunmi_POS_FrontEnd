package anukul.fmtcop.sunmi_pos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int TIME_INTERVAL = 1000; // ประมาณ 1 วิานที
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button register = (Button) findViewById(R.id.btn_registerGo);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, register_Activity.class);
                startActivity(i);
            }
        });

        Button Login = (Button) findViewById(R.id.btn_loginGo);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login_Activity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "กดปุ่ม ย้อนกลับอีกครั้ง เพื่อออกจากแอป", Toast.LENGTH_SHORT).show();
            mBackPressed = System.currentTimeMillis();
        }

    }
}
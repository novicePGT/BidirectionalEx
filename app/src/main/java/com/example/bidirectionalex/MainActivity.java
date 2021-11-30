package com.example.bidirectionalex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioButton radioAdd, radioMul, radioSub, radioDiv;
    // 전역적으로 사용하기위해 전역변수로 라디오버튼들을 선언했다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메인 액티비티"); // 타이틀 설정

        EditText edtNum1 = (EditText) findViewById(R.id.edtNum1);
        EditText edtNum2 = (EditText) findViewById(R.id.edtNum2);
        radioAdd = (RadioButton) findViewById(R.id.radioAdd);
        radioSub = (RadioButton) findViewById(R.id.radioSub);
        radioMul = (RadioButton) findViewById(R.id.radioMul);
        radioDiv = (RadioButton) findViewById(R.id.radioDiv);
        // XML의 위젯들을 사용하기 위해 변수에 대입하였다.

        Button resultBtn = (Button) findViewById(R.id.addbtn);
        // 버튼의 이벤트처리 메소드이다.
        resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                // Intent를 사용하여 SecondActivity에 연결하였다.
                if (radioAdd.isChecked() == true) {
                    intent.putExtra("Num1", Integer.parseInt(edtNum1.getText().toString()));
                    intent.putExtra("Num2", Integer.parseInt(edtNum2.getText().toString()));
                    /* 넘어가는 이름을 Num1과 Num2를 사용하여 edtNum1과 edtNum2에 입력받은 문자열을 정수형으로
                    변환시켜서 넘겼다. */
                } else if (radioSub.isChecked() == true) {
                    intent.putExtra("Num3", Integer.parseInt(edtNum1.getText().toString()));
                    intent.putExtra("Num4", Integer.parseInt(edtNum2.getText().toString()));
                    /* radioSub가 체크되었을 때 이 문장을 실행하는데 Num1과 Num2와 다르게 구분한 이유는 오류가 발생했을때
                    오류가 난 부분을 찾기 위해서 Num3와 Num4로 구분하여줬다. */
                } else if (radioMul.isChecked() == true) {
                    intent.putExtra("Num5", Integer.parseInt(edtNum1.getText().toString()));
                    intent.putExtra("Num6", Integer.parseInt(edtNum2.getText().toString()));
                } else {
                    intent.putExtra("Num7", Float.parseFloat(edtNum1.getText().toString()));
                    intent.putExtra("Num8", Float.parseFloat(edtNum2.getText().toString()));
                    /* 나눗셈에 대하여 동작할 Num7과 Num8은 edtNum1과 edtNum2에서 받은 문자열을 실수형으로 변환
                    하여 SecondActivity에 넘겨주었다. */
                }
                startActivityForResult(intent, 30);
                //startActivityForResult는 SecondActivity에서 되돌려받을 값을 기다리기 때문에 양방향 인텐트에 반드시 필요하다.
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 되돌려받은 값을 처리하는 메소드이다.
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // SecondActivity에서 setResult()로 값을 돌려주고 그 값이 RESULT_OK면 실행된다.
            if(radioAdd.isChecked() == true) {
                int hap = data.getIntExtra("Hap", 0);
                Toast.makeText(MainActivity.this, "계산 결과 : " + hap, Toast.LENGTH_SHORT).show();
                // hap은 정수형으로 getIntExtra로 Hap이라는 이름으로 정수를 받아왔다.
            } else if(radioSub.isChecked() == true) {
                int sub = data.getIntExtra("Sub", 0);
                Toast.makeText(MainActivity.this, "계산 결과 : " + sub, Toast.LENGTH_SHORT).show();
                // 토스트 부분에서 MainActivity.this를 사용한 이유는 메인 액티비티에서 토스트를 띄우기에 오류를 줄일 수 있다고 생각했다.
            } else if(radioMul.isChecked() == true) {
                int mul = data.getIntExtra("Mul", 0);
                Toast.makeText(MainActivity.this, "계산 결과 : " + mul, Toast.LENGTH_SHORT).show();
            } else {
                float div = data.getFloatExtra("Div", 0);
                // 나눗셈은 소수점까지 나와야하므로 data.getFloatExtra를 이용하였고 변수 역시 float으로 선언하였다.
                String div2 = String.format("%.2f", div);
                // 소수점 2번째자리까지 나타내는 메소드이다.
                Toast.makeText(MainActivity.this, "계산 결과 : " + div2, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
package com.example.bidirectionalex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("SecondActivity"); // 타이틀 설정

        Intent inIntent = getIntent();
        // Intent를 이용하여 MainActivity의 정보를 전달받고 inIntent라는 변수에 대입하였다.
        final int hapValue = inIntent.getIntExtra("Num1", 0) +
                inIntent.getIntExtra("Num2", 0);
        // final을 사용한 이유는 버튼처리 메소드의 무기명 클래스에 접근하기위해 사용하였다.
        final int subValue = inIntent.getIntExtra("Num3",0) -
                inIntent.getIntExtra("Num4", 0);
        final int mulValue = inIntent.getIntExtra("Num5", 0) *
                inIntent.getIntExtra("Num6", 0);
        final float divValue = inIntent.getFloatExtra("Num7", 0) /
                inIntent.getFloatExtra("Num8", 0);
        // Num7과 Num8은 float의 값으로 getIntent되어 넘어왔으므로 divValue 역시 float값으로 선언하였다.

        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        // 되돌아가기 버튼을 클릭할시 이벤트 처리할 메소드이다.
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                // Intent를 사용하여 outIntent라는 변수로 MainActivity에 연결하였다.
                outIntent.putExtra("Hap", hapValue);
                // 만약 Num1과 Num2의 이름으로 정보가 들어왔다면 Hap이라는 이름으로 MainActivity에 정보를 전달 할 것이다.
                outIntent.putExtra("Sub", subValue);
                // 만약 Num3와 Num4의 이름으로 정보가 들어왔다면 Sub라는 이름으로 MainActivity에 정보를 전달 할 것이다.
                outIntent.putExtra("Mul", mulValue);
                // 만약 Num5와 Num6의 이름으로 정보가 들어왔다면 Mul이라는 이름으로 MainActivity에 정보를 전달 할 것이다.
                outIntent.putExtra("Div", divValue);
                // 만약 Num7과 Num8의 이름으로 정보가 들어왔다면 Div라는 이름으로 MainActivity에 정보를 float값으로 전달 할 것이다.
                setResult(RESULT_OK, outIntent);
                // setResult로 MainActivity에 돌려준다. MainActivity에서 onActivityResult() 메소드가 실행된다.
                finish();
                // 현재 액티비티를 닫는다
            }
        });
    }
}

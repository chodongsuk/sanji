package com.kr.sanji;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (mButton = (Button)findViewById(R.id.button1)).setOnClickListener(this);
        mEditText = (EditText)findViewById(R.id.editText1);
        mEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                // 요기서 입력된 이벤트가 무엇인지 찾아서 실행해 줌
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);


                        if (mEditText.getText().toString().matches("")) {
                            Toast.makeText(getApplicationContext(), "검색어를 입력 해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Uri uri = Uri.parse("http://www.sanjimall.com/sear_page.html?txt="+mEditText.getText().toString());
                            Intent nextIntent  = new Intent(Intent.ACTION_VIEW,uri);
                            startActivity(nextIntent);
                        }
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.button1:
                if(mEditText.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), "검색어를 입력 해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                }else{
                    InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
                    Uri uri = Uri.parse("http://www.sanjimall.com/sear_page.html?txt="+mEditText.getText().toString());
                    Intent nextIntent  = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(nextIntent);
                }
                break;

        }
    }


}

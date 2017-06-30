package com.example.zhang.mvpdemo.login;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhang.mvpdemo.R;
import com.example.zhang.mvpdemo.login.presenter.LoginPresenter;
import com.example.zhang.mvpdemo.login.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener{

    private TextInputLayout text_input_layout_1;
    private TextInputLayout text_input_layout_2;
    private Button btn2;
    private Button btn1;
    private Button btn0;
    private EditText et_name;
    private EditText et_password;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initListener();

    }
    /**查找控件*/
    private void initView() {
        text_input_layout_1 = (TextInputLayout) findViewById(R.id.text_input_layout_1);
        text_input_layout_2 = (TextInputLayout) findViewById(R.id.text_input_layout_2);
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        et_name = text_input_layout_1.getEditText();
        et_password = text_input_layout_2.getEditText();
    }

    /**控件的监听*/
    private void initListener() {
        //监听edittext
        et_password.addTextChangedListener(new TextWatcher() {
            @Override//变化之前
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override//变化中
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override//变化之后
            public void afterTextChanged(Editable editable) {
                if(editable.length()>10){
                    text_input_layout_2.setError("密码必须包含英文，且密码长度为6～15位");
                }
                else{
                    text_input_layout_2.setErrorEnabled(false);
                }
            }
        });

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);


        loginPresenter = new LoginPresenter(this, this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn0:
                btn0.setEnabled(false);
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                loginPresenter.doSave(et_name.getText().toString(),et_password.getText().toString());
                break;
            case R.id.btn1:
                btn0.setEnabled(false);
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                loginPresenter.doLogin(et_name.getText().toString(),et_password.getText().toString());
                break;
            case R.id.btn2:
                loginPresenter.clear();
                break;
        }
    }

    @Override
    public void onClearText() {
        et_name.setText("");
        et_password.setText("");
    }

    @Override
    public void onSaveResult(Boolean result, String code) {
        btn0.setEnabled(true);
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        if(result) {
            Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, code + "0000", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoginResult(Boolean result, String code) {
        btn0.setEnabled(true);
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        if(result){
            Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, code + "0000", Toast.LENGTH_SHORT).show();
        }

    }
}

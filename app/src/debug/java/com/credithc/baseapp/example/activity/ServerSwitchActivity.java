package com.credithc.baseapp.example.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.credithc.baseapp.R;
import com.credithc.baseapp.lifecycle.ActivityServerConfigLifecycle;
import com.credithc.baseapp.net.config.ServerHelper;
import com.credithc.baseapp.server.ServerManager;
import com.credithc.baseapp.util.UserUtil;
import com.credithc.common.util.ActivityManager;
import com.credithc.common.util.ActivityUtil;
import com.credithc.common.util.AppUtil;
import com.credithc.common.util.DisplayUtil;
import com.credithc.common.util.SPManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * 切换服务器环境
 */
public class ServerSwitchActivity extends AppCompatActivity {

    RadioGroup rg_server_switch;
    TextView tv_current_server, tv_cipher;
    LinearLayout ll_root;
    EditText et_clear;

    public static void launch(Context context) {
        ActivityUtil.startActivity(context, ServerSwitchActivity.class);
    }

    private String selectedServerType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_switch);

        ll_root = findViewById(R.id.ll_root);
        rg_server_switch = findViewById(R.id.rg_server_switch);
        tv_current_server = findViewById(R.id.tv_current_server);
        et_clear = findViewById(R.id.et_clear);
        tv_cipher = findViewById(R.id.tv_cipher);


        selectedServerType = ServerManager.currentServerType;
        StringBuilder sb = new StringBuilder("当前环境：").append(ServerManager.currentServerType);
        tv_current_server.setText(sb);
        switch (ServerManager.currentServerType) {
            case ServerManager.ServerTestType:
                rg_server_switch.check(R.id.rb_dev);
                break;
            case ServerManager.ServerPreType:
                rg_server_switch.check(R.id.rb_pre);
                break;
            case ServerManager.ServerReleaseType:
                rg_server_switch.check(R.id.rb_release);
                break;
        }


        rg_server_switch.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_dev:
                        selectedServerType = ServerManager.ServerTestType;
                        break;
                    case R.id.rb_pre:
                        selectedServerType = ServerManager.ServerPreType;
                        break;
                    case R.id.rb_release:
                        selectedServerType = ServerManager.ServerReleaseType;
                        break;
                }

            }
        });


        createView("点击切换环境").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedServerType.equals(ServerManager.currentServerType)) {
                    finish();
                } else {
                    SPManager.getManager(ActivityServerConfigLifecycle.SERVER_CONFIG).commitString(ActivityServerConfigLifecycle.SERVER_TYPE, selectedServerType);
                    SPManager.getDefaultManager().clear();
                    SPManager.getManager(UserUtil.USER).clear();
                    // 重启
                    ActivityManager.popAll();
                    AppUtil.restartMySelf();
                }
            }
        });

        createView("加密测试").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clearText = et_clear.getText().toString();
                String cipherText = ServerHelper.getInstance().encryptData(clearText);
                tv_cipher.setText(cipherText);
            }
        });

        createView("api测试").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ExampleApiActivity.class));
            }
        });
    }


    private View createView(String text) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dp2px(50));
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setBackgroundColor(Color.parseColor("#FFF34F36"));
        textView.setTextSize(15);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.WHITE);
        params.topMargin = DisplayUtil.dp2px(30);
        ll_root.addView(textView, params);
        return textView;
    }


}

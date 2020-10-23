package com.credithc.baseapp.lifecycle;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.credithc.baseapp.R;
import com.credithc.baseapp.example.activity.ServerSwitchActivity;
import com.credithc.mvp.lifecycle.IActLifeSubscriber;
import com.credithc.baseapp.server.ServerManager;
import com.credithc.baseapp.widget.ServerConfigView;
import com.credithc.common.util.ColorUtil;
import com.credithc.common.util.DisplayUtil;
import com.credithc.common.util.SPManager;


/**
 * @author liyong
 * @date 2020/10/13
 * @des
 */
public class ActivityServerConfigLifecycle implements IActLifeSubscriber {


    public static final String LEFT_MARGIN = "left_margin";
    public static final String RIGHT_MARGIN = "right_margin";
    public static final String TOP_MARGIN = "top_margin";
    public static final String BOTTOM_MARGIN = "bottom_margin";
    public static final String SERVER_CONFIG = "server_config";
    public static final String SERVER_TYPE = "server_type";

    public static int leftMargin = -1;
    public static int rightMargin = -1;
    public static int bottomMargin = -1;
    public static int topMargin = -1;

    public ActivityServerConfigLifecycle() {
        init();
    }

    public static void init() {
        leftMargin = SPManager.getManager(SERVER_CONFIG).getInt(LEFT_MARGIN, 0);
        topMargin = SPManager.getManager(SERVER_CONFIG).getInt(TOP_MARGIN, 0);
        rightMargin = SPManager.getManager(SERVER_CONFIG).getInt(RIGHT_MARGIN, 10);
        bottomMargin = SPManager.getManager(SERVER_CONFIG).getInt(BOTTOM_MARGIN, 30);
    }

    @Override
    public void onResume(Activity activity) {
        if (activity instanceof ServerSwitchActivity) {
            return;
        }
        View view = activity.findViewById(R.id.server_config_view);
        if (view != null) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
            params.leftMargin = leftMargin;
            params.rightMargin = rightMargin;
            params.topMargin = topMargin;
            params.bottomMargin = bottomMargin;
            view.setLayoutParams(params);
            return;
        }
        FrameLayout frameLayout = activity.findViewById(android.R.id.content);
        ServerConfigView configView = new ServerConfigView(activity);
        configView.setTextSize(12);
        configView.setTextColor(Color.WHITE);
        configView.setText(ServerManager.currentServerType);
        configView.setMaxEms(3);
        configView.setLines(1);
        configView.setGravity(Gravity.CENTER);
        configView.setId(R.id.server_config_view);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(ColorUtil.argb(150, Color.GRAY));
        drawable.setShape(GradientDrawable.OVAL);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(DisplayUtil.dp2px(40), DisplayUtil.dp2px(40));
        params.gravity = Gravity.RIGHT | Gravity.BOTTOM;
        configView.setBackground(drawable);
        params.leftMargin = leftMargin;
        params.rightMargin = rightMargin;
        params.topMargin = topMargin;
        params.bottomMargin = bottomMargin;
        frameLayout.addView(configView, params);
        configView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerSwitchActivity.launch(activity);
            }
        });
    }

    @Override
    public void onPause(Activity activity) {
        View view = activity.findViewById(R.id.server_config_view);
        if (view != null) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
            leftMargin = params.leftMargin;
            rightMargin = params.rightMargin;
            topMargin = params.topMargin;
            bottomMargin = params.bottomMargin;
            saveMargin(leftMargin, topMargin, rightMargin, bottomMargin);
            return;
        }
    }

    public static void saveMargin(int l, int t, int r, int b) {
        SPManager.getManager(SERVER_CONFIG).commitInt(LEFT_MARGIN, l);
        SPManager.getManager(SERVER_CONFIG).commitInt(TOP_MARGIN, t);
        SPManager.getManager(SERVER_CONFIG).commitInt(RIGHT_MARGIN, r);
        SPManager.getManager(SERVER_CONFIG).commitInt(BOTTOM_MARGIN, b);
    }
}

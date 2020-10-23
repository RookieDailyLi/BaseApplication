package com.credithc.baseapp.example.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.credithc.baseapp.R;
import com.credithc.baseapp.image.GlideApp;
import com.credithc.common.util.DisplayUtil;

import androidx.appcompat.app.AppCompatActivity;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class GlideExampleActivity extends AppCompatActivity {
    public static final String url = "http://img3.imgtn.bdimg.com/it/u=4172575530,4047321565&fm=26&gp=0.jpg";
    public static final String gifUrl = "http://ww2.sinaimg.cn/large/85cc5ccbgy1ffo5cx8z7lg20dw0kuaut.jpg";
    private ImageView iv_usual_redId, iv_usual_url, iv_thumb, iv_radius, iv_round, iv_gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_example);

        iv_usual_redId = findViewById(R.id.iv_usual_resId);
        iv_usual_url = findViewById(R.id.iv_usual_url);
        iv_thumb = findViewById(R.id.iv_thumb);
        iv_radius = findViewById(R.id.iv_radius);
        iv_round = findViewById(R.id.iv_round);
        iv_gif = findViewById(R.id.iv_gif);

        loadImg();
    }

    private void loadImg() {
        //常规resId加载
        GlideApp.with(this)
                .load(R.drawable.iv_glide_example)
                .placeholder(R.drawable.place_hoder_default)
                .error(R.drawable.place_hoder_default)
                .into(iv_usual_redId);

        //常规url加载
        GlideApp.with(this)
                .load(Uri.parse(url))
                .placeholder(R.drawable.place_hoder_default)
                .error(R.drawable.place_hoder_default)
                .into(iv_usual_url);

        //缩略图加载
        GlideApp.with(this)
                .load(Uri.parse(url))
                .placeholder(R.drawable.place_hoder_default)
                .error(R.drawable.place_hoder_default)
                .override(100)
                .thumbnail(0.2f)
                .into(iv_thumb);

        //圆角加载
        GlideApp.with(this)
                .load(Uri.parse(url))
                .placeholder(R.drawable.place_hoder_default)
                .error(R.drawable.place_hoder_default)
                .transform(new RoundedCornersTransformation(DisplayUtil.dp2px(10), 0))
                .into(iv_radius);

        //圆形图加载
        GlideApp.with(this)
                .load(Uri.parse(url))
                .placeholder(R.drawable.place_hoder_default)
                .error(R.drawable.place_hoder_default)
                .circleCrop()
                .into(iv_round);

        //gif加载
        GlideApp.with(this)
                .asGif()
                .load(Uri.parse(gifUrl))
                .placeholder(R.drawable.place_hoder_default)
                .error(R.drawable.place_hoder_default)
                .into(iv_gif);
    }
}

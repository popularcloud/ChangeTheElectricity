package module.login.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.wv_data)
    WebView wv_data;


    @BindView(R.id.tv_center_title)
    TextView tv_center_title;

    @BindView(R.id.rl_fanhui_left)
    RelativeLayout rl_fanhui_left;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");

        tv_center_title.setText(title);
        wv_data.loadUrl(url);

        rl_fanhui_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}

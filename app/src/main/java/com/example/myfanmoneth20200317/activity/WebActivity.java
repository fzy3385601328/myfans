package com.example.myfanmoneth20200317.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myfanmoneth20200317.R;

public class WebActivity extends AppCompatActivity implements View.OnClickListener{

    private WebView webView;
    private Button btnFenxiang;
    private Button btnZan;
    private ImageView imaImage;
    private TextView textTitle;
    private TextView textAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);


        initView();

        initWeburl();

        initData();
    }

    private void initWeburl() {
        //设置连接的加载路径
        webView.loadUrl("file:///android_asset/test.html");
    }

    private void initView() {
        imaImage = findViewById(R.id.img_image);
        textTitle = findViewById(R.id.text_Title);
        textAuthor = findViewById(R.id.text_author);
        btnFenxiang = findViewById(R.id.btn_fenxiang);
        btnZan = findViewById(R.id.btn_zan);
        webView = findViewById(R.id.Web_View);
    }


    @SuppressLint("JavascriptInterface")
    private void initData() {
        //接受RecyclerView网格传来的数据
        Intent intent = getIntent();
        String pic_big = intent.getStringExtra("pic_big");
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");

        Glide.with(WebActivity.this)
                .load(pic_big).error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(imaImage);
        textTitle.setText(title);
        textAuthor.setText(author);


        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBlockNetworkLoads(true);
        webView.requestFocus();
        webView.addJavascriptInterface(new TestJavascriptInterface(),"share");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btn_fenxiang:
                webView.loadUrl("javascript:share()");
                break;
            case R.id.btn_zan:
                String string = "";
                webView.loadUrl("javascript:fabulous('"+string+"')");
                break;
        }
    }


    public class TestJavascriptInterface{
        @JavascriptInterface
        public void startCall(){
            Toast.makeText(WebActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void showToast(){
            Toast.makeText(WebActivity.this, "点击了赞按钮", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (webView.canGoBack()){
            webView.goBack();
        }
    }
}

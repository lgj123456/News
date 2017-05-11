package ad0424.yls.example.com.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.ViewFlipper;

import ad0424.yls.example.com.news.R;

public class GuideActivity extends AppCompatActivity {
    private ViewFlipper guideViewFlipper;
    private CustomGestureDetectorListener mDetectorListener;
    private GestureDetector mGestureDetector;
    private TextView txtStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        initVews();
    }
    private void initVews() {
        guideViewFlipper = (ViewFlipper) findViewById(R.id.guide_viewFlipper);
        txtStart = (TextView) findViewById(R.id.tv_begin);
        mDetectorListener = new CustomGestureDetectorListener();
        mGestureDetector = new GestureDetector(GuideActivity.this, mDetectorListener);

        txtStart = (TextView) findViewById(R.id.tv_begin);
        txtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.screen_zoom_in,R.anim.screen_zoom_out);
                finish();
            }
        });

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    class CustomGestureDetectorListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() > e2.getX()) {
                guideViewFlipper.setInAnimation(GuideActivity.this,R.anim.slide_left_in);
                guideViewFlipper.setOutAnimation(GuideActivity.this,R.anim.left_out);
                guideViewFlipper.showNext();

            }

            if (e1.getX() < e2.getX()) {
                guideViewFlipper.setInAnimation(GuideActivity.this,R.anim.right_in);
                guideViewFlipper.setOutAnimation(GuideActivity.this,R.anim.right_out);
                guideViewFlipper.showPrevious();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

}

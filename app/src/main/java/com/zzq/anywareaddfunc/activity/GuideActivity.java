package com.zzq.anywareaddfunc.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zzq.anywareaddfunc.R;
import com.zzq.anywareaddfunc.utils.DensityUtil;
import com.zzq.anywareaddfunc.utils.SPUtils;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private ArrayList<ImageView> imageViews;
    private ViewPager viewpager;
    private Button btn_start_main;
    private LinearLayout ll_point_group;
    private ImageView iv_red_point;

    private int maxLeft;
    private float margLeft;
    private int dipsize;

    private Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        context = this;

        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        btn_start_main.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                SPUtils.put(context, SPUtils.SHOW_GUIDE, true);
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void initData() {
        //把这个单位当成dip -10 -
        dipsize = DensityUtil.dip2px(this, 10);

        int ids[] = {R.mipmap.lead_bg1, R.mipmap.lead_bg2, R.mipmap.lead_bg3};
        //把图片资源转换-ImageView-->并且放入集合中
        imageViews = new ArrayList<ImageView>();
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(ids[i]);
            imageViews.add(imageView);
            //添加灰色的点-有多少个页面就添加多少个点击
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dipsize, dipsize);//
            if (i != 0) {
                params.leftMargin = dipsize;
            }
            point.setLayoutParams(params);
            ll_point_group.addView(point);
        }

        viewpager.setAdapter(new MyPagerAdapter());
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        btn_start_main = (Button) findViewById(R.id.btn_start_main);
        ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
        iv_red_point = (ImageView) findViewById(R.id.iv_red_point);
    }

    class MyOnPageChangeListener implements OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            margLeft = maxLeft * (position + positionOffset);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(dipsize, dipsize);
            params.leftMargin = (int) margLeft;//这种方式可以
            iv_red_point.setLayoutParams(params);
        }

        /**
         * 当某个页面被选中的时候回调这个方法
         */
        @Override
        public void onPageSelected(int position) {
            if (position == (imageViews.size() - 1)) {//最后一个页面，2
                btn_start_main.setVisibility(View.VISIBLE);
            } else {
                btn_start_main.setVisibility(View.GONE);
            }
        }

        /**
         * 当页面的状态发送变化的时候回调这个方法
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }

    }

    class MyOnGlobalLayoutListener implements OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            iv_red_point.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            maxLeft = ll_point_group.getChildAt(1).getLeft() - ll_point_group.getChildAt(0).getLeft();
        }

    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);//更加位置得到对应的数据
            container.addView(imageView);//把对应的页面添加到容器中(ViewPager)
            return imageView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}

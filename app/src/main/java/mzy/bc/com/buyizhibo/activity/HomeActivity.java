package mzy.bc.com.buyizhibo.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import mzy.bc.com.buyizhibo.R;
import mzy.bc.com.buyizhibo.fragment.HomeHomeFragment;
import mzy.bc.com.buyizhibo.fragment.HomeWoDeFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private HomeHomeFragment homeFragmentHomeFragment;
    private HomeWoDeFragment homeFragmentWoDeFragment;
    private ImageView btnHome,btnWoDe;
    ScaleAnimation animation;
    ScaleAnimation animation01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        initData();
    }
    private void initViews() {
        btnHome=(ImageView) findViewById(R.id.imgbtn_home_home);
        btnWoDe=(ImageView) findViewById(R.id.imgbtn_home_wode);
        btnHome.setOnClickListener(this);
        btnWoDe.setOnClickListener(this);

        animation =new ScaleAnimation(1f, 1.2f, 1f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(200);//设置动画持续时间
        animation.setFillAfter(true);
        animation.setRepeatMode(Animation.REVERSE);
        btnHome.setAnimation(animation);

        animation01 =new ScaleAnimation(1.2f, 1f, 1.2f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation01.setDuration(200);//设置动画持续时间
        btnHome.setAnimation(animation01);
    }


    private void initData(){
        setSelection(0);
    }

    private  void hideFrag(FragmentTransaction  fragmentTransaction){
        if(homeFragmentHomeFragment != null){
            fragmentTransaction.hide(homeFragmentHomeFragment);
        }

        if(homeFragmentWoDeFragment  != null){
            fragmentTransaction.hide(homeFragmentWoDeFragment);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgbtn_home_home:
                setSelection(0);
                break;
            case R.id.imgbtn_home_wode:
                setSelection(1);
                break;
        }
    }

    private void setSelection(int index){
      FragmentTransaction  fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFrag(fragmentTransaction);
        switch (index){
            //点击首页中的 房子 图标
            case 0:
                btnHome.startAnimation(animation);
                btnHome.startAnimation(animation01);
                btnHome.setImageResource(R.mipmap.tab_live_p);
                btnWoDe.setImageResource(R.mipmap.tab_me);
                if(homeFragmentHomeFragment == null){
                    homeFragmentHomeFragment = new HomeHomeFragment();
                    fragmentTransaction.add(R.id.rl_home_frament, homeFragmentHomeFragment);
                }else {
                    fragmentTransaction.show(homeFragmentHomeFragment);
                }
                break;
            //点击首页中的 我的 图标
            case 1:
                btnWoDe.startAnimation(animation);
                btnWoDe.startAnimation(animation01);
                btnWoDe.setImageResource(R.mipmap.tab_me_p);
                btnHome.setImageResource(R.mipmap.tab_live);
                if(homeFragmentWoDeFragment == null){
                    homeFragmentWoDeFragment = new HomeWoDeFragment();
                    fragmentTransaction.add(R.id.rl_home_frament, homeFragmentWoDeFragment);
                }else {
                    fragmentTransaction.show(homeFragmentWoDeFragment);
                }
                break;
        }
        fragmentTransaction.commit();

    }
}

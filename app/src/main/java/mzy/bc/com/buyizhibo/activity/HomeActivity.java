package mzy.bc.com.buyizhibo.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import mzy.bc.com.buyizhibo.R;
import mzy.bc.com.buyizhibo.fragment.HomeHomeFragment;
import mzy.bc.com.buyizhibo.fragment.HomeWoDeFragment;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private HomeHomeFragment homeFragmentHomeFragment;
    private HomeWoDeFragment homeFragmentWoDeFragment;
    private ImageView btnHome,btnWoDe,imgZhong;
    private ScaleAnimation animation;
    private ScaleAnimation animation01;
    private Dialog dialog;
    private View inflate;
    private LinearLayout diaLogZhiBo,diaLogShiPin;
    private ImageView imgCha;
    private TranslateAnimation animation03,animation02;
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
        imgZhong=(ImageView) findViewById(R.id.img_home_zhong);
        btnHome.setOnClickListener(this);
        btnWoDe.setOnClickListener(this);
        imgZhong.setOnClickListener(this);
        animation =new ScaleAnimation(1f, 1.2f, 1f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(200);//设置动画持续时间
        animation.setFillAfter(true);
        animation.setRepeatMode(Animation.REVERSE);
//        btnHome.setAnimation(animation);

        animation01 =new ScaleAnimation(1.2f, 1f, 1.2f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation01.setDuration(200);//设置动画持续时间
//        btnHome.setAnimation(animation01);
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
            case R.id.img_home_zhong:
                setShowDiaLog();
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

    private void setShowDiaLog(){
        dialog = new Dialog(this, R.style.DialogStyle);
        //填充对话框的布局
        inflate = View.inflate(this, R.layout.home_dialog, null);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        dialog.setCanceledOnTouchOutside(true);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialog.setCancelable(true);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//       将属性设置给窗体
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height =(int)(display.getHeight()*0.3);
        dialogWindow.setAttributes(lp);
        initDiaLogView(inflate);
        dialog.show();
    }
    private float mScreenHeight;
    public void initDiaLogView(View view){
        diaLogZhiBo=(LinearLayout) view.findViewById(R.id.ll_home_dialog_zhibo);
        diaLogShiPin=(LinearLayout) view.findViewById(R.id.ll_home_dialog_shipin);
        imgCha=(ImageView) view.findViewById(R.id.img_home_dia_cha);

        float s=diaLogZhiBo.getTranslationY();

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(diaLogZhiBo, "translationY",250,-50f,s );
        objectAnimator.setDuration(1000).start();

        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                float s0=diaLogShiPin.getTranslationY();
                diaLogShiPin.setVisibility(View.VISIBLE);
                ObjectAnimator objectAnimator0 = ObjectAnimator.ofFloat(diaLogShiPin, "translationY",250,-50f,s0 );
                objectAnimator0.setDuration(1000).start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }
}

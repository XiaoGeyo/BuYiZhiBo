package mzy.bc.com.buyizhibo.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.ksyun.media.player.IMediaPlayer;
import com.ksyun.media.player.KSYMediaMeta;
import com.ksyun.media.player.KSYMediaPlayer;
import com.ksyun.media.player.KSYTextureView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mzy.bc.com.buyizhibo.R;
import mzy.bc.com.buyizhibo.adapter.BoFangViewPagerAdapter;
import mzy.bc.com.buyizhibo.fragment.BoFangCaoZuoOne;
import mzy.bc.com.buyizhibo.fragment.BoFangCaoZuoTwo;

public class BoFangActivity extends AppCompatActivity {
    private ViewPager viewPager;

    private BoFangViewPagerAdapter boFangViewPagerAdapter;
    private static final String TAG = "TextureVideoActivity";
    private SharedPreferences settings;
    private String bufferTime;
    private String bufferSize;

    private Context mContext;

    KSYTextureView mVideoView = null;


    private int mVideoWidth = 0;
    private int mVideoHeight = 0;

    boolean useHwCodec = false;
    private float centerPointX;
    private float centerPointY;
    private float lastMoveX = -1;
    private float lastMoveY = -1;
    private float movedDeltaX;
    private float movedDeltaY;
    private float totalRatio;
    private float deltaRatio;
    private double lastSpan;

    private IMediaPlayer.OnPreparedListener mOnPreparedListener = new IMediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(IMediaPlayer mp) {
            Log.d("VideoPlayer", "OnPrepared");
            mVideoWidth = mVideoView.getVideoWidth();
            mVideoHeight = mVideoView.getVideoHeight();

            // Set Video Scaling Mode
            mVideoView.setVideoScalingMode(KSYMediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);

            //start player
            mVideoView.start();

            //  get meta data
            Bundle bundle = mVideoView.getMediaMeta();
            KSYMediaMeta meta = KSYMediaMeta.parse(bundle);
            if (meta != null) {
                if (meta.mHttpConnectTime > 0) {
                }

                int dns_time = meta.mAnalyzeDnsTime;
                if (dns_time > 0) {
                }
            }


        }
    };

    private IMediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(IMediaPlayer mp, int percent) {
        }
    };

    private IMediaPlayer.OnVideoSizeChangedListener mOnVideoSizeChangeListener = new IMediaPlayer.OnVideoSizeChangedListener() {
        @Override
        public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sarNum, int sarDen) {
            if (mVideoWidth > 0 && mVideoHeight > 0) {
                if (width != mVideoWidth || height != mVideoHeight) {
                    mVideoWidth = mp.getVideoWidth();
                    mVideoHeight = mp.getVideoHeight();

                    if (mVideoView != null)
                        mVideoView.setVideoScalingMode(KSYMediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                }
            }
        }
    };

    private IMediaPlayer.OnSeekCompleteListener mOnSeekCompletedListener = new IMediaPlayer.OnSeekCompleteListener() {
        @Override
        public void onSeekComplete(IMediaPlayer mp) {
            Log.e(TAG, "onSeekComplete...............");
        }
    };

    private IMediaPlayer.OnCompletionListener mOnCompletionListener = new IMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(IMediaPlayer mp) {
            Toast.makeText(mContext, "OnCompletionListener, play complete.", Toast.LENGTH_LONG).show();
            videoPlayEnd();
        }
    };

    private IMediaPlayer.OnErrorListener mOnErrorListener = new IMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(IMediaPlayer mp, int what, int extra) {
            switch (what) {
                //case KSYVideoView.MEDIA_ERROR_UNKNOWN:
                // Log.e(TAG, "OnErrorListener, Error Unknown:" + what + ",extra:" + extra);
                //  break;
                default:
                    Log.e(TAG, "OnErrorListener, Error:" + what + ",extra:" + extra);
            }

            videoPlayEnd();

            return false;
        }
    };

    public IMediaPlayer.OnInfoListener mOnInfoListener = new IMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
            switch (i) {
                case KSYMediaPlayer.MEDIA_INFO_BUFFERING_START:
                    Log.d(TAG, "Buffering Start.");
                    break;
                case KSYMediaPlayer.MEDIA_INFO_BUFFERING_END:
                    Log.d(TAG, "Buffering End.");
                    break;
                case KSYMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START:
                    Toast.makeText(mContext, "Audio Rendering Start", Toast.LENGTH_SHORT).show();
                    break;
                case KSYMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                    Toast.makeText(mContext, "Video Rendering Start", Toast.LENGTH_SHORT).show();
                    break;
                case KSYMediaPlayer.MEDIA_INFO_RELOADED:
                    Toast.makeText(mContext, "Succeed to reload video.", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Succeed to mPlayerReload video.");
                    return false;
            }
            return false;
        }
    };

    private IMediaPlayer.OnMessageListener mOnMessageListener = new IMediaPlayer.OnMessageListener() {
        @Override
        public void onMessage(IMediaPlayer iMediaPlayer, String name, String info, double number) {
            Log.e(TAG, "name:" + name + ",info:" + info + ",number:" + number);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getApplicationContext();
        useHwCodec = getIntent().getBooleanExtra("HWCodec", false);
        setContentView(R.layout.activity_bo_fang);
        initView();
        setVideo();
    }
    public void setVideo(){

        mVideoView = (KSYTextureView) findViewById(R.id.texture_view);
        mVideoView.setKeepScreenOn(true);
        mVideoView.setOnTouchListener(mTouchListener);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mVideoView.setOnBufferingUpdateListener(mOnBufferingUpdateListener);
        mVideoView.setOnCompletionListener(mOnCompletionListener);
        mVideoView.setOnPreparedListener(mOnPreparedListener);
        mVideoView.setOnInfoListener(mOnInfoListener);
        mVideoView.setOnVideoSizeChangedListener(mOnVideoSizeChangeListener);
        mVideoView.setOnErrorListener(mOnErrorListener);
        mVideoView.setOnSeekCompleteListener(mOnSeekCompletedListener);
        mVideoView.setOnMessageListener(mOnMessageListener);
        mVideoView.setScreenOnWhilePlaying(true);
        mVideoView.setTimeout(5, 30);
        settings = getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        bufferTime = settings.getString("buffertime", "2");
        bufferSize = settings.getString("buffersize", "15");

        if (!TextUtils.isEmpty(bufferTime)) {
            mVideoView.setBufferTimeMax(Integer.parseInt(bufferTime));
            Log.e(TAG, "palyer buffertime :" + bufferTime);
        }

        if (!TextUtils.isEmpty(bufferSize)) {
            mVideoView.setBufferSize(Integer.parseInt(bufferSize));
            Log.e(TAG, "palyer buffersize :" + bufferSize);
        }

        if (useHwCodec) {
            //硬解264&265
            Log.e(TAG, "Hardware !!!!!!!!");
            mVideoView.setDecodeMode(KSYMediaPlayer.KSYDecodeMode.KSY_DECODE_MODE_AUTO);
        }

        try {
            mVideoView.setDataSource("rtmp://live.hkstv.hk.lxdns.com/live/hks");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mVideoView.prepareAsync();
    }
    private void initView() {

        viewPager = (ViewPager) findViewById(R.id.vp_bofang);
        List<Fragment> list = new ArrayList<>();
        list.add(new BoFangCaoZuoOne());
        list.add(new BoFangCaoZuoTwo());
        boFangViewPagerAdapter = new BoFangViewPagerAdapter(this.getSupportFragmentManager(), list);
        viewPager.setAdapter(boFangViewPagerAdapter);
        viewPager.setCurrentItem(1);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView = null;
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mVideoView != null) {
            mVideoView.runInBackground(true);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mVideoView != null) {
            mVideoView.runInForeground();
        }
    }

    @Override
    public int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    if (event.getPointerCount() == 2) {
                        lastSpan = getCurrentSpan(event);
                        centerPointX = getFocusX(event);
                        centerPointY = getFocusY(event);
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (event.getPointerCount() == 1) {
                        float posX = event.getX();
                        float posY = event.getY();
                        if (lastMoveX == -1 && lastMoveX == -1) {
                            lastMoveX = posX;
                            lastMoveY = posY;
                        }
                        movedDeltaX = posX - lastMoveX;
                        movedDeltaY = posY - lastMoveY;

                        if (Math.abs(movedDeltaX) > 5 || Math.abs(movedDeltaY) > 5) {
                            if (mVideoView != null) {
                                mVideoView.moveVideo(movedDeltaX, movedDeltaY);
                            }
                        }
                        lastMoveX = posX;
                        lastMoveY = posY;
                    } else if (event.getPointerCount() == 2) {
                        double spans = getCurrentSpan(event);
                        if (spans > 5)
                        {
                            deltaRatio = (float) (spans / lastSpan);
                            totalRatio = mVideoView.getVideoScaleRatio() * deltaRatio;
                            /*
                            //限定缩放边界,如果视频的宽度小于屏幕的宽度则停止播放
                            if ((rotateNum / 90) %2 != 0){
                                if (totalRatio * mVideoWidth <= mVideoView.getHeight())
                                    break;
                            }
                            else {
                                if (totalRatio * mVideoWidth <= mVideoView.getWidth())
                                    break;
                            }
                            */
                            if(mVideoView != null){
                                mVideoView.setVideoScaleRatio(totalRatio, centerPointX, centerPointY);
                            }
                            lastSpan = spans;
                        }
                    }
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    if (event.getPointerCount() == 2) {
                        lastMoveX = -1;
                        lastMoveY = -1;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    lastMoveX = -1;
                    lastMoveY = -1;

                    break;
                default:
                    break;
            }
            return true;
        }
    };
    private double getCurrentSpan(MotionEvent event) {
        float disX = Math.abs(event.getX(0) - event.getX(1));
        float disY = Math.abs(event.getY(0) - event.getY(1));
        return Math.sqrt(disX * disX + disY * disY);
    }

    private float getFocusX(MotionEvent event){
        float xPoint0 = event.getX(0);
        float xPoint1 = event.getX(1);
        return (xPoint0 + xPoint1) / 2;
    }
    private float getFocusY(MotionEvent event) {
        float yPoint0 = event.getY(0);
        float yPoint1 = event.getY(1);
        return (yPoint0 + yPoint1) / 2;
    }
    private void videoPlayEnd() {
        if (mVideoView != null) {
            mVideoView.release();
            mVideoView = null;
        }
        finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            videoPlayEnd();
        }
        return super.onKeyDown(keyCode, event);
    }
}

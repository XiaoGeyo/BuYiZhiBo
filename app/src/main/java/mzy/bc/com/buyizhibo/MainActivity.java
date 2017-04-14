package mzy.bc.com.buyizhibo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import mzy.bc.com.buyizhibo.utils.ImageOption;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView) findViewById(R.id.img);
        Toast.makeText(this,"我的滴哪",Toast.LENGTH_LONG).show();
        ImageLoader.getInstance().displayImage("http://img1.imgtn.bdimg.com/it/u=2932272756,4220615981&fm=23&gp=0.jpg", imageView, ImageOption.defaultOptions);
    }
}

package demo.zq.com.customdemo1.customView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import demo.zq.com.customdemo1.R;

/**
 * Package_name:demo.zq.com.customdemo1.customView
 * Author:zhaoQiang
 * Email:zhao_hero@163.com
 * Date:2016/11/2  20:32
 *
 * 将   画板对象   保存为 Bitmap  并显示
 */
public class PaintActivity extends Activity implements View.OnClickListener {

    private CustomPaint customPaint;

    private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paint);

        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn:
                customPaint = (CustomPaint) findViewById(R.id.iv);
                ImageView imageView = (ImageView) findViewById(R.id.imageshow);
                //保存图片
                Bitmap bitmap = createViewBitmap(customPaint);  //调用本地方法
                imageView.setImageBitmap(bitmap);
                break;
        }
    }

    //把  View对象  转为  Bitmap对象
    public Bitmap createViewBitmap(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }
}

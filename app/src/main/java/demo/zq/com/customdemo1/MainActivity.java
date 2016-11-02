package demo.zq.com.customdemo1;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import demo.zq.com.customdemo1.customView.CustomProgress;

public class MainActivity extends AppCompatActivity {

    private CustomProgress customProgress, customProgress2, customProgress3;

    //处理  消息
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                customProgress.setProgress(msg.arg1); //更新进度
            } else if (msg.what == 2) {
                customProgress2.setProgress(msg.arg1); //
            } else {
                customProgress3.setProgress(msg.arg1);//
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //查找控件
        customProgress = (CustomProgress) findViewById(R.id.custom_progress);
        customProgress2 = (CustomProgress) findViewById(R.id.custom_progress2);
        customProgress3 = (CustomProgress) findViewById(R.id.custom_progress3);
        //设置进度
        customProgress.setmTotalProgress(100);
        customProgress2.setmTotalProgress(200);
        customProgress3.setmTotalProgress(365);

        //开始更新进度
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    for (int i = 1; i <= 100; i++) {
                        Thread.sleep(1000);//每隔  1秒发送一次进度
                        Message message = handler.obtainMessage();
                        message.what = 1;
                        message.arg1 = i;
                        handler.sendMessage(message); //发送进度信息
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 1; i <= 200; i++) {
                        Thread.sleep(200);//每隔  0.2秒发送一次进度
                        Message message = handler.obtainMessage();
                        message.what = 2;
                        message.arg1 = i;
                        handler.sendMessage(message);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 1; i <= 365; i++) {
                        Thread.sleep(100);//每隔  0.1秒发送一次进度
                        Message message = handler.obtainMessage();
                        message.what = 3;
                        message.arg1 = i;
                        handler.sendMessage(message);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

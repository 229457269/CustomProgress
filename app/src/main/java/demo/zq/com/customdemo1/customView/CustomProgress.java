package demo.zq.com.customdemo1.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import demo.zq.com.customdemo1.R;

/**
 * Created by zq on 2016/8/17.
 * 创建  单个  圆形进度条
 */
public class CustomProgress extends View {
    // 画实心圆的画笔
    private Paint mCirclePaint;
    // 画圆环的画笔
    private Paint mRingPaint;
    // 画字体的画笔
    private Paint mTextPaint;
    // 圆形颜色
    private int mCircleColor;
    // 圆环颜色
    private int mRingColor;
    // 半径
    private float mRadius;
    // 圆环半径
    private float mRingRadius;
    // 圆环宽度
    private float mStrokeWidth;
    // 圆心x坐标
    private int mXCenter;
    // 圆心y坐标
    private int mYCenter;
    // 字的长度
    private float mTxtWidth;
    // 字的高度
    private float mTxtHeight;
    // 总进度
    private int mTotalProgress;
    // 当前进度
    private int mProgress;
    //大圆
    private Paint mBigPatient;
    //字体颜色
    private int mTextColor;
    //外圆颜色
    private int mBigCircleColor;

    public CustomProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获取自定义的属性
        initAttrs(context, attrs);
        //初始化  画笔
        initVariable();
    }


    //初始化  属性集
    private void initAttrs(Context context, AttributeSet attrs) {
        //获取  自定义属性集
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CustomProgress, 0, 0);

        mRadius = typeArray.getDimension(R.styleable.CustomProgress_radius, 300); //半径
        mStrokeWidth = typeArray.getDimension(R.styleable.CustomProgress_strokeWidth, 20); //画笔宽度
        mCircleColor = typeArray.getColor(R.styleable.CustomProgress_circleColor, Color.BLUE); //内圆颜色
        mRingColor = typeArray.getColor(R.styleable.CustomProgress_ringColor, Color.RED);//进度条颜色
        mTotalProgress = typeArray.getInt(R.styleable.CustomProgress_totalProgress, 100); //总进度
        mTextColor = typeArray.getColor(R.styleable.CustomProgress_textColor, Color.WHITE); //字体颜色
        mBigCircleColor = typeArray.getColor(R.styleable.CustomProgress_bigCircleColor, Color.WHITE);//外圆颜色

        typeArray.recycle();//注意这里要释放掉

//        大圆半径  等于  小圆半径 + 画笔宽度/2
        mRingRadius = mRadius + mStrokeWidth/2;
    }

    private void initVariable() {
        mCirclePaint = new Paint();  //创建画笔  设置样式
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mCircleColor);//内圆颜色
        mCirclePaint.setStyle(Paint.Style.FILL);

        mRingPaint = new Paint();   //创建画笔
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(mStrokeWidth);

        mTextPaint = new Paint();   //创建画笔
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mRadius / 2);

        mBigPatient = new Paint();  //创建画笔
        mBigPatient.setColor(mBigCircleColor);
        mBigPatient.setAntiAlias(true);
        mBigPatient.setStyle(Paint.Style.FILL);

        //字体属性测量类  绘制文本对象
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        //返回大于参数x的最小整数,即对浮点数向上取整.   设置  字体高度
        mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);
    }

    //开始绘制
    @Override
    protected void onDraw(Canvas canvas) {

        mXCenter = getWidth() / 2;  //控件宽度 /2
        mYCenter = getHeight() / 2; //控件高度 /2

        //画圆   圆心：
        canvas.drawCircle(mXCenter, mYCenter, mRadius + mStrokeWidth, mBigPatient);
        canvas.drawCircle(mXCenter, mYCenter, mRadius, mCirclePaint);

        if (mProgress > 0) {
            RectF oval = new RectF();
            oval.left = (mXCenter - mRingRadius);
            oval.top = (mYCenter - mRingRadius);
            oval.right = mRingRadius * 2 + (mXCenter - mRingRadius);
            oval.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
            canvas.drawArc(oval, -90, ((float) mProgress / mTotalProgress) * 360, false, mRingPaint); //

            String txt = (int) (mProgress * 1.0f / mTotalProgress * 100) + "%";
            mTxtWidth = mTextPaint.measureText(txt, 0, txt.length());
            canvas.drawText(txt, mXCenter - mTxtWidth / 2, mYCenter + mTxtHeight / 4, mTextPaint);
        }
    }

    //更新进度的   方法
    public void setProgress(int progress) {
        mProgress = progress;
        postInvalidate();//刷新ui
//        invalidate()是用来刷新View的，必须是在UI线程中进行工作。
//        使用postInvalidate则比较简单，不需要handler，直接在线程中调用postInvalidate即可。
    }

    //定义  设置总进度的方法
    public void setmTotalProgress(int totalProgress) {
        mTotalProgress = totalProgress;
    }
}

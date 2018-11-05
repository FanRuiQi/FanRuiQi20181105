package fanruiqi.bwie.com.fanruiqi20181105;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

public class Zp extends View implements View.OnClickListener{

    Paint paint;
    RotateAnimation rotateAnimation;
    public String[] text =new String[]{"一等奖","二等奖","三等奖","四等奖","参与奖","谢谢参与"};
    private int mWidth;
    private int mPadding;
    private RectF mRectF;
    public boolean yy=false;
    public String str="start";

    public Zp(Context context) {
        this(context,null);
    }

    public Zp(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public Zp(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPint();
        initAnim();
        setOnClickListener(this);
    }

    private void initAnim() {  //旋转动画
        rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setFillAfter(true);
    }

    private void initPint() {

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);  //消除锯齿
        paint.setColor(Color.WHITE);  //设置颜色
        paint.setStrokeWidth(3);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  //测量
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mPadding=5;

        mRectF = new RectF(0, 0, mWidth, mWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {  //绘制
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mWidth/2,mWidth/2,mWidth/2-mPadding,paint); //画圆

        paint.setStyle(Paint.Style.FILL);
        initArc(canvas);  //绘制六个扇形

        paint.setColor(Color.RED);  //绘制中心小圆
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mWidth/2,mWidth/2,50,paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(24);
        Rect rect = new Rect();
        paint.getTextBounds(str,0,str.length(),rect);

        int width = rect.width();
        int height = rect.height();
        canvas.drawText(str,mWidth/2-25+25-width/2,mWidth/2+height/2,paint);

    }

    private void initArc(Canvas canvas) {

        for (int i=0;i<6;i++){
            paint.setColor(color[i]);
            canvas.drawArc(mRectF,(i-1)*60+60,60,true,paint);
        }

        for (int i=0;i<6;i++){
            paint.setColor(Color.BLACK);
            Path path = new Path();
            path.addArc(mRectF,(i-1)*60+60,60);

            canvas.drawTextOnPath(text[i],path,60,60,paint);
        }
    }

    public int[] color=new int[]{   //存放颜色
            Color.parseColor("#8EE5EE"),
            Color.parseColor("#FFD700"),
            Color.parseColor("#FFD39B"),
            Color.parseColor("#FF8247"),
            Color.parseColor("#FF34B3"),
            Color.parseColor("#F0E68C"),
    };
    @Override
    public void onClick(View view) {  //设置点击
        if (!yy){
            yy=true;
            rotateAnimation.setDuration(1000);
            rotateAnimation.setInterpolator(new LinearInterpolator());  //不间断
            startAnimation(rotateAnimation);

        }else {
            yy=false;
            clearAnimation();
        }
    }
}

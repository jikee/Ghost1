package com.mph.ghost.ghost1.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.mph.ghost.ghost1.R;


/**
 * @author 马鹏昊
 * @date {2016-9-30}
 * @des 带有可设置大小的drawable的TextView
 * @updateAuthor
 * @updateDate
 * @updateDes
 */
public class DrawableTextView extends android.support.v7.widget.AppCompatTextView {

//    public static final String TAG = Contacts.TAG;

    public static final int LEFT = 1, TOP = 2, RIGHT = 3, BOTTOM = 4;

    private Drawable src;
    private int drawablePosition;
    private int drawableWidth;
    private int drawableHeight;

    private Context mContext ;

    public DrawableTextView(Context context) {
        this(context,null,0);
    }

    public DrawableTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context ;

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DrawableTextView, defStyleAttr, 0);
        //默认设置为“college”图片
        src = array.getDrawable(R.styleable.DrawableTextView_drawableSrc);
//        Log.d(TAG, "DrawableTextView: " + src);
        //默认设置为左边
        drawablePosition = array.getInt(R.styleable.DrawableTextView_drawablePosition, LEFT);
//        Log.d(TAG, "DrawableTextView: " + drawablePosition);
        //默认为20dp宽
        drawableWidth = array.getDimensionPixelSize(R.styleable.DrawableTextView_drawableWidth, 0);
//        Log.d(TAG, "DrawableTextView: " + drawableWidth);
        //默认为20dp长
        drawableHeight = array.getDimensionPixelSize(R.styleable.DrawableTextView_drawableHeight, 0);
//        Log.d(TAG, "DrawableTextView: " + drawableHeight);

        array.recycle();
        drawDrawable();


    }

    /**
     * 代码中设置图片id
     * @param drawableId
     */
    public void setDrawableSrc(int drawableId){
        src = ContextCompat.getDrawable(mContext,drawableId);
    }

    private void drawDrawable() {
        if (src != null) {
            Bitmap bitmap = ((BitmapDrawable) src).getBitmap();
            Drawable drawable;
            if (drawableWidth != 0 && drawableHeight != 0) {
                drawable = new BitmapDrawable(getResources(), getBitmap(bitmap, drawableWidth, drawableHeight));
            } else {
                drawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true));
            }
            switch (drawablePosition) {
                case LEFT:
                    this.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                    break;
                case TOP:
                    this.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
                    break;
                case RIGHT:
                    this.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                    break;
                case BOTTOM:
                    this.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);
                    break;
            }
        }
    }

    /**
     * 设置图片大小
     * @param width
     * @param height
     */
    public void setDrawableSize(float width,float height){
        drawableWidth = (int) width;
        drawableHeight = (int) height;
        drawDrawable();
    }


    public Bitmap getBitmap(Bitmap bitmap, int width, int height) {
        //实际的大小
        int totalWidth = bitmap.getWidth();
        int totalHeight = bitmap.getHeight();
        //        int a = width;
        //        int b = height;
        //计算缩放比例
        float scaleWidth = (float) width / totalWidth;
        float scaleHeight = (float) height / totalHeight;
        Matrix matrix = new Matrix();
        //提交缩放
        matrix.postScale(scaleWidth, scaleHeight);
//        Log.d(TAG, "宽: " + totalWidth + "高:" + totalHeight);

        //得到缩放后的图片
        Bitmap bitmapResult = Bitmap.createBitmap(bitmap, 0, 0, totalWidth, totalHeight, matrix, true);

        return bitmapResult;
    }

}

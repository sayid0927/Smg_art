package com.smg.art.photo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.smg.art.R;


/**
 * Created by lurs on 2018/1/6 0006.
 */

public class CircleImageView extends ImageView {

    private Context mContext;

    private Bitmap mBitmap;
    private Drawable mDrawable;
    private float radius = 0;
    private int viewWidth, viewHeight;

    public CircleImageView(Context context) {
        super(context);
        mContext = context;
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(attrs);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(attrs);
    }

    public void setImageResource(@DrawableRes int res) {
        mBitmap = BitmapFactory.decodeResource(getResources(), res);
        if (mBitmap != null) {
            invalidate();
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        if (bitmap != null && mBitmap != bitmap) {
            mBitmap = bitmap;
            invalidate();
        }
    }

    private void initView(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        mBitmap = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(R.styleable.CircleImageView_src, 0));
        radius = typedArray.getDimension(R.styleable.CircleImageView_radius, 0);
        if (mBitmap != null) {
            setImageBitmap(mBitmap);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            viewWidth = widthSize;
        } else {
            if (mBitmap != null) {
                viewWidth = mBitmap.getWidth();
                int widthPixels = getResources().getDisplayMetrics().widthPixels;
                if (viewWidth > widthPixels) {
                    viewWidth = widthMeasureSpec;
                }
            } else {
                viewWidth = 0;
            }
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            viewHeight = heightSize;
        } else {
            if (mBitmap != null) {
                viewHeight = mBitmap.getHeight();
                int heightPixels = getResources().getDisplayMetrics().heightPixels;
                if (viewHeight > heightPixels) {
                    viewHeight = heightPixels;
                }
            } else {
                viewHeight = 0;
            }
        }
        setMeasuredDimension(viewWidth, viewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmap != null) {
            if (radius == 0) {
                mDrawable = new BitmapDrawable(mBitmap);
                mDrawable.setBounds(0, 0, viewWidth, viewHeight);
                mDrawable.draw(canvas);
            } else {
                drawRadiusDrawable();
                mDrawable.setBounds(0, 0, viewWidth, viewHeight);
                mDrawable.draw(canvas);
            }
        }
    }

    private void drawRadiusDrawable() {
        Bitmap rectBitmap;
        int min = Math.min(mBitmap.getWidth(), mBitmap.getHeight());//图片尺寸
        rectBitmap = Bitmap.createBitmap(mBitmap, (mBitmap.getWidth() - min) / 2, (mBitmap.getHeight() - min) / 2, min, min);
        int size = Math.min(viewWidth, viewHeight);//图片的压缩尺寸
        Bitmap scaleBitmap;
        scaleBitmap = Bitmap.createScaledBitmap(rectBitmap, size, size, false);
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(target);
        if (radius == size / 2) {
            c.drawCircle(viewWidth / 2, viewHeight / 2, radius, paint);
        } else {
            if (Build.VERSION.SDK_INT >= 21) {
                c.drawRoundRect(getPaddingLeft(), getPaddingTop(), viewWidth - getPaddingRight(),
                        viewHeight - getPaddingBottom(), radius, radius, paint);
            } else {
                c.drawRect(getPaddingLeft(), getPaddingTop(), viewWidth - getPaddingRight(),
                        viewHeight - getPaddingBottom(), paint);
            }
        }
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        c.drawBitmap(scaleBitmap, 0, 0, paint);
        mDrawable = new BitmapDrawable(target);
        if (rectBitmap != null) {
            rectBitmap = null;
        }
        if (scaleBitmap != null) {
            scaleBitmap = null;
        }
        if (target != null) {
            target = null;
        }
    }

}

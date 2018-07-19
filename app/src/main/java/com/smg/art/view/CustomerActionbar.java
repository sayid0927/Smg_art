package com.smg.art.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.MenuRes;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;


import java.util.ArrayList;

/**
 * Created by Mervin on 16/7/13.
 * app action bar , including a left-layout back button ,
 * a middle-layout title view ,
 * a right-layout text menu
 * , and several customizable image menu
 */
public class CustomerActionbar extends RelativeLayout {

    private TextView titleText;
    private ImageView backImage;
    private ImageView iv_right;
    private TextView textAction;
    private TextView leftTitle;
    private ArrayList<ImageView> imageActions;
    private Context context;

    public CustomerActionbar(Context context) {
        this(context, null, 0);
    }

    public CustomerActionbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerActionbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(context).inflate(R.layout.actionbar_root_layout, this);
        titleText = (TextView) findViewById(R.id.actionbar_title);
        backImage = (ImageView) findViewById(R.id.actionbar_back);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        leftTitle = (TextView)findViewById(R.id.left_title);
        textAction = (TextView) findViewById(R.id.actionbar_text_action);
        imageActions = new ArrayList<>();
    }

    /**
     * 设置右边按钮颜色
     *
     * @param color 颜色
     */
    public void setTextActionColor(int color) {
        textAction.setTextColor(color);
    }

    /**
     * 设置右边按钮能否点击
     *
     * @param click 能否点击
     */
    public void setTextActionEditClick(boolean click) {
        textAction.setClickable(click);
    }

    /**
     * set the activity title
     *
     * @param titleId:activity title resource
     */
    public void setTitle(@StringRes int titleId) {
        titleText.setText(titleId);
    }

    /**
     * set the left title
     *
     * @param titleId:activity title resource
     */
    public void setLeftTitle(@StringRes int titleId) {
        leftTitle.setVisibility(VISIBLE);
        leftTitle.setText(titleId);
    }

    /**
     * set the activity title
     *
     * @param title :activity title
     */
    public void setTitle(String title) {
        if (TextUtils.isEmpty(title))
            throw new NullPointerException("title can never be empty !");
        titleText.setText(title);
    }

    /**
     * if need to show the back key on the left
     *
     * @param enable : true of false
     */
    public void setDisplayHomeAsEnable(boolean enable) {
        backImage.setVisibility(enable ? View.VISIBLE : View.GONE);
    }

    public ImageView getBackView() {
        return backImage;
    }

    /**
     * show the text menu
     *
     * @param actionTitle the title resource
     * @param action      what to do when clicked
     */
    public void setTextAction(@StringRes int actionTitle, View.OnClickListener action) {
        setTextAction(getContext().getString(actionTitle), action);
    }

    /**
     * show the text menu
     *
     * @param actionTitle the title
     * @param action      what to do when clicked
     */
    public void setTextAction(String actionTitle, View.OnClickListener action) {
        if (TextUtils.isEmpty(actionTitle))
            throw new NullPointerException("title can never be empty !");
        textAction.setText(actionTitle);
        textAction.setVisibility(View.VISIBLE);
        textAction.setOnClickListener(action);
    }


    /**
     * 显示右边按钮
     *
     * @param imageResId
     * @param action
     */
    public void setImageAction(@DrawableRes int imageResId,
                               View.OnClickListener action) {
        iv_right.setImageResource(imageResId);
        iv_right.setVisibility(View.VISIBLE);
        iv_right.setOnClickListener(action);
    }

    /**
     * 隐藏右边按钮
     */
    public void hindImageRight() {
        iv_right.setVisibility(View.GONE);
    }

    /**
     * 显示右边按钮
     */
    public void showImageRight() {
        iv_right.setVisibility(View.VISIBLE);
    }


    /**
     * add one image menu item
     *
     * @param imageResId   image resource
     * @param actionPrompt what to prompt when long-clicked
     * @param action       what to do when clicked
     * @return this
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public CustomerActionbar addImageAction(@DrawableRes int imageResId,
                                            @StringRes final int actionPrompt,
                                            OnClickListener action) {
        ImageView image = new ImageView(context);
        image.setImageResource(imageResId);
        image.setId(imageActions.size());
        image.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                ToastUtils.show(context, actionPrompt);
                return true;
            }
        });
        image.setTag(actionPrompt);
        image.setOnClickListener(action);
        LayoutParams layoutParams = new LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        if (imageActions != null && imageActions.size() != 0) {
            layoutParams.addRule(RelativeLayout.LEFT_OF, imageActions.get(imageActions.size() - 1).getId());
            ImageView leftImage = imageActions.get(imageActions.size() - 1);
            LayoutParams leftParams = (LayoutParams) leftImage.getLayoutParams();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                leftParams.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            }
            leftParams.addRule(RelativeLayout.LEFT_OF, image.getId());
//            layoutParams.addRule(RelativeLayout.RIGHT_OF, imageActions.get(imageActions.size() - 1).getId());
        }
//        else{

//        }
        layoutParams.rightMargin = 20;
        image.setPadding(20, 20, 20, 20);
        image.setLayoutParams(layoutParams);
        int[] attrs = new int[]{android.R.attr.selectableItemBackground};
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs);
        Drawable drawable = a.getDrawable(0);
        image.setBackground(drawable);
        a.recycle();
        addView(image);
        imageActions.add(image);
        return this;
    }


    /**
     * get the action menu item
     *
     * @param id the action id
     * @return the image action
     */
    public ImageView getActionItemById(int id) {
        for (ImageView image : imageActions) {
            if (image.getId() == id) {
                return image;
            }
        }
        return null;
    }


    /**
     * init menu items from menu file
     *
     * @param activity current activity
     * @param menuRes  menu resource file
     */

    public void inflateMenuFromResource(Activity activity, @MenuRes int menuRes) {
        PopupMenu popupMenu = new PopupMenu(activity, null);
        Menu menu = popupMenu.getMenu();
        activity.getMenuInflater().inflate(menuRes, menu);

        int menuSize = menu.size();

        for (int i = 0; i < menuSize; i++) {
            MenuItem item = menu.getItem(i);
            ImageView image = new ImageView(getContext());
            image.setImageDrawable(item.getIcon());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            layoutParams.leftMargin = 10;
            layoutParams.rightMargin = 10;
            image.setId(item.getItemId());
            int[] attrs = new int[]{android.R.attr.selectableItemBackground};
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs);
            Drawable drawable = a.getDrawable(0);
            image.setBackground(drawable);
            a.recycle();
            addView(image, layoutParams);
            imageActions.add(image);
        }
    }


}

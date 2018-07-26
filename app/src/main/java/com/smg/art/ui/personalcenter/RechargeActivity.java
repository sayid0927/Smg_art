package com.smg.art.ui.personalcenter;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.component.AppComponent;
import com.smg.art.utils.KeyBoardUtils;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class RechargeActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    AutoRelativeLayout rlBack;
    @BindView(R.id.left_title)
    TextView leftTitle;
    @BindView(R.id.actionbar_title)
    TextView actionbarTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.actionbar_text_action)
    TextView actionbarTextAction;
    @BindView(R.id.et_context)
    EditText etContext;
    @BindView(R.id.iv_del)
    ImageView ivDel;
    @BindView(R.id.confirm)
    TextView confirm;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!etContext.getText().toString().trim().equals("")) {
                setInputStyle(View.VISIBLE);
            } else {
                setInputStyle(View.GONE);
            }
        }
    };

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.recharge;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void initView() {
        actionbarTitle.setText(R.string.recharge);
        etContext.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        //切换后将EditText光标置于末尾
        CharSequence charSequence = etContext.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }

        ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etContext.setText("");
            }
        });

        setInputStyle(View.GONE);
        etContext.addTextChangedListener(textWatcher);
    }

    /**
     * 设置输入时的控件状态
     *
     * @param
     * @param visible 是否显示删除图标
     * @param
     */
    private void setInputStyle(int visible) {
        ivDel.setVisibility(visible);
    }

    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                KeyBoardUtils.hiddenKeyboart(this);
                finish();
                break;
        }
    }
}

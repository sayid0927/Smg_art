package com.smg.art.ui.personalcenter;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.bean.CardUrlBean;
import com.smg.art.bean.ComplaintBean;
import com.smg.art.bean.Feedback;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.picutils.ImageInfo;
import com.smg.art.picutils.ImagePreviewActivity;
import com.smg.art.presenter.contract.activity.ComplaintContract;
import com.smg.art.presenter.impl.activity.ComplaintPresenter;
import com.smg.art.ui.personalcenter.adapter.AddImageGridAdapter;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.L;
import com.smg.art.utils.ListToStringUtils;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.view.FlowRadioGroup;
import com.smg.art.view.NoScrollGridView;
import com.yho.image.imageselectorbrowser.ImageSelectorActivity;
import com.yho.image.imp.ImageSelectorUtils;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Mervin on 2018/8/9 0009.
 */

public class StartComplaintActivity extends BaseActivity implements AddImageGridAdapter.OnItemClickListener, ComplaintContract.View {
    @Inject
    ComplaintPresenter mPresenter;
    private static final int MAX_IMAGE_SELECT_COUNT = 3;
    private static final int REQUEST_SHOW_GALLERY = 5;
    private static final int REQUEST_CODE_SELECT_IMAGES = 2001;
    @BindView(R.id.radioGroupID)
    FlowRadioGroup radioGroupID;
    @BindView(R.id.cause)
    TextView cause;
    @BindView(R.id.girdview)
    NoScrollGridView girdview;
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
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.check_auction)
    TextView checkAuction;
    private AddImageGridAdapter addImageGridAdapter;
    private List<String> list = new ArrayList<String>();
    private List<String> urlList = new ArrayList<String>();
    private List<Feedback.DataBean> dataList = new ArrayList<Feedback.DataBean>();
    //反馈类型
    private String type_id;
    private String type_name;
    private int auctionId;
    private int goodsId;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.start_complaint;
    }


    @Override
    public void attachView() {
        mPresenter.attachView(this, this);
    }

    @Override
    public void detachView() {
        mPresenter.detachView();
    }


    @Override
    public void initView() {
        actionbarTitle.setText(R.string.start_complaint);
        auctionId = getIntent().getIntExtra("auctionId", 0);
        goodsId= getIntent().getIntExtra("goodsId", 0);
        Feedback.DataBean dataBean1 = new Feedback.DataBean();
        Feedback.DataBean dataBean2 = new Feedback.DataBean();
        Feedback.DataBean dataBean3 = new Feedback.DataBean();
        dataBean1.setId("1");
        dataBean1.setT_value("产品建议");
        dataBean2.setId("2");
        dataBean2.setT_value("商品破损");
        dataBean3.setId("3");
        dataBean3.setT_value("违规投诉");
        dataList.add(dataBean1);
        dataList.add(dataBean2);
        dataList.add(dataBean3);
        addData(dataList);
        addImageGridAdapter = new AddImageGridAdapter(this, list, MAX_IMAGE_SELECT_COUNT);
        girdview.setAdapter(addImageGridAdapter);
        addImageGridAdapter.setOnItemClickListener(this);
        cause.setFocusable(true);
        cause.setFocusableInTouchMode(true);
        cause.requestFocus();
        etContext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvNum.setText(etContext.getText().length() + "/255");
            }
        });
    }

    @OnClick({R.id.rl_back,R.id.check_auction})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                KeyBoardUtils.hiddenKeyboart(this);
                finish();
                break;
            case R.id.check_auction:
                if(checkUp()){
                    mPresenter.FetchComplaint("memberId",String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),"auctionId",String.valueOf(auctionId),"goodsId",String.valueOf(goodsId),
                            "type",type_id,"content",etContext.getText().toString(),"imageUrl", ListToStringUtils.listToString(urlList));
                }
                break;
        }
    }

    /**
     * 检查输入内容
     *
     * @return
     */
    private boolean checkUp() {

        if (TextUtils.isEmpty(type_id) || TextUtils.isEmpty(type_name)) {
            ToastUtils.showShortToast("请选择反馈类型");
            return false;
        }
        if (TextUtils.isEmpty(etContext.getText().toString())) {
            ToastUtils.showShortToast("投诉内容不能为空");
            return false;
        }
        if (urlList.size() < 1) {
            ToastUtils.showShortToast("请上传图片");
            return false;
        }

        return true;
    }

    /**
     * 动态添加radiogroup
     */
    private void addData(final List<Feedback.DataBean> list) {
        for (int i = 0; i < list.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT
                    , RadioGroup.LayoutParams.WRAP_CONTENT);
            //设置RadioButton边距 (int left, int top, int right, int bottom)
            // lp.setMargins(15, 0, 15, 0);
            //设置RadioButton背景
            //radioButton.setBackgroundResource(R.drawable.xx);
            //设置RadioButton的样式
            // radioButton.setButtonDrawable(R.drawable.radio_bg);
            //设置文字距离四周的距离
            radioButton.setPadding(0, 0, 48, 0);
            //设置文字
            radioButton.setText(list.get(i).getT_value());
            radioButton.setTextSize(10);
            radioButton.setTextColor(getResources().getColor(R.color.black_212121));

            final int finalI = i;
            //设置radioButton的点击事件
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    type_id = list.get(finalI).getId();
                    type_name = list.get(finalI).getT_value();
                }
            });
            //将radioButton添加到radioGroup中
            radioGroupID.addView(radioButton);
        }
    }

    @Override
    public void onItemImageClick(int position) {
        if (position == list.size()) {
            if (list.size() < MAX_IMAGE_SELECT_COUNT) {
                ImageSelectorUtils.show(this, ImageSelectorActivity.Mode.MODE_MULTI, true, MAX_IMAGE_SELECT_COUNT - list.size());
            }
        } else {
            ArrayList<ImageInfo> imageInfo = new ArrayList<>();
            for (String imageUrl : list) {
                ImageInfo info = new ImageInfo();
                info.setThumbnailUrl(imageUrl);
                info.setBigImageUrl(imageUrl);
                imageInfo.add(info);
            }
            Intent intent = new Intent(this, ImagePreviewActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(ImagePreviewActivity.IMAGE_INFO, (Serializable) imageInfo);
            bundle.putInt(ImagePreviewActivity.CURRENT_ITEM, position);
            bundle.putBoolean(ImagePreviewActivity.IS_DELETE_ABLE, true);
            intent.putExtras(bundle);
            startActivityForResult(intent, REQUEST_SHOW_GALLERY);
        }
    }

    public void upLoadImages(List<String> imagePaths) {
        for (int i = 0; i < imagePaths.size(); i++) {
            upLoadImage(imagePaths.get(i));
        }
    }

    public void upLoadImage(String imagePath) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM); //表单类型
        File Mainfile = new File(String.valueOf(imagePath));
        RequestBody Mainbody = RequestBody.create(MediaType.parse("multipart/form-data"), Mainfile);//表单类型
        builder.addFormDataPart("type", "member");//传入服务器需要的key，和相应value值
        builder.addFormDataPart("upfile", Mainfile.getName(), Mainbody); //添加图片数据，body创建的请求体
        List<MultipartBody.Part> parts = builder.build().parts();
        mPresenter.FetchUploadFile(parts);
    }

    @Override
    public void onItemCancelClick(int position) {
        list.remove(position);
        urlList.remove(position);
        addImageGridAdapter.update(list);

        for (int i = 0; i < urlList.size(); i++) {
            L.e(urlList.get(i));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_SELECT_IMAGES:
                if (resultCode == RESULT_OK) {
                    List<String> imageSelectorList = ImageSelectorUtils.getImageSelectorList(requestCode, resultCode, data);
                    if (imageSelectorList != null) {
                        list.addAll(imageSelectorList);
                        upLoadImages(imageSelectorList);
                        addImageGridAdapter.update(list);
                    }
                }
                break;
            case REQUEST_SHOW_GALLERY:
                if (resultCode == RESULT_OK) {
                    List<ImageInfo> imageInfos =
                            (List<ImageInfo>) data.getSerializableExtra(ImagePreviewActivity.IMAGE_INFO);
                    list.clear();
                    if (imageInfos.size() > 0) {
                        for (ImageInfo info : imageInfos) {
                            list.add(info.getBigImageUrl());
                        }
                    }
                    addImageGridAdapter.update(list);
                }
                break;
        }
    }

    @Override
    public void FetchComplaintSuccess(ComplaintBean complaintBean) {
            if(complaintBean.getStatus()==1){
                EventBus.getDefault().post(complaintBean);
                ToastUtils.showShortToast("投诉成功");
                this.finish();
            }else {
                ToastUtils.showShortToast(complaintBean.getMsg());
            }
    }

    @Override
    public void FetchUploadFileSuccess(CardUrlBean cardUrlBean) {
        if (cardUrlBean.getStatus() == 1) {
            urlList.add(cardUrlBean.getData());
        } else {
            ToastUtils.showShortToast(cardUrlBean.getMsg());
        }
    }

    @Override
    public void showError(String message) {

    }
}

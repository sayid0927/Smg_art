package com.smg.art.ui.personalcenter;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.smg.art.BuildConfig;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.base.CardUrlBean;
import com.smg.art.base.Constant;
import com.smg.art.bean.CreatWordsBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.AuthenticationContract;
import com.smg.art.presenter.impl.activity.AuthenticationActivityPresenter;
import com.smg.art.utils.ClipFileUtil;
import com.smg.art.utils.CommonUtil;
import com.smg.art.utils.LocalAppConfigUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AuthenticationActivity extends BaseActivity implements AuthenticationContract.View {
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    @Inject
    AuthenticationActivityPresenter mPresenter;
    @BindView(R.id.rl_back)
    AutoRelativeLayout rlBack;
    @BindView(R.id.actionbar_title)
    TextView actionbarTitle;
    @BindView(R.id.actionbar_text_action)
    TextView actionbarTextAction;
    @BindView(R.id.iv_tick)
    ImageView ivTick;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.iv_tick1)
    ImageView ivTick1;
    @BindView(R.id.iv_tick2)
    ImageView ivTick2;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_card_num)
    EditText edCardNum;
    @BindView(R.id.bt_net)
    Button btNet;
    @BindView(R.id.ll_net)
    LinearLayout llNet;
    @BindView(R.id.iv_main)
    ImageView ivMain;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_hand)
    ImageView ivHand;
    @BindView(R.id.bt_post)
    Button btPost;
    @BindView(R.id.ll_post)
    LinearLayout llPost;
    private String name, CardNum;
    private File tempMainFile, tempBackFile, tempHandFile;
    private int type;
    private int postType = 0;
    private String mainUri, backUri, handUri;
    private MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
    private List<MultipartBody.Part> parts;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_authentication;
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
        setSwipeBackEnable(true);
        actionbarTitle.setText(R.string.authentication);

    }

    @Override
    public void showError(String message) {
        ToastUtils.showLongToast(message);
    }

    @OnClick({R.id.bt_net, R.id.iv_main, R.id.iv_back, R.id.iv_hand, R.id.bt_post, R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back: //返回
                finish();
                break;
            case R.id.bt_net:  //下一步
//                if (checkUp()) {
                    name = edName.getText().toString().trim();
                    CardNum = edCardNum.getText().toString().trim();
                    if (llNet.getVisibility() == View.VISIBLE && llPost.getVisibility() == View.GONE) {
                        llNet.setVisibility(View.GONE);
                        llPost.setVisibility(View.VISIBLE);
                        ivTick1.setBackground(this.getResources().getDrawable(R.drawable.tick_renzheng_green));
                    }
//                }
                break;
            case R.id.iv_main:  // 正面昭
                type = 1;
                Camera();
                break;
            case R.id.iv_back:// 反面昭
                type = 2;
                Camera();
                break;
            case R.id.iv_hand: //手执照
                type = 3;
                Camera();
                break;
            case R.id.bt_post://提交
                if(tempMainFile!=null && tempBackFile !=null && tempHandFile !=null){
                    postType = 1;
                    File Mainfile = new File(String.valueOf(tempMainFile));
                    RequestBody Mainbody = RequestBody.create(MediaType.parse("multipart/form-data"), Mainfile);
                    builder.addFormDataPart("type", "member");
                    builder.addFormDataPart("upfile", Mainfile.getName(), Mainbody);
                    parts = builder.build().parts();
                    mPresenter.FetchUploadFile(parts);
                }else {
                    ToastUtils.showLongToast("请选择照片");
                }
                break;
        }
    }

    private void Camera() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(AuthenticationActivity.this, Manifest.permission.CAMERA);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AuthenticationActivity.this, new String[]{Manifest.permission.CAMERA}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                return;
            } else {
                gotoCamera();
            }
        } else {
            gotoCamera();
        }
    }

    /**
     * 跳转到照相机
     */
    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        //创建拍照存储的图片文件

        switch (type) {
            case 1:
                tempMainFile = new File(ClipFileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
                break;
            case 2:
                tempBackFile = new File(ClipFileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
                break;
            case 3:
                tempHandFile = new File(ClipFileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
                break;
        }

        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = null;
            switch (type) {
                case 1:
                    contentUri = FileProvider.getUriForFile(AuthenticationActivity.this, BuildConfig.APPLICATION_ID + ".fileProvider", tempMainFile);
                    break;
                case 2:
                    contentUri = FileProvider.getUriForFile(AuthenticationActivity.this, BuildConfig.APPLICATION_ID + ".fileProvider", tempBackFile);
                    break;
                case 3:
                    contentUri = FileProvider.getUriForFile(AuthenticationActivity.this, BuildConfig.APPLICATION_ID + ".fileProvider", tempHandFile);
                    break;
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            switch (type) {
                case 1:
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempMainFile));
                    break;
                case 2:
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempBackFile));
                    break;
                case 3:
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempHandFile));
                    break;
            }

        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    switch (type) {
                        case 1:
                            Glide.with(this).load(tempMainFile).into(ivMain);
                            break;
                        case 2:
                            Glide.with(this).load(tempBackFile).into(ivBack);
                            break;
                        case 3:
                            Glide.with(this).load(tempHandFile).into(ivHand);
                            break;
                    }
                }
                break;
        }
    }


    /**
     * 检查输入有错
     */
    private boolean checkUp() {
        if (TextUtils.isEmpty(edName.getText().toString())) {
            ToastUtils.showLongToast("姓名不能为空");
            return false;
        }

        if (TextUtils.isEmpty(edCardNum.getText().toString())) {
            ToastUtils.showLongToast("身份证号不能为空");
            return false;
        }
        try {
            if (!CommonUtil.IDCardValidate(edCardNum.getText().toString())) {
                ToastUtils.showLongToast("请输入有效身份证号码");
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 上传图片
     */
    @Override
    public void FetchUploadFileSuccess(CardUrlBean cardUrlBean) {
        switch (postType) {
            case 1:

                postType = 2;
                File Backfile = new File(String.valueOf(tempBackFile));
                RequestBody Backbody = RequestBody.create(MediaType.parse("multipart/form-data"), Backfile);
                builder.addFormDataPart("type", "member");
                builder.addFormDataPart("upfile", Backfile.getName(), Backbody);
                parts = builder.build().parts();
                mainUri =  cardUrlBean.getData();
                mPresenter.FetchUploadFile(parts);

                break;
            case 2:
                postType = 3;
                File Headfile = new File(String.valueOf(tempHandFile));
                RequestBody Headbody = RequestBody.create(MediaType.parse("multipart/form-data"), Headfile);
                builder.addFormDataPart("type", "member");
                builder.addFormDataPart("upfile", Headfile.getName(), Headbody);
                parts = builder.build().parts();
                backUri = cardUrlBean.getData();
                mPresenter.FetchUploadFile(parts);
                break;
            case 3:
                handUri =cardUrlBean.getData();
                mPresenter.FetchMemberAuthSave("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()),
                        "realName", name, "cardNo", CardNum, "cardUrl", mainUri + ";" + backUri + ";" + handUri);
                postType = 0;
                break;
        }
    }

    /**
     * 新增实名认证
     */
    @Override
    public void FetchMemberAuthSaveSuccess(CreatWordsBean cardUrlBean) {
        ToastUtils.showLongToast("提交成功,等待审核");
        this.finish();
    }
}

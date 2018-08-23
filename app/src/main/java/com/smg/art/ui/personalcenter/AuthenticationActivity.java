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
import com.smg.art.bean.CardUrlBean;
import com.smg.art.bean.CreatWordsBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.AuthenticationContract;
import com.smg.art.presenter.impl.activity.AuthenticationActivityPresenter;
import com.smg.art.utils.CameraCanUseUtils;
import com.smg.art.utils.ClipFileUtil;
import com.smg.art.utils.KeyBoardUtils;
import com.smg.art.utils.L;
import com.smg.art.utils.LocalAppConfigUtil;
import com.smg.art.view.PopDialog;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.File;
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
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;

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

    PopDialog popDialog;

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
                KeyBoardUtils.hiddenKeyboart(this);
                finish();
                break;
            case R.id.bt_net:  //下一步
//                if (checkUp()) {
                KeyBoardUtils.hiddenKeyboart(this);
                name = edName.getText().toString().trim();
                CardNum = edCardNum.getText().toString().trim();
                if (llNet.getVisibility() == View.VISIBLE && llPost.getVisibility() == View.GONE) {
                    llNet.setVisibility(View.GONE);
                    llPost.setVisibility(View.VISIBLE);
                    ivTick1.setBackgroundResource(R.drawable.tick_renzheng_green);
                }
//                }
                break;
            case R.id.iv_main:  // 正面昭
                type = 1;
                uploadHeadImage();
                break;
            case R.id.iv_back:// 反面昭
                type = 2;
                uploadHeadImage();
                break;
            case R.id.iv_hand: //手执照
                type = 3;
                uploadHeadImage();
                break;
            case R.id.bt_post://提交
                if (tempMainFile != null && tempBackFile != null && tempHandFile != null) {
                    postType = 1;
                    File Mainfile = new File(String.valueOf(tempMainFile));
                    RequestBody Mainbody = RequestBody.create(MediaType.parse("multipart/form-data"), Mainfile);

                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    List<MultipartBody.Part> parts;

                    builder.addFormDataPart("type", "member");
                    builder.addFormDataPart("upfile", Mainfile.getName(), Mainbody);
                    parts = builder.build().parts();
                    mPresenter.FetchUploadFile(parts);

                } else {
                    ToastUtils.showLongToast("请选择照片");
                }
                break;
        }
    }


    /**
     * 上传图片
     */
    @Override
    public void FetchUploadFileSuccess(CardUrlBean cardUrlBean) {
        switch (postType) {
            case 1:
                MultipartBody.Builder builder2 = new MultipartBody.Builder().setType(MultipartBody.FORM);
                List<MultipartBody.Part> parts2;
                postType = 2;
                File Backfile = new File(String.valueOf(tempBackFile));
                RequestBody Backbody = RequestBody.create(MediaType.parse("multipart/form-data"), Backfile);
                builder2.addFormDataPart("type", "member");
                builder2.addFormDataPart("upfile", Backfile.getName(), Backbody);
                parts2 = builder2.build().parts();
                mainUri = cardUrlBean.getData();
                mPresenter.FetchUploadFile(parts2);

                break;
            case 2:
                MultipartBody.Builder builder3 = new MultipartBody.Builder().setType(MultipartBody.FORM);
                List<MultipartBody.Part> parts3;
                postType = 3;
                File Headfile = new File(String.valueOf(tempHandFile));
                RequestBody Headbody = RequestBody.create(MediaType.parse("multipart/form-data"), Headfile);
                builder3.addFormDataPart("type", "member");
                builder3.addFormDataPart("upfile", Headfile.getName(), Headbody);
                parts3 = builder3.build().parts();
                backUri = cardUrlBean.getData();
                mPresenter.FetchUploadFile(parts3);
                break;
            case 3:

                handUri = cardUrlBean.getData();
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


    /**
     * 上传头像
     */
    private void uploadHeadImage() {
        popDialog = new PopDialog(this, R.layout.layout_popupwindow);
        popDialog.show();
        popDialog.findViewById(R.id.btn_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(AuthenticationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(AuthenticationActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到调用系统相机
                    if (CameraCanUseUtils.isCameraCanUse()) {
                        //摄像头可用
                        gotoCamera();
                    } else {
                        //摄像头不可用
                        ToastUtils.showShortToast("没相机权限，请到应用程序权限管理开启权限");
                        //跳转至app设置
                        getAppDetailSettingIntent();
                    }
                }
                popDialog.dismiss();
            }
        });
        popDialog.findViewById(R.id.btn_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(AuthenticationActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(AuthenticationActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到相册
                    gotoPhoto();
                }
                popDialog.dismiss();
            }
        });
        popDialog.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popDialog.dismiss();
            }
        });
    }

    //跳转app设置
    private void getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", this.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", this.getPackageName());
        }
        startActivity(localIntent);
    }

    /**
     * 外部存储权限申请返回
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoCamera();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoPhoto();
            }
        }
    }

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        L.e("evan", "*****************打开图库********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
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
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }

                    switch (type) {
                        case 1:
                            tempMainFile = new File((ClipFileUtil.getRealFilePathFromUri(getApplicationContext(), uri)));
                            Glide.with(this).load(tempMainFile).into(ivMain);
                            break;
                        case 2:
                            tempBackFile = new File((ClipFileUtil.getRealFilePathFromUri(getApplicationContext(), uri)));
                            Glide.with(this).load(tempBackFile).into(ivBack);
                            break;
                        case 3:
                            tempHandFile = new File((ClipFileUtil.getRealFilePathFromUri(getApplicationContext(), uri)));
                            Glide.with(this).load(tempHandFile).into(ivHand);
                            break;
                    }
                }
                break;
        }
    }
}

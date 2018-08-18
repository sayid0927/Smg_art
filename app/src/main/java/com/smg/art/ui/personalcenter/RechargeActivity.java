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
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.smg.art.BuildConfig;
import com.smg.art.R;
import com.smg.art.base.BaseActivity;
import com.smg.art.base.CardUrlBean;
import com.smg.art.bean.CheckBankCardBean;
import com.smg.art.bean.ReChargeBean;
import com.smg.art.component.AppComponent;
import com.smg.art.component.DaggerMainComponent;
import com.smg.art.presenter.contract.activity.ReChargeContract;
import com.smg.art.presenter.impl.activity.ReChargePresenter;
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

/**
 * Created by Mervin on 2018/7/26 0026.
 */

public class RechargeActivity extends BaseActivity implements ReChargeContract.View {
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    @Inject
    ReChargePresenter mPresenter;
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
    @BindView(R.id.payee)
    TextView payee;
    @BindView(R.id.bank_icon)
    ImageView bankIcon;
    @BindView(R.id.bank_name)
    TextView bankName;
    @BindView(R.id.bank_card)
    TextView bankCard;
    @BindView(R.id.up_pic)
    ImageView upPic;
    CheckBankCardBean mCheckBankCardBean;
    String url;
    private int type;
  //  private File tempMainFile;
    PopDialog popDialog;
    //调用照相机返回图片文件
    private File tempFile;
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
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.recharge;
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
        getBankData();
    }

    public void getBankData() {
        mPresenter.FetchCheckBankCard("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "type", "1");
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

    @OnClick({R.id.rl_back, R.id.confirm, R.id.up_pic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                KeyBoardUtils.hiddenKeyboart(this);
                finish();
                break;
            case R.id.confirm:
                if (TextUtils.isEmpty(etContext.getText().toString())) {
                    ToastUtils.showShortToast("请输入充值金额");
                } else {
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM); //表单类型
                    File Mainfile = new File(String.valueOf(tempFile));
                    RequestBody Mainbody = RequestBody.create(MediaType.parse("multipart/form-data"), Mainfile);//表单类型
                    builder.addFormDataPart("type", "wallet");//传入服务器需要的key，和相应value值
                    builder.addFormDataPart("upfile", Mainfile.getName(), Mainbody); //添加图片数据，body创建的请求体
                    List<MultipartBody.Part> parts = builder.build().parts();
                    mPresenter.FetchUploadFile(parts);
                }
                break;
            case R.id.up_pic:
                type = 1;
                uploadHeadImage();
           /*     if (Build.VERSION.SDK_INT >= 23) {
                    int checkCallPhonePermission = ContextCompat.checkSelfPermission(RechargeActivity.this, Manifest.permission.CAMERA);
                    if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(RechargeActivity.this, new String[]{Manifest.permission.CAMERA}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                        return;
                    } else {
                        gotoCamera();
                    }
                } else {
                    gotoCamera();
                }*/

                break;
        }
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
                //权限判断
               /* if (ContextCompat.checkSelfPermission(RechargeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(RechargeActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
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
                }*/
                popDialog.dismiss();
            }
        });
        popDialog.findViewById(R.id.btn_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(RechargeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(RechargeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
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
        tempFile = new File(ClipFileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");

        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(RechargeActivity.this, BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    /**
     * 跳转到照相机
     */
   /* private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        //创建拍照存储的图片文件

        switch (type) {
            case 1:
                tempMainFile = new File(ClipFileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
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
                    contentUri = FileProvider.getUriForFile(RechargeActivity.this, BuildConfig.APPLICATION_ID + ".fileProvider", tempMainFile);
                    break;

            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            switch (type) {
                case 1:
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempMainFile));
                    break;
            }

        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    switch (type) {
                        case 1:
                            Glide.with(this).load(tempFile).into(upPic);
                            break;
                    }
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode ==RESULT_OK) {
                    Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    tempFile = new File((ClipFileUtil.getRealFilePathFromUri(getApplicationContext(), uri)));
                    switch (type) {
                        case 1:
                            Glide.with(this).load(tempFile).into(upPic);
                            break;
                    }
                }
                break;
        }
    }

    @Override
    public void FetchReChargeSuccess(ReChargeBean reChargeBean) {
        if (reChargeBean.getStatus() == 1) {
            ToastUtils.showShortToast("充值申请成功!");
            finish();
        } else {
            ToastUtils.showShortToast(reChargeBean.getMsg());
        }

    }

    @Override
    public void FetchCheckBankCardSuccess(CheckBankCardBean checkBankCardBean) {
        if (checkBankCardBean.getStatus() == 1) {
            mCheckBankCardBean = checkBankCardBean;
            bankName.setText(checkBankCardBean.getData().getBank());
            bankCard.setText(checkBankCardBean.getData().getCardNo());
            payee.setText(checkBankCardBean.getData().getReceiptName());
        } else {
            ToastUtils.showShortToast(checkBankCardBean.getMsg());
        }
    }

    @Override
    public void FetchUploadFileSuccess(CardUrlBean cardUrlBean) {
        if (cardUrlBean.getStatus() == 1) {
            if (!TextUtils.isEmpty(cardUrlBean.getData()))
                mPresenter.FetchReCharge("memberId", String.valueOf(LocalAppConfigUtil.getInstance().getCurrentMerberId()), "cardNo", mCheckBankCardBean.getData().getCardNo(),
                        "amount", etContext.getText().toString(), "voucherUrl", cardUrlBean.getData());
        } else {
            ToastUtils.showShortToast(cardUrlBean.getMsg());
        }
    }

    @Override
    public void showError(String message) {

    }
}

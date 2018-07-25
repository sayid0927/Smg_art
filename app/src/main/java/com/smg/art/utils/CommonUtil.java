package com.smg.art.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：
 * @ClassName ： CommonUtil
 * @Description: 通用工具类
 * @date ： 2015-3-11 下午3:47:34
 */

public class CommonUtil extends Activity {

    private static final String SEP1 = "#";
    private static final String SEP2 = "|";
    private static final String SEP3 = "=";

    /**
     * 获取app版本号
     *
     * @param context
     * @param version
     * @return
     */
    public static String getVersion(Context context, String version) {

        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            return info.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace(System.err);

        }

        return version;
    }


    //获取版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //获取版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    //通过PackageInfo得到的想要启动的应用的包名
    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pInfo = null;

        try {
            //通过PackageManager可以得到PackageInfo
            PackageManager pManager = context.getPackageManager();
            pInfo = pManager.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pInfo;
    }

    /**
     * dp转px
     *
     * @param context
     * @param
     * @return
     */
    public static int dp2px(Context context, float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().density);
    }

    // 判断手机格式是否正确
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^1[3|4|5|6|7|8|][0-9]{9}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    // 判断密码格式是否正确
    public static boolean isPwd(String pwd) {
        Pattern p = Pattern.compile("^[a-zA-Z\\d_\\s]*$");

        Matcher m = p.matcher(pwd);

        return m.matches();
    }

    // 判断密码格式是否是数字和字母组合，并且是6-16位
    public static boolean isPassword(String pwd) {
        Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");

        Matcher m = p.matcher(pwd);

        return m.matches();
    }

    // 判断邮编是否正确
    public static boolean isZipNO(String zipString) {
        String str = "^[1-9][0-9]{5}$";
        return Pattern.compile(str).matcher(zipString).matches();
    }

    /**
     * 判断邮箱是否合法
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 获取控件的高度，如果获取的高度为0，则重新计算尺寸后再返回高度
     *
     * @param view
     * @return
     */
    public static int getViewMeasuredHeight(View view) {
//        int height = view.getMeasuredHeight();
//        if(0 < height){
//            return height;
//        }
        calcViewMeasure(view);
        return view.getMeasuredHeight();
    }

    /**
     * 获取控件的宽度，如果获取的宽度为0，则重新计算尺寸后再返回宽度
     *
     * @param view
     * @return
     */
    public static int getViewMeasuredWidth(View view) {
//        int width = view.getMeasuredWidth();
//        if(0 < width){
//            return width;
//        }
        calcViewMeasure(view);
        return view.getMeasuredWidth();
    }

    /**
     * 测量控件的尺寸
     *
     * @param view
     */
    public static void calcViewMeasure(View view) {

        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        view.measure(width, expandSpec);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getTime(int i) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_YEAR, i);
        return df.format(calendar.getTime());
    }

    // 时间戳======>字符串
    @SuppressLint("SimpleDateFormat")
    public static String getStrTime(String cc_time) {
        String re_StrTime = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lcc_time = Long.valueOf(cc_time) * 1000;
        re_StrTime = sdf.format(new Date(lcc_time));

        return re_StrTime;

    }

    @SuppressLint("SimpleDateFormat")
    public static String getStrTime1(String cc_time) {
        String re_StrTime = null;

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long lcc_time = Long.valueOf(cc_time) * 1000;
        re_StrTime = sdf.format(new Date(lcc_time));
        return re_StrTime;

    }

    /**
     * List转换String
     *
     * @param list :需要转换的List
     * @return String转换后的字符串
     */
    public static String ListToString(List<?> list) {
        StringBuffer sb = new StringBuffer();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == null || list.get(i) == "") {
                    continue;
                }
                // 如果值是list类型则调用自己 
                if (list.get(i) instanceof List) {
                    sb.append(ListToString((List<?>) list.get(i)));
                    sb.append(SEP1);
                } else if (list.get(i) instanceof Map) {
                    sb.append(MapToString((Map<?, ?>) list.get(i)));
                    sb.append(SEP1);
                } else {
                    sb.append(list.get(i));
                    sb.append(SEP1);
                }
            }
        }
        return "L" + sb.toString();
    }

    /**
     * Map转换String
     *
     * @param map :需要转换的Map
     * @return String转换后的字符串
     */
    public static String MapToString(Map<?, ?> map) {
        StringBuffer sb = new StringBuffer();
        // 遍历map 
        for (Object obj : map.keySet()) {
            if (obj == null) {
                continue;
            }
            Object key = obj;
            Object value = map.get(key);
            if (value instanceof List<?>) {
                sb.append(key.toString() + SEP1 + ListToString((List<?>) value));
                sb.append(SEP2);
            } else if (value instanceof Map<?, ?>) {
                sb.append(key.toString() + SEP1
                        + MapToString((Map<?, ?>) value));
                sb.append(SEP2);
            } else {
                sb.append(key.toString() + SEP3 + value.toString());
                sb.append(SEP2);
            }
        }
        return "M" + sb.toString();
    }

    /**
     * 替换 <img src 相对地址 为绝对地址
     *
     * @param str         替换的字符串
     * @param replacement 替换增加的内容
     * @return
     */
    public static String replacImgSrc(String str, String replacement) {
        return str.replaceAll("(.*?)src=\"(.*?)", "$1src=\"" + replacement + "$2");
    }


    public static String clearStyle(String html) {
        html = html.replaceAll("\\r\\n", "");
        html = html.replaceAll("\\n", "");
        html = html.replaceAll("\\r", "");
        html = html.replaceAll("\\&nbsp\\;", "");
        html = html.replaceAll("class=[^\\s|>]*", "");
        html = html.replaceAll("style=\"[^>]*\"", "");
        html = html.replaceAll("align=[^\\s|>]*", "");
        html = html.replaceAll("<b [^>]*>", "<b>");
        html = html.replaceAll("<br [^>]*>", "<br />");
        html = html.replaceAll("<i [^>]*>", "<i>");
        html = html.replaceAll("<li [^>]*>", "<li>");
        html = html.replaceAll("ul [^>]*>", "<ul>");
        html = html.replaceAll("<em>", "<i>");
        html = html.replaceAll("<\\/em>", "</i>");
        html = html.replaceAll("<\\?xml:[^>]*>", "");
        html = html.replaceAll("<\\/?st1:[^>]*>", "");
        html = html.replaceAll("<\\/?[a-z]\\:[^>]*>", "");
        html = html.replaceAll("<\\/?font[^>]*>", "");
        html = html.replaceAll("<\\/?span[^>]*>", "");
        html = html.replaceAll("<\\/?div[^>]*>", "");
        html = html.replaceAll("<\\/?pre[^>]*>", "");
        html = html.replaceAll("<\\/?h[1-6][^>]*>", "");
        html = html.replaceAll("width=\"[0-9]*\"", "");
        html = html.replaceAll("height=\"[0-9]*\"", "");
        return html;

    }
    
	/*public static String replacImgSrcWidth(String html, String urlImg, int width) {

		Document doc = Jsoup.parse(html);
		Elements pngs = doc.select("img");
		
		for (Element element : pngs) {
			String imgUrl = element.attr("src");
			int height = StringUtils.toInt(element.attr("height"), 0);
			if (imgUrl.trim().startsWith("/") && height > 200) {
				imgUrl = urlImg + imgUrl;
				element.attr("src", imgUrl);
			}
			element.attr("width", "100%");
			element.attr("height", "auto");
		}
		
		doc.select("img").remove();
		
		
//		Elements ele_Img = doc.getElementsByTag("img");
//		for (Element e_Img : ele_Img) {
//			String imgUrl = e_Img.attr("src");
//			if (imgUrl.trim().startsWith("/")) {
//				imgUrl = urlImg + imgUrl;
//				e_Img.attr("src", imgUrl);
//			}
//			e_Img.attr("width", "100%");
//			e_Img.attr("height", "" + width);
//		}
		
		return doc.toString();

	}
    
    */

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    // 获取当前版本号
    public static int getCurrentVersionCode(Context mContext) {
        int versionCode = 1;
        try {
            PackageInfo info = mContext.getPackageManager().getPackageInfo(
                    mContext.getPackageName(), 0);

            versionCode = info.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        return versionCode;
    }

    /**
     * 获取最后刷新时间
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());

    }

    //身份证验证
    public static boolean IDCardValidate(String IDStr) throws ParseException {
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = {"1", "0", "X", "9", "8", "7", "6", "5", "4",
                "3", "2"};
        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2"};
        String Ai = "";
        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";
            return false;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return false;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (isDataFormat(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "身份证生日无效。";
            return false;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                    strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "身份证生日不在有效范围。";
                return false;
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "身份证月份无效";
            return false;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "身份证日期无效";
            return false;
        }
        // =====================(end)=====================

        // ================ 地区码是否有效 ================
        Hashtable h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "身份证地区编码错误。";
            return false;
        }
        // ==============================================

        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
                    * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (IDStr.length() == 18) {
            if (Ai.equals(IDStr) == false) {
                errorInfo = "身份证无效，不是合法的身份证号码";
                return false;
            }
        } else {
            return true;
        }
        // =====================(end)=====================
        return true;
    }

    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /**
     * 验证日期字符串是否是YYYY-MM-DD格式
     *
     * @param str
     * @return
     */
    private static boolean isDataFormat(String str) {
        boolean flag = false;
        // String
        // regxStr="[1-9][0-9]{3}-[0-1][0-2]-((0[1-9])|([12][0-9])|(3[01]))";
        String regxStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern pattern1 = Pattern.compile(regxStr);
        Matcher isNo = pattern1.matcher(str);
        if (isNo.matches()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 对double数据进行取精度.
     *
     * @param value        double数据.
     * @param scale        精度位数(保留的小数位数).
     * @param roundingMode 精度取值方式.
     * @return 精度计算后的数据.
     */
    public static double round(double value, int scale,
                               int roundingMode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }

    public static String sha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes());

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }

    // 隐藏软键盘
    public void hideInputManager(Context ct) {
        try {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(((Activity) ct)
                    .getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
        }
    }


    /**
     *  更新版本
     * @param context
     * @param versionSer
     * @param mVerUrl
     *//*
    public void parseData(final Context context, String versionSer, final String mVerUrl) {
		try {

			String versionLoc = CommonUtil.getVersion(context, "0.0");
			String versionLocv=versionLoc.replace(".","");
			if (versionSer.compareTo(versionLocv) >0) {
				if (MyApplication.isDownloading) {
					Toast.makeText(context, "版本正在下载，请稍后", Toast.LENGTH_SHORT).show();
					return;
				}
				DialogMy dialog = DialogMy.getInstance(context);
				dialog.withTitle("版本更新").withDetail("检测到新版本，是否立即升级?")
						.withBtnSure("是").withBtnCancel("否")
						.withListener(new DialogMy.OnDialogListener() {

							@Override
							public void ok() {
								// 下载新版
								MyApplication.isDownloading = true;
								Intent updateIntent = new Intent(context,
										UpdateService.class);
								updateIntent.putExtra(Constants.KEY_APK_URL,
										mVerUrl);
								Log.e("mVerUrl－－－－－－－－",mVerUrl);
								updateIntent.putExtra(
										Constants.KEY_APK_NAME,
										context.getResources().getString(
												R.string.app_name));
								context.startService(updateIntent);
							}

							@Override
							public void cancel() {
								// TODO Auto-generated method stub
							}
						}).show();
			} else {
				Toast.makeText(context, "当前版本已是最新版本", Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

*/
}

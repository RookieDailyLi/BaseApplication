package com.credithc.common.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import android.telephony.TelephonyManager;

import com.credithc.common.GlobalContext;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author liyong
 * @date 2019/10/22
 * @des
 */
public final class AppUtil {

    public final static String SHA1 = "SHA1";


    public static PackageInfo getPackageInfo() {
        return getPackageInfo(getPackageName());
    }

    public static PackageInfo getPackageInfo(String _packageName) {
        try {
            return getPackageManager().getPackageInfo(_packageName, 0);
        } catch (PackageManager.NameNotFoundException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static String getAppName() {
        int labelRes = getPackageInfo().applicationInfo.labelRes;
        return ResUtil.getString(labelRes);
    }

    public static String getVersionName() {
        return getPackageInfo().versionName;
    }

    public static int getVersionCode() {
        return getPackageInfo().versionCode;
    }

    public static String getPackageName() {
        return GlobalContext.getContext().getPackageName();
    }

    public static List<ApplicationInfo> getAllApps() {
        PackageManager pm = getPackageManager();
        if (pm == null) {
            return null;
        } else {
            return pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public boolean isRunning(String packageName) {

        boolean isRunning = false;
        ActivityManager am = (ActivityManager) GlobalContext.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return false;
        }
        List<ActivityManager.RunningTaskInfo> runningTasks = am.getRunningTasks(100);
        for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTasks) {

            if (runningTaskInfo.topActivity.getPackageName().equals(packageName) || runningTaskInfo.baseActivity.getPackageName().equals(packageName)) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    // 重启
    public static void restartMySelf() {
        Intent i = GlobalContext.getContext().getPackageManager().getLaunchIntentForPackage(GlobalContext.getContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        GlobalContext.getContext().startActivity(i);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static void shutDownApp() {
        com.credithc.common.util.ActivityManager.popAll();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }


    /**
     * 返回对应包的签名信息
     *
     * @param packageName
     * @return
     */
    @Nullable
    public static Signature[] getSignatures(String packageName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = GlobalContext.getContext().getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            return packageInfo.signatures;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public static Signature[] getSignatures() {
        return getSignatures(getPackageName());
    }


    /**
     * 返回一个签名的对应类型的字符串
     *
     * @param packageName
     * @param type
     * @return
     */
    @Nullable
    public static String getSingInfo(String packageName, String type) {
        String tmp = null;
        Signature[] signs = getSignatures(packageName);
        if (signs == null) {
            return null;
        }
        for (Signature sig : signs) {
            if (SHA1.equals(type)) {
                tmp = getSignatureString(sig, SHA1);
                break;
            }
        }
        return tmp;
    }


    /**
     * 返回一个签名的对应类型的字符串
     *
     * @param type
     * @return
     */
    @Nullable
    public static String getSingInfo(String type) {
        return getSingInfo(getPackageName(), type);
    }

    /**
     * 返回一个签名的对应类型的字符串
     *
     * @return
     */
    @Nullable
    public static String getSingInfo() {
        return getSingInfo(getPackageName(), SHA1);
    }


    /**
     * 获取相应的类型的字符串（把签名的byte[]信息转换成16进制）
     *
     * @param sig
     * @param type
     * @return
     */
    public static String getSignatureString(Signature sig, String type) {
        byte[] hexBytes = sig.toByteArray();
        String fingerprint = "error!";
        try {
            MessageDigest digest = MessageDigest.getInstance(type);
            if (digest != null) {
                byte[] digestBytes = digest.digest(hexBytes);
                StringBuilder sb = new StringBuilder();
                for (byte digestByte : digestBytes) {
                    sb.append((Integer.toHexString((digestByte & 0xFF) | 0x100)).substring(1, 3));
                }
                fingerprint = sb.toString();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return fingerprint;
    }

    public static PackageManager getPackageManager() {
        return GlobalContext.getContext().getPackageManager();
    }

    /**
     * 获取手机IMEI
     *
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getIMEI(Context context) {
        String imei = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return "";
            }
            imei = telephonyManager.getDeviceId();
        } catch (Exception e) {
            imei = "";
        }
        if (imei == null) {
            imei = "";
        }
        return imei;
    }

    /**
     * 梆梆加固，防劫持检查
     * Android 5.0及以下可用
     */
    @SuppressLint("NewApi")
    public static void checkHijack() {
        try {
            ActivityManager activityManager = OSUtils.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(2);
            if (tasks == null || tasks.isEmpty()) {
                return;
            }
            ActivityManager.RunningTaskInfo info = tasks.get(0);
            if (info == null) {
                return;
            }
            String topPackage = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                topPackage = info.topActivity.getPackageName();
            }

            if (!AppUtil.getPackageName().equals(topPackage)) {
                LogUtil.i("ui hijack show...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 多进程环境下，防止多次初始化
     *
     * @return true 初始化， false 不初始化
     */
    public static boolean isInitAble() {

        String packageName = getPackageName();
        if (packageName == null) { // impossible
            return true;
        }
        return packageName.equalsIgnoreCase(OSUtils.getProcessName());
    }
}


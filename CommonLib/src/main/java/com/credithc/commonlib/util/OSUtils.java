package com.credithc.commonlib.util;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.credithc.commonlib.GlobalContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author liyong
 * @date 2019/8/27
 * @des
 */
public final class OSUtils {


    @SuppressWarnings("unchecked")
    public static <T> T getSystemService(String service) {
        return (T) GlobalContext.getContext().getSystemService(service);
    }

    @TargetApi(23)
    public static <T> T getSystemService(Class<T> tClass) {
        return GlobalContext.getContext().getSystemService(tClass);
    }

    public static String getProcessName() {
        int pid = android.os.Process.myPid();
        return getProcessName(pid);
    }


    public static String getProcessName(int pid) {
        ActivityManager manager = OSUtils.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfoList = manager.getRunningAppProcesses();
        if (processInfoList != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : processInfoList) {
                if (runningAppProcessInfo.pid == pid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return null;
    }

    /**
     * 8
     *
     * @return 2.2
     */
    public static boolean hasFroyo_8() {
        return has(Build.VERSION_CODES.FROYO);
    }

    /**
     * 9
     *
     * @return 2.3
     */
    public static boolean hasGingerbread_9() {
        return has(Build.VERSION_CODES.GINGERBREAD);
    }

    /**
     * 11
     *
     * @return 3.0
     */
    public static boolean hasHoneycomb_11() {
        return has(Build.VERSION_CODES.HONEYCOMB);
    }

    /**
     * api 14
     *
     * @return 4.0
     */
    public static boolean hasIceCreamSandwich_14() {
        return has(Build.VERSION_CODES.ICE_CREAM_SANDWICH);
    }


    /**
     * api 16
     *
     * @return 4.2
     */
    public static boolean hasJellyBean_16() {
        return has(Build.VERSION_CODES.JELLY_BEAN);
    }


    /**
     * api 17
     *
     * @return 4.3
     */
    public static boolean hasJELLY_BEAN_MR1_17() {
        return has(Build.VERSION_CODES.JELLY_BEAN_MR1);
    }

    /**
     * api 18
     *
     * @return 4.4
     */
    public static boolean hasJELLY_BEAN_MR2_18() {
        return has(Build.VERSION_CODES.JELLY_BEAN_MR2);
    }

    /**
     * api 19
     *
     * @return 4.4
     */
    public static boolean hasKitKat_19() {
        return has(Build.VERSION_CODES.KITKAT);
    }

    /**
     * api 21
     *
     * @return 5.0
     */
    public static boolean hasLollipop_21() {
        return has(Build.VERSION_CODES.LOLLIPOP);
    }


    /**
     * api 22
     *
     * @return 5.1
     */
    public static boolean hasLollipop_MR1_22() {
        return has(Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    /**
     * api 23
     *
     * @return 6.0
     */
    public static boolean hasM_23() {
        return has(Build.VERSION_CODES.M);
    }

    /**
     * api 24
     *
     * @return 7.0
     */
    public static boolean hasN_24() {
        return has(Build.VERSION_CODES.N);
    }

    /**
     * api 25
     *
     * @return 7.1.1
     */
    public static boolean hasN_MR1_25() {
        return has(Build.VERSION_CODES.N_MR1);
    }


    /**
     * api 26
     *
     * @return 8.0.0
     */
    public static boolean hasO_26() {
        return has(Build.VERSION_CODES.O);
    }


    /**
     * api 27
     *
     * @return 8.1.0
     */
    public static boolean hasO_MR1_27() {
        //        return has(Build.VERSION_CODES.O_MR1);
        return has(27);
    }


    /**
     * api 28
     *
     * @return 9.0.0
     */
    public static boolean hasP_28() {
        //        return has(Build.VERSION_CODES.P);
        return has(28);
    }


    /**
     * The user-visible SDK version of the framework); its possible
     * values are defined in {@link Build.VERSION_CODES}.
     */
    public static boolean has(int versionCode) {
        return Build.VERSION.SDK_INT >= versionCode;
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSysVersion() {
        return Build.VERSION.RELEASE;
    }

    public static final String ROM_MIUI = "MIUI";
    public static final String ROM_EMUI = "EMUI";
    public static final String ROM_FLYME = "FLYME";
    public static final String ROM_OPPO = "OPPO";
    public static final String ROM_SMARTISAN = "SMARTISAN";
    public static final String ROM_VIVO = "VIVO";
    public static final String ROM_QIKU = "QIKU";

    private static final String KEY_VERSION_MIUI = "ro.miui.ui.version.name";
    private static final String KEY_VERSION_EMUI = "ro.build.version.emui";
    private static final String KEY_VERSION_OPPO = "ro.build.version.opporom";
    private static final String KEY_VERSION_SMARTISAN = "ro.smartisan.version";
    private static final String KEY_VERSION_VIVO = "ro.vivo.os.version";

    private static String sName;
    private static String sVersion;

    public static boolean isEmui() {
        return check(ROM_EMUI);
    }

    public static boolean isMiui() {
        return check(ROM_MIUI);
    }

    public static boolean isVivo() {
        return check(ROM_VIVO);
    }

    public static boolean isOppo() {
        return check(ROM_OPPO);
    }

    public static boolean isFlyme() {
        return check(ROM_FLYME);
    }

    public static boolean is360() {
        return check(ROM_QIKU) || check("360");
    }

    public static boolean isSmartisan() {
        return check(ROM_SMARTISAN);
    }

    public static String getName() {
        if (sName == null) {
            check("");
        }
        return sName;
    }

    public static String getVersion() {
        if (sVersion == null) {
            check("");
        }
        return sVersion;
    }

    public static boolean check(String rom) {
        if (sName != null) {
            return sName.equals(rom);
        }

        if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_MIUI))) {
            sName = ROM_MIUI;
        } else if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_EMUI))) {
            sName = ROM_EMUI;
        } else if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_OPPO))) {
            sName = ROM_OPPO;
        } else if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_VIVO))) {
            sName = ROM_VIVO;
        } else if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_SMARTISAN))) {
            sName = ROM_SMARTISAN;
        } else {
            sVersion = Build.DISPLAY;
            if (sVersion.toUpperCase().contains(ROM_FLYME)) {
                sName = ROM_FLYME;
            } else {
                sVersion = Build.UNKNOWN;
                sName = Build.MANUFACTURER.toUpperCase();
            }
        }
        return sName.equals(rom);
    }

    public static String getProp(String name) {
        String line = null;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + name);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return line;
    }
}

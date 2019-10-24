package com.credithc.netlib.okhttp.converter;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @author liyong
 * @date 2019/10/23
 * @des
 */

public class StringConvert implements IConvert<String> {


    public static StringConvert getInstance() {
        return ConvertHolder.CONVERT;
    }

    StringConvert() {
    }

    private static class ConvertHolder {
        private static final StringConvert CONVERT = new StringConvert();
    }


    @Override
    public String convert(ResponseBody responseBody) throws IOException {
        if (responseBody == null) {
            return "";
        }
        return responseBody.string();
    }
}

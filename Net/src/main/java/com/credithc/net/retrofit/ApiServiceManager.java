package com.credithc.net.retrofit;

import com.credithc.net.retrofit.service.ApiService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyong
 * @date 2020/9/14 11:11
 * @description
 */
public class ApiServiceManager {

    private static List<ApiService> apiServiceList = new ArrayList<>();

    public static <T> void addApiService(ApiService<T> apiService) {
        apiServiceList.add(apiService);
    }

    public static <T> T getApiService(Class<T> serviceCls) {
        for (ApiService apiService : apiServiceList) {
            if (serviceCls == apiService.getServiceCls()) {
                return (T) apiService.createService();
            }
        }
        throw new RuntimeException("未创建 " + serviceCls.getSimpleName() + " Retrofit 动态代理");
    }
}

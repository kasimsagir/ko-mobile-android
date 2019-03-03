package com.kinght.commerce.data.network;

public interface ServiceCallback<T> {

    void onSuccess(T response);
    void onSuccess();
    void onError(int code, String errorResponse);
}

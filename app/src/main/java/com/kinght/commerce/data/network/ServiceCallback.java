package com.kinght.commerce.data.network;

public interface ServiceCallback<T> {

    void onSuccess(T response);
    void onError(int code, String errorResponse);
}

package com.i502tech.gitclub.api.http.api.subscriber;

public interface SubscriberListener<T> {
    void onSuccess(T t);
    void onFailer(String msg);
    void onTokenError();
}

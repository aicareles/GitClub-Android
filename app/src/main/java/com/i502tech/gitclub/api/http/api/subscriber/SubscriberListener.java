package com.i502tech.gitclub.api.http.api.subscriber;

public abstract class SubscriberListener<T> {
    public abstract void onSuccess(T t);
    public abstract void onFailer(String msg);
    public void onTokenError(){}
}

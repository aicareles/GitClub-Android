package com.i502tech.gitclub.api.http.api;

public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}

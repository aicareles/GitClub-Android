package com.i502tech.gitclub.api.http.api.subscriber;

public interface ResponseListener<T> {
    void onResponse(T t);
}

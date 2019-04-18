package com.i502tech.gitclub.base.mvvm;

import com.i502tech.gitclub.code.event.LiveBus;

/**
 * description $desc$
 * created by jerry on 2019/4/13.
 */
public class BaseRepository {

    protected void sendData(Object eventKey, Object t) {
        sendData(eventKey, null, t);
    }

    protected void sendData(Object eventKey, String tag, Object t) {
        LiveBus.getDefault().postEvent(eventKey, tag, t);
    }
}

package com.i502tech.gitclub.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.i502tech.gitclub.App;
import com.i502tech.gitclub.R;


/**
 * Created by LiuLei on 2017/11/27.
 */
public class ToastUtil {

    private static Toast mToast;

    public static void show(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void show(int msg) {
        if (mToast == null) {
            mToast = Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    public static void centerToast(Context context,String msg){
        Toast toast2 = new Toast(context);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
        TextView textView = view.findViewById(R.id.msg);
        textView.setText(msg);
        toast2.setView(view);
        toast2.setGravity(Gravity.CENTER, 0, 0);
        toast2.show();
    }
}

package com.example.zzn.nuomi.common.dialog;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.zzn.nuomi.R;

/**
 * Created by ZZN on 2017/8/21.
 */

public class AddHead implements View.OnClickListener{
    private PopupWindow popupWindow;
    private View mMenuView;
    private Activity mContext;
    private TextView camera,photo;
    private View.OnClickListener clickListener;

    public AddHead(Activity context, View.OnClickListener clickListener){
        LayoutInflater inflater=LayoutInflater.from(context);
        this.mContext=context;
        this.clickListener=clickListener;
        mMenuView=inflater.inflate(R.layout.popup_dialog,null);
        camera=mMenuView.findViewById(R.id.top);
        photo=mMenuView.findViewById(R.id.bottom);
        camera.setText("打开相机");
        photo.setText("从相册选择图片");

        popupWindow=new PopupWindow(mMenuView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);//设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
//        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.rg_sex).getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        popupWindow. dismiss();
                        backgroundAlpha(1);
                    }
                }
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
//        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效

        camera.setOnClickListener(this);
        photo.setOnClickListener(this);
        backgroundAlpha(0.4f);
    }

    public void backgroundAlpha(float f) {
        WindowManager.LayoutParams lp =mContext.getWindow().getAttributes();
        lp.alpha = f;
        mContext.getWindow().setAttributes(lp);
    }

    public void show(){
        //得到当前activity的rootView
        View rootView=((ViewGroup)mContext.findViewById(android.R.id.content)).getChildAt(0);
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void onClick(View view) {
        popupWindow.dismiss();
        clickListener.onClick(view);
        backgroundAlpha(1);
    }

}


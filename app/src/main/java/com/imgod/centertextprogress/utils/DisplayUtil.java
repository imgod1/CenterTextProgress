package com.imgod.centertextprogress.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * DisplayUtil.java 尺寸转换类
 *
 * @author kk
 * @version 2.0.0 2018/3/19 13:24
 * @update kk 2018/3/19 13:24
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class DisplayUtil {

    public static float dp2px(float dpValue, Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue, context.getResources().getDisplayMetrics());
    }

    public static float sp2px(float spValue, Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spValue, context.getResources().getDisplayMetrics());
    }
}
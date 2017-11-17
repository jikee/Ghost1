package com.mph.ghost.ghost1.utils;

import android.content.Context;
import android.content.Intent;

/**
 * @author 马鹏昊
 * @name
 * @des
 * @remark
 * @date
 * @updateAuthor
 * @updateDate
 * @updateDes
 */

public class JumpActivityUtil {

    public static void openActivity(Context res, Class des){
        Intent intent = new Intent(res,des);
        res.startActivity(intent);
    }

}

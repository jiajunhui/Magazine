package com.kk.taurus.app.magazine.bean;

import com.kk.taurus.app.magazine.base.BaseReq;

/**
 * Created by Taurus on 2017/2/4.
 */

public class WxArticleReq extends BaseReq {

    public static final int DEFAULT_PNO = 1;
    public static final int DEFAULT_PS = 20;

    public String key_pno = "pno";
    public String key_ps = "ps";
    public String key_dtype = "dtype";

    public int pno = DEFAULT_PNO;
    public int ps = DEFAULT_PS;
    public String dtype;

}

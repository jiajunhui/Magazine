package com.kk.taurus.app.magazine.base;

import com.kk.taurus.baseframe.base.HolderData;
import com.kk.taurus.http_helper.bean.BaseResponse;

import java.io.Serializable;

/**
 * Created by Taurus on 2017/2/4.
 */

public class BaseRsp<T extends HolderData> extends BaseResponse<T> implements Serializable {
    private int error_code;
    private String reason;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}

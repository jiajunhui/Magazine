package com.kk.taurus.app.magazine;

import com.kk.taurus.baseframe.FrameApplication;
import com.kk.taurus.http_helper.XHTTP;

/**
 * Created by Taurus on 2017/2/4.
 */

public class MApp extends FrameApplication {
    @Override
    public void onCreateInAppMainProcess() {
        super.onCreateInAppMainProcess();
        XHTTP.init(this,null);
    }
}

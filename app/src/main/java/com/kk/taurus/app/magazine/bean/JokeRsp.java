package com.kk.taurus.app.magazine.bean;

import com.kk.taurus.baseframe.base.HolderData;
import com.kk.taurus.http_helper.bean.AbsResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Taurus on 2017/2/7.
 */

public class JokeRsp extends AbsResponse<JokeRsp.JokeData> {

    public static class JokeData implements Serializable{
        public int error_code;
        public String reason;
        public JokeList result;
    }

    public static class JokeList implements HolderData{
        public List<Joke> data;
    }

    public static class Joke implements Serializable{
        public String content;
        public String hashId;
        public String updatetime;
        public long unixtime;
    }

}

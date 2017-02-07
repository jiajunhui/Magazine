package com.kk.taurus.app.magazine.bean;

import com.kk.taurus.app.magazine.base.BaseRsp;
import com.kk.taurus.baseframe.base.HolderData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Taurus on 2017/2/7.
 */

public class JokeRsp extends BaseRsp<JokeRsp.JokeList> {

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

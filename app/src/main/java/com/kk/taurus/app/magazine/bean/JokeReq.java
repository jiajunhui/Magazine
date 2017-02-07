package com.kk.taurus.app.magazine.bean;

import com.kk.taurus.app.magazine.base.BaseReq;

/**
 * Created by Taurus on 2017/2/4.
 */
/**
 *
 * sort	string	是	类型，desc:指定时间之前发布的，asc:指定时间之后发布的
 page	int	否	当前页数,默认1
 pagesize	int	否	每次返回条数,默认1,最大20
 time	string	是	时间戳（10位），如：1418816972
 *
 */

public class JokeReq extends BaseReq {

    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_PAGE_SIZE = 20;

    public static final String SORT_TIME_PRE = "desc";
    public static final String SORT_TIME_AFTER = "asc";

    public static final String key_sort = "sort";
    public static final String key_page = "page";
    public static final String key_page_size = "pagesize";
    public static final String key_time = "time";

    public String sort = SORT_TIME_PRE;
    public int page = DEFAULT_PAGE;
    public int pageSize = DEFAULT_PAGE_SIZE;
    public String time;

}

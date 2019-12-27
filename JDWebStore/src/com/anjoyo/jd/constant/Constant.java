package com.anjoyo.jd.constant;

import com.anjoyo.jd.R;

public class Constant {
public static String url="http://192.168.1.204:8080/JD/";
//private static String url="http://172.16.2.204:8080/JD/";
public final static String URL_REGISTER=url+"register";
public final static String URL_LOGIN=url+"login";
public final static String URL_GOODS=url+"goods";
public final static String URL_SEARCH=url+"search";
public final static String URL_INSERT_GOODS_CART=url+"insert_goods_cart";
public final static String URL_DELETE_GOODS_CART=url+"delete_goods_cart";
public final static String URL_CLEAR_GOODS_CART=url+"clear_goods_cart";
public final static String URL_SEARCH_GOODS_CART=url+"search_goods_cart";
public final static String URL_SPIKE_GOODS=url+"spike_goods";
public final static String URL_SELECT_GOODS=url+"select_goods";
public final static String URL_UP_PROFILE_IMAGE=url+"up_profile_image";
public final static String SQL_SELECT_HOT_SALE="SELECT *  FROM goods GROUP BY scales_volume DESC LIMIT 0,10";
public final static String SQL_SELECT_PRAISE_GOODS="SELECT *  FROM goods GROUP BY praise_scale  DESC LIMIT 0,10";
public final static String SQL_SELECT_GOODS="SELECT *  FROM goods t1 join  sub_category t2 on t1.s_sub_category=t2.s_sub_category ";
public final static String SQL_SELECT_GOODS1="SELECT *  FROM goods t1 JOIN  sub_category t2 ON t1.s_sub_category=t2.s_sub_category WHERE t2.sub_category IN (SELECT sub_category FROM category ";
public final static String URL_GETCATEGORY=url+"getcategory";
public final static String URL_UPVERSON=url+"updateversion";
}


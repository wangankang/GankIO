package com.cornor.gank.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 * User:cornor
 * Date:2018/3/14
 */

public class GankDateContent implements Serializable {
    public boolean error;
    public List<String> category;
    public GankCategory results;

    public static class GankCategory implements Serializable{
        @SerializedName("Android")
        public List<GankData> androidList;
        @SerializedName("iOS")
        public List<GankData> iOSList;
        @SerializedName("App")
        public List<GankData> appList;
        @SerializedName("前端")
        public List<GankData> h5List;
        @SerializedName("休息视频")
        public List<GankData> videoList;
        @SerializedName("拓展资源")
        public List<GankData> expandList;
        @SerializedName("瞎推荐")
        public List<GankData> recomList;
        @SerializedName("福利")
        public List<GankData> girlList;
    }
}

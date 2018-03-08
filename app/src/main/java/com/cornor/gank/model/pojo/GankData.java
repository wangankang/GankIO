package com.cornor.gank.model.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 * User:cornor
 * Date:2018/3/7
 */

public class GankData<T> implements Serializable {
    public boolean error;
    public List<T> results;
}

package com.xc.as.core.crawler;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yxc on 2016/11/26.
 */
public interface ICallback {
    public List<HashMap<String, Object>> callbackAction(HashMap<String, Object> response);
}

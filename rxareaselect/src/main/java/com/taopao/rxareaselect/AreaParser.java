package com.taopao.rxareaselect;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author： 淘跑
 * @Date: 2018/5/29 14:35
 * @Use：
 */
public class AreaParser {

    public static String provinceLevel = "1";

    private static AreaParser mInstance;
    private List<AreaBean> allList;
    private List<AreaBean> provinceList;

    private AreaParser(Context context) {
        String data = get(context, R.raw.area);
        Gson gson = new Gson();
        Type type = new TypeToken<List<AreaBean>>() {
        }.getType();
        allList = new ArrayList<>();
        allList = gson.fromJson(data, type);
        provinceList = new ArrayList<>();
        for (AreaBean areaBean : allList) {
            if (provinceLevel.equals(areaBean.getLevel())) {
                provinceList.add(areaBean);
            }
        }
    }

    public static synchronized AreaParser getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AreaParser(context);
        }
        return mInstance;
    }

    public List<AreaBean> getProvinceList() {
        if (provinceList == null) {
            return new ArrayList<>();
        }
        return provinceList;
    }

    public List<AreaBean> getChildList(int tid) {
        String id = String.valueOf(tid);
        List<AreaBean> childList = new ArrayList<>();
        if (TextUtils.isEmpty(id)) {
            return childList;
        }
        for (AreaBean areaBean : allList) {
            if (id.equals(areaBean.getPid())) {
                childList.add(areaBean);
            }
        }
        return childList;
    }

    public int getChoosePos(List<AreaBean> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCheck()) {
                return i;
            }
        }
        return 0;
    }

    public String get(Context context, int id) {
        InputStream stream = context.getResources().openRawResource(id);
        return read(stream);
    }

    public String read(InputStream stream) {
        return read(stream, "utf-8");
    }

    public String read(InputStream is, String encode) {
        if (is != null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, encode));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                return sb.toString();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}

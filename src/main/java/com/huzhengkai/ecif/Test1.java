package com.huzhengkai.ecif;

import com.huzhengkai.ecif.bean.T_MK_CUST_CERT_INFO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by root on 2018/3/26.
 */
public class Test1
{
    public static void main(String[] args) throws ParseException
    {
        String s = "2017/7/31 5:37:56";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date d = sdf.parse(s);
        System.out.println(d);
    }
}

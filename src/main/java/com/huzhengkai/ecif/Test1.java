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
        String s = "2018-03-09 01:50:44.0";
        Integer i = s.indexOf(".");
        if(i!=-1)
        {
            s.split(".");
        }

        String []sss =  s.split("\\.");
        System.out.println(sss.length);
        for(String a :sss)
        {
            System.out.println(a);
        }
    }
}

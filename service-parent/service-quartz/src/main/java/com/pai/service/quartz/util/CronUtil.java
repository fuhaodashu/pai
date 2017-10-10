package com.pai.service.quartz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronExpression;

public class CronUtil {
	public static boolean check(String cronExpr) {
		boolean isPass = false;
		try {
			CronExpression exp = new CronExpression(cronExpr);
			SimpleDateFormat df = new SimpleDateFormat("YYYYMMDD HH:mm:ss");
			Date d = new Date();
			d = exp.getNextValidTimeAfter(d);
			isPass = true;			
		} catch (ParseException e) {
			isPass = false;
		}
		return isPass;
	}
	public static void main(String[] args) {  
        System.out.println(check("0 0/1 * * * ?"));
        System.out.println(check("0 */1 * * * ?"));
        System.out.println(check("0 */1 * * * L ?"));
    }  	
}

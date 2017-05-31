package com.junit.platform;

import java.util.HashMap;

import org.junit.Test;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.junit.TestBase;

public class TestSql extends TestBase {
	
	@Test
    public void getSql(){
        System.out.println("-------------start------------");
        
		//String sql = Db.getSql("platform.user.findByUserName", new HashMap<String, Object>());
		//System.out.println(sql);
		
		String invoice = "invoice";
		Record ss = Db.findFirst("select count(*) as count FROM b_trading_poci WHERE invoceNo like '%" + invoice + "%'");
		Long count = ss.get("count");
		count = count+1;
		invoice = invoice + "A0" + count.toString();
		System.out.println(invoice);
		System.out.println("-------------end------------");
    }

}

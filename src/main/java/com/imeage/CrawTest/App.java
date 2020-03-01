package com.imeage.CrawTest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.imeage.CrawTest.crawMain.CrawTest;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger logger=LogManager.getLogger(App.class);
    public static void main( String[] args )
    {
        try {
        	logger.info("nihao ya");
        	while (true) {
        		CrawTest.run();
        		Thread.sleep(1000 *60 * 60 * 1);
        	}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

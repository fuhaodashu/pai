//package com.pai.service.ftp;
//
//import java.io.File;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:conf/service-ftp.xml"})
//public class FTPKitTest extends AbstractJUnit4SpringContextTests{
//
//	@Resource
//	private FTPKitFactory ftpKitFactory;
//	
//	@Test
//	public void testUpload() throws Exception{
//		FTPKit kit = ftpKitFactory.create();
//		for(int i = 0; i < 5; i++){
//			kit.upload(new File("d:/t.txt"), "/" + i + ".txt");
//			for(int j = 0; j < 10; j++){
//				kit.upload(new File("d:/t.txt"), "/" + i + "/" + j + ".txt");
//				for(int k = 0; k < 15; k++){
//					kit.upload(new File("d:/t.txt"), "/" + i + "/" + j + "/" + k + ".txt");
//				}
//			}
//		}
//		
//		kit.discard();
//	}
//	
//	@Test
//	public void testRename() throws Exception{
//		FTPKit kit = ftpKitFactory.create();
//		kit.rename("/one/two/three/four/five/5.txt", "one/two/three/four/five/rename-5.txt");
//		kit.discard();
//	}
//	
//	@Test
//	public void testDelete() throws Exception{
//		FTPKit kit = ftpKitFactory.create();
//		kit.delete("/one/two/three/four/3.txt");
//		kit.discard();
//	}
//	
//}

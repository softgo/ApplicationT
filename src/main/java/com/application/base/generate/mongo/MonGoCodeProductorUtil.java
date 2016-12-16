package com.application.base.generate.mongo;

import com.application.base.generate.mongo.def.FtlDef;
import com.application.base.generate.mongo.factory.MonGoCodeGenerateFactory;

public class MonGoCodeProductorUtil {

	/**
	 * test it .
	 * @param args
	 */
	public static void main(String[] args) {
		String tableName = "mongo";
	    String codeName = "测试";
	    String codePrefix = "测试";
	    //String entityPackage = "zhongsou\\yjmanager\\"; //如果是目录就用"\\",否则就不用.
	    String entityPackage = "mongo"; //如果是目录就用"\\",否则就不用.
	    //MonGoCodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage,FtlDef.KEY_TYPE_AUTO);
	    MonGoCodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage,FtlDef.KEY_TYPE_MAN);
	}
	
}

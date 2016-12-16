package com.application.base.generate.javabase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.application.base.generate.javabase.bin.FileBuilder;
import com.application.base.generate.javabase.bin.GenerateConfig;
import com.application.base.generate.javabase.constant.CommonConstant;
import com.application.base.generate.javabase.utils.GenerateUtils;
import com.application.base.utils.common.PropertiesUtils;

/**
 * 代码生成器.
 */
public class CodeProductorUtil {

	/**
	 * 生成代码.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		PropertiesUtils prop = new PropertiesUtils();
		List<String> configFilePath = new ArrayList<String>();
		configFilePath.add(CommonConstant.GENERATE_FILE_PATH);
		prop.setUrls(configFilePath);

		//工程名字
		String systemName = prop.get("generate.systemName");
		//包
		String packageName = prop.get("generate.packageName");
		//是否批量生成
		String generateAll = prop.get("generate.all");
		//包结构
		String instancePackage = prop.get("generate.instancePackage");
		//主键生成策略.
		String primaryKeyStyle = prop.get("generate.primaryKeyStyle");

		
		List<String> list = new ArrayList<String>();
		if ("1".endsWith(generateAll)) {
			// 全库生成
			list = GenerateUtils.getTable();
		} else {
			String tableNames = prop.get("generate.tableNames");
			// 部分生成
			String[] tables = tableNames.split(",");
			for (String table : tables) {
				list.add(table);
			}
		}
		try {
			for (String tableName : list) {
				GenerateConfig config = null;
				if (StringUtils.isEmpty(instancePackage)) {
					 config = new GenerateConfig(tableName,systemName, packageName,primaryKeyStyle);
				}else{
					 config = new GenerateConfig(tableName,systemName, packageName,primaryKeyStyle,instancePackage);
				}
					
				FileBuilder builder = new FileBuilder(config);
				builder.createJavaBeanMapper(); // sqlMapper.
				builder.createJavaBean(); // entityBean.
				builder.createJavaBeanDao(); // entityDao.
				builder.createJavaBeanDaoImpl(); // entityDaoImpl.
				builder.createJavaBeanService(); //entityService.
				builder.createJavaBeanServiceImpl(); //entityServiceImpl.
				builder.createJavaBeanController(); //entityController.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

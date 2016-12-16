package com.javabase.demo.utils;

import com.application.base.core.apisupport.impl.MultiStrutsBaseDaoImpl;
import com.application.base.core.exception.CommonException;
import com.application.base.redis.api.RedisSession;
import com.application.base.utils.common.JSONUtils;
import com.application.base.utils.common.PropertiesUtils;
import com.javabase.demo.constant.ResultConstant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * 登陆 Session 的存储信息.
 *
 */
@SuppressWarnings("rawtypes")
public class LoginSessionUtils extends MultiStrutsBaseDaoImpl {

	static Logger logger = LoggerFactory.getLogger(LoginSessionUtils.class);

	/**
	 * 用户会话过期时间，单位：秒
	 */
	private static int SESSION_TIMEOUT_SECONDS = 28 * 60;
	private static RedisSession redisSession;

	public static final String LOCAL_USERSESSION_REDIS_HEAD = "LOCAL_USERSESSION:";

	@Autowired
	PropertiesUtils propUtils;

	@PostConstruct
	public void start() {
		redisSession = factory.getRedisSessionFactory().getSession();
		SESSION_TIMEOUT_SECONDS = Integer.valueOf(propUtils.get("session.timeout.minutes")) * 60;
		System.out.println(SESSION_TIMEOUT_SECONDS);
	}

	/**
	 * 创建并存本地user登陆后到sessioId
	 * 
	 * @param userId
	 * @param nonoUserId
	 * @param mobileNumber
	 * @return
	 */
	public static String createLocalSession(Integer userId, String mobileNumber, String nonoUserId,
			String payPassword) {
		String localSessionId = UUID.randomUUID().toString().replaceAll("-", "");
		saveOrUpdate(localSessionId, new UserSession(userId, mobileNumber));
		return localSessionId;
	}

	/**
	 * 根据本地sessionId获取用户Id
	 * 
	 * @param localSessionId
	 * @return
	 */
	public static Integer getUserId(String localSessionId) {
		Integer uid = getAndRefashUserSession(localSessionId).getUserId();
		logger.debug("localSessionId:" + localSessionId + "=================uid:" + uid);
		return uid;
	}


	/**
	 * 获取用户到手机号
	 * 
	 * @param localSessionId
	 * @return
	 */
	public static String getMobileNumber(String localSessionId) {
		return getAndRefashUserSession(localSessionId).getMobileNumber();
	}

	/**
	 * 获取用户会话对象
	 * 
	 * @param localSessionId
	 * @return
	 */
	private static UserSession getAndRefashUserSession(String localSessionId) {
		UserSession userSession = redisSession.getTypeObject(UserSession.class,LOCAL_USERSESSION_REDIS_HEAD + localSessionId);
		if (userSession != null) {
			saveOrUpdate(localSessionId, userSession);
			return userSession;
		}
		else {
			throw new CommonException(ResultConstant.CommonResult.GET_RESULT_IS_NULL);
		}
	}

	/**
	 * 保存或更新会话信息，延长过期时间
	 * 
	 * @param localSessionId
	 * @param userSession
	 */
	private static void saveOrUpdate(String localSessionId, UserSession userSession) {
		redisSession.setData(LOCAL_USERSESSION_REDIS_HEAD + localSessionId, JSONUtils.toJson(userSession),SESSION_TIMEOUT_SECONDS);
	}

	/**
	 * reids中缓存到用户session对象
	 */
	static class UserSession {
		
		Integer userId;
		
		String mobileNumber;

		public UserSession(Integer userId, String mobileNumber) {
			super();
			this.userId = userId;
			this.mobileNumber = mobileNumber;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
	}
}

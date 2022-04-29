package com.uway.sports.common.service;

import com.uway.sports.common.utils.Digests;
import com.uway.sports.common.utils.Encodes;
import com.uway.sports.common.utils.WebConsoleException;
import com.uway.sports.common.utils.SpringContextHolder;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service("baseService")
public class BaseServiceImpl implements IBaseService {
	protected Logger logger = Logger.getLogger(this.getClass());

	protected Transaction getTransaction() {
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Transaction transaction = transactionFactory.newTransaction(getConnection());
		return transaction;
	}
	
	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public String entryptPassword(String plainPassword) {
		String plain = Encodes.unescapeHtml(plainPassword);
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(salt)+ Encodes.encodeHex(hashPassword);
	}
	
	/**
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @return 验证成功返回true
	 */
	public boolean validatePassword(String plainPassword, String password) {
		String plain = Encodes.unescapeHtml(plainPassword);
		byte[] salt = Encodes.decodeHex(password.substring(0,16));
		byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
	}
	
	protected Connection getConnection() {
		DataSource dbSource = (DataSource) SpringContextHolder.getBean("dataSource");
		Connection con = null;
		try {
			con = dbSource.getConnection();
		} catch (SQLException e) {
			logger.error(WebConsoleException.getStackTraceAsString(e));
		}
		return con;
	}
	
	public HttpServletRequest getRequest() {
		HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
}

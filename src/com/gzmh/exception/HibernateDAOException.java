package com.gzmh.exception;

/**
 * 对在用Hibernate框架，或者是Spring框架里的HibernateDaoSupport.java 进行数据库操作有错误的时候抛出该异常。
 * 
 * @author Alex_Cheung
 * 
 */
public class HibernateDAOException extends Throwable {

	private static final long serialVersionUID =1L;



	public HibernateDAOException()
	{
		super();
	}

	public HibernateDAOException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public HibernateDAOException(String message)
	{
		super(message);
	}

	public HibernateDAOException(Throwable cause)
	{
		super(cause);
	}

}

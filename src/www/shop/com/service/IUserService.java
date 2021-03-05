package www.shop.com.service;

import java.util.List;

import www.shop.com.dao.po.User;

public interface IUserService {
	/**
	 * ��ѯȫ���û���Ϣ
	 * 
	 * @return ����ȫ���û���Ϣ
	 */
	public List<User> listAll();

	/**
	 * ����idɾ��һ���û���Ϣ
	 * 
	 * @param id ����
	 * @return ɾ���ɹ�������Ӱ���������ʧ�ܷ���-1
	 */
	public int deleteById(int id);

	/**
	 * �޸�һ���û���Ϣ
	 * 
	 * @param user
	 * @return �޸ĳɹ�������Ӱ���������ʧ�ܷ���-1
	 */
	public int update(User user);

	/**
	 * ���һ���û���Ϣ
	 * 
	 * @param user
	 * @return ��ӳɹ�������Ӱ���������ʧ�ܷ���-1
	 */
	public int insert(User user);

	/**
	 * ����id��ѯһ���û���Ϣ
	 * 
	 * @param id ����
	 * @return ��ѯ�ɹ�����һ���û���Ϣ��ʧ�ܷ���null
	 */
	public User getById(int id);

	/**
	 * ��¼����
	 * 
	 * @param username �û���
	 * @param password ����
	 * @return ��¼�ɹ�����һ���û���Ϣ��ʧ�ܷ���null
	 */
	public User login(String username, String password);

	public int add(User user);
}

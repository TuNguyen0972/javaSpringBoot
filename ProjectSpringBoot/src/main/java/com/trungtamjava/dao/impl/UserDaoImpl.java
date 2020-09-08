package com.trungtamjava.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.trungtamjava.dao.UserDao;
import com.trungtamjava.entity.User;

@Transactional
@Repository
public class UserDaoImpl implements UserDao{
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void addUser(User user) {
		entityManager.persist(user);
		
	}

	@Override
	public void updateUser(User user) {
		entityManager.merge(user);
		
	}

	@Override
	public void deleteUser(User user) {
		entityManager.remove(user);
		
	}

	@Override
	public User getUserById(int id) {
		
		return entityManager.find(User.class, id);
	}

	@Override
	public List<User> getAllUsers() {
		String jql = "SELECT u FROM User u";
		return entityManager.createQuery(jql, User.class).getResultList();
	}

	@Override
	public User getUserByUsername(String username) {
		String jql = "SELECT u FROM User u WHERE u.username = :uname";
		return entityManager.createQuery(jql, User.class).setParameter("uname", username).getSingleResult();
	}

	@Override
	public List<User> search(String username, int start, int length) {
		String jql = "SELECT u from User u where fullname like :fullname";
		Query query = entityManager.createQuery(jql, User.class);
		query.setParameter("fullname", "%" + username + "%");
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.getResultList();
		
		}

	@Override
	public int countUserWhenSearch(String username) {
		
		String jql ="SELECT u from User u where fullname like :fullname";
		Query query = entityManager.createQuery(jql,User.class);
		query.setParameter("fullname","%"+ username +  "%");
		return (int) query.getResultList().size();
	}
	
	

}

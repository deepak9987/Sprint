package com.usermanagement.dao;

import com.usermanagement.entity.Role;
import com.usermanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RoleDAO {

	// ➕ Add Role
	public boolean addRole(Role role) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(role);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	// 📜 View All Roles
	public List<Role> getAllRoles() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Query<Role> query = session.createQuery("from Role", Role.class);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ✏️ Update Role
	public boolean updateRole(int roleId, String newName, String newDescription) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Role role = session.get(Role.class, roleId);
			if (role != null) {
				role.setRoleName(newName);
				role.setDescription(newDescription);
				session.update(role);
				transaction.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	// ❌ Delete Role
	public boolean deleteRole(int roleId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Role role = session.get(Role.class, roleId);
			if (role != null) {
				session.delete(role);
				transaction.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}
}

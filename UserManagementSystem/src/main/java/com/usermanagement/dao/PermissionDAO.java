package com.usermanagement.dao;

import com.usermanagement.entity.Permission;
import com.usermanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class PermissionDAO {

	public void addPermission(Scanner sc) {
		System.out.print("Enter permission name: ");
		String name = sc.nextLine();
		System.out.print("Enter description: ");
		String desc = sc.nextLine();

		Permission p = new Permission(name, desc);
		Transaction tx = null;
		try (Session session = HibernateUtil.getSession()) {
			tx = session.beginTransaction();
			session.save(p);
			tx.commit();
			System.out.println("✅ Permission added successfully!");
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
	}

	public void viewPermissions() {
		try (Session session = HibernateUtil.getSession()) {
			List<Permission> list = session.createQuery("from Permission", Permission.class).list();
			for (Permission p : list) {
				System.out.println(p.getPermissionId() + " - " + p.getPermissionName() + " (" + p.getDescription() + ")");
			}
		} catch (Exception e) { e.printStackTrace(); }
	}

	public void updatePermission(Scanner sc) {
		System.out.print("Enter Permission ID to update: ");
		int id = Integer.parseInt(sc.nextLine());
		try (Session session = HibernateUtil.getSession()) {
			Permission p = session.get(Permission.class, id);
			if (p == null) { System.out.println("❌ Permission not found."); return; }
			System.out.print("Enter new name: ");
			p.setPermissionName(sc.nextLine());
			System.out.print("Enter new description: ");
			p.setDescription(sc.nextLine());
			Transaction tx = session.beginTransaction();
			session.update(p);
			tx.commit();
			System.out.println("✅ Permission updated successfully!");
		} catch (Exception e) { e.printStackTrace(); }
	}

	public void deletePermission(Scanner sc) {
		System.out.print("Enter Permission ID to delete: ");
		int id = Integer.parseInt(sc.nextLine());
		try (Session session = HibernateUtil.getSession()) {
			Permission p = session.get(Permission.class, id);
			if (p == null) { System.out.println("❌ Permission not found."); return; }
			Transaction tx = session.beginTransaction();
			session.delete(p);
			tx.commit();
			System.out.println("✅ Permission deleted successfully!");
		} catch (Exception e) { e.printStackTrace(); }
	}
}

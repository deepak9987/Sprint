package com.usermanagement.dao;

import com.usermanagement.entity.Admin;
import com.usermanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class AdminDAO {

	public void addAdmin(Scanner sc) {
		System.out.print("Enter name: ");
		String name = sc.nextLine();
		System.out.print("Enter email: ");
		String email = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();

		Admin admin = new Admin(name, email, password);

		Transaction tx = null;
		try (Session session = HibernateUtil.getSession()) {
			tx = session.beginTransaction();
			session.save(admin);
			tx.commit();
			System.out.println("✅ Admin added successfully!");
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
	}

	public void viewAdmins() {
		try (Session session = HibernateUtil.getSession()) {
			List<Admin> list = session.createQuery("from Admin", Admin.class).list();
			for (Admin a : list) {
				System.out.println((a.getAdminId()) + " - " + a.getName() + " (" + a.getEmail() + ")");
			}
		} catch (Exception e) { e.printStackTrace(); }
	}

	public void updateAdmin(Scanner sc) {
		System.out.print("Enter Admin ID to update: ");
		int id = Integer.parseInt(sc.nextLine());
		try (Session session = HibernateUtil.getSession()) {
			Admin a = session.get(Admin.class, id);
			if (a == null) { System.out.println("❌ Admin not found."); return; }
			System.out.print("Enter new name: ");
			a.setName(sc.nextLine());
			System.out.print("Enter new email: ");
			a.setEmail(sc.nextLine());
			System.out.print("Enter new password: ");
			a.setPassword(sc.nextLine());

			Transaction tx = session.beginTransaction();
			session.update(a);
			tx.commit();
			System.out.println("✅ Admin updated successfully!");
		} catch (Exception e) { e.printStackTrace(); }
	}

	public void deleteAdmin(Scanner sc) {
		System.out.print("Enter Admin ID to delete: ");
		int id = Integer.parseInt(sc.nextLine());
		try (Session session = HibernateUtil.getSession()) {
			Admin a = session.get(Admin.class, id);
			if (a == null) { System.out.println("❌ Admin not found."); return; }
			Transaction tx = session.beginTransaction();
			session.delete(a);
			tx.commit();
			System.out.println("✅ Admin deleted successfully!");
		} catch (Exception e) { e.printStackTrace(); }
	}
}

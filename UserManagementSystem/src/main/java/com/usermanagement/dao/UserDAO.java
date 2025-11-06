package com.usermanagement.dao;

import com.usermanagement.entity.User;
import com.usermanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class UserDAO {

	public void addUser(Scanner sc) {
	    System.out.print("Enter first name: ");
	    String firstName = sc.next();
	    System.out.print("Enter last name: ");
	    String lastName = sc.next();
	    System.out.print("Enter email: ");
	    String email = sc.next();
	    System.out.print("Enter password: ");
	    String password = sc.next();
	    System.out.print("Enter phone: ");
	    String phone = sc.next();
	    System.out.print("Enter created_by (admin ID): ");
	    int createdBy = sc.nextInt();

	    User user = new User(firstName, lastName, email, password, phone, "Active", createdBy);

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        Transaction tx = session.beginTransaction();
	        session.persist(user);
	        tx.commit();
	        System.out.println("✅ User added successfully!");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


    public void viewUsers() {
        try (Session session = HibernateUtil.getSession()) {
            List<User> list = session.createQuery("from User", User.class).list();
            for (User u : list) {
                System.out.println((u.getUserId()) + " - " + u.getFirstName() + " " + u.getLastName() + " (" + u.getEmail() + ")");
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void updateUser(Scanner sc) {
        System.out.print("Enter User ID to update: ");
        int id = Integer.parseInt(sc.nextLine());
        try (Session session = HibernateUtil.getSession()) {
            User u = session.get(User.class, id);
            if (u == null) { System.out.println("❌ User not found."); return; }
            System.out.print("Enter new first name: ");
            u.setFirstName(sc.nextLine());
            System.out.print("Enter new last name: ");
            u.setLastName(sc.nextLine());
            System.out.print("Enter new email: ");
            u.setEmail(sc.nextLine());
            System.out.print("Enter new password: ");
            u.setPassword(sc.nextLine());
            System.out.print("Enter new phone: ");
            u.setPhone(sc.nextLine());
            System.out.print("Enter new status: ");
            u.setStatus(sc.nextLine());

            Transaction tx = session.beginTransaction();
            session.update(u);
            tx.commit();
            System.out.println("✅ User updated successfully!");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void deleteUser(Scanner sc) {
        System.out.print("Enter User ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());
        try (Session session = HibernateUtil.getSession()) {
            User u = session.get(User.class, id);
            if (u == null) { System.out.println("❌ User not found."); return; }
            Transaction tx = session.beginTransaction();
            session.delete(u);
            tx.commit();
            System.out.println("✅ User deleted successfully!");
        } catch (Exception e) { e.printStackTrace(); }
    }
}

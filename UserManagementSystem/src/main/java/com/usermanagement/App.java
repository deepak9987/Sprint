package com.usermanagement;

import com.usermanagement.dao.*;
import com.usermanagement.entity.Role;
import com.usermanagement.util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class App {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		AdminDAO adminDAO = new AdminDAO();
		UserDAO userDAO = new UserDAO();
		RoleDAO roleDAO = new RoleDAO();
		PermissionDAO permissionDAO = new PermissionDAO();

		while (true) {
			System.out.println("\n=== USER MANAGEMENT SYSTEM ===");
			System.out.println("1. Admin Management");
			System.out.println("2. User Management");
			System.out.println("3. Role Management");
			System.out.println("4. Permission Management");
			System.out.println("5. Exit");
			System.out.print("Enter choice: ");

			int choice = readInt();
			switch (choice) {
			case 1:
				adminMenu(adminDAO);
				break;
			case 2:
				userMenu(userDAO);
				break;
			case 3:
				roleMenu(roleDAO);
				break;
			case 4:
				permissionMenu(permissionDAO);
				break;
			case 5:
				System.out.println("👋 Exiting...");
				HibernateUtil.shutdown();
				sc.close();
				System.exit(0);
				break;
			default:
				System.out.println("❌ Invalid choice! Enter between 1-5.");
			}
		}
	}

	// ✅ Safe method to read integer input (prevents NumberFormatException)
	private static int readInt() {
		while (true) {
			String input = sc.nextLine().trim();
			if (input.isEmpty()) {
				System.out.print("⚠️ Input cannot be empty! Enter again: ");
				continue;
			}
			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.print("❌ Invalid number! Enter again: ");
			}
		}
	}

	private static void adminMenu(AdminDAO dao) {
		while (true) {
			System.out.println("\n=== ADMIN MANAGEMENT ===");
			System.out.println("1. Add Admin");
			System.out.println("2. View All Admins");
			System.out.println("3. Update Admin");
			System.out.println("4. Delete Admin");
			System.out.println("5. Back");
			System.out.print("Enter choice: ");

			int choice = readInt();
			switch (choice) {
			case 1:
				dao.addAdmin(sc);
				break;
			case 2:
				dao.viewAdmins();
				break;
			case 3:
				dao.updateAdmin(sc);
				break;
			case 4:
				dao.deleteAdmin(sc);
				break;
			case 5:
				return;
			default:
				System.out.println("❌ Invalid choice!");
			}
		}
	}

	private static void userMenu(UserDAO dao) {
		while (true) {
			System.out.println("\n=== USER MANAGEMENT ===");
			System.out.println("1. Add User");
			System.out.println("2. View All Users");
			System.out.println("3. Update User");
			System.out.println("4. Delete User");
			System.out.println("5. Back");
			System.out.print("Enter choice: ");

			int choice = readInt();
			switch (choice) {
			case 1:
				dao.addUser(sc);
				break;
			case 2:
				dao.viewUsers();
				break;
			case 3:
				dao.updateUser(sc);
				break;
			case 4:
				dao.deleteUser(sc);
				break;
			case 5:
				return;
			default:
				System.out.println("❌ Invalid choice!");
			}
		}
	}

	private static void roleMenu(RoleDAO roleDao) {
		while (true) {
			System.out.println("\n=== ROLE MANAGEMENT ===");
			System.out.println("1. Add Role");
			System.out.println("2. View All Roles");
			System.out.println("3. Update Role");
			System.out.println("4. Delete Role");
			System.out.println("5. Back");
			System.out.print("Enter choice: ");

			int choice = readInt();
			switch (choice) {
			case 1:
				System.out.print("Enter role name: ");
				String name = sc.nextLine();
				System.out.print("Enter description: ");
				String desc = sc.nextLine();
				Role newRole = new Role(name, desc);
				if (roleDao.addRole(newRole))
					System.out.println("✅ Role added successfully!");
				else
					System.out.println("❌ Failed to add role!");
				break;

			case 2:
				List<Role> roles = roleDao.getAllRoles();
				System.out.println("\n=== ALL ROLES ===");
				if (roles != null && !roles.isEmpty()) {
					for (Role r : roles) {
						System.out.println("ID: " + r.getRoleId() +
								" | Name: " + r.getRoleName() +
								" | Description: " + r.getDescription());
					}
				} else {
					System.out.println("⚠️ No roles found!");
				}
				break;

			case 3:
				System.out.print("Enter Role ID to update: ");
				int updateId = readInt();
				System.out.print("Enter new role name: ");
				String newName = sc.nextLine();
				System.out.print("Enter new description: ");
				String newDesc = sc.nextLine();
				if (roleDao.updateRole(updateId, newName, newDesc))
					System.out.println("✅ Role updated successfully!");
				else
					System.out.println("❌ Failed to update role!");
				break;

			case 4:
				System.out.print("Enter Role ID to delete: ");
				int deleteId = readInt();
				if (roleDao.deleteRole(deleteId))
					System.out.println("✅ Role deleted successfully!");
				else
					System.out.println("❌ Failed to delete role!");
				break;

			case 5:
				return;

			default:
				System.out.println("⚠️ Invalid choice!");
			}
		}
	}

	private static void permissionMenu(PermissionDAO dao) {
		while (true) {
			System.out.println("\n=== PERMISSION MANAGEMENT ===");
			System.out.println("1. Add Permission");
			System.out.println("2. View All Permissions");
			System.out.println("3. Update Permission");
			System.out.println("4. Delete Permission");
			System.out.println("5. Back");
			System.out.print("Enter choice: ");

			int choice = readInt();
			switch (choice) {
			case 1:
				dao.addPermission(sc);
				break;
			case 2:
				dao.viewPermissions();
				break;
			case 3:
				dao.updatePermission(sc);
				break;
			case 4:
				dao.deletePermission(sc);
				break;
			case 5:
				return;
			default:
				System.out.println("❌ Invalid choice!");
			}
		}
	}
}

//STEP 1. Import required packages

import java.sql.*;
import java.util.Scanner;

public class JdbcDemo {

   // Set JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://localhost:3306/projectdb?useSSL=false";

   // Database credentials
   static final String USER = "root";// add your user
   static final String PASSWORD = "kritvi2612";// add password

   public static void main(String[] args) {
      Connection conn = null;
      Statement stmt = null;

      // STEP 2. Connecting to the Database
      try {
         // STEP 2a: Register JDBC driver
         Class.forName(JDBC_DRIVER);
         // STEP 2b: Open a connection
         System.out.println("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
         // STEP 2c: Execute a query
         System.out.println("Creating statement...");
         stmt = conn.createStatement();
         conn.setAutoCommit(false);
         // INSERT, UPDATE, DELETE
         // stmt.executeUpdate(createEmployee);
         int prompt = 1;
         while (prompt > 0 && prompt < 18) {
            // Menu-List
            System.out.println();
            System.out.println("Select an option from the below menu");
            System.out.println("1. Add a student");
            System.out.println("2. Add a teacher");
            System.out.println("3. Add a course(insert n update)");
            System.out.println("4. Add a book");
            System.out.println("5. Add student to existing courses");
            System.out.println("6. List all students");
            System.out.println("7. List all teachers");
            System.out.println("8. List all courses");
            System.out.println("9. List all books");
            System.out.println("10. List all students in corresponding courses");
            System.out.println("11. Update existing student");
            System.out.println("12. List student with maximum courses enrolled");
            System.out.println("13. List all students in a course");
            System.out.println("14. List teacher taking maximum no. of courses");
            System.out.println("15. Update existing book");
            System.out.println("16. Delete a particular teacher");
            System.out.println("17. Delete a particular student");
            System.out.println("Enter any other number to exit");
            System.out.print("Enter your choice: ");
            Scanner sc = new Scanner(System.in);
            prompt = sc.nextInt();
            System.out.println();
            if (prompt == 1) {
               System.out.print("First Name: ");
               String fn = sc.next();
               System.out.print("Last Name: ");
               String ln = sc.next();
               System.out.print("Roll No: ");
               Integer roll = sc.nextInt();
               String sql = "insert into student" + " (fname, lname, roll_no)" + " values (?, ?, ?)";
               PreparedStatement myStmt = conn.prepareStatement(sql);
               // set param values
               myStmt.setString(1, fn);
               myStmt.setString(2, ln);
               myStmt.setInt(3, roll);
               // 3. Execute SQL query
               if (myStmt.executeUpdate() < 0) {
                  System.out.println("Insertion failed");
                  conn.rollback();
               }
               else{
                  conn.commit();
               }
            } else if (prompt == 2) {
               System.out.print("Name: ");
               String fn = sc.next();
               System.out.print("Teacher ID: ");
               Integer roll = sc.nextInt();
               String sql = "insert into teacher" + " (teacher_name, teacher_id)" + " values (?, ?)";
               PreparedStatement myStmt = conn.prepareStatement(sql);
               // set param values
               myStmt.setString(1, fn);
               myStmt.setInt(2, roll);
               // 3. Execute SQL query
               if (myStmt.executeUpdate() < 0) {
                  System.out.println("Insertion failed");
                  conn.rollback();
               }
               else{
                  conn.commit();
               }
            } else if (prompt == 3) {
               System.out.print("Course Name: ");
               String fn = sc.next();
               System.out.print("Course ID: ");
               Integer ln = sc.nextInt();
               String sql = "insert into course" + " (course_name, course_id, teacher_id)" + " values (?, ?, NULL)";
               PreparedStatement myStmt = conn.prepareStatement(sql);
               // set param values
               myStmt.setString(1, fn);
               myStmt.setInt(2, ln);
               myStmt.executeUpdate();
               System.out.print("Teacher ID: ");
               Integer roll = sc.nextInt();
               String mysql = "update course set teacher_id = " + roll + " where course_id = " + ln;
               myStmt = conn.prepareStatement(mysql);
               // 3. Execute SQL query
               if (myStmt.executeUpdate() < 0) {
                  System.out.println("Insertion failed");
                  conn.rollback();
               }
               else{
                  conn.commit();
               }
            } else if (prompt == 4) {
               sc.nextLine();
               System.out.print("ISBN: ");
               String fn = sc.nextLine();
               System.out.print("Book Name: ");
               String ln = sc.nextLine();
               System.out.print("Author: ");
               String lns = sc.nextLine();
               System.out.print("Course ID: ");
               Integer roll = sc.nextInt();
               String sql = "insert into book" + " values (?, ?, ?, ?)";
               PreparedStatement myStmt = conn.prepareStatement(sql);
               // set param values
               myStmt.setString(1, fn);
               myStmt.setString(2, ln);
               myStmt.setString(3, lns);
               myStmt.setInt(4, roll);
               // 3. Execute SQL query
               if (myStmt.executeUpdate() < 0) {
                  System.out.println("Insertion failed");
                  conn.rollback();
               }
               else{
                  conn.commit();
               }
            } else if (prompt == 5) {
               System.out.print("Course ID: ");
               Integer ln = sc.nextInt();
               System.out.print("Student ID: ");
               Integer roll = sc.nextInt();
               String sql = "insert into student_course" + " values (?, ?)";
               PreparedStatement myStmt = conn.prepareStatement(sql);
               // set param values
               myStmt.setInt(1, roll);
               myStmt.setInt(2, ln);
               // 3. Execute SQL query
               if (myStmt.executeUpdate() < 0) {
                  System.out.println("Insertion failed");
                  conn.rollback();
               }
               else{
                  conn.commit();
               }
            } else if (prompt == 6) {
               String query = "SELECT * from student";
               ResultSet rs = stmt.executeQuery(query);
               while (rs.next()) {

                  // Retrieve by column name
                  String fname = rs.getString("fname");
                  String lname = rs.getString("lname");
                  Integer dno = rs.getInt("roll_no");

                  // Display values
                  System.out.print("fname: " + fname);
                  System.out.print(", lname: " + lname);
                  System.out.println(", roll_no: " + dno);
               }
               rs.close();
            } else if (prompt == 7) {
               String query = "SELECT * from teacher";
               ResultSet rs = stmt.executeQuery(query);
               while (rs.next()) {

                  // Retrieve by column name
                  String fname = rs.getString("teacher_name");
                  Integer dno = rs.getInt("teacher_id");

                  // Display values
                  System.out.print("teacher_name: " + fname);
                  System.out.println(", teacher_id: " + dno);
               }
               rs.close();
            } else if (prompt == 8) {
               String query = "SELECT * from course";
               ResultSet rs = stmt.executeQuery(query);
               while (rs.next()) {

                  // Retrieve by column name
                  String fname = rs.getString("course_name");
                  Integer lname = rs.getInt("course_id");
                  Integer dno = rs.getInt("teacher_id");

                  // Display values
                  System.out.print("course_name: " + fname);
                  System.out.print(", course_id: " + lname);
                  System.out.println(", teacher_id: " + dno);
               }
               rs.close();
            } else if (prompt == 9) {
               String query = "SELECT * from book";
               ResultSet rs = stmt.executeQuery(query);
               while (rs.next()) {

                  // Retrieve by column name
                  String fname = rs.getString("ISBN");
                  String lname = rs.getString("book_name");
                  String name = rs.getString("author");
                  Integer dno = rs.getInt("course_id");

                  // Display values
                  System.out.print("ISBN: " + fname);
                  System.out.print(", book_name: " + lname);
                  System.out.print(", author: " + name);
                  System.out.println(", course_id: " + dno);
               }
               rs.close();
            } else if (prompt == 10) {
               String query = "SELECT * from student_course";
               ResultSet rs = stmt.executeQuery(query);
               while (rs.next()) {

                  // Retrieve by column name
                  Integer fname = rs.getInt("roll_no");
                  Integer dno = rs.getInt("course_id");

                  // Display values
                  System.out.print("roll_no: " + fname);
                  System.out.println(", course_id: " + dno);
               }
               rs.close();
            } else if (prompt == 11) {
               System.out.print("Enter Roll No of student to be updated: ");
               Integer roll = sc.nextInt();
               System.out.print("First Name: ");
               String fn = sc.next();
               System.out.print("Last Name: ");
               String ln = sc.next();
               System.out.print("Roll_no: ");
               Integer lq = sc.nextInt();
               String sql = "update student" + " set fname = ?, lname = ?, roll_no = ?" + " where roll_no = ?";
               PreparedStatement myStmt = conn.prepareStatement(sql);
               // set param values
               myStmt.setString(1, fn);
               myStmt.setString(2, ln);
               myStmt.setInt(3, lq);
               myStmt.setInt(4, roll);
               // 3. Execute SQL query
               if (myStmt.executeUpdate() < 0) {
                  System.out.println("Updation failed");
                  conn.rollback();
               }
               else{
                  conn.commit();
               }
            } else if (prompt == 12) {
               String query = "create view max_courses as select fname, lname, count(*) as no_courses from student_course INNER JOIN student where student_course.roll_no = student.roll_no group by student_course.roll_no";
               if(stmt.execute(query)){
                  conn.rollback();
               }
               else{
                  conn.commit();
               }
               String q = "select * from max_courses where no_courses = (select max(no_courses) from max_courses)";
               ResultSet rs = stmt.executeQuery(q);
               while (rs.next()) {

                  // Retrieve by column name
                  String fname = rs.getString("fname");
                  String lname = rs.getString("lname");
                  Integer no = rs.getInt("no_courses");
                  // Display values
                  System.out.print("fname: " + fname);
                  System.out.print(", lname: " + lname);
                  System.out.println(", no_courses: " + no);
               }
               rs.close();
            } else if (prompt == 13) {
               String q = "select fname, lname, course_id from student_course inner join student where course_id = ";
               System.out.print("Enter course_id: ");
               Integer k = sc.nextInt();
               q = q + k + " and student_course.roll_no = student.roll_no";
               ResultSet rs = stmt.executeQuery(q);
               while (rs.next()) {

                  // Retrieve by column name
                  String fname = rs.getString("fname");
                  String lname = rs.getString("lname");
                  Integer no = rs.getInt("course_id");
                  // Display values
                  System.out.print("fname: " + fname);
                  System.out.print(", lname: " + lname);
                  System.out.println(", course_id: " + no);
               }
               rs.close();
            } else if (prompt == 14) {
               String query = "create view max_teach as select teacher_name, count(*) as no_courses from course INNER JOIN teacher where course.teacher_id = teacher.teacher_id group by course.teacher_id";
               if(stmt.execute(query)){
                  conn.rollback();
               }
               else{
                  conn.commit();
               }
               String q = "select * from max_teach where no_courses = (select max(no_courses) from max_teach)";
               ResultSet rs = stmt.executeQuery(q);
               while (rs.next()) {

                  // Retrieve by column name
                  String fname = rs.getString("teacher_name");
                  Integer no = rs.getInt("no_courses");
                  // Display values
                  System.out.print("teacher_name: " + fname);
                  System.out.println(", no_courses: " + no);
               }
               rs.close();
            } else if (prompt == 15) {
               sc.nextLine();
               System.out.print("Enter ISBN of the book to be updated: ");
               String roll = sc.nextLine();
               System.out.print("New ISBN: ");
               String wq = sc.nextLine();
               System.out.print("Book name: ");
               String fn = sc.nextLine();
               System.out.print("Author: ");
               String ln = sc.nextLine();
               System.out.print("Course_id: ");
               Integer lq = sc.nextInt();
               String sql = "update book" + " set book_name = ?, author = ?, course_id = ?, ISBN = ?"
                     + " where ISBN = ?";
               PreparedStatement myStmt = conn.prepareStatement(sql);
               // set param values
               myStmt.setString(1, fn);
               myStmt.setString(2, ln);
               myStmt.setInt(3, lq);
               myStmt.setString(4, wq);
               myStmt.setString(5, roll);
               // 3. Execute SQL query
               if (myStmt.executeUpdate() < 0) {
                  System.out.println("Updation failed");
                  conn.rollback();
               }
               else{
                  conn.commit();
               }
            } else if (prompt == 16) {
               System.out.print("Teacher_id: ");
               Integer lq = sc.nextInt();
               String sql = "delete from teacher" + " where teacher_id = ?";
               PreparedStatement myStmt = conn.prepareStatement(sql);
               // set param values
               myStmt.setInt(1, lq);
               // 3. Execute SQL query
               if (myStmt.executeUpdate() < 0) {
                  System.out.println("Deletion failed");
                  conn.rollback();
               }
               else{
                  conn.commit();
               }
            }
            else if (prompt == 17) {
               System.out.print("Roll_No: ");
               Integer lq = sc.nextInt();
               String sql = "delete from student" + " where roll_no = ?";
               PreparedStatement myStmt = conn.prepareStatement(sql);
               // set param values
               myStmt.setInt(1, lq);
               // 3. Execute SQL query
               if (myStmt.executeUpdate() < 0) {
                  System.out.println("Deletion failed");
                  conn.rollback();
               }
               else{
                  conn.commit();
               }
            }
            // STEP 5: Clean-up environment
         }
         stmt.close();
         conn.close();
      } catch (SQLException se) { // Handle errors for JDBC
         se.printStackTrace();
      } catch (Exception e) { // Handle errors for Class.forName
         e.printStackTrace();
      } finally { // finally block used to close resources regardless of whether an exception was
                  // thrown or not
         try {
            if (stmt != null)
               stmt.close();
         } catch (SQLException se2) {
         }
         try {
            if (conn != null)
               conn.close();
         } catch (SQLException se) {
            se.printStackTrace();
         } // end finally try
      } // end try
      System.out.println("End of Code");
   } // end main
} // end class

// Note : By default autocommit is on. you can set to false using
// con.setAutoCommit(false)

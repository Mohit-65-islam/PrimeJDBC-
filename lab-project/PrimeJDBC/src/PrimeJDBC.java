import java.sql.*;
import java.util.Scanner;

/**
 * Task 2: JDBC connectivity - n-th prime insertion.
 * Requires MySQL (XAMPP) running and the table from sql/setup.sql.
 */
public class PrimeJDBC {

    // --- Change these to match your database ---
    private static final String URL   = "jdbc:mysql://localhost:3306/lab_db";
    private static final String USER  = "root";
    private static final String PASS  = "";            // XAMPP default: empty
    private static final String TABLE = "prime_number"; // your table name

    // Returns true if num is prime
    static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; (long) i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    // Finds the n-th prime number
    static int nthPrime(int n) {
        int count = 0, candidate = 1;
        while (count < n) {
            candidate++;
            if (isPrime(candidate)) count++;
        }
        return candidate;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();

        int prime = nthPrime(n);
        System.out.println("The " + n + "-th prime number is: " + prime);

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("Connected to database successfully.");

            // 1. Insert the calculated prime
            String insertSql = "INSERT INTO " + TABLE + " (n, nth_prime) VALUES (?, ?)";
            try (PreparedStatement ps = con.prepareStatement(insertSql)) {
                ps.setInt(1, n);
                ps.setInt(2, prime);
                ps.executeUpdate();
                System.out.println("Record inserted.");
            }

            // 2. Retrieve and display stored records to verify
            String selectSql = "SELECT id, n, nth_prime FROM " + TABLE;
            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(selectSql)) {
                System.out.println("ID | n | nth_prime");
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + " | "
                            + rs.getInt("n") + " | "
                            + rs.getInt("nth_prime"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

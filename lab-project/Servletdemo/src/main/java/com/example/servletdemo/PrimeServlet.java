package com.example.servletdemo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Task 4: Browser-based primality testing.
 * URL: http://localhost:8080/Servletdemo/prime?number=17
 */
@WebServlet("/prime")
public class PrimeServlet extends HttpServlet {

    // Returns true if num is a prime number
    private boolean isPrime(long num) {
        if (num < 2) return false;
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String param = req.getParameter("number");

        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'><title>Prime Number Checker</title>");
        out.println("<style>"
                + "body{font-family:Arial,sans-serif;max-width:480px;margin:60px auto;padding:0 16px;}"
                + "input[type=text]{padding:8px;font-size:16px;}"
                + "input[type=submit]{padding:8px 16px;font-size:16px;cursor:pointer;}"
                + ".result{margin-top:24px;padding:14px;border-radius:6px;font-size:18px;}"
                + ".prime{background:#e6f6e6;color:#1a6b1a;}"
                + ".notprime{background:#fdeaea;color:#a12020;}"
                + ".error{background:#fff4e0;color:#8a5a00;}"
                + "</style></head><body>");
        out.println("<h2>Prime Number Checker</h2>");
        out.println("<form method='get' action='prime'>"
                + "Enter an integer: <input type='text' name='number' value='"
                + (param == null ? "" : escape(param)) + "'/> "
                + "<input type='submit' value='Check'/></form>");

        if (param != null && !param.isBlank()) {
            try {
                long number = Long.parseLong(param.trim());
                if (isPrime(number)) {
                    out.println("<div class='result prime'><b>" + number
                            + "</b> is a PRIME number.</div>");
                } else {
                    out.println("<div class='result notprime'><b>" + number
                            + "</b> is NOT a prime number.</div>");
                }
            } catch (NumberFormatException e) {
                out.println("<div class='result error'>Please enter a valid integer.</div>");
            }
        }
        out.println("</body></html>");
    }

    // Prevents HTML injection when echoing user input back
    private String escape(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("'", "&#39;").replace("\"", "&quot;");
    }
}

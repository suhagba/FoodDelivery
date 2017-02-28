/*
 * This code is the property of mylo
 * The developers of this project own full rights to this code  * 
 */
package controller;

import application_exceptions.*;
import dbAccessor.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mailAgent.MailAutoPassword;
import randomPasswordGenerator.GenratePassword;
import model.Login;
import model.User;
import saltHash.SaltHash;

/**
 *
 *
 * @author Puru
 */
public class LoginControllerServlet extends HttpServlet {

    String userPath;
    HttpSession session;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        userPath = request.getServletPath();        //Gets the path of the current web page
        String url = "/WEB-INF/" + userPath + ".jsp";       //Gets the full url link of the current page

        if (userPath.equals("/userregister")) {
            request.setAttribute("registerError", false);       //On load, the variable registerError is set to false so 'registration error' doesn't pop-up
            request.setAttribute("registerErrorMessage", null);     //Registration error message is null since no error occurs on load
            this.dispatcher(request, response, userPath);
        }

        if (userPath.equals("/userlogin")) {
            request.setAttribute("loginError", false);      //On load, the variable loginError is set to false so 'login error' doesn't pop-up
            request.setAttribute("loginErrorMessage", null);        //Login error message is null since no error occurs on load
            this.dispatcher(request, response, userPath);
        }

        if (userPath.equals("/userLogOff")) {
            session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect(request.getContextPath() + "/expert");
        }
        if (userPath.equals("/forgotPassword")) {
            request.setAttribute("Error", false);      //On load, the variable loginError is set to false so 'login error' doesn't pop-up
            request.setAttribute("ErrorMessage", null);
            request.setAttribute("success", false);
            this.dispatcher(request, response, userPath);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        userPath = request.getServletPath();

        if (userPath.equals("/userlogin")) {

            String email = request.getParameter("username");        //Stores user input username (email) on login
            String password = request.getParameter("password");     //Stores user input password on login
            char[] passArr = password.toCharArray();        //Converts string passwrod into an array of characters
            boolean validate = true;        //Boolean for validating login (correct/incorrect password)

            try {
                LoginDAO loginDAO = new LoginDAOImpl();
                Login login = loginDAO.getLoginFromID(email);
                if (login != null) {
                    byte[] salt = SaltHash.toByteArray(login.getSalt());        //Obtains salt in an array of bytes (salt is mixed with the password before hashing)
                    byte[] hash = SaltHash.toByteArray(login.getHash());      //Obtains hash in an array of bytes (hashing secures the password)
                    validate = SaltHash.isExpectedPassword(passArr, salt, hash);        //Sets boolean value depending on whether password matches the un-hashed un-salted stored password
                    if (!(validate)) {
                        throw new ExLoginFailed();
                    } else {
                        session = request.getSession(true);
                        UserDAO userDAO = new UserDAOImpl();
                        User user = userDAO.getUserFromID(email);
                        session.setAttribute("userFname", user.getfName());
                        session.setAttribute("userEmail", user.getEmail());
                        response.sendRedirect("/home");
                    }
                } else {
                    throw new ExLoginFailed();
                }

            } catch (ExLoginFailed e) {
                request.setAttribute("loginError", true);
                request.setAttribute("loginErrorMessage", e.getMessage());
                String url = "/WEB-INF/" + userPath + ".jsp";
                try {
                    request.getRequestDispatcher(url).forward(request, response);
                } catch (ServletException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        if (userPath.equals("/userregister")) {
            String email = request.getParameter("email");       //Stores user input username (email) on registration
            String password = request.getParameter("password");     //Stores user input password on registration
            String salutation = request.getParameter("salutation");     //Stores user input salutation on registration
            String fname = request.getParameter("fname");       //Stores user input first name on registration
            String lname = request.getParameter("lname");       //Stores user input last name on registration
            String address = request.getParameter("address");       //Stores user input address on registration
            String pNumber = request.getParameter("pNumber");       //Stores user input phone number on registration
            String pNumber2 = request.getParameter("pNumber2");       //Stores user input phone number 2 on registration
            char[] passArr = password.toCharArray();        //Converts string passwrod into an array of characters

            try {
                LoginDAO loginDAO = new LoginDAOImpl();
                byte[] salt = SaltHash.getNextSalt();        //Obtains salt in an array of bytes which is to be used to mix up the password
                byte[] hash = SaltHash.hash(passArr, salt);     //Obtains hash in an array of bytes, which is used to hash the salt-password combination
                String salted = SaltHash.toHexString(salt);     //Result of salting
                String hashed = SaltHash.toHexString(hash);     //Result of hashing
                Login login;
                login = new Login();
                login.setId(email);
                login.setHash(hashed);
                login.setSalt(salted);
                loginDAO.addLogin(login);
                UserDAO userDAO = new UserDAOImpl();
                User user = new User(fname, lname, salutation, email, address, pNumber, pNumber2);      //Creates new user and stores details

                if (!(userDAO.addUser(user))) {
                    throw new ExRegisterFailed("Cannot add user details.");
                }

                response.sendRedirect("/login");        //redirects to login page after successful registration

            } catch (ExRegisterFailed e) {
                request.setAttribute("registerError", true);
                request.setAttribute("registerErrorMessage", e.getMessage());
                String url = "/WEB-INF/" + userPath + ".jsp";
                try {
                    request.getRequestDispatcher(url).forward(request, response);
                } catch (ServletException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (userPath.equals("/forgotPassword")) {
            String email = request.getParameter("email");
            boolean success = true;
            try {
                LoginDAO loginDAO = new LoginDAOImpl();
                Login login = loginDAO.getLoginFromID(email);
                if (login != null) {
                    GenratePassword gp = new GenratePassword();
                    String newPassword = gp.randomString(16);
                    char[] passArr = newPassword.toCharArray();
                    byte[] salt = SaltHash.getNextSalt();        //Obtains salt in an array of bytes which is to be used to mix up the password
                    byte[] hash = SaltHash.hash(passArr, salt);     //Obtains hash in an array of bytes, which is used to hash the salt-password combination
                    String salted = SaltHash.toHexString(salt);     //Result of salting
                    String hashed = SaltHash.toHexString(hash);
                    login.setHash(hashed);
                    login.setSalt(salted);
                    loginDAO.updatePass(login);
                    MailAutoPassword.generateAndSendEmail(email, newPassword);

                } else {
                    success = false;
                    throw new ExLoginFailed("Invalid email. Please register with this email before login");

                }

            } catch (ExLoginFailed e) {
                success = false;
                request.setAttribute("Error", true);
                request.setAttribute("ErrorMessage", e.getMessage());
                this.dispatcher(request, response, userPath);
            } catch (MessagingException ex) {
                success = false;
                Logger.getLogger(LoginControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("Error", true);
                request.setAttribute("ErrorMessage", "Failed to send the newly generated password. Please contact mylo Admin as your account is locked.");
            }

            request.setAttribute("success", success);
            request.setAttribute("Error", false);

            this.dispatcher(request, response, userPath);

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void dispatcher(HttpServletRequest request, HttpServletResponse response, String userPath) {
        String url = "/WEB-INF/Views/" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

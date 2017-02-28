/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application_exceptions;

/**
 *
 * @author Puru
 */
public class ExLoginFailed extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ExLoginFailed() {
        super("Login failed.");
    }

    public ExLoginFailed(String message) {
        super(message);
    }
}

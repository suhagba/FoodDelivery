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
public class ExRegisterFailed extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ExRegisterFailed() {
        super("Registration failed.");
    }

    public ExRegisterFailed(String message) {
        super(message);
    }
}

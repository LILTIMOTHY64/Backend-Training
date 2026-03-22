package org.employeemanagement;

class InvalidChoice extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public InvalidChoice() {
        super("Invalid Choice Selected");
    }
}

class InvalidDesignation extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public InvalidDesignation() {
        super("Invalid Designation Entered");
    }
}
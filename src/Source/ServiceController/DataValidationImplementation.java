package Source.ServiceController;

import Source.DataValidation;

import java.util.regex.Pattern;

public class DataValidationImplementation implements DataValidation {
    @Override
    public boolean validateUserName(String username) {
        //check if username size is <3 and if the first character is not upper case
        return username.length() >= 3 && Character.isUpperCase(username.charAt(0));
        //it can be replaced with regex
        //^([A-Z].*?){3,}$
    }

    @Override
    public boolean validatePassword(String password) {
       return Pattern.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*_-]).{6,}$",password);
       //at least one lowercase (?=.*?[a-z])
       //at least one uppercase (?=.*?[A-Z])
       //at least one digit (?=.*?[0-9])
       //at least one special character (?=.*?[0-9])

        //The lookahead ( (?=.*?[A-Z]) ) is used to check if after some characters
        // if there is an occurrence of an upper case letter. Similarly, all the other
        // lookaheads(lower, numbers, etc.) are checked to complete the whole regex.


    }
}

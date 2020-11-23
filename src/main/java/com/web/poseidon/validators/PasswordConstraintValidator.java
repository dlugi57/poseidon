package com.web.poseidon.validators;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * PasswordConstraint verify the input password according to the 4 constraints set (between 8 and 30 characters, 1 uppercase, 1 number, 1 special)
 */
public class PasswordConstraintValidator implements ConstraintValidator<Password, String> {

    @Override
    public void initialize(Password arg0) {
    }


    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 125),
                new UppercaseCharacterRule(1),
                new DigitCharacterRule(1),
                new SpecialCharacterRule(1)));

        RuleResult result = validator.validate(new PasswordData(password));
        return result.isValid();
    }
}
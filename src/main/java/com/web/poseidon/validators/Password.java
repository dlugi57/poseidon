package com.web.poseidon.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Retention(RUNTIME)
public @interface Password {

    String message() default "Invalid Password. At least one uppercase letter, at least 8 " +
            "characters, at least one number and one symbol.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
package leoluiten.presentation.utils.validations.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Custom annotation for validating passwords. This annotation uses
 * {@link PasswordConstraintValidator} to enforce password rules such as length,
 * character types, and sequences.
 */
@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface ValidPassword {

    /**
     * The default error message that will be shown when the password
     * validation fails.
     *
     * @return the error message.
     */
    String message() default "Invalid password";

    /**
     * Allows specification of validation groups, to which this constraint
     * belongs. This must default to an empty array.
     *
     * @return the groups the constraint belongs to.
     */
    Class<?>[] groups() default {};

    /**
     * Can be used by clients to assign custom payload objects to a constraint.
     *
     * @return custom payload objects.
     */
    Class<? extends Payload>[] payload() default {};
}

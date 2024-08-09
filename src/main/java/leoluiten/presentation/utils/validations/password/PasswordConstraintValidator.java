package leoluiten.presentation.utils.validations.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.Arrays;

/**
 * Custom password constraint validator that checks whether a given password
 * meets specific security requirements.
 */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    /**
     * Initializes the validator in preparation for checking password constraints.
     *
     * @param arg0 the annotation instance for a given constraint declaration (not used in this implementation).
     */
    @Override
    public void initialize(ValidPassword arg0) {}

    /**
     * Validates a password based on defined rules, such as length, character content,
     * and absence of common sequences.
     *
     * @param password the password string to be validated.
     * @param context the context in which the constraint is evaluated.
     * @return {@code true} if the password is valid according to the rules, {@code false} otherwise.
     */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30),  // Password must be between 8 and 30 characters long.
                new CharacterRule(EnglishCharacterData.UpperCase, 1),  // At least one uppercase letter.
                new CharacterRule(EnglishCharacterData.Digit, 1),  // At least one digit.
                new CharacterRule(EnglishCharacterData.Special, 1),  // At least one special character.
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 3, false),  // No numerical sequences of 3 or more characters.
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 3, false),  // No alphabetical sequences of 3 or more characters.
                new IllegalSequenceRule(EnglishSequenceData.USQwerty, 3, false),  // No keyboard sequences of 3 or more characters.
                new WhitespaceRule()));  // No whitespace allowed.

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                        String.join(",", validator.getMessages(result)))
                .addConstraintViolation();
        return false;
    }
}

package ua.example.annotations.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {

        Pattern pattern = Pattern.compile("\\A\\+380-\\(([69])([0356789])\\)-[0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]\\Z");

        return pattern.matcher(phoneNumber).find();
    }
}

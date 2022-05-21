package com.br.sarahaa.validators;

import com.br.sarahaa.exceptions.ObjectValidationException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectsValidator<T extends Serializable> {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void validate(T clazz) {
        Set<ConstraintViolation<T>> violations = validator.validate(clazz);
        if (violations.size() > 0) {
            Set<String> errors = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
            throw new ObjectValidationException(errors, clazz.getClass().getName());
        }
    }

}
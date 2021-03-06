package com.blablaprincess.springboot_simplejava.business.validation.integers;

import com.blablaprincess.springboot_simplejava.business.common.utils.IntUtils;
import com.blablaprincess.springboot_simplejava.business.validation.ValidationException;
import com.blablaprincess.springboot_simplejava.business.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "validation.int.max-length.enabled", havingValue = "true")
@RequiredArgsConstructor
public class MaxLengthValidator implements Validator<Integer> {

    @Value("${validation.int.max-length:8}")
    private final Integer maxLength;

    @Override
    public void validate(Integer value) throws ValidationException {
        if (IntUtils.getLength(value) > maxLength) {
            throw new ValidationException(String.format("The number cannot be larger than %d signs", maxLength));
        }
    }

}

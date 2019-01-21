package com.mojito.mojitoboot.common.utils.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


/**
 * @Auther: Mojito
 * @Date: 2019/1/21 23:37
 * @Description:
 */
@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;

    public ValidationResult validate(Object object){
        final ValidationResult validationResult = new ValidationResult();
        Set<ConstraintViolation<Object>> validateSets = validator.validate(object);
        if(validateSets.size()>0){
            validationResult.setHasError(true);
            validateSets.forEach(item->{
                String errMsg = item.getMessage();
                String propertyName = item.getPropertyPath().toString();
                validationResult.getErrorMsgMap().put(propertyName,errMsg);
            });
        }
        return validationResult;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // hibernate的validator通过工厂化的方式使其实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}

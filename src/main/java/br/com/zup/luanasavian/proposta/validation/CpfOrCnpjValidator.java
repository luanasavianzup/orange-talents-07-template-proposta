package br.com.zup.luanasavian.proposta.validation;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfOrCnpjValidator implements ConstraintValidator<CpfOrCnpj, CharSequence> {
    private CPFValidator cpfValidator = new CPFValidator();
    private CNPJValidator cnpjValidator = new CNPJValidator();

    @Override
    public void initialize(CpfOrCnpj constraintAnnotation) {
        cpfValidator.initialize(null);
        cnpjValidator.initialize(null);
    }

    @Override
    public boolean isValid(CharSequence documento, ConstraintValidatorContext context) {
        return cpfValidator.isValid(documento, context) || cnpjValidator.isValid(documento, context);
    }

}

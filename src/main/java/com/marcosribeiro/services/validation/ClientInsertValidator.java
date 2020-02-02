package com.marcosribeiro.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.marcosribeiro.domain.enums.ClientType;
import com.marcosribeiro.dto.ClientNewDTO;
import com.marcosribeiro.resources.exception.FieldMessage;
import com.marcosribeiro.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getType().equals(ClientType.PERSON.getCode()) && !BR.isValidCPF(objDto.getRegister())) {
			list.add(new FieldMessage("register", "CPF Inválido!"));
		}
		
		if (objDto.getType().equals(ClientType.COMPANY.getCode()) && !BR.isValidCNPJ(objDto.getRegister())) {
			list.add(new FieldMessage("register", "CNPJ Inválido!"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
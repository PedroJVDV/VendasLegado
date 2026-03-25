package com.pedrojvdv.jdbc.validation;

import com.pedrojvdv.jdbc.exception.UserException;
import com.pedrojvdv.jdbc.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class UserValidation {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z 0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE
    );


    // TODO: VALIDATE --> USER PASSWORD

    public void validate(User user){
        validateName(user.getName());
        validateEmail(user.getEmail());
        validateAge(user.getAge());
    }

    public void validateId(Long id){
        if (id == null || id < 0){
            throw new UserException("ID não pode ser nulo!");
        }
    }

    private void validateName(String name){
        if (name == null || name.trim().isEmpty()){
            throw new UserException("Nome não pode ser vazio!");
        }
        if (name.length() < 3){
            throw new UserException("Nome deve conter ao menos 3 caracteres!");
        }
    }

    private void validateEmail(String email){
        if (email == null || email.trim().isEmpty()) {
            throw new UserException("Email não pode ser vazio!");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new UserException("Formato de email não aceito!");
        }
    }

    private void validateAge(Integer age){
        if (age <= 0) {
            throw new UserException("Idade inválida, digite uma válida!");
        }
        if (age < 16) {
            throw new UserException("Usuário deve possuir ao menos 16 anos!");
        }
    }

}

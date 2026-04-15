package com.pedrojvdv.jdbc.validation;

import com.pedrojvdv.jdbc.exception.AuthException;
import com.pedrojvdv.jdbc.exception.UserException;
import com.pedrojvdv.jdbc.model.User;
import com.pedrojvdv.jdbc.util.PasswordEncoder;

import java.util.regex.Pattern;

public final class UserValidation {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,}$"
    );

    public void validate(User user){
        validateName(user.getName());
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
    }

    public void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new UserException("Senha não pode ser vazia!");
        }
        if (password.length() < 6) {
            throw new UserException("Senha deve conter ao menos 6 caracteres!");
        }
    }

    public void validateName(String name){
        if (name == null || name.trim().isEmpty()){
            throw new UserException("Nome não pode ser vazio!");
        }
        if (name.length() < 3){
            throw new UserException("Nome deve conter ao menos 3 caracteres!");
        }
    }

    public void validateEmail(String email){
        if (email == null || email.isEmpty()) {
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

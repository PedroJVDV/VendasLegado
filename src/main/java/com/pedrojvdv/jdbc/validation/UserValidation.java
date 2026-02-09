package com.pedrojvdv.jdbc.validation;

import com.pedrojvdv.jdbc.model.Users;
import com.pedrojvdv.jdbc.exception.AgeException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class UserValidation {

    // Aplica regra referente a digitação de email, emails "falsos" não serão aceitos!!
    // Exemplos de emails não aceitos: Joao"##"@gmail.get, teste@.com, usuario@meudominio. e etc...

    private static final Pattern EMAIL_PATT = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE
    );

    private UserValidation() {
    }

    public static List<String> listValidate(Users users) {
        List<String> errorsList = new ArrayList<>();

        // aqui retorna a lista de erros, está armazenado todas as mensagens etc.
        if (users == null) {
            errorsList.add("Usuário não pode ser nulo!");
            return errorsList;
        }

        String nome = users.getNome();
        if (nome == null || nome.trim().isEmpty()) {
            errorsList.add("É obrigatório ter um nome!");
        } else if (nome.trim().length() < 3) {
            errorsList.add("Nome deve conter ao menos 3 caracteres");
        }

        String email = users.getEmail();
        if (email == null || email.trim().isEmpty()) {
            errorsList.add("É obrigatório ter email");
        } else if (!EMAIL_PATT.matcher(email).matches()) {
            errorsList.add("Formato de email não aceito!");
        }

        try {
            int idade = users.getIdade();
            if (idade <= 0) {
                errorsList.add("Idade inválida, digite uma válida.");
            } else if (idade < 16) {
                errorsList.add("Usuário deve possuir ao menos 16 anos.");
            }

            // aqui eu criei uma idade exception apenas para retornar algo especifico
        } catch (AgeException e) {
            throw new RuntimeException(e);
        }

        return errorsList;
    }

}

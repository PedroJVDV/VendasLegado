package com.pedrojvdv.jdbc.model;

public class Users {

    private int id;
    private int idade;
    private String email;
    private String nome;

    public Users(int id, String email, String nome) {
        this.id = id;
        this.email = email;
        this.nome = nome;
    }

    public String getNome() {return nome;}

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package br.motorola.manpower.service;

import java.util.List;

public interface CRUDService <T>{
    
    // Metodo para inserir um novo objeto do T
    public T save(T object);

    // Obter objeto do T pelo ID
    public T getById(Long id);

    // Obter todos os objetos do tipo T
    public List<T> getAll();

    // Busca um objeto contendo o termo
    public List<T> getByAll(String termo);

    // Atualizar objeto object
    public T update(T object);

    // Deleta o objeto com o id
    public void delete(Long id);
}

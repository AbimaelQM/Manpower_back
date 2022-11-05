package br.motorola.manpower.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface CRUDController <T>{
    
    
    public ResponseEntity<T> insert(T object);

    public ResponseEntity<List<T>> getAll();

    public ResponseEntity<List<T>> getByAll(String termo);

    public ResponseEntity<T> getById(Long id);

    public ResponseEntity<T> update(T object);

    public ResponseEntity<?> delete(Long id);
}

package com.example.repositories;

import java.util.List;

/**
 * Created by monju on 06-Jan-17.
 */
public interface CRUDRepository {
    <T> void create(T o);

    <T> void createOrUpdate(List<T> o);

    <T> void delete(List<T> o);

    <T> void delete(T o);

    <T> void update(T o);
}

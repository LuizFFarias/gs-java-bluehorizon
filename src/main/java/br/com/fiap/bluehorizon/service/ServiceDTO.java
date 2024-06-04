package br.com.fiap.bluehorizon.service;

import org.springframework.data.domain.Example;

import java.util.Collection;

public interface ServiceDTO <Entity, Request, Response>{

    Collection<Entity> findAll();

    Collection<Entity> findAll(Example<Entity> example);

    Entity findById(Long id);

    Entity save(Entity e);

    Entity toEntity(Request dto);

    Response toResponse(Entity e);
}

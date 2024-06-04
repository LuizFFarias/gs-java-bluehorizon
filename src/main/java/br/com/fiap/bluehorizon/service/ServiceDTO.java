package br.com.fiap.bluehorizon.service;

import org.springframework.data.domain.Example;

import java.util.Collection;

public interface ServiceDTO <Entity, Request, Response>{

    public Collection<Entity> findAll();

    public Collection<Entity> findAll(Example<Entity> example);

    public Entity findById(Long id);

    public Entity save(Entity e);

    public Entity toEntity(Request dto);

    public Response toResponse(Entity e);
}

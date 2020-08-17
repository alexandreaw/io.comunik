package io.comunik.repositoryadapters;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import io.comunik.models.EnvioComunicacaoEntity;

public interface EnvioComunicacaoRepositoryJpa extends CrudRepository<EnvioComunicacaoEntity, UUID> {
}
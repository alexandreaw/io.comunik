package io.comunik.repositoryadapters;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.comunik.mappers.EnvioComunicacaoRepositoryMapper;
import io.comunik.models.EnvioComunicacao;
import io.comunik.models.EnvioComunicacaoEntity;
import io.comunik.ports.EnvioComunicaoRepository;

@Repository
public class EnvioComunicacaoRepositoryImpl implements EnvioComunicaoRepository {
	
	private EnvioComunicacaoRepositoryJpa jpa;

	@Autowired
	EnvioComunicacaoRepositoryImpl(EnvioComunicacaoRepositoryJpa jpa) {
        this.jpa = jpa;
    }

	@Override
	public String add(EnvioComunicacao comunicacao) {
		EnvioComunicacaoEntity envioComunicacaoEntity = jpa.save(EnvioComunicacaoRepositoryMapper.toDatabaseEntity(comunicacao));
		
		return envioComunicacaoEntity.getId().toString();
	}

	@Override
	public Optional<EnvioComunicacao> findById(String id) {
		Optional<EnvioComunicacaoEntity> comunicacao = jpa.findById(UUID.fromString(id));
		return comunicacao.map(EnvioComunicacaoRepositoryMapper::toDomainEntity);
	}

	@Override
	public void deleteById(String id) {
		jpa.deleteById(UUID.fromString(id));
	}
}

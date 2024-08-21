package com.library.management.system.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PublisherRepository extends JpaRepository<PublisherEntity,Long> {
}

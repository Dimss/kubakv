package com.redhat.kubakv.repository;


import com.redhat.kubakc.model.Metadata;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetadataRepository extends CrudRepository<Metadata, String> {
}

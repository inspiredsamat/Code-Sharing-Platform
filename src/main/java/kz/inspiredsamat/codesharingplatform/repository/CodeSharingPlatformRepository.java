package kz.inspiredsamat.codesharingplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kz.inspiredsamat.codesharingplatform.model.Code;

@Repository
public interface CodeSharingPlatformRepository extends JpaRepository<Code, String> {
    List<Code> findAll();
}
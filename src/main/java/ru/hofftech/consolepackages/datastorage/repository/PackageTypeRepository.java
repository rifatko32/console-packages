package ru.hofftech.consolepackages.datastorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;

import java.util.List;
import java.util.Map;

/**
 * Interface for package type repository
 */
@Repository
public interface PackageTypeRepository extends JpaRepository<PackageType, Long> {

    @Query("select p from PackageType p where p.id in :ids")
    public List<PackageType> findByIds(List<Long> ids);
}

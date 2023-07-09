package com.dashboard.repository;

import com.dashboard.model.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface WidgetRepository extends JpaRepository<Widget, Long> {
    Optional<Widget> findByName(String name);
    Set<Widget> findAllByServiceName(String serviceName);
    Boolean existsByName(String email);
}

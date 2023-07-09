package com.dashboard.repository;

import com.dashboard.model.UserWidget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserWidgetRepository extends JpaRepository<UserWidget, Long> {


}

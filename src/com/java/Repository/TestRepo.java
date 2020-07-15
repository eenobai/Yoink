package com.java.Repository;

import com.java.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TestRepo extends JpaRepository<TestModel, Integer> {
}

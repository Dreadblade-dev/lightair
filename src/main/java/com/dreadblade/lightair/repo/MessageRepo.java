package com.dreadblade.lightair.repo;

import com.dreadblade.lightair.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}

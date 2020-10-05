package com.dreadblade.lightair.repo;

import com.dreadblade.lightair.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}

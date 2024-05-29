package com.sparta.schedulemanagement_spring.repository;

import com.sparta.schedulemanagement_spring.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
}

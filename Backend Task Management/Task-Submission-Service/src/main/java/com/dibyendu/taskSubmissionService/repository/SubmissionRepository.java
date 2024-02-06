package com.dibyendu.taskSubmissionService.repository;

import com.dibyendu.taskSubmissionService.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long>
{
    List<Submission> findByTaskId(Long taskId);
}

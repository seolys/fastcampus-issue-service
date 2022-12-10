package seol.issueservice.domain

import org.springframework.data.jpa.repository.JpaRepository
import seol.issueservice.domain.enums.IssueStatus

interface IssueRepository : JpaRepository<Issue, Long> {
    fun findAllByStatusOrderByCreatedAtDesc(status: IssueStatus): List<Issue>
}
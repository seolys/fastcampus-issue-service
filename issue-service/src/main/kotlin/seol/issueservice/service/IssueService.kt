package seol.issueservice.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import seol.issueservice.domain.Issue
import seol.issueservice.domain.IssueRepository
import seol.issueservice.model.IssueRequest
import seol.issueservice.model.IssueResponse

@Service
class IssueService(
    private val issueRepository: IssueRepository
) {

    @Transactional
    fun create(userId: Long, request: IssueRequest): IssueResponse {
        val issue = Issue(
            summary = request.summary,
            description = request.description,
            userId = userId,
            type = request.type,
            priority = request.priority,
            status = request.status,
        )
        return IssueResponse(issueRepository.save(issue))
    }
}
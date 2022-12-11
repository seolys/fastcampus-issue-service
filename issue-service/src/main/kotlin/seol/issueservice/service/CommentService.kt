package seol.issueservice.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import seol.issueservice.domain.Comment
import seol.issueservice.domain.CommentRepository
import seol.issueservice.domain.IssueRepository
import seol.issueservice.exception.NotFoundException
import seol.issueservice.model.CommentRequest
import seol.issueservice.model.CommentResponse
import seol.issueservice.model.toResponse

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val issueRepository: IssueRepository
) {

    @Transactional
    fun create(
        issueId: Long,
        userId: Long,
        username: String,
        request: CommentRequest
    ): CommentResponse {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw NotFoundException("이슈가 존재하지 않습니다.")

        val comment = Comment(
            issue = issue,
            userId = userId,
            username = username,
            body = request.body,
        )

        issue.comments.add(comment)
        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    fun edit(id: Long, userId: Long, request: CommentRequest): CommentResponse? {
        return commentRepository.findByIdAndUserId(id, userId)?.run {
            body = request.body
            commentRepository.save(this).toResponse()
        }
    }

}
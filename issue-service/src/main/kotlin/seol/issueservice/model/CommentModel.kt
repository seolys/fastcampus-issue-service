package seol.issueservice.model

import com.fasterxml.jackson.annotation.JsonFormat
import seol.issueservice.domain.Comment
import java.time.LocalDateTime

data class CommentRequest(
    val body: String
)

data class CommentResponse(
    val id: Long,
    val issueId: Long,
    val userId: Long,
    val body: String,
    val username: String? = null,
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime?,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime?,
)

fun Comment.toResponse() = CommentResponse(
    id = id!!,
    issueId = issue.id!!,
    userId = userId,
    body = body,
    username = username,
    createdAt = createdAt,
    updatedAt = updatedAt,
)
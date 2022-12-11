package seol.issueservice.web

import org.springframework.web.bind.annotation.*
import seol.issueservice.config.AuthUser
import seol.issueservice.model.CommentRequest
import seol.issueservice.model.CommentResponse
import seol.issueservice.service.CommentService

@RestController
@RequestMapping("/api/v1/issues/{issueId}/comments")
class CommentController(
    private val commentService: CommentService
) {

    @PostMapping
    fun create(
        authUser: AuthUser,
        @PathVariable issueId: Long,
        @RequestBody commentRequest: CommentRequest
    ): CommentResponse {
        return commentService.create(issueId, authUser.userId, authUser.username, commentRequest)
    }

    @PutMapping("/{id}")
    fun edit(
        authUser: AuthUser,
        @PathVariable id: Long,
        @RequestBody request: CommentRequest
    ) = commentService.edit(id, authUser.userId, request)

}
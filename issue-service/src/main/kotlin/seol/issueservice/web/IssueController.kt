package seol.issueservice.web

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import seol.issueservice.config.AuthUser
import seol.issueservice.model.IssueRequest
import seol.issueservice.model.IssueResponse
import seol.issueservice.service.IssueService

@RestController
@RequestMapping("/api/v1/issues")
class IssueController(
    private val issueService: IssueService
) {

    @PostMapping
    fun create(
        authUser: AuthUser,
        @RequestBody request: IssueRequest
    ): IssueResponse {
        return issueService.create(authUser.userId, request)
    }

}
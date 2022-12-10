package seol.issueservice.web

import org.springframework.web.bind.annotation.*
import seol.issueservice.config.AuthUser
import seol.issueservice.domain.enums.IssueStatus
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

    @GetMapping
    fun getAll(
        authUser: AuthUser,
        @RequestParam(required = false, defaultValue = "TODO") status: IssueStatus,
    ) = issueService.getAll(status)

    @GetMapping("/{id}")
    fun get(
        authUser: AuthUser,
        @PathVariable id: Long
    ) = issueService.get(id)

}
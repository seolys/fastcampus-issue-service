package seol.issueservice.config

import org.springframework.context.annotation.Configuration
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@Configuration
class WebConfig(
    private val authUserHandlerArgumentResolver: AuthUserHandlerArgumentResolver
) : WebMvcConfigurationSupport() {

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
        /*
         * 범위지정 함수
         * 특정 객체에 대한 작업을 블록 안에 넣어 실행할 수 있도록 하는 함수이다.
         * 블록은 특정 객체에 대해 할 작업의 범위가 되며, 따라서 범위 지정 함수라 부른다.
         *
         * apply
         * apply는 수신객체 내부 프로퍼티를 변경한다음 수신객체 자체를 반환하기 위해 사용되는 함수이다.
         * 따라서 객체 생성 시에 다양한 프로퍼티를 설정해야 하는 경우 자주 사용된다.
         *
         * apply에서의 block은 람다식의 수신객체로 apply의 수신객체(T)를 지정하기 때문에,
         * 람다식 내부에서 수신객체에 대한 명시를 하지 않고 함수를 호출할 수 있게 된다.
         * */
        argumentResolvers.apply {
            add(authUserHandlerArgumentResolver)
        }
    }
}

@Component
class AuthUserHandlerArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return AuthUser::class.java.isAssignableFrom(parameter.parameterType)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        return AuthUser(
            userId = 1,
            username = "test"
        )
    }

}

data class AuthUser(
    val userId: Long,
    val username: String,
    val profileUrl: String? = null
)

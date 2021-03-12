package ch.mofobo.impacct.interceptors

import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.jvm.Throws

@Component
class CustomAccessDeniedHandler : AccessDeniedHandler {
    @Throws(IOException::class)
    override fun handle(req: HttpServletRequest, res: HttpServletResponse, e: AccessDeniedException) {
        res.sendRedirect("/error/403")
    }
}
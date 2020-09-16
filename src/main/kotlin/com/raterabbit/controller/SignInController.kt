package com.raterabbit.controller

import com.raterabbit.dto.SignInRequest
import com.raterabbit.dto.SignInResponse
import com.raterabbit.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import org.mindrot.jbcrypt.BCrypt
import java.nio.charset.Charset
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

@Controller("/sign-in")
class SignInController(private val userService: UserService) {

    @Post("/")
    fun signIn(request: SignInRequest): HttpResponse<SignInResponse> {
        val user = userService.findByUserName(request.userName)
        if (user == null || !BCrypt.checkpw(request.password, user.hashedPassword)) {
            return HttpResponse.unauthorized<SignInResponse>()
        }
        val token = Jwts.builder()
                .setIssuer("raterabbit")
                .setSubject(user.userName)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .signWith(Keys.hmacShaKeyFor(UUID.randomUUID().toString()
                        .toByteArray(Charset.forName("UTF-8")))
                )
                .compact()

        return HttpResponse.ok(SignInResponse(token))
    }

}

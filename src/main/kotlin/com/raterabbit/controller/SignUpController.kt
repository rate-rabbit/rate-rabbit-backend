package com.raterabbit.controller

import com.raterabbit.dto.SignUpRequest
import com.raterabbit.dto.SignUpResponse
import com.raterabbit.entity.User
import com.raterabbit.service.UserService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import org.mindrot.jbcrypt.BCrypt


@Controller("/sign-up")
class SignUpController(private val userService: UserService) {

    @Post("/")
    fun signUp(request: SignUpRequest): SignUpResponse {
        val hashedPassword = BCrypt.hashpw(request.password, BCrypt.gensalt())
        val insertedId = userService.insert(User(request.userName, hashedPassword))
        return SignUpResponse(insertedId!!.asObjectId().value.toHexString())
    }
}

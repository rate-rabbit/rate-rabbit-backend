package com.raterabbit.entity

import io.micronaut.core.annotation.Introspected

@Introspected
class User {
    var userName: String = ""
    var hashedPassword: String = ""

    constructor(userName: String, hashedPassword: String) {
        this.userName = userName
        this.hashedPassword = hashedPassword
    }

    constructor()
}

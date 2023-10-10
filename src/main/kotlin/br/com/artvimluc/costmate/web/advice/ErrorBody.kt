package br.com.artvimluc.costmate.web.advice

import org.springframework.http.HttpStatus

class ErrorBody (
    var erroCode: String? = null,
    var statusCode: Int? = null,
    var message: String? = null,
)
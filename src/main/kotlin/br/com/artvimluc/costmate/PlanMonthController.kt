package br.com.artvimluc.costmate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plan-month")
class PlanMonthController

@PostMapping
fun  create(): ResponseEntity<String> {
    return ResponseEntity.ok("foi")
}


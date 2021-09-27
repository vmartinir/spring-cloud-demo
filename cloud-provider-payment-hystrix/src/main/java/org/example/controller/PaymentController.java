package org.example.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.entities.Payment;
import org.example.model.CommonResult;
import org.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @PostMapping("/")
    public CommonResult create(@RequestBody Payment payment) {
        log.info("Request port: {}", serverPort);
        int result = paymentService.create(payment);
        if (result > 0) {
            return new CommonResult(200, "success", result);
        }
        return new CommonResult(400, "failed");
    }

    @GetMapping("/{id}")
    public CommonResult getById(@PathVariable("id") Long id) {
        log.info("Request port: {}", serverPort);
        Payment payment = paymentService.getById(id);
        if (Optional.ofNullable(payment).isPresent()) {
            return new CommonResult<>(200, "success", payment);
        }
        return new CommonResult<>(400, "failed");
    }

    @GetMapping("/")
    public CommonResult getByCondition(@RequestParam(required = false) String search) {
        log.info("Request port: {}", serverPort);
        List<Payment> payments = paymentService.getByCondition(search);
        if (Optional.ofNullable(payments).isPresent()) {
            return new CommonResult<>(200, "success", payments);
        }
        return new CommonResult<>(400, "failed");
    }

    @GetMapping("/timeout")
    public CommonResult getByConditionTimeout(@RequestParam(required = false) String search) {
        log.info("Request port: {}", serverPort);
        List<Payment> payments = paymentService.getByConditionTimeout(search);
        if (Optional.ofNullable(payments).isPresent()) {
            return new CommonResult<>(200, "success", payments);
        }
        return new CommonResult<>(400, "failed");
    }

    @GetMapping("/circuit")
    public CommonResult getByConditionCircuit(@RequestParam(required = false) String search) {
        log.info("Request port: {}", serverPort);
        List<Payment> payments = paymentService.getByConditionCircuit(search);
        if (Optional.ofNullable(payments).isPresent()) {
            return new CommonResult<>(200, "success", payments);
        }
        return new CommonResult<>(400, "failed");
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        log.info("Request port: {}", serverPort);
        int result = paymentService.delete(id);
        if (result > 0) {
            return new CommonResult(200, "success", result);
        }
        return new CommonResult(400, "failed");
    }
}

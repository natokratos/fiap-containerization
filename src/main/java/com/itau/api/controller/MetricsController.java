package com.itau.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itau.api.service.MetricService;

@RestController
@RequestMapping("/")
public class MetricsController {
	
	@Autowired
	public MetricService metricService;
	
	ArrayList<String> result = new ArrayList<String>();
	
	@GetMapping("/metrics")
	public List<String> doMetrics() {
		result.add("Total de requisicoes: " + metricService.getTotalRequests());
		result.add("Media dos tempos das requisicoes: " + metricService.getAverageProcessTime());
		result.add("Total de requisicoes por operação: " + metricService.getTotalRequestsByOperationType().toString());
		result.add("Total de requisicoes por usuario: " + metricService.getTotalRequestsByUser().toString());
		return result;
	}
}

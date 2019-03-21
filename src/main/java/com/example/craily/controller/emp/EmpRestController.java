package com.example.craily.controller.emp;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.craily.po.Emp;
import com.example.craily.utils.PageUtil;

@RestController
@RequestMapping("/emp")
public class EmpRestController {

	@PostMapping(value="/queryEmp", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Emp>> queryEmp(PageUtil pageUtil) {
		return null;
	}
}

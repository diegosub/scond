package br.com.cdtec.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdtec.entity.Categoria;
import br.com.cdtec.security.jwt.JwtTokenUtil;
import br.com.cdtec.service.CategoriaService;

@RestController
@RequestMapping("/api/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController extends CDTecController<Categoria, BigInteger, CategoriaService> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	protected JwtTokenUtil jwtTokenUtil;

	
}

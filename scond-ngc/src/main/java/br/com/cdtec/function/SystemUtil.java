package br.com.cdtec.function;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cdtec.security.entity.Usuario;
import br.com.cdtec.security.jwt.JwtTokenUtil;
import br.com.cdtec.security.service.UsuarioService;

public class SystemUtil {

	@Autowired
	public static JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	public static UsuarioService usuarioService;
	
	public static Usuario getUsuarioFromRequest(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		String login = jwtTokenUtil.getLoginFromToken(token);
		
		return usuarioService.pesquisarPorLogin(login);
		
	}
	
}

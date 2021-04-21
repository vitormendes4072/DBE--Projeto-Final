package br.com.fiap.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import br.com.fiap.bo.UsuarioBo;
import br.com.fiap.dao.EnderecoDao;
import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.dao.impl.EnderecoDaoImpl;
import br.com.fiap.dao.impl.UsuarioDaoImpl;
import br.com.fiap.singleton.EntityManagerFactorySingleton;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SalvarUsuario
 */
@WebServlet("/SalvarUsuario")
public class SalvarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalvarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String bodyPost = IOUtils.toString(request.getReader());
		JSONObject usuarioJson = JSONObject.fromObject(bodyPost);

		
		EntityManagerFactory emf = EntityManagerFactorySingleton.getInstance();
		EntityManager em = emf.createEntityManager();
		
		UsuarioDao uDao = new UsuarioDaoImpl(em);
		EnderecoDao eDao = new EnderecoDaoImpl(em);
		
		UsuarioBo ubo = new UsuarioBo(uDao, eDao);
		JSONObject jsonRetorno = ubo.saveUsuario(usuarioJson);
		
		response.setContentType("text/html; charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonRetorno.toString());
	}

}

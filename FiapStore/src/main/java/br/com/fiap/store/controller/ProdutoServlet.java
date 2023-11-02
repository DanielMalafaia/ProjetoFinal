package br.com.fiap.store.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.store.bean.Produto;
import br.com.fiap.store.dao.ProdutoDAO;
import br.com.fiap.store.excpetion.DBException;
import br.com.fiap.store.factory.DAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/produto")
public class ProdutoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private ProdutoDAO dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getProdutoDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			String nome = request.getParameter("nome");
			int quantidade = Integer.parseInt(request.getParameter("quantidade"));
			double preco = Double.parseDouble(request.getParameter("valor"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar fabricacao = Calendar.getInstance();
			fabricacao.setTime(format.parse(request.getParameter("fabricacao")));
			
			Produto produto = new Produto(0, nome, preco, fabricacao, quantidade); 
			dao.cadastrar(produto);
			
			request.setAttribute("msg", "Produto cadastrado!");
		}catch(DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("erro","Por favor, valide os dados");
		}
		request.getRequestDispatcher("cadastro-produto.jsp").forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Produto> lista = dao.listar();
			request.setAttribute("produtos", lista);
			request.getRequestDispatcher("lista-produto.jsp").forward(request, response);	
	}

}
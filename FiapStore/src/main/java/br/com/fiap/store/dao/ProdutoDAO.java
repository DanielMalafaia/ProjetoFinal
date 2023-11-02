package br.com.fiap.store.dao;

import java.util.List;
import br.com.fiap.store.bean.Produto;
import br.com.fiap.store.excpetion.DBException;

public interface ProdutoDAO {
	
	void cadastrar(Produto produto) throws DBException;
	void atualizar(Produto produto) throws DBException;
	void remover(int codigo) throws DBException;
	Produto buscar(int id);
	List<Produto> listar();
}
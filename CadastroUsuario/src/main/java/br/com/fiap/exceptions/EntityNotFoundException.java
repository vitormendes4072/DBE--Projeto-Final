package br.com.fiap.exceptions;

@SuppressWarnings("serial")
public class EntityNotFoundException  extends Exception {

	public EntityNotFoundException() {
		super("Entidade não encontrada");
	}
	
	public EntityNotFoundException(String msg) {
		super(msg);
	}
}

package com.jacaranda.memoryStorage;

import com.jacaranda.publicacion.*;
import com.jacarnda.usuario.Usuario;

public class MemoryStorage {
	private static final int NUM_MAXIMO_USUARIOS = 15;
	private static final int NUM_MAXIMO_PUBLICACIONES = 50;
	private int numUsuariosActuales;
	private int numPublicacionesActuales;
	private Usuario[] listaUsuarios;
	private Publicacion[] listaPublicaciones;
	
	public MemoryStorage() {
		this.numUsuariosActuales = 0;
		this.numPublicacionesActuales = 0;
		this.listaUsuarios = new Usuario[NUM_MAXIMO_USUARIOS];
		this.listaPublicaciones = new Publicacion[NUM_MAXIMO_PUBLICACIONES];
	}
	//Le introducimos el nombre del usuario que queremos buscar y nos devolvera la posicion del mismo
	private int posicionUsuario(String usuario) throws MemoryStorageException {
		boolean encontrado = false;
		int resultado = 0;
		for (int i=0; i < numUsuariosActuales || encontrado != true; i++) {
			if (numUsuariosActuales == 0 || listaUsuarios[i] == null) {
				throw new MemoryStorageException("El usuario no existe.");
			}
			else if (listaUsuarios[i].getLogin().equals(usuario)) {
				resultado = i;
				encontrado = true;
			}
		}
		return resultado;
	}
	//Añade un usuario a la lista
	public void addUsuario(String login, String pass) throws MemoryStorageException {
		if (numUsuariosActuales == NUM_MAXIMO_USUARIOS) {
			throw new MemoryStorageException("La lista de usuarios esta completa.");
		}
		listaUsuarios[numUsuariosActuales++] = new Usuario(login, pass);
	}
	//Añade un tweet a la lista de publicaciones
	public void addPublicacion(String mensaje, String login) throws MemoryStorageException {
		int posicion = posicionUsuario(login);
		try {
			listaPublicaciones[numPublicacionesActuales] = new Tweet(mensaje, listaUsuarios[posicion]);
			this.numPublicacionesActuales ++;
		} catch (PublicacionException e) {
			throw new MemoryStorageException(e.getMessage());
		}
	}
	//Añade un post en la lista de publicaciones
	public void addPublicacion(String mensaje, String login, String tema) throws MemoryStorageException {
		int posicion = posicionUsuario(login);
		try {
			listaPublicaciones[numPublicacionesActuales] = new Post(mensaje, listaUsuarios[posicion], tema);
			this.numPublicacionesActuales ++;
		} catch (PublicacionException e) {
			throw new MemoryStorageException(e.getMessage());
		}
	}
	//Añade una recomendacion en la lista de publicaciones
	public void addPublicacion(String mensaje, String login, int estrellas) throws MemoryStorageException {
		int posicion = posicionUsuario(login);
		try {
			listaPublicaciones[numPublicacionesActuales] = new Recomendacion(mensaje, listaUsuarios[posicion], estrellas);
			this.numPublicacionesActuales ++;
		} catch (PublicacionException e) {
			throw new MemoryStorageException(e.getMessage());
		}
	}
	//Nos muestra todas las publicaciones que existen
	public String mostrarListaPublicaciones() {
		StringBuilder resultado = new StringBuilder();
		for (int i=0; i < numPublicacionesActuales; i++) {
			resultado.append(listaPublicaciones[i] + "\n\n");
		}
		return resultado.toString();
	}
	//Solo nos muestra las publicaciones que son tweets
	public String mostrarTweets() {
		StringBuilder resultado = new StringBuilder();
		for (int i=0; i < numPublicacionesActuales; i++) {
			if (listaPublicaciones[i] instanceof Tweet) {
				resultado.append(listaPublicaciones[i] + "\n\n");
			}
		}
		return resultado.toString();
	}
	//Solo nos muestra los posts
	public String mostrarPosts() {
		StringBuilder resultado = new StringBuilder();
		for (int i=0; i < numPublicacionesActuales; i++) {
			if (listaPublicaciones[i] instanceof Post) {
				resultado.append(listaPublicaciones[i] + "\n\n");
			}
		}
		return resultado.toString();
	}
	//Nos muestra las recomendaciones
	public String mostrarRecomendacion() {
		StringBuilder resultado = new StringBuilder();
		for (int i=0; i < numPublicacionesActuales; i++) {
			if (listaPublicaciones[i] instanceof Recomendacion) {
				resultado.append(listaPublicaciones[i] + "\n\n");
			}
		}
		return resultado.toString();
	}
}

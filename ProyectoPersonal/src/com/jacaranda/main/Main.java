package com.jacaranda.main;

import java.time.LocalDateTime;

import com.jacaranda.usuario.Estandar;
import com.jacaranda.usuario.Premium;
import com.jacaranda.usuario.UsuarioException;

public class Main {

	public static void main(String[] args) throws UsuarioException {
		Premium u = new Premium("florianRM", "florian", 2000);
		Estandar u1 = new Estandar("florian", "florian");
		u.comprarBillete("Florian", "First", LocalDateTime.of(2022, 5, 28, 12, 50), "Iberia");
		System.out.println(u.getSaldo());
		System.out.println(u.listadoBilletes());
		System.out.println(u.calcularPrecio());
		System.out.println(u.getSaldo());

		System.out.println(u1.getSaldo());
		u1.setSaldo(400);
		u1.comprarBillete("Florian", "Economy", LocalDateTime.of(2022, 9, 28, 12, 50), "Iberia");
		System.out.println(u1.calcularPrecio());
		System.out.println(u1.getSaldo());
	}

}

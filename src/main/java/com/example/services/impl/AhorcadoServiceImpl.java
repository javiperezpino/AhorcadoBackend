package com.example.services.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Palabra;
import com.example.entity.Partida;
import com.example.repository.PalabraRepository;
import com.example.repository.PartidaRepository;
import com.example.responses.PartidaResponse;
import com.example.services.AhorcadoService;
import com.example.utils.Utils;

@Service
public class AhorcadoServiceImpl implements AhorcadoService {

	@Autowired
	private PalabraRepository palabraRepository;
	@Autowired
	private PartidaRepository partidaRepository;
	
	public final int MAX_ERRORS = 6;
	public final String EMPTY_CHAR_MARKER = "-";
	public final String LETTER_SEPARATOR = ", ";
	
	
	@Override
	public PartidaResponse NewGame() {
		//Creamos y seteamos una nueva partida
		Partida partida = new Partida();
		partida.setNumeroDeFallos(MAX_ERRORS);
		partida.setLetrasUsadas("");
		//Pedimos el total de rows en la tabla palabra
		int totalDePalabras = (int) palabraRepository.count();
		//Pedimos una palabra con un id aleatoria entre 1 y el total de palabras
		int idPalabra = Utils.getRandomNumberInRange(1, totalDePalabras);
		Optional<Palabra> pa = palabraRepository.findById(idPalabra);
		partida.setPalabra(pa.get().getNombre());
		//rellenamos con guiones la palabra a acertar
		String palabraRellenada = "";
		for (int i = 0; i < partida.getPalabra().length(); ++i) {
			palabraRellenada += EMPTY_CHAR_MARKER;
		}
		//Se inserta la partida y se devuelve sin la palabra a acertar
		partida.setPalabraRellenada(palabraRellenada);
		partidaRepository.save(partida);
		return new PartidaResponse(partida);
	}
	
	@Override
	public PartidaResponse UpdateGame(int idPartida, Character letra) {
		//Pedimos a la base de datos la partida que corresponde al id recibido
		Optional<Partida> partidaOpt = partidaRepository.findById(idPartida);
		Partida partida = partidaOpt.get();
		String palabra = partida.getPalabra();
		//Comprobamos si la letra recibida esta en la palabra a acertar, en cuyo caso modificamos la palabra incógnita
		char[] palabraRellenada = partida.getPalabraRellenada().toCharArray();
		
		boolean encontrada = false;
		for(int i = 0; i < palabra.length(); i++) {
			if(palabra.charAt(i) == letra) {
				palabraRellenada[i] = letra;
				encontrada = true;
			}
		}
		//Añadimos la letra al array de letras usadas y si no estaba la letra restamos un fallo
		if(!encontrada) partida.setNumeroDeFallos(partida.getNumeroDeFallos().intValue() - 1);
		else partida.setPalabraRellenada(new String(palabraRellenada));
		
		partida.setLetrasUsadas(partida.getLetrasUsadas() + letra + LETTER_SEPARATOR);
		//Actualizamos la partida en la base de datos y devolvemos su versión sin la palabra a acertar
		partidaRepository.save(partida);
		return new PartidaResponse(partida);
	}

	@Override
	public Optional<Palabra> SearchPalabra(int id) {
		//Devuelve una palabra en base a su id
		return palabraRepository.findById(id);
	}

	@Override
	public List<Palabra> SearchAllPalabras() {
		//Devuelve la lista completa de palabras
		return palabraRepository.findAll();
	}

	@Override
	public Palabra InsertPalabra(String nombre) {
		//Creamos una nueva palabra en base al nombre recibido y comprobamos si es una palabra y que no exista ya en la base de datos en cuyo caso devolvemos un null
		Palabra palabra = new Palabra(nombre);
		if(!IsOnDataBase(palabra) && IsWord(palabra)) {
			palabraRepository.save(palabra);
			return palabra;
		}
		return null;
	}
	
	@Override
	public Optional<PartidaResponse> GetPalabraRellenada(int idPartida) {
		//Pedimos a la base de datos la partida que corresponde al id recibido seteando la palabra incógnita con la palabra a acertar 
		Optional<Partida> partidaOpt = partidaRepository.findById(idPartida);
		Partida partida = partidaOpt.get();
		partida.setPalabraRellenada(partida.getPalabra());
		
		return Optional.of(new PartidaResponse(partida));
	}
	
	public boolean IsOnDataBase(Palabra palabra) {
		//Comprobamos si la palabra que queremos insertar existe o no en la base de datos
		boolean exists = false;
		List<Palabra> lista = SearchAllPalabras();
		for (Palabra palabra2 : lista) {
			if(palabra.getNombre().equals(palabra2.getNombre())) {
				exists = true;
				break;
			}
		}
		return exists;
	}
	
	public boolean IsWord(Palabra palabra) {
		//Comprobamos que cada letra de la palabra a insertar es del alfabeto, para evitar que se inserten números o carácteres especiales
		char[] chars = palabra.getNombre().toCharArray();
		for (char c : chars) {
			if(!Character.isLetter(c)) return false;
		}
		return true;
	}


}

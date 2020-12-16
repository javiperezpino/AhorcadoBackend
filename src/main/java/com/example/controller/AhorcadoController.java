package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Palabra;
import com.example.responses.PartidaResponse;
import com.example.services.AhorcadoService;

//Contiene los end points de la api
@RestController
@CrossOrigin(origins = { "https://javiperezpino.github.io" })
public class AhorcadoController {
	
	public final String PARTIDA_PATH = "/partida";
	
	@Autowired
	private AhorcadoService ahorcadoService;

	@GetMapping("/")
	public List<Palabra> getAllPalabras() {
		return ahorcadoService.SearchAllPalabras();
	}

	@GetMapping("/{id}")
	public Optional<Palabra> getPalabra(@PathVariable int id) {
		return ahorcadoService.SearchPalabra(id);
				
	}

	@PutMapping(PARTIDA_PATH + "/{idPartida}/{letra}")
	public ResponseEntity<PartidaResponse> updatePartida(@PathVariable int idPartida, @PathVariable Character letra) {
		return new ResponseEntity<PartidaResponse>(ahorcadoService.UpdateGame(idPartida, letra), HttpStatus.OK);
	}
	
	@PutMapping("/palabra/{nombre}")
	public ResponseEntity<Palabra> insertPalabra(@PathVariable String nombre) {
		//Comprobamos si nos ha devuelto una palabra en cuyo caso devolvemos un OK (Code 200) y en caso de ser null un BAD_REQUEST(Code 400)
		Palabra palabra = ahorcadoService.InsertPalabra(nombre);
		if(palabra != null) return new ResponseEntity<Palabra>(palabra, HttpStatus.OK);
		else return new ResponseEntity<Palabra>(palabra, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/nuevaPartida")
	public ResponseEntity<PartidaResponse> createPartida() {

		return new ResponseEntity<PartidaResponse>(ahorcadoService.NewGame(), HttpStatus.OK);
	}
	
	@GetMapping(PARTIDA_PATH + "/{idPartida}")
	public Optional<PartidaResponse> GetPalabraRellenada(@PathVariable int idPartida) {
		return ahorcadoService.GetPalabraRellenada(idPartida);
	}

}

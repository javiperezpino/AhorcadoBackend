package com.example.services;

import java.util.List;
import java.util.Optional;

import com.example.entity.Palabra;
import com.example.responses.PartidaResponse;

public interface AhorcadoService {
	public PartidaResponse NewGame();
	public PartidaResponse UpdateGame(int idPartida, Character letra);
	public Optional<Palabra> SearchPalabra(int id);
	public List<Palabra> SearchAllPalabras();
	public Palabra InsertPalabra(String nombre);
	public Optional<PartidaResponse> GetPalabraRellenada(int idPartida);
}

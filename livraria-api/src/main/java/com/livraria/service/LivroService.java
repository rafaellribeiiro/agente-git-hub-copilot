package com.livraria.service;

import com.livraria.dto.LivroDTO;
import com.livraria.entity.Autor;
import com.livraria.entity.Categoria;
import com.livraria.entity.Livro;
import com.livraria.exception.LivroNotFoundException;
import com.livraria.repository.AutorRepository;
import com.livraria.repository.CategoriaRepository;
import com.livraria.repository.LivroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<LivroDTO> buscarTodos() {
        return livroRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public LivroDTO buscarPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException("Livro não encontrado com ID: " + id));
        return converterParaDTO(livro);
    }

    @Transactional
    public LivroDTO salvar(LivroDTO livroDTO) {
        if (livroDTO.getId() != null && livroRepository.existsById(livroDTO.getId())) {
            throw new IllegalArgumentException("Livro já existe com ID: " + livroDTO.getId());
        }
        if (livroRepository.existsByIsbn(livroDTO.getIsbn())) {
            throw new IllegalArgumentException("Já existe um livro com ISBN: " + livroDTO.getIsbn());
        }

        Livro livro = new Livro();
        BeanUtils.copyProperties(livroDTO, livro, "autor", "categoria");

        Autor autor = autorRepository.findById(livroDTO.getAutorId())
            .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado com ID: " + livroDTO.getAutorId()));

        Categoria categoria = categoriaRepository.findById(livroDTO.getCategoriaId())
            .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada com ID: " + livroDTO.getCategoriaId()));

        livro.setAutor(autor);
        livro.setCategoria(categoria);

        livro = livroRepository.save(livro);
        return converterParaDTO(livro);
    }

    @Transactional
    public LivroDTO atualizar(Long id, LivroDTO livroDTO) {
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException("Livro não encontrado com ID: " + id));

        if (!livroExistente.getIsbn().equals(livroDTO.getIsbn()) &&
            livroRepository.existsByIsbn(livroDTO.getIsbn())) {
            throw new IllegalArgumentException("Já existe um livro com ISBN: " + livroDTO.getIsbn());
        }

        Autor autor = autorRepository.findById(livroDTO.getAutorId())
            .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado com ID: " + livroDTO.getAutorId()));

        Categoria categoria = categoriaRepository.findById(livroDTO.getCategoriaId())
            .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada com ID: " + livroDTO.getCategoriaId()));

        BeanUtils.copyProperties(livroDTO, livroExistente, "id", "autor", "categoria");
        livroExistente.setAutor(autor);
        livroExistente.setCategoria(categoria);

        livroExistente = livroRepository.save(livroExistente);
        return converterParaDTO(livroExistente);
    }

    @Transactional
    public void deletar(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new LivroNotFoundException("Livro não encontrado com ID: " + id);
        }
        livroRepository.deleteById(id);
    }

    private LivroDTO converterParaDTO(Livro livro) {
        LivroDTO dto = new LivroDTO();
        BeanUtils.copyProperties(livro, dto);
        dto.setAutorId(livro.getAutor().getId());
        dto.setCategoriaId(livro.getCategoria().getId());
        return dto;
    }
}

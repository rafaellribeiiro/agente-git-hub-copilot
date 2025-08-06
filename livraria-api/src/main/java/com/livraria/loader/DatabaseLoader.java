package com.livraria.loader;

import com.livraria.entity.Autor;
import com.livraria.entity.Categoria;
import com.livraria.entity.Livro;
import com.livraria.repository.AutorRepository;
import com.livraria.repository.CategoriaRepository;
import com.livraria.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) {
        // Criar autores
        Autor machadoDeAssis = new Autor();
        machadoDeAssis.setNome("Machado de Assis");
        machadoDeAssis.setNacionalidade("Brasileira");

        Autor aluisioAzevedo = new Autor();
        aluisioAzevedo.setNome("Aluísio Azevedo");
        aluisioAzevedo.setNacionalidade("Brasileira");

        autorRepository.saveAll(Arrays.asList(machadoDeAssis, aluisioAzevedo));

        // Criar categorias
        Categoria literatura = new Categoria();
        literatura.setNome("Literatura Brasileira");

        Categoria romance = new Categoria();
        romance.setNome("Romance");

        categoriaRepository.saveAll(Arrays.asList(literatura, romance));

        // Criar livros
        Livro livro1 = new Livro();
        livro1.setTitulo("Dom Casmurro");
        livro1.setIsbn("9788535914660");
        livro1.setPreco(new BigDecimal("29.90"));
        livro1.setQuantidade(10);
        livro1.setAutor(machadoDeAssis);
        livro1.setCategoria(literatura);

        Livro livro2 = new Livro();
        livro2.setTitulo("O Cortiço");
        livro2.setIsbn("9788535913588");
        livro2.setPreco(new BigDecimal("24.90"));
        livro2.setQuantidade(8);
        livro2.setAutor(aluisioAzevedo);
        livro2.setCategoria(romance);

        Livro livro3 = new Livro();
        livro3.setTitulo("Memórias Póstumas de Brás Cubas");
        livro3.setIsbn("9788535912524");
        livro3.setPreco(new BigDecimal("27.90"));
        livro3.setQuantidade(15);
        livro3.setAutor(machadoDeAssis);
        livro3.setCategoria(literatura);

        livroRepository.saveAll(Arrays.asList(livro1, livro2, livro3));
    }
}

package com.algaworks.algafood.api.controller;


import com.algaworks.algafood.api.model.input.FotoProdutoInput;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {


    @PutMapping
    public void atualizarFoto(@PathVariable Long restauranteId
                            , @PathVariable Long produtoId, FotoProdutoInput fotoProdutoInout
                            ){

        var nomeArquivo = UUID.randomUUID().toString() + "_"  + fotoProdutoInout.getArquivo();

        var arquivoFoto = Path.of("/Users/itamarrocha/Desktop/Catalogo", nomeArquivo);

        System.out.println("Descricao do produto" + fotoProdutoInout.getDescricao());
        System.out.println("arquivo foto " + arquivoFoto);
        System.out.println("arquivo " + fotoProdutoInout.getArquivo().getContentType());

        try{
            fotoProdutoInout.getArquivo().transferTo(arquivoFoto);
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }

}

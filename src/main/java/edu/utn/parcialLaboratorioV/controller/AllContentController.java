package edu.utn.parcialLaboratorioV.controller;

import edu.utn.parcialLaboratorioV.model.AllContent;
import edu.utn.parcialLaboratorioV.service.AllContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/allContent")
public class AllContentController {
    @Autowired
    AllContentService allContentService;

    @GetMapping("")
    public ResponseEntity<AllContent> getAll(){
        AllContent allContent = new AllContent();
        allContent.setComentarios(allContentService.getComentarios().join());
        allContent.setPublicaciones(allContentService.getPublicaciones().join());
        allContent.setUsuarios(allContentService.getUsuarios().join());
        return ResponseEntity.ok().body(allContent);
    }
}

package com.coderhouse.proyectofinal.controller;

import com.coderhouse.proyectofinal.dto.ResponseDto;
import com.coderhouse.proyectofinal.entity.Empresa;
import com.coderhouse.proyectofinal.service.EmpresaService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/{id}")
    public Empresa getEmpresaById(@PathVariable Long id) {
        return empresaService.getEmpresaById(id);
    }


    @PostMapping
    public Empresa saveEmpresa(@Valid @RequestBody Empresa empresa) {
        return empresaService.saveEmpresa(empresa);
    }

    @PutMapping("/{id}")
    public Empresa updateEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        return empresaService.updateEmpresa(id, empresa);
    }

    @DeleteMapping("/{id}")
    public ResponseDto deleteEmpresa(@PathVariable Long id) {
        return empresaService.deleteEmpresaById(id);
    }


}

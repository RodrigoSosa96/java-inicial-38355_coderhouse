package com.coderhouse.proyectofinal.service;

import com.coderhouse.proyectofinal.dto.ResponseDto;
import com.coderhouse.proyectofinal.entity.Empresa;

public interface EmpresaService {
    Empresa getEmpresaById(Long id);
    Empresa saveEmpresa(Empresa empresa);
    Empresa updateEmpresa(Long Id, Empresa empresa);
    ResponseDto deleteEmpresaById(Long id);

}

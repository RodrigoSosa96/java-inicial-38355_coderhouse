package com.coderhouse.proyectofinal.service;

import com.coderhouse.proyectofinal.dto.ResponseDto;
import com.coderhouse.proyectofinal.entity.Empresa;
import com.coderhouse.proyectofinal.exception.DbException;
import com.coderhouse.proyectofinal.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServiceImpl implements EmpresaService {
    private final EmpresaRepository empresaRepository;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa getEmpresaById(Long id) {
        return empresaRepository.findById(id).orElseThrow(
                () -> new DbException("Empresa no encontrada")
        );
    }

    public Empresa saveEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public Empresa updateEmpresa(Long id, Empresa empresa) {
        Empresa empresaActual = getEmpresaById(id);
        if(empresa.getRubro() != null) {
            empresaActual.setRubro(empresa.getRubro());
        }
        if(empresa.getNombre() != null) {
            empresaActual.setNombre(empresa.getNombre());
        }
        return empresaRepository.save(empresaActual);
    }

    public ResponseDto deleteEmpresaById(Long id) {
        empresaRepository.deleteById(id);
        ResponseDto response =  new ResponseDto();
        response.setMessage("Empresa eliminada");
        response.putFieldErrors("id", id.toString());
        return response;
    }


}

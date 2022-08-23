package com.coderhouse.proyectofinal.service;

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
        empresaActual.setNombre(empresa.getNombre());
        empresaActual.setRubro(empresa.getRubro());
        return empresaRepository.save(empresaActual);
    }

    public String deleteEmpresaById(Long id) {
        empresaRepository.deleteById(id);
        return "Empresa eliminada";
    }


}

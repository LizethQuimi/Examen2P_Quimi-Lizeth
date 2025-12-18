package com.banquito.sucursal.controller;

import com.banquito.sucursal.dto.*;
import com.banquito.sucursal.mapper.BranchMapper;
import com.banquito.sucursal.model.Branch;
import com.banquito.sucursal.model.BranchHoliday;
import com.banquito.sucursal.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {

    private static final Logger log = LoggerFactory.getLogger(BranchController.class);
    private final BranchService service;

    public BranchController(BranchService service) {
        this.service = service;
    }

    @Operation(summary = "1) Obtener listado de todas las sucursales")
    @GetMapping
    public List<BranchResponseDto> listAll() {
        try {
            log.info("EP1 listAll");
            return service.listAll().stream().map(BranchMapper::toDto).toList();
        } catch (Exception e) {
            log.error("EP1 error", e);
            throw e;
        }
    }

    @Operation(summary = "2) Crear una sucursal")
    @PostMapping
    public BranchResponseDto create(@Valid @RequestBody BranchCreateRequestDto req) {
        try {
            log.info("EP2 create id={}", req.getId());
            Branch b = service.create(req);
            return BranchMapper.toDto(b);
        } catch (Exception e) {
            log.error("EP2 error", e);
            throw e;
        }
    }

    @Operation(summary = "3) Obtener sucursal por su ID")
    @GetMapping("/{id}")
    public BranchResponseDto getById(@PathVariable String id) {
        try {
            log.info("EP3 getById id={}", id);
            return BranchMapper.toDto(service.getById(id));
        } catch (Exception e) {
            log.error("EP3 error", e);
            throw e;
        }
    }

    @Operation(summary = "4) Modificar sucursal")
    @PutMapping("/{id}/phone")
    public BranchResponseDto updatePhone(@PathVariable String id, @Valid @RequestBody BranchUpdatePhoneRequestDto req) {
        try {
            log.info("EP4 updatePhone id={}", id);
            return BranchMapper.toDto(service.updatePhone(id, req));
        } catch (Exception e) {
            log.error("EP4 error", e);
            throw e;
        }
    }

    @Operation(summary = "5) Crear feriados para una sucursal")
    @PostMapping("/{id}/holidays")
    public BranchResponseDto addHoliday(@PathVariable String id, @Valid @RequestBody HolidayCreateRequestDto req) {
        try {
            log.info("EP5 addHoliday id={} date={}", id, req.getDate());
            return BranchMapper.toDto(service.addHoliday(id, req));
        } catch (Exception e) {
            log.error("EP5 error", e);
            throw e;
        }
    }

    @Operation(summary = "6) Eliminar feriados de una sucursal (por fecha)")
    @DeleteMapping("/{id}/holidays")
    public void deleteHoliday(@PathVariable String id, @RequestParam LocalDate date) {
        try {
            log.info("EP6 deleteHoliday id={} date={}", id, date);
            service.deleteHoliday(id, date);
        } catch (Exception e) {
            log.error("EP6 error", e);
            throw e;
        }
    }

    @Operation(summary = "7) Obtener todos los feriados de una sucursal")
    @GetMapping("/{id}/holidays")
    public List<BranchHoliday> listHolidays(@PathVariable String id) {
        try {
            log.info("EP7 listHolidays id={}", id);
            return service.listHolidays(id);
        } catch (Exception e) {
            log.error("EP7 error", e);
            throw e;
        }
    }

    @Operation(summary = "8) Verificar si una fecha es feriado en una sucursal")
    @GetMapping("/{id}/holidays/check")
    public boolean isHoliday(@PathVariable String id, @RequestParam LocalDate date) {
        try {
            log.info("EP8 isHoliday id={} date={}", id, date);
            return service.isHoliday(id, date);
        } catch (Exception e) {
            log.error("EP8 error", e);
            throw e;
        }
    }
}

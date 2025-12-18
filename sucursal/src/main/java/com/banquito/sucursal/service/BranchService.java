package com.banquito.sucursal.service;

import com.banquito.sucursal.dto.*;
import com.banquito.sucursal.exception.BadRequestException;
import com.banquito.sucursal.exception.NotFoundException;
import com.banquito.sucursal.model.*;
import com.banquito.sucursal.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class BranchService {

    private final BranchRepository repo;

    public BranchService(BranchRepository repo) {
        this.repo = repo;
    }

    // 1
    public List<Branch> listAll() {
        return repo.findAll();
    }

    // 2 (sin feriados)
    public Branch create(BranchCreateRequestDto req) {
        if (repo.existsById(req.getId())) {
            throw new BadRequestException("Ya existe sucursal con id=" + req.getId());
        }

        String now = OffsetDateTime.now().toString();

        Branch b = new Branch();
        b.setId(req.getId());
        b.setEmailAddress(req.getEmailAddress());
        b.setName(req.getName());
        b.setPhoneNumber(req.getPhoneNumber());
        b.setState(BranchState.ACTIVE);
        b.setCreationDate(now);
        b.setLastModifiedDate(now);
        b.getBranchHolidays().clear();

        return repo.save(b);
    }

    // 3
    public Branch getById(String id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Sucursal no encontrada: " + id));
    }

    // 4 (solo phone + update lastModifiedDate)
    public Branch updatePhone(String id, BranchUpdatePhoneRequestDto req) {
        Branch b = getById(id);
        b.setPhoneNumber(req.getPhoneNumber());
        b.setLastModifiedDate(OffsetDateTime.now().toString());
        return repo.save(b);
    }

    // 5
    public Branch addHoliday(String id, HolidayCreateRequestDto req) {
        Branch b = getById(id);

        boolean exists = b.getBranchHolidays().stream()
                .anyMatch(h -> h.getDate().equals(req.getDate()));
        if (exists) throw new BadRequestException("Ya existe feriado para " + req.getDate());

        BranchHoliday h = new BranchHoliday();
        h.setDate(req.getDate());
        h.setName(req.getName());

        b.getBranchHolidays().add(h);
        b.setLastModifiedDate(OffsetDateTime.now().toString());
        return repo.save(b);
    }

    // 6 (eliminar por fecha)
    public void deleteHoliday(String id, LocalDate date) {
        Branch b = getById(id);

        boolean removed = b.getBranchHolidays().removeIf(h -> h.getDate().equals(date));
        if (!removed) throw new NotFoundException("No existe feriado para la fecha " + date);

        b.setLastModifiedDate(OffsetDateTime.now().toString());
        repo.save(b);
    }

    // 7
    public List<BranchHoliday> listHolidays(String id) {
        return getById(id).getBranchHolidays();
    }

    // 8
    public boolean isHoliday(String id, LocalDate date) {
        Branch b = getById(id);
        return b.getBranchHolidays().stream().anyMatch(h -> h.getDate().equals(date));
    }
}

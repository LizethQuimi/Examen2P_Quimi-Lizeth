package com.banquito.sucursal.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class BranchHoliday {

    @Field("date")
    private LocalDate date;

    @Field("name")
    private String name;

    public BranchHoliday() {
    }

    public BranchHoliday(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BranchHoliday)) return false;
        BranchHoliday other = (BranchHoliday) o;
        return Objects.equals(this.date, other.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.date);
    }

    @Override
    public String toString() {
        return "BranchHoliday{" +
                "date=" + date +
                ", name='" + name + '\'' +
                '}';
    }
}

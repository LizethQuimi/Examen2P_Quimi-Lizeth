package com.banquito.sucursal.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "branch")
@Getter
@Setter
public class Branch {

    @Id
    private String id;

    @Field("emailAddress")
    private String emailAddress;

    @Field("name")
    private String name;

    @Field("phoneNumber")
    private String phoneNumber;

    @Field("state")
    private BranchState state;

    @Field("creationDate")
    private String creationDate;

    @Field("lastModifiedDate")
    private String lastModifiedDate;

    @Field("branchHolidays")
    private List<BranchHoliday> branchHolidays = new ArrayList<>();

    public Branch() {
    }

    public Branch(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Branch)) return false;
        Branch other = (Branch) o;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id='" + id + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", state=" + state +
                ", creationDate='" + creationDate + '\'' +
                ", lastModifiedDate='" + lastModifiedDate + '\'' +
                ", branchHolidays=" + branchHolidays +
                '}';
    }
}

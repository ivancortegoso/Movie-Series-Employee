package com.example.demo.domain.model;

import java.util.Objects;

public abstract class EntityParent {
    public abstract Long getId();
    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass().equals(this.getClass()) && Objects.equals((this.getClass().cast(obj)).getId(), this.getId())) {
            return true;
        }

        return false;
    }

}

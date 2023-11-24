package com.example.packagert.column;

import com.example.packagert.feature.FeatureEntity;

public class ColumnParameter {

    private Boolean cascadeDeletion;
    private FeatureEntity linkedEntity;
    private Boolean nullable;
    private Integer sizeMax;
    private Integer sizeMin;


    public Boolean getCascadeDeletion() {
        return cascadeDeletion;
    }

    public void setCascadeDeletion(Boolean cascadeDeletion) {
        this.cascadeDeletion = cascadeDeletion;
    }

    public String getCascadeDeletionMariadb() {
        if(cascadeDeletion)
            return " ON DELETE CASCADE";
        return "";
    }

    public FeatureEntity getLinkedEntity() {
        return linkedEntity;
    }

    public void setLinkedEntity(FeatureEntity linkedEntity) {
        this.linkedEntity = linkedEntity;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public Integer getSizeMax() {
        return sizeMax;
    }

    public void setSizeMax(Integer sizeMax) {
        this.sizeMax = sizeMax;
    }

    public Integer getSizeMin() {
        return sizeMin;
    }

    public void setSizeMin(Integer sizeMin) {
        this.sizeMin = sizeMin;
    }
}

package com.example.packagert.generator.typegenerator;

public enum TypeGeneratorEnum {

    ID(new TgId()), MANY_TO_MANY(new TgManyToMany()), MANY_TO_ONE(new TgManyToOne()), STRING(new TgString());

    private TypeGenerator type;

    TypeGeneratorEnum(TypeGenerator type) {
        this.type = type;
    }

    public TypeGenerator getType(){
        return this.type;
    }
}

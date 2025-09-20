package br.com.cactusdigital.cedrus.infrastructure.handler.util;

import lombok.Getter;

@Getter
public enum ConstraintEnum {

    UNDEFINED("UNDEFINED");
    private final String name;

    private ConstraintEnum(String name ) {
        this.name = name;
    }

    public static ConstraintEnum getConstraint( String messageError ) {
        for ( ConstraintEnum v : ConstraintEnum.values() ) {
            if( messageError.contains( v.getName() )) {
                return v;
            }
        }
        return UNDEFINED;
    }
}

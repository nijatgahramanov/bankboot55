package az.orient.bankboot55.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EnumAvailableStatus {

    ACTIVE(1),DEACTIVE(0);

    private int value;

    public int getValue(){
        return value;
    }

}

package socket_installer.SI_parts.data_carriers.carrier_enums;


public enum ResponseCarrierEnum {

    DEFINED_RESPONSE("DEFINED"),
    UNDEFINED_RESPONSE("UNDEFINED")
    ;

    private String response;

    ResponseCarrierEnum(String response){
        this.response = response;
    }

    public String getResponseCarrier(){
        return response;
    }

}

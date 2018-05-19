package socket_installer.SI_parts.actionHolder.actions.string_buffer.string_parser.enum_parser;

public enum RegexParser {

    END_PROTOCOL("[<,/]*END\\s(DEFINED|UNDEFINED)[(]PROTOCOL[_]VERSION[_]'\\d+'[),>]*"),
    ;
    private String protocol;

    RegexParser(String protocol){
        this.protocol = protocol;
    }

    public String getRegex() {
        return protocol;
    }
}

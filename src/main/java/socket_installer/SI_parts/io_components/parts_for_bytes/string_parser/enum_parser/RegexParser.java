package socket_installer.SI_parts.io_components.parts_for_bytes.string_parser.enum_parser;

public enum RegexParser {

    END_PROTOCOL("[<,/]*END[(]PROTOCOL[_]VERSION[_]'\\d+'[),>]*"),
    ;

    private String protocol;

    RegexParser(String protocol){
        this.protocol = protocol;
    }

    public String getRegex() {
        return protocol;
    }
}

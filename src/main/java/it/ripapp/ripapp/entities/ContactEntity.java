package it.ripapp.ripapp.entities;

public class ContactEntity {

    private String num;
    private String name;
    private String phonehash;
    private String prefix;

    public String getNum() {
        return num;
    }

    public ContactEntity setNum(String num) {
        this.num = num;
        return this;
    }

    public String getName() {
        return name;
    }

    public ContactEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhonehash() {
        return phonehash;
    }

    public ContactEntity setPhonehash(String phonehash) {
        this.phonehash = phonehash;
        return this;
    }

    public String getPrefix() {
        return prefix;
    }

    public ContactEntity setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }
}

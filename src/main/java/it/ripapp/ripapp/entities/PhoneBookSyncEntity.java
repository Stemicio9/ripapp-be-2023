package it.ripapp.ripapp.entities;

import java.util.List;

public class PhoneBookSyncEntity {

    private List<ContactEntity> contacts;
    private Integer offset;
    private Integer total;
    private Boolean hasnextchunk;

    public List<ContactEntity> getContacts() {
        return contacts;
    }

    public PhoneBookSyncEntity setContacts(List<ContactEntity> contacts) {
        this.contacts = contacts;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public PhoneBookSyncEntity setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public PhoneBookSyncEntity setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Boolean getHasnextchunk() {
        return hasnextchunk;
    }

    public PhoneBookSyncEntity setHasnextchunk(Boolean hasnextchunk) {
        this.hasnextchunk = hasnextchunk;
        return this;
    }
}

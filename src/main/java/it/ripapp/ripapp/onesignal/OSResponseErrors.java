package it.ripapp.ripapp.onesignal;
import java.util.List;
import java.util.UUID;

public class OSResponseErrors {
    private List<UUID> invalid_player_ids;

    public List<UUID> getInvalid_player_ids() {
        return invalid_player_ids;
    }

    public OSResponseErrors setInvalid_player_ids(List<UUID> invalid_player_ids) {
        this.invalid_player_ids = invalid_player_ids;
        return this;
    }
}

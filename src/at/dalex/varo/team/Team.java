package at.dalex.varo.team;

import java.util.ArrayList;
import java.util.UUID;

public class Team {

    private String contraction;
    private ArrayList<UUID> members;
    private ArrayList<UUID> deadMembers;

    public Team(String contraction) {
        this.contraction = contraction;
        this.members = new ArrayList<>();
        this.deadMembers = new ArrayList<>();
    }

    public String getContraction() {
        return this.contraction;
    }

    public ArrayList<UUID> getMembers() {
        return this.members;
    }

    public void addMember(UUID player) {
        if (!members.contains(player)) {
            this.members.add(player);
        }
    }

    public void removeMember(UUID player) {
        if (members.contains(player)) {
            this.members.remove(player);
        }
    }

    public ArrayList<UUID> getDeadMembers() {
        return this.deadMembers;
    }
}

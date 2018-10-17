package at.dalex.varo.team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TeamManager {

    private ArrayList<Team> teams = new ArrayList<>();
    private HashMap<UUID, ArrayList<String>> teamInvitations = new HashMap<>();

    public void invitePlayerToTeam(UUID player, String teamContraction) {
        ArrayList<String> teamContractions = teamInvitations.get(player);
        if (teamContractions == null) teamContractions = new ArrayList<>();

        teamContractions.add(teamContraction);
        teamInvitations.put(player, teamContractions);
    }

    public void removeInvitation(UUID player, String teamContraction) {
        ArrayList<String> invitations = getTeamInvitations(player);
        invitations.remove(teamContraction);
        teamInvitations.put(player, invitations);
    }

    public ArrayList<String> getTeamInvitations(UUID player) {
        return teamInvitations.get(player);
    }

    public Team getTeam(UUID playerId) {
        for (Team team : teams) {
            for (UUID player : team.getMembers()) {
                if (player.equals(playerId)) {
                    return team;
                }
            }
        }
        return null;
    }

    public Team getTeam(String contraction) {
        for (Team team : teams) {
            if (team.getContraction().equals(contraction)) {
                return team;
            }
        }
        return null;
    }

    public void addTeam(Team team) {
        if (!teams.contains(team)) {
            teams.add(team);
        }
    }

    public void removeTeam(Team team) {
        teams.remove(team);
    }
}

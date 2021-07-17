package Main.TeamController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Main.models.Team;

@Service
public class TeamService {
	
	@Autowired
	private TeamRepository teamRepository;
	
	
    public Team getTeamName(String teamName) {
		return teamRepository.findByTeamName(teamName);
//      
    }
	}

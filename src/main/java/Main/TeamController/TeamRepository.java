package Main.TeamController;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Main.models.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

	
	public Team findByTeamName(String teamName);
	
	
	
	
	
}

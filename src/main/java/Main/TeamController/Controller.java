package Main.TeamController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Main.MatchController.MatchService;
import Main.models.Match;
import Main.models.Team;

@RestController
@RequestMapping("/teamInfo")
public class Controller {

	@Autowired
	private TeamService teamService;
	
	@Autowired
	private MatchService matchService;
	@GetMapping("/{teamName}")		
		public Team getTeamdata(@PathVariable String teamName) {
		
			Team team = teamService.getTeamName(teamName);
			Pageable p = PageRequest.of(0,5);
			team.setMatch(matchService.getLatestMatch(teamName, teamName, p));
		
			return team;
			
		
	}
	
	@GetMapping("/iplSeason/{teamName}")		
	public List<Match> getIplyear(@PathVariable String teamName,@RequestParam int year) {
	
	
		return matchService.getIplSeasonMatch(year,teamName);
		
	
}
	
	
}

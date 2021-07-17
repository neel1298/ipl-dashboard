package Main.MatchController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Main.models.Match;

@Service
public class MatchService {
	
	@Autowired
	private MatchRepository matchRepo;
	
	public List<Match> getLatestMatch(String team1,String team2,Pageable pg){
		
		
		return matchRepo.findByTeam1OrTeam2OrderByDateDesc(team1, team2, pg);
		
		
	}
	
	
public List<Match> getIplSeasonMatch(int year,String teamName){
		
		
		return matchRepo.getAllMatchesInyear(year,teamName);
		
		
	}
	
}

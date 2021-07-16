package Main.util;

import java.time.LocalDate;

import org.springframework.batch.item.ItemProcessor;

import Main.models.*;


public class MatchDataProcessor implements ItemProcessor<RawMatchData, Match> {

	 // private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

	  @Override
	  public Match process(final RawMatchData data) throws Exception {
		Match match = new Match();
		match.setId(Long.parseLong(data.getId()));
		
		match.setCity(data.getCity());
		match.setDate(LocalDate.parse(data.getDate()));
		match.setPlayerOfMatch(data.getPlayer_of_match());
		match.setVenue(data.getVenue());
		
		String firstInningTeam, secondInningTeam;
		
		if("bat".equals(data.getToss_decision())) {
			firstInningTeam = data.getToss_winner();
			secondInningTeam = data.getToss_winner()
							   .equals(data.getTeam1()) ? data.getTeam2() : data.getTeam1();
		}else {
			secondInningTeam = data.getToss_winner();
			firstInningTeam = data.getToss_winner()
					   			.equals(data.getTeam1()) ? data.getTeam2() : data.getTeam1();	
		}
		
		match.setTeam1(firstInningTeam);
		match.setTeam2(secondInningTeam);
		
		match.setTossWinner(data.getToss_winner());
		match.setTossDecision(data.getToss_decision());
		
		match.setResult(data.getResult());
		match.setResultMargin(data.getResult_margin());
		match.setUmpire1(data.getUmpire1());
		match.setUmpire2(data.getUmpire2());
	

	   return match;
	  }

	


	}
	
	


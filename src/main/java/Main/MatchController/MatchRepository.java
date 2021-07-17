package Main.MatchController;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Main.models.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

	List<Match> findByTeam1OrTeam2OrderByDateDesc(String team1,String team2,Pageable page);

	
	
	@Query("select m from Match m where EXTRACT(YEAR FROM m.date) =:year AND (m.team1 =:teamName OR m.team2=:teamName)")
	List<Match> getAllMatchesInyear(int year,String teamName);
	
	
}

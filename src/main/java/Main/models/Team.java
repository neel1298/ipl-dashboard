package Main.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Team {
		
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String teamName;
	private long totalMatch;
	private long totalWins;
	
	@Transient
	private List<Match> match;
	
	public Team(String teamName, Long totalMatch) {
		super();
		this.teamName = teamName;
		this.totalMatch = totalMatch;
	}
	
	
	
}

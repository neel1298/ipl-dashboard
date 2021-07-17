package Main.util;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Main.models.Team;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
  
  private final EntityManager em;

  @Autowired
  public JobCompletionNotificationListener(EntityManager em) {
    this.em = em;
  }

  @Override
  @Transactional
  public void afterJob(JobExecution jobExecution) {
    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");
      HashMap<String, Team> teamData = new HashMap<>();
      
      
      em.createQuery("select m.team1 , count(m) from Match m group by m.team1",Object[].class)
      .getResultStream()
      .map(res-> new Team((String)res[0], (long) res[1]))
      .forEach(team-> teamData.put(team.getTeamName(), team));
      
      
      em.createQuery("select m.team2, count(m) from Match m group by m.team2",Object[].class)
      .getResultStream()
      .forEach(res->{
    	  
    	  Team team = teamData.get((String)res[0]);
    	  
    	  team.setTotalMatch(team.getTotalMatch() + (long)res[1]);
    	  
    	  
      });
      
      em.createQuery("select m.matchWinner, count(m) from Match m group by m.matchWinner",Object[].class)
      .getResultList()
      .stream()
      .forEach(entry->{
    	  Team team = teamData.get((String)entry[0]);
    	  if(team!=null)  team.setTotalWins((long)entry[1]);
    	  
      });
      
      teamData.values()
      .forEach(team->{
    	  em.persist(team);
    	 
    	  });
     teamData.values().forEach(team->System.out.println(team));
      
      
    }
  }
}
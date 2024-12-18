package Employee_wellfare.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Employee_wellfare.entity.Challenge;
import Employee_wellfare.entity.ChallengeParticipation;
import Employee_wellfare.entity.Employee;
import Employee_wellfare.entity.Progress;
import Employee_wellfare.repository.ChallengeParticipationRepository;
import Employee_wellfare.repository.ChallengeRepository;
import Employee_wellfare.repository.Employeerepository;

@Service
public class ChallengeParticipationService {

	@Autowired
	private final Employeerepository emprepo;
	private final ChallengeParticipationRepository challengeparticipationrepo;
	private final ChallengeRepository challengerepo;

	public ChallengeParticipationService(Employeerepository emprepo,
			ChallengeParticipationRepository challengeparticipationrepo, ChallengeRepository challengerepo) {

		this.emprepo = emprepo;
		this.challengeparticipationrepo = challengeparticipationrepo;
		this.challengerepo = challengerepo;
	}

	public ChallengeParticipation participationEmployeeInProgram(Long employeeId, Long challengeId) {

		Employee employee = emprepo.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee Not Found"));

		Challenge challenge = challengerepo.findById(challengeId)
				.orElseThrow(() -> new RuntimeException("This Challenge Not Found"));

		ChallengeParticipation chall = new ChallengeParticipation();
		chall.setEmployee(employee);
		chall.setChallenge(challenge);
		chall.setParticipationDate(LocalDateTime.now());
		chall.setProgress(Progress.ONGOING);

		return challengeparticipationrepo.save(chall);
	}

	public List<ChallengeParticipation> getParticipationEmployeeId(Long employeeId) {
		
		return challengeparticipationrepo.findByEmployeeEmployeeId(employeeId);
	}

}

package Employee_wellfare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Employee_wellfare.dto.ChallengeDto;
import Employee_wellfare.dto.EmployeeDTO;

import Employee_wellfare.entity.Challenge;
import Employee_wellfare.entity.Employee;
import Employee_wellfare.repository.ChallengeRepository;

@Service
public class ChallengeService {
	
	@Autowired
	private ChallengeRepository challengerepo;

	public List<ChallengeDto> getAllChallenge() {
		
		List<Challenge> chall= challengerepo.findAll();
		return chall.stream()
                .map(program -> {
                    ChallengeDto dto = new ChallengeDto();
                    dto.setChallengeId(program.getChallengeId());
                    dto.setDescription(program.getDescription());
                    dto.setChallengeName(program.getChallengeName());
                    dto.setChallengeType(program.getChallengeType());
                    dto.setChallengeStartDate(program.getChallengeStartDate());
                    dto.setChallengeEndDate(program.getChallengeEndDate());
                    dto.setDescription(program.getDescription());
                   
                    return dto;
                })
                .collect(Collectors.toList());
}
	

	public Challenge create(Challenge challenge) {
		
		return challengerepo.save(challenge);
	}

	public ChallengeDto getChallengeEmployeedetails(Long challengeId) {
		Challenge challenge=challengerepo.findById(challengeId).orElse(null);
		
		ChallengeDto chall=new ChallengeDto();
		chall.setChallengeId(challenge.getChallengeId());
		chall.setChallengeName(challenge.getChallengeName());
		chall.setChallengeType(challenge.getChallengeType());
		chall.setDescription(challenge.getDescription());
		chall.setChallengeStartDate(chall.getChallengeStartDate());
		chall.setChallengeEndDate(challenge.getChallengeEndDate());
		chall.setScore(challenge.getScore());
		
		List<EmployeeDTO> employeedto=challenge.getChallengeParticipations().stream()
				.map(participation->{
					Employee employee=participation.getEmployee();
					EmployeeDTO emp=new EmployeeDTO();
					emp.setEmployeeId(employee.getEmployeeId());
					emp.setName(employee.getName());
					emp.setPassword(employee.getPassword());
					emp.setRole(employee.getRole());
					emp.setContactNumber(employee.getContactNumber());
					emp.setEmail(employee.getEmail());
					emp.setAge(employee.getAge());
					emp.setGender(employee.getGender());
					emp.setBloodgroup(employee.getBloodgroup());
					emp.setBmi(employee.getBmi());
					emp.setHobbies(employee.getHobbies());
					emp.setDepartment(employee.getDepartment());
					return emp;
					
				}).collect(Collectors.toList());
				chall.setParticipationemployee(employeedto);
				return chall; 
		
			
		}
	
	public Challenge upadteChallenge(Long challengeId, Challenge challenge) {

		Challenge chall = challengerepo.findById(challengeId).orElse(null);

		chall.setChallengeName(challenge.getChallengeName());
		chall.setDescription(challenge.getDescription());
		chall.setChallengeType(challenge.getChallengeType());
		chall.setChallengeStartDate(challenge.getChallengeStartDate());
		chall.setChallengeEndDate(challenge.getChallengeEndDate());
		chall.setScore(challenge.getScore());

		return challengerepo.save(challenge);
	}

	public void deleteChallenge(Long challengeId) {

		challengerepo.deleteById(challengeId);

	}


}

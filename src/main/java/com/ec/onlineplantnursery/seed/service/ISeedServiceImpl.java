package com.ec.onlineplantnursery.seed.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.exceptions.SeedIdNotFoundException;
import com.ec.onlineplantnursery.order.repository.IOrderRepository;
import com.ec.onlineplantnursery.seed.entity.Seed;
import com.ec.onlineplantnursery.seed.repository.ISeedRepository;

@Service
public class ISeedServiceImpl implements ISeedService{
	
	@Autowired
	ISeedRepository seedRepo;
	

	public ISeedServiceImpl(ISeedRepository seedRepo) {
		super();
		this.seedRepo = seedRepo;
	}




/*Method Name:addSeed
	 *Parameters:Seed
	 *ReturnType:Seed
	 *Author Name:Nagolu Tejashwini
	 *Created Date: 21/05/2021 */
	
	@Override
	@Transactional
	public Seed addSeed(Seed seed) {
		// TODO Auto-generated method stub
		seedRepo.save(seed);
		return seed;
	}
	
	/*Method Name:updateSeed
	 *Parameters:Seed
	 *ReturnType:Seed
	 *Author Name:Nagolu Tejashwini
	 *Created Date: 24/05/2021 */

	@Override
	public Seed updateSeed(Seed seed) throws SeedIdNotFoundException {
		int seedId = seed.getSeedId();
		Optional<Seed> s = seedRepo.findById(seedId);
		Seed s1 = null;
		if(s.isEmpty()) {
			throw new SeedIdNotFoundException(seedId);
		}
		else {
			s1 = s.get();
			s1.setCommonName(seed.getCommonName());
			s1.setBloomTime(seed.getBloomTime());
			s1.setWatering(seed.getWatering());
			s1.setDifficultyLevel(seed.getDifficultyLevel());
			s1.setTemparature(seed.getTemparature());
			s1.setTypeOfSeeds(seed.getTypeOfSeeds());
			s1.setSeedsDescription(seed.getSeedsDescription());
			s1.setSeedsStock(seed.getSeedsStock());
			s1.setSeedsCost(seed.getSeedsCost());
			s1.setSeedsPerPacket(seed.getSeedsPerPacket());
			seedRepo.save(s1);
		}
		
		return s1;
	}

	/*Method Name:deleteSeed
	 *Parameters:Seed
	 *ReturnType:Seed
	 *Author Name:Nagolu Tejashwini
	 *Created Date: 24/05/2021 */
	@Override
	@Transactional
	public Seed deleteSeed(Seed seed) throws SeedIdNotFoundException{
		boolean isDeleted = false;
		int seedId = seed.getSeedId();
		Optional<Seed> s = seedRepo.findById(seedId);
		if(s.isEmpty()) {
			throw new SeedIdNotFoundException(seedId);
		}
		else {
			seedRepo.delete(seed);
		}
		return seed;
	}

	/*Method Name:viewSeed
	 *Parameters:seedId
	 *ReturnType:Seed
	 *Author Name:Nagolu Tejashwini
	 *Created Date: 21/05/2021 */

	@Override
	public Seed viewSeed(int seedId) throws SeedIdNotFoundException  {
		Optional<Seed> s = seedRepo.findById(seedId);
		if(s.isEmpty()) {
			throw new SeedIdNotFoundException(seedId);
		}
		return seedRepo.findById(seedId).get();
	}

/*Method Name:viewSeed
	 *Parameters:commonName
	 *Author Name:Nagolu Tejashwini
	 *ReturnType:Optional<Seed>
	 *Created Date: 24/05/2021 */
	
	@Override
	public Optional<Seed> viewSeed(String commonName) throws ResourceNotFoundException {
		Optional<Seed> s1 = seedRepo.getSeedByCommonName(commonName);
		
		if(s1.isEmpty()) throw new ResourceNotFoundException(commonName);

		return s1;
	}

	/*Method Name:viewAllSeeds
	 *Parameters:No Parameters
	 *ReturnType:Optional<List<Seed>>
	 *Author Name:Nagolu Tejashwini
	 *Created Date: 21/05/2021 */
	
	@Override
	public List<Seed> viewAllSeeds() {
		// TODO Auto-generated method stub
		return seedRepo.findAll();
	}


/*Method Name:viewAllSeeds
	 *Parameters:Seed
	 *ReturnType:Seed
	 *Author Name:Nagolu Tejashwini
	 *Created Date: 24/05/2021 */
	@Override
	public Optional<List<Seed>> viewAllSeeds(String typeOfSeed) throws ResourceNotFoundException {
		Optional<List<Seed>> sList = seedRepo.getSeedsByTypeOfSeed(typeOfSeed);
		if(sList==null || sList.isEmpty()) {
			throw new ResourceNotFoundException(typeOfSeed);
		}
		return seedRepo.getSeedsByTypeOfSeed(typeOfSeed);
	}
}

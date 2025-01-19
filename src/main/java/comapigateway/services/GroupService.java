package comapigateway.services;


import java.util.List;

import comapigateway.entities.Group;

public interface GroupService {

	public Group createGroup(Group group);

	public List<Group> getGroupsByUser(Long userId);
	
	public void deleteGroup (Integer id);

}

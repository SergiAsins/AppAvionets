package AppAvionets.java.AppAvionets.roles;

import org.springframework.stereotype.Service;
import javax.management.relation.RoleNotFoundException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role getById(Long id){
        //Manage RoleNotFoundException explicitly
        return repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Role with ID" + id + "not found"));
    }

    public Set<Role> assignDefaultRole(){
        Role defaultRole = this.getById(1L);

        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);

        return roles;
    }
}

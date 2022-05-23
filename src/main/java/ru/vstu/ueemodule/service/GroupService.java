package ru.vstu.ueemodule.service;

import org.springframework.stereotype.Service;
import ru.vstu.ueemodule.domain.Group;
import ru.vstu.ueemodule.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> findAll() {
        Iterable<Group> repositoryAll = groupRepository.findAll();
        List<Group> groupList = new ArrayList<>();
        repositoryAll.forEach(groupList::add);

        return groupList;
    }

    public Long count() {
        return groupRepository.count();
    }
}

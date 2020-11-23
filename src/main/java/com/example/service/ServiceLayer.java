package com.example.service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.viewModels.NotesVm;

@Component
public class ServiceLayer {
	
	private Set<NotesVm> notes = new TreeSet();
	
	public boolean saveNotes(NotesVm vm) {
		if(!this.notes.contains(vm)) {
			this.notes.add(vm);
			return true;
		}  else {
			return false;
		}
	}
	
	public Set<NotesVm> getAllNotes() {
		return this.notes;
	}
	
	public Set<NotesVm> filterByTags(String tags) {
		return this.notes.stream().filter(note -> note.getTags().equals(tags)).collect(Collectors.toSet());
	}
	
	public Set<NotesVm> filterByTitle(String title) {
		return this.notes.stream().filter(note -> note.getTitle().equals(title)).collect(Collectors.toSet());
	}
}
